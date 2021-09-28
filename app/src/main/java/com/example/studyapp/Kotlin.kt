package com.example.studyapp

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.FieldPosition

class Kotlin : AppCompatActivity() {

    private lateinit var RecyclerView : RecyclerView

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val item1=menu!!.getItem(0)
        val item2=menu.getItem(1)
        item1.title="Main Menu"
        item2.title="Android Studio Review"
        return super.onPrepareOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.kotlinChoice -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.androidChoice -> {
                startActivity(Intent(this, Android::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.study_list)

        title="Kotlin Review"

        RecyclerView=findViewById(R.id.listStudy)

        val dialogBuilder = AlertDialog.Builder(this)

        val listTitle= listOf(
            "var and val",
            "try and catch",
            "when"
        )
        val listMaterials= listOf(
            "Use to Initialize Variables",
            "Use To Catch Errors",
            "Use for Multiple Choices"
        )

        val listDetails= listOf(
            "The Value of Variable (var) Can be Change\nThe Value of Variable (val) can't be Change",
            "You also use it to let the user enter only specific type of data",
            "Has the same use as if/else but more shorter"
        )

        val adapter=RecyclerViewAdapter(listTitle,listMaterials)
        RecyclerView.adapter = adapter
        RecyclerView.layoutManager = LinearLayoutManager(this)

        adapter.setOnItemClickListener(object : RecyclerViewAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {

                dialogBuilder.setMessage("Details:\n${listDetails[position]}")
                    .setCancelable(false)
                    .setPositiveButton("OK") { dialog, _ -> dialog.cancel() }

                val alert=dialogBuilder.create()
                alert.setTitle(listTitle[position])
                alert.show()

            }
        })

    }
}