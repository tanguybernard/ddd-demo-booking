package com.example.booking.preparation.domain.hotel

import com.example.booking.preparation.domain.room.Room

class Hotel(val hotelId: HotelId, val hotelName: HotelName) {

    private val listRoom = ArrayList<Room>();

    fun addRoom(room: Room) {
        listRoom.add(room)
    }

    fun getListRoom(): List<Room>{
        return listRoom
    }

    fun has(room: Room): Boolean {
        return listRoom.any { it.roomNumber.equals(room.roomNumber) }
    }

}
