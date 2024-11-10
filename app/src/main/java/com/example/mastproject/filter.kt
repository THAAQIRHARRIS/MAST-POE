package com.example.mastproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class filter : AppCompatActivity() {

    private val menuItems = mutableListOf<MenuItem>()
    private lateinit var lvMenu: ListView
    private lateinit var spFilter: Spinner
    private lateinit var btnFilter: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)


        lvMenu = findViewById(R.id.lvMenu)
        spFilter = findViewById(R.id.spFilter)
        btnFilter = findViewById(R.id.btnFliter)

        // Sample data (normally, this would come from another activity or data source)
        menuItems.add(MenuItem("Spring Rolls", "Crispy rolls", 5.0, "Starters"))
        menuItems.add(MenuItem("Pasta", "Creamy Alfredo pasta", 12.0, "Mains"))
        menuItems.add(MenuItem("Chocolate Cake", "Rich chocolate cake", 7.0, "Desserts"))
        menuItems.add(MenuItem("Salad", "Fresh garden salad", 4.0, "Starters"))


        lvMenu.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, menuItems)


        val courses = arrayOf("Select Course", "Starters", "Mains", "Desserts")
        spFilter.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, courses)


        btnFilter.setOnClickListener {
            val selectedCourse = spFilter.selectedItem.toString()


            if (selectedCourse != "Select Course") {
                val filteredItems = menuItems.filter { it.course == selectedCourse }
                if (filteredItems.isNotEmpty()) {
                    lvMenu.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, filteredItems)
                } else {
                    Toast.makeText(this, "No items found for $selectedCourse", Toast.LENGTH_SHORT).show()
                }
            } else {

                lvMenu.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, menuItems)
            }
        }
    }

    // Data class to represent menu items
    data class MenuItem(val name: String, val description: String, val price: Double, val course: String) {
        override fun toString(): String {
            return "$name - $course (R$price)"
        }
    }
}

