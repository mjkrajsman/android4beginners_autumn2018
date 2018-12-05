package com.daftmobile.android4beginners4.robots.viewmodel

import androidx.lifecycle.LiveData

interface RobotsViewModel {
    fun getRobotList(): LiveData<String>
    fun getOrder(): Boolean
    fun setOrder(ascending: Boolean)
    fun addRobot()
    fun sortRobotList(ascending: Boolean)
}
