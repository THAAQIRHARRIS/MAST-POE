package com.example.mastproject

import android.annotation.SuppressLint
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

            val lstItem = findViewById<ListView>(R.id.lstItem)
            val txtCount = findViewById<TextView>(R.id.txtCount)
            val edtName = findViewById<EditText>(R.id.txtName)
            val edtDes = findViewById<EditText>(R.id.txtDes)
            val edtPrice = findViewById<EditText>(R.id.edtPrice)
            val spnCourse = findViewById<Spinner>(R.id.spnCourse)
            val btnAdd = findViewById<Button>(R.id.btnAdd)

            val courses = arrayOf("Select Course", "Starters", "Mains", "Desserts")
            spnCourse.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, courses)

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
                        Toast.makeText(this, "Please fill all fields correctly",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


    data class MenuItem(val name: String, val description: String, val price: Double, val course: String) {
        override fun toString(): String {
            return "$name - $course (R$price)"
        }
    }

