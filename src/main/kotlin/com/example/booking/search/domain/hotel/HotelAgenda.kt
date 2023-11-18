package com.example.booking.search.domain.hotel

import java.util.*
import kotlin.collections.HashMap


class HotelAgenda() {

    val dayList = HashMap<Date, List<Room>>()

    fun setRooms(date: Date, rooms: List<Room>){
        dayList[date] = rooms
    }


}