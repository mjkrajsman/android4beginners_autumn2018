package com.daftmobile.android4beginners4.robots.model


interface RobotsDataSource {
    fun getRobots(): MutableList<Robot>
    fun getListOrder(): Boolean
    fun setListOrder(ascending: Boolean)
    fun addNew(robot: Robot = RobotGenerator.generate())
    fun sortRobots(ascending: Boolean)
}
