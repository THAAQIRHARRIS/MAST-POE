package com.example.mastproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class menu : AppCompatActivity() {

    private lateinit var lstItem: ListView
    private lateinit var txtCount: TextView
    private lateinit var edtName: EditText
    private lateinit var edtDes: EditText
    private lateinit var edtPrice: EditText
    private lateinit var spnCourse: Spinner
    private val menuItems = mutableListOf<MenuItem>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        lstItem = findViewById(R.id.lstItem)
        txtCount = findViewById(R.id.txtCount)
        edtName = findViewById(R.id.txtName)
        edtDes = findViewById(R.id.txtDes)
        edtPrice = findViewById(R.id.edtPrice)
        spnCourse = findViewById(R.id.spnCourse)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnNext = findViewById<Button>(R.id.btnNext)

        val courses = arrayOf("Select Course", "Starters", "Mains", "Desserts")
        spnCourse.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, courses)

        // Add item to the list
        btnAdd.setOnClickListener {
            val dishName = edtName.text.toString()
            val description = edtDes.text.toString()
            val price = edtPrice.text.toString().toDoubleOrNull()
            val course = spnCourse.selectedItem.toString()

            if (dishName.isNotEmpty() && description.isNotEmpty() && price != null && course != "Select Course") {
                menuItems.add(MenuItem(dishName, description, price, course))

                txtCount.text = "Total Menu Items: ${menuItems.size}"
                lstItem.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, menuItems)

                edtName.text.clear()
                edtDes.text.clear()
                edtPrice.text.clear()
                spnCourse.setSelection(0)
            } else {
                Toast.makeText(this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
            }
        }


        lstItem.setOnItemLongClickListener { parent, view, position, id ->

            val item = menuItems[position]
            menuItems.removeAt(position)


            txtCount.text = "Total Menu Items: ${menuItems.size}"
            lstItem.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, menuItems)

            Toast.makeText(this, "${item.name} deleted", Toast.LENGTH_SHORT).show()
            true
        }


        btnNext.setOnClickListener {
            val intent = Intent(this,filter::class.java)
            startActivity(intent)
        }
    }

    data class MenuItem(val name: String, val description: String, val price: Double, val course: String) {
        override fun toString(): String {
            return "$name - $course (R$price)"
        }
    }
}



