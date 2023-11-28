package com.example.training.registration.application


interface EmailSender {
    fun sendEmail(userEmail: String, title: String, body: String)
}