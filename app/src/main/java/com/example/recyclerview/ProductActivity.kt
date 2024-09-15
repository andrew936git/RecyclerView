package com.example.recyclerview

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextClock
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout


class ProductActivity : AppCompatActivity() {

    private lateinit var productImageIV: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var layout: ConstraintLayout

    private var product: Product? = null
    private var position: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product)
        init()
    }

    @SuppressLint("SetTextI18n", "InflateParams")
    private fun init(){

        productImageIV = findViewById(R.id.productImageIV)
        nameTextView = findViewById(R.id.nameTextView)
        descriptionTextView = findViewById(R.id.descriptionTextView)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        title = "Мой гардероб"
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("newProduct", product)
            intent.putExtra("item", position)
            startActivity(intent)
            onBackPressed()
        }

        if (intent.hasExtra("product")){
            product = intent.getParcelableExtra("product")

        }
        if (product != null){
            productImageIV.setImageResource(product!!.image)
            nameTextView.text = product!!.name
            descriptionTextView.text = product!!.description
        }

        layout = findViewById(R.id.layout)
        layout.setOnLongClickListener{
                val dialog = AlertDialog.Builder(this)
                val inflater = this.layoutInflater
                val dialogView = inflater.inflate(R.layout.update_dialog, null)
                dialog.setView(dialogView)
                val editName = dialogView.findViewById<EditText>(R.id.updateNameET)
                val editDescription = dialogView.findViewById<EditText>(R.id.updateDescriptionET)

                dialog.setTitle("Обновить запись")
                dialog.setMessage("Введите данные:")
                dialog.setPositiveButton("Обновить"){_, _ ->
                    nameTextView.text = editName.text
                    descriptionTextView.text = editDescription.text
                    product?.name = editName.text.toString()
                    product?.description = editDescription.text.toString()

                    position = intent.extras?.getInt("position")


                }
                dialog.setNegativeButton("Отмена"){_, _ ->}
                dialog.create().show()
                false
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.product_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.exit_menu -> {
                Toast.makeText(
                    applicationContext,
                    "Программа завершена",
                    Toast.LENGTH_LONG
                ).show()
                finishAffinity()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}