package com.example.recyclerview

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecondActivity : AppCompatActivity() {

    private val productList = mutableListOf(
        Product("Футболка", "Футболка белая однотонная", R.drawable.t_shirt),
        Product("Брюки", "Брюки карго женские спортивные",R.drawable.cargo),
        Product("Футболка", "Футболка в рубчик однотонная базовая хлопок", R.drawable.t_shirt_black),
        Product("Джинсы ", "Джинсы прямые широкие трубы черные", R.drawable.jeans),
        Product("Джинсы", "Джинсы прямые широкие трубы синие", R.drawable.jeans_blue),
        Product("Джинсы", "Джинсы прямые широкие трубы серые", R.drawable.jeans_grey),
        Product("Лонгслив ", "Лонгслив оверсайз хлопок белый", R.drawable.longsliv_white),
        Product("Лонгслив ", "Лонгслив оверсайз хлопок черный", R.drawable.longsliv_black),
        Product("Лонгслив ", "Лонгслив оверсайз хлопок розовый", R.drawable.longsliv_pink),
        Product("Лонгслив ", "Лонгслив оверсайз хлопок голубой", R.drawable.longsliv_blue),
        Product("Лонгслив ", "Лонгслив оверсайз хлопок серый", R.drawable.longsliv_grey),
        Product("Брюки клеш", "Брюки клеш классические палаццо с высокой посадкой черные", R.drawable.flared_trousers_black),
        Product("Брюки клеш", "Брюки клеш классические палаццо с высокой посадкой синие", R.drawable.flared_trousers_blue),
        Product("Брюки клеш", "Брюки клеш классические палаццо с высокой посадкой красные", R.drawable.flared_trousers_red),
        Product("Брюки клеш", "Брюки клеш классические палаццо с высокой посадкой белые", R.drawable.flared_trousers_white),
        Product("Брюки клеш", "Брюки клеш классические палаццо с высокой посадкой бежевые", R.drawable.flared_trousers_beige),
        Product("Джемпер", "Джемпер мужской больших размеров теплый вязаный бежевый черный", R.drawable.sweater_black),
        Product("Джемпер", "Джемпер мужской больших размеров теплый вязаный бежевый синий", R.drawable.sweater_blue),
        Product("Джемпер", "Джемпер мужской больших размеров теплый вязаный бежевый бежевый", R.drawable.sweater_beige),
        Product("Джемпер", "Джемпер мужской больших размеров теплый вязаный бежевый темно-бежевый", R.drawable.sweater_dark_beige)
    )
    private var item: Int? = null
    private var adapter: CustomAdapter? = null
    private lateinit var recyclerViewRV: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        init()
    }

    private fun init(){
        item = intent.extras?.getInt("item")
        val product = intent.getParcelableExtra<Product>("newProduct")
        if (product != null){
            if (item != null) {
                productList[item!!] = product
                adapter = CustomAdapter(productList)
                item = null
            }
        }
        //recyclerViewRV.adapter = adapter
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        title = "Мой гардероб"
        setSupportActionBar(toolbar)

        adapter = CustomAdapter(productList)
        recyclerViewRV = findViewById(R.id.recyclerViewRV)
        recyclerViewRV.layoutManager = LinearLayoutManager(this)
        recyclerViewRV.adapter = adapter
        recyclerViewRV.setHasFixedSize(true)
        adapter!!.setOnProductClickListener(object:
        CustomAdapter.OnProductClickListener{
            override fun onProductClick(product: Product, position: Int) {
                val intent = Intent(this@SecondActivity, ProductActivity::class.java)
                intent.putExtra("product", product)
                intent.putExtra("position", position)
                startActivity(intent)
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
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