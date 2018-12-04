package com.daftmobile.android4beginners4.robots.model


interface RobotsDataSource {
    fun getRobots(): MutableList<Robot>
    fun addNew(robot: Robot = RobotGenerator.generate())
    fun sortRobots(ascending: Boolean)
}
