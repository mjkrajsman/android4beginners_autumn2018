package com.daftmobile.android4beginners4.robots.model

import android.os.Build


class ListRobotsDataSource: RobotsDataSource {
    private val robots = mutableListOf<Robot>()
    private var order = true

    override fun getRobots() = robots.toMutableList()

    override fun getListOrder(): Boolean = order

    override fun setListOrder(ascending: Boolean) {
        order = ascending
    }

    override fun addNew(robot: Robot) {
        robots.add(robot)
    }

    override fun sortRobots(ascending: Boolean) {
        println("Model")
        val robotComparator = Comparator<Robot> { p1, p2 ->
            when {
                p1.name > p2.name -> 1
                p1.name < p2.name -> -1
                p1.name == p2.name && p1.mood > p2.mood -> 1
                p1.name == p2.name && p1.mood < p2.mood -> -1
                else -> 0
            }
        }

        if(ascending){
            robots.sortWith(robotComparator) //sortBy{it.name}
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                robots.sortWith(robotComparator.reversed())
            }else{
                robots.sortWith(robotComparator)
                robots.reverse()
            }
        }
    }




}
