package com.example.studyapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Android : AppCompatActivity() {

    private lateinit var RecyclerView : RecyclerView

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val item1=menu!!.getItem(0)
        val item2=menu.getItem(1)
        item1.title="Main Menu"
        item2.title="Kotlin Review"
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
                startActivity(Intent(this, Kotlin::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.study_list)

        RecyclerView=findViewById(R.id.listStudy)

        var dialogBuilder=AlertDialog.Builder(this)

        title="Android Studio Review"

        val listTitle= listOf(
            "setTextColor",
            "currentTextColor",
            "setPadding(left,top,right,down)"
        )
        val listMaterials= listOf(
            "Use to change the color of the text",
            "Use to know the current color of the text",
            "Use to change position of the text view"
        )

        val listDetails= listOf(
            "Ex: TextView.setTextColor(Color.GREEN)",
            "Ex: TextView.currentTextColor ==> Int",
            "Ex: TextView.setPadding(0,0,0,10)\nThis will push it up 10dp"
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