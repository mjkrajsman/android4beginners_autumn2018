package com.daftmobile.android4beginners4.robots.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.daftmobile.android4beginners4.robots.model.ListRobotsDataSource
import com.daftmobile.android4beginners4.robots.model.RobotsDataSource

class ExternalSourceRobotsViewModel(
        private val robotDataSource: RobotsDataSource = ListRobotsDataSource()
): ViewModel(), RobotsViewModel {

    private val mutableLiveData: MutableLiveData<String> = MutableLiveData()

    override fun getRobotList(): LiveData<String> = mutableLiveData

    override fun getOrder(): Boolean {
        return robotDataSource.getListOrder()
    }
    override fun setOrder(ascending: Boolean) {
        sortRobotList(ascending)
        robotDataSource.setListOrder(ascending)
    }

    override fun addRobot() {
        robotDataSource.addNew()
        mutableLiveData.value = robotDataSource.getRobots().joinToString(separator = "\n")//toString()
    }

    override fun sortRobotList(ascending: Boolean) {
        println("ViewModel")
        if(ascending){
            robotDataSource.sortRobots(true)
        }else{
            robotDataSource.sortRobots(false)
        }
        mutableLiveData.value = robotDataSource.getRobots().joinToString(separator = "\n")//toString()
    }


}
//class ExternalSourceRobotsViewModel: ViewModel(), RobotsViewModel {
//
//    private val mutableLiveData: MutableLiveData<String> = MutableLiveData()
//    private val robotDataSource = ListRobotsDataSource()
//
//
//    override fun getRobotList(): LiveData<String> = mutableLiveData
//
//    override fun addRobot() {
//        robotDataSource.addNew()
//        mutableLiveData.value = robotDataSource.getRobots().toString()
//    }
//}
