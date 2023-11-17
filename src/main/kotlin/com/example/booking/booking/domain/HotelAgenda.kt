package com.example.booking.booking.domain

import java.util.*
import kotlin.collections.HashMap


class HotelAgenda() {

    val dayList = HashMap<Date, List<Room>>()

    fun setRooms(date: Date, rooms: List<Room>){
        dayList[date] = rooms
    }


}