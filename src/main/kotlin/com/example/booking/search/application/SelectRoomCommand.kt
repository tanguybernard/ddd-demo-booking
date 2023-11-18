package com.example.booking.search.application

import java.util.*

data class SelectRoomCommand(val hotelId: String, val capacity: Int, val date: Date) {

}
