package com.example.dessertsinmiri

object DataProvider {
    // Task 1: Data storage for catalog items
    private val items = mutableListOf<CatalogItem>()

    init {
        // Task 1: Initialize catalog items with data
        items.addAll(listOf(
            CatalogItem(
                id = 1,
                title = "Dolce Unico",
                description = "A little bit pricy but it's definitely a must-try waffle in town. The waffle is so creamy and crispy!",
                category = listOf("Waffle"),
                imageResourceId = R.drawable.dolce1,
                imageResourceIds = listOf(R.drawable.dolce1, R.drawable.dolce2, R.drawable.dolce3)
            ),
            CatalogItem(
                id = 2,
                title = "Bon Bon Patisserie",
                description = "Craving for croissant or cakes? This place are selling fancy and yummy cakes and buns. You will not regret walking in here.",
                category = listOf("Cake"),
                imageResourceId = R.drawable.bonbon1,
                imageResourceIds = listOf(R.drawable.bonbon1, R.drawable.bonbon2, R.drawable.bonbon3)
            ),
            CatalogItem(
                id = 3,
                title = "Sunshine Ice Cream",
                description = "A corner store located at Senadin. The price here are not that expensive and the fries are saltless but tasty.",
                category = listOf("Ice Cream", "Waffle"),
                imageResourceId = R.drawable.sunshine1,
                imageResourceIds = listOf(R.drawable.sunshine1, R.drawable.sunshine2, R.drawable.sunshine3)
            ),
            CatalogItem(
                id = 4,
                title = "Flora Bakery",
                description = "A bakery famous for its floral-themed cakes. They have many choose of design and type. A place that you can definitely find your dream cake.",
                category = listOf("Cake"),
                imageResourceId = R.drawable.flora1,
                imageResourceIds = listOf(R.drawable.flora1, R.drawable.flora2, R.drawable.flora3)
            ),
            CatalogItem(
                id = 5,
                title = "TAKA Patisserie Miri",
                description = "Offers a wide range of cakes and pastries. The cakes and pastries are beautifully presented.",
                category = listOf("Cake"),
                imageResourceId = R.drawable.taka1,
                imageResourceIds = listOf(R.drawable.taka1, R.drawable.taka2, R.drawable.taka3)
            ),
            CatalogItem(
                id = 6,
                title = "QQ Dessert",
                description = "The dessert here are nice, but you must not miss out their Scallion Oil Noodles!",
                category = listOf("Bingsu"),
                imageResourceId = R.drawable.qq1,
                imageResourceIds = listOf(R.drawable.qq1, R.drawable.qq2, R.drawable.qq3)
            ),
            CatalogItem(
                id = 7,
                title = "Mykori - Miri Sarawak",
                description = "A popular spot for bingsu and Japanese desserts. It's also my favorite dating Spot with calming environment.",
                category = listOf("Bingsu"),
                imageResourceId = R.drawable.mykori1,
                imageResourceIds = listOf(R.drawable.mykori1, R.drawable.mykori2, R.drawable.mykori3)
            ),
            CatalogItem(
                id = 8,
                title = "38°c Magice Snow",
                description = "Known for Taiwan style food. I couldn't even imagine eating bingsu while having crispy and delicious fried food as a side dish too!",
                category = listOf("Bingsu", "Waffle"),
                imageResourceId = R.drawable._81,
                imageResourceIds = listOf(R.drawable._81, R.drawable._82, R.drawable._83)
            ),
            CatalogItem(
                id = 9,
                title = "Harvest Café",
                description = "A cozy café offering waffles and coffee. The environment was peaceful and quiet — perfect for relaxing or catching up with friends.",
                category = listOf("Waffle"),
                imageResourceId = R.drawable.harvest1,
                imageResourceIds = listOf(R.drawable.harvest1, R.drawable.harvest2, R.drawable.harvest3)
            ),
            CatalogItem(
                id = 10,
                title = "Sin Wan Delight & Cold Drink",
                description = "This long established spot is well known for its local desserts and fried food. Price is cheap in town, it is a favourite spot to go to during free time.",
                category = listOf("Ice Cream"),
                imageResourceId = R.drawable.sinwan1,
                imageResourceIds = listOf(R.drawable.sinwan1, R.drawable.sinwan2, R.drawable.sinwan3)
            )
        ))
    }

    // Task 1: Design Content - To get all catalog items
    fun getCatalogItems(): List<CatalogItem> {
        return items
    }

    // Task 3: Item Details - To get specific item by ID
    fun getItemById(itemId: Int): CatalogItem? {
        return items.find { it.id == itemId }
    }
}