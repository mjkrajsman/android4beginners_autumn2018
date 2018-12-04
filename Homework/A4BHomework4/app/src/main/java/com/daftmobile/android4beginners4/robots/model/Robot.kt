package com.daftmobile.android4beginners4.robots.model


data class Robot(val id: Int, val name: String, val mood: Mood) {
    override fun toString() = "$name (${mood.name.toLowerCase()})"

    enum class Mood { HAPPY, SAD }
}
