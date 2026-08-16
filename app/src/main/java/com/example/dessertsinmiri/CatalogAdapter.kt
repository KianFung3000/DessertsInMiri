package com.example.dessertsinmiri

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CatalogAdapter(private var items: List<CatalogItem>) :
    RecyclerView.Adapter<CatalogAdapter.ViewHolder>(), Filterable {

    // Task 2: Data storage for filtering
    private var filteredItems: List<CatalogItem> = items
    private var currentFilter: String = ""
    private var currentCategory: String = "All"

    // Task 1: ViewHolder for list items
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.item_image)
        val titleView: TextView = view.findViewById(R.id.item_title)
        val descriptionView: TextView = view.findViewById(R.id.item_description)
        val categoryView: TextView = view.findViewById(R.id.item_category)
        val favouriteIcon: ImageView = view.findViewById(R.id.favourite_icon)
    }

    // Task 1: Inflating the item layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_catalog, parent, false)
        return ViewHolder(view)
    }

    // Task 3: Interface for item clicks
    interface OnItemClickListener {
        fun onItemClick(itemId: Int)
    }

    // Task 3: Setting click listener
    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    // Task 1: Binding data to views
    // Task 4: Handling favourite functionality
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = filteredItems[position]
        holder.imageView.setImageResource(item.imageResourceId)
        holder.titleView.text = item.title
        holder.descriptionView.text = item.description
        holder.categoryView.text = item.category.joinToString(", ")

        // Task 4: Update favourite icon
        updateFavouriteIcon(holder.favouriteIcon, item.isFavourite)

        // Task 3: Set click listener for item details
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(item.id)
        }

        // Task 4: Long press to toggle favourite
        holder.itemView.setOnLongClickListener {
            item.isFavourite = !item.isFavourite
            updateFavouriteIcon(holder.favouriteIcon, item.isFavourite)
            true
        }

        // Task 4: Click on favourite icon to toggle
        holder.favouriteIcon.setOnClickListener {
            item.isFavourite = !item.isFavourite
            updateFavouriteIcon(holder.favouriteIcon, item.isFavourite)
        }
    }

    // Task 4: Helper method to update favourite icon
    private fun updateFavouriteIcon(imageView: ImageView, isFavourite: Boolean) {
        val drawableId = if (isFavourite) {
            R.drawable.ic_favourite
        } else {
            R.drawable.ic_favourite_border
        }
        imageView.setImageResource(drawableId)
    }

    // Task 1: Get item count
    override fun getItemCount(): Int = filteredItems.size

    // Task 2: Implementing search functionality
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.lowercase() ?: ""
                currentFilter = query

                val results = FilterResults()
                results.values = if (query.isEmpty() && currentCategory == "All") {
                    items
                } else {
                    items.filter { item ->
                        val matchesQuery = query.isEmpty() ||
                                item.title.lowercase().contains(query) ||
                                item.description.lowercase().contains(query)

                        val matchesCategory = currentCategory == "All" ||
                                item.category.any { it.equals(currentCategory, ignoreCase = true) }

                        matchesQuery && matchesCategory
                    }
                }
                return results
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredItems = results?.values as? List<CatalogItem> ?: emptyList()
                notifyDataSetChanged()
            }
        }
    }

    // Task 2: Filter by category
    fun filterByCategory(category: String) {
        currentCategory = category
        filter.filter(currentFilter)
    }

    // Task 2: Update data and apply current filter
    fun updateData(newItems: List<CatalogItem>) {
        items = newItems
        filter.filter(currentFilter)
    }
}