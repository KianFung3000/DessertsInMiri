package com.example.dessertsinmiri

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        // Task 3: Get item ID from arguments
        val itemId = arguments?.getInt(ARG_ITEM_ID, -1) ?: -1

        // Task 3: Retrieve item from DataProvider
        val catalogItem = DataProvider.getItemById(itemId)

        if (catalogItem == null) {
            // If item not found, go back
            requireActivity().supportFragmentManager.popBackStack()
            return view
        }

        // Task 3: Initialize views for detail display
        val viewPager: ViewPager2 = view.findViewById(R.id.imageViewPager)
        val indicatorLayout: LinearLayout = view.findViewById(R.id.indicatorLayout)
        val detailTitle: TextView = view.findViewById(R.id.detail_title)
        val detailDescription: TextView = view.findViewById(R.id.detail_description)
        val detailCategory: TextView = view.findViewById(R.id.detail_category)
        val favouriteButton: ImageButton = view.findViewById(R.id.favourite_button)
        val closeButton: Button = view.findViewById(R.id.close_button)

        // Task 3: Set up image slider with ViewPager2
        val adapter = ImageSliderAdapter(catalogItem.imageResourceIds)
        viewPager.adapter = adapter

        // Task 3: Set up indicators for image slider
        setupIndicators(catalogItem.imageResourceIds.size, indicatorLayout, viewPager)

        // Task 3: Populate text views with item data
        detailTitle.text = catalogItem.title
        detailDescription.text = catalogItem.description
        detailCategory.text = catalogItem.category.joinToString(", ")

        // Task 4: Set up favourite button with current state
        updateFavouriteButton(favouriteButton, catalogItem.isFavourite)
        favouriteButton.setOnClickListener {
            catalogItem.isFavourite = !catalogItem.isFavourite
            updateFavouriteButton(favouriteButton, catalogItem.isFavourite)
            (requireActivity() as MainActivity).notifyAdapterDataChanged()
        }

        // Task 3: Set up close button to return to list
        closeButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        return view
    }

    // Task 3: Helper method to set up image indicators
    private fun setupIndicators(count: Int, indicatorLayout: LinearLayout, viewPager: ViewPager2) {
        val indicators = arrayOfNulls<ImageView>(count)
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)

        for (i in indicators.indices) {
            indicators[i] = ImageView(requireContext())
            indicators[i]?.setImageResource(
                if (i == 0) R.drawable.indicator_active else R.drawable.indicator_inactive
            )
            indicators[i]?.layoutParams = layoutParams
            indicatorLayout.addView(indicators[i])
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                for (i in indicators.indices) {
                    indicators[i]?.setImageResource(
                        if (i == position) R.drawable.indicator_active else R.drawable.indicator_inactive
                    )
                }
            }
        })
    }

    // Task 4: Helper method to update favourite button icon
    private fun updateFavouriteButton(button: ImageButton, isFavourite: Boolean) {
        val drawableId = if (isFavourite) {
            R.drawable.ic_favourite
        } else {
            R.drawable.ic_favourite_border
        }
        button.setImageResource(drawableId)
    }

    companion object {
        private const val ARG_ITEM_ID = "item_id"

        // Task 3: Create new instance of DetailFragment with item ID
        fun newInstance(itemId: Int): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putInt(ARG_ITEM_ID, itemId)
            fragment.arguments = args
            return fragment
        }
    }
}