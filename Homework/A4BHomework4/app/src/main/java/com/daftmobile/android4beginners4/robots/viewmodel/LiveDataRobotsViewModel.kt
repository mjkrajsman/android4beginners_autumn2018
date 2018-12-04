package com.daftmobile.android4beginners4.robots.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.daftmobile.android4beginners4.robots.model.Robot
import com.daftmobile.android4beginners4.robots.model.RobotGenerator

class LiveDataRobotsViewModel: RobotsViewModel, ViewModel() {
    override fun sortRobotList(ascending: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val mutableLiveData: MutableLiveData<MutableList<Robot>> = MutableLiveData()

    override fun getRobotList(): LiveData<String> = Transformations.map(mutableLiveData) { list: List<Robot>? -> list.toString() }

    override fun addRobot() {
        val mutableList = mutableLiveData.value ?: mutableListOf()
        mutableList.add(RobotGenerator.generate())
        mutableLiveData.value = mutableList
    }
}
