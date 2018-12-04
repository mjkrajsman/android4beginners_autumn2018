package com.daftmobile.android4beginners4.robots

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.daftmobile.android4beginners4.robots.viewmodel.ExternalSourceRobotsViewModel
import com.daftmobile.android4beginners4.robots.viewmodel.LiveDataRobotsViewModel
import com.daftmobile.android4beginners4.robots.viewmodel.RobotsViewModel
import kotlinx.android.synthetic.main.activity_robots.*

class RobotsActivity : AppCompatActivity() {
    private lateinit var viewModel: RobotsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_robots)


        //viewModel = LiveDataRobotsViewModel() //bez ViewModelu od google
        //viewModel = ViewModelProviders.of(this).get(LiveDataRobotsViewModel::class.java) //implementacja nie trzymajaca danych miedzy uruchomieniami aplikacji

        //val dataSource = SqliteDataSource(this.applicationContext) - mozna podac zewn. zrodlo danych
        //drugi parametr of - modelproviderfactory - tworzy instancje viewmodelu
        viewModel = ViewModelProviders.of(this).get(ExternalSourceRobotsViewModel::class.java)  //mozna korzystac z zewnetrznych zrodel
        viewModel.getRobotList().observe(this, Observer {
            robotTextView.text = it
        })

        addRobotFab.setOnClickListener {
            addNewItem()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.robots_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
        //R.id.actionSort -> println("Sortujemy!")
        R.id.ascending -> {
            println("Sortujemy rosnąco!")
            viewModel.sortRobotList(true)}
        R.id.descending -> {
            println("Sortujemy malejąco!")
            viewModel.sortRobotList(false)
        }
        else -> return super.onOptionsItemSelected(item)
    }
        return true
    }

    private fun addNewItem() {
        viewModel.addRobot()
    }

}
