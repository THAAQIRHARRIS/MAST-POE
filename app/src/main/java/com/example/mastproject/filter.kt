package com.example.mastproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class filter : AppCompatActivity() {

    private val menuItems = mutableListOf<MenuItem>()
    private lateinit var lvMenu: ListView
    private lateinit var spFilter: Spinner
    private lateinit var btnFilter: Button
    private lateinit var txtAverage: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)


        lvMenu = findViewById(R.id.lvMenu)
        spFilter = findViewById(R.id.spFilter)
        btnFilter = findViewById(R.id.btnFliter)
        txtAverage = findViewById(R.id.txtAverage)


        menuItems.add(MenuItem("Pancakes and Sausages", "Crispy rolls", 25.0, "Starters"))
        menuItems.add(MenuItem("Pasta (Chicken)", "Creamy Alfredo pasta", 45.0, "Mains"))
        menuItems.add(MenuItem("Chocolate Cake", "Rich chocolate cake", 37.0, "Desserts"))
        menuItems.add(MenuItem("Green Salad", "Fresh garden salad", 20.0, "Starters"))
        menuItems.add(MenuItem("Waffles (Chocolate)", "Crispy rolls", 25.0, "Starters"))
        menuItems.add(MenuItem("Pasta (veg)", "Creamy Alfredo pasta", 45.0, "Mains"))
        menuItems.add(MenuItem("Cheese Cake (Raspberry)", "Rich chocolate cake", 37.0, "Desserts"))
        menuItems.add(MenuItem("Fruit Salad", "Fresh garden salad", 20.0, "Starters"))
        menuItems.add(MenuItem("Soothe", "Crispy rolls", 25.0, "Starters"))
        menuItems.add(MenuItem("Hamburger and chips", "Creamy Alfredo pasta", 45.0, "Mains"))
        menuItems.add(MenuItem("Ice-cream (choco/peanut)", "Rich chocolate cake", 37.0, "Desserts"))
        menuItems.add(MenuItem("eggs and toast", "Fresh garden salad", 20.0, "Starters"))


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

            if (selectedCourse == "Starters"){
                txtAverage.text = "Average price of the $selectedCourse is R22.5"
            }else{
                if (selectedCourse == "Mains"){
                    txtAverage.text = "Average price of the $selectedCourse is R45"
                }else{
                    txtAverage.text = "Average price of the Desserts is is R37 "
                }
            }
            lvMenu.setOnItemClickListener { _, _, position, _ ->
                val selectedItem = lvMenu.getItemAtPosition(position) as MenuItem
                Toast.makeText(this, "${selectedItem.name} has been added to your cart.", Toast.LENGTH_SHORT).show()
            }
        }

    }


    data class MenuItem(val name: String, val description: String, val price: Double, val course: String) {
        override fun toString(): String {
            return "$name - $course (R$price)"
        }
    }
}

