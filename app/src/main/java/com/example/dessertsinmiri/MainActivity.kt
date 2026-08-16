package com.example.dessertsinmiri

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    // Task 1: Initialize adapter for catalog list
    private lateinit var adapter: CatalogAdapter
    private var showingFavourites = false
    private val allItems = DataProvider.getCatalogItems()

    // Task 4: Show favourite items only
    private fun showFavourites() {
        val favouriteItems = allItems.filter { it.isFavourite }
        adapter.updateData(favouriteItems)
    }

    // Task 4: Show all catalog items
    private fun showAllItems() {
        adapter.updateData(allItems)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Task 1: Set up RecyclerView to display catalog items
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val items = DataProvider.getCatalogItems()
        adapter = CatalogAdapter(items)
        recyclerView.adapter = adapter

        // Task 4: Set up favourites toggle button
        val toggleFavouritesButton: Button = findViewById(R.id.toggleFavouritesButton)
        toggleFavouritesButton.setOnClickListener {
            showingFavourites = !showingFavourites
            if (showingFavourites) {
                toggleFavouritesButton.text = "All Items"
                showFavourites()
            } else {
                toggleFavouritesButton.text = "Favourites"
                showAllItems()
            }
        }

        // Task 3: Set click listener to show item details
        adapter.setOnItemClickListener(object : CatalogAdapter.OnItemClickListener {
            override fun onItemClick(itemId: Int) {
                showDetailFragment(itemId)
            }
        })

        // Task 2: Set up search and filter functionality
        setupSearchView()
        setupCategoryFilters()
    }

    // Task 2: Implement search functionality
    private fun setupSearchView() {
        val searchView: SearchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })
    }

    // Task 2: Set up category filter buttons
    private fun setupCategoryFilters() {
        val categoryContainer: LinearLayout = findViewById(R.id.categoryContainer)

        // Get all unique categories
        val categories = DataProvider.getCatalogItems()
            .flatMap { it.category }
            .distinct()
            .sorted()

        // Create "All" button
        val allButton = Button(this).apply {
            text = "All"
            setOnClickListener { adapter.filterByCategory("All") }
        }
        categoryContainer.addView(allButton)

        // Create buttons for each category
        val categoryButtons = categories.map { category ->
            Button(this).apply {
                text = category
                setOnClickListener { adapter.filterByCategory(category) }
            }
        }

        categoryButtons.forEach { categoryContainer.addView(it) }
    }

    // Task 3: Display detail fragment for selected item
    private fun showDetailFragment(itemId: Int) {
        val detailFragment = DetailFragment.newInstance(itemId)
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_up,
                R.anim.slide_out_down,
                R.anim.slide_in_up,
                R.anim.slide_out_down
            )
            .replace(R.id.fragment_container, detailFragment)
            .addToBackStack(null)
            .commit()
    }

    // Task 4: Notify adapter when data changes
    fun notifyAdapterDataChanged() {
        adapter.notifyDataSetChanged()
    }
}