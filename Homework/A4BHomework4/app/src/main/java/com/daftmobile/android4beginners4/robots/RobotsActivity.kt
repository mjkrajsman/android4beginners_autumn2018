package com.daftmobile.android4beginners4.robots

//import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
//import android.widget.RadioGroup
//import androidx.appcompat.app.ActionBar
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

        val order = viewModel.getOrder()
        viewModel.sortRobotList(order)
        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.robots_menu, menu)
        //val radio: MenuItem = findViewById(R.id.menuSortGroup)
        if(viewModel.getOrder()){
            menu?.findItem(R.id.ascending)?.setChecked(true)
            //findViewById(R.id.ascending)
        }else{
            menu?.findItem(R.id.descending)?.setChecked(true)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            //R.id.actionSort -> println("Sortujemy!")
            R.id.ascending -> {
                println("Sortujemy rosnąco!")
                viewModel.setOrder(true)
                //viewModel.sortRobotList(true)
                item.isChecked = true
            }
            R.id.descending -> {
                println("Sortujemy malejąco!")
                viewModel.setOrder(false)
                //viewModel.sortRobotList(false)
                item.isChecked = true
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun addNewItem() {
        viewModel.addRobot()
        //TODO: val order = viewModel.getOrder()
        val order = viewModel.getOrder()
        viewModel.sortRobotList(order)
    }

}
