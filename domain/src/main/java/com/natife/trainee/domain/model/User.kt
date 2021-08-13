package com.natife.trainee.domain.model

data class User(
    val id: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val age: Int,
    val country: String,
    val state: String,
    val city: String,
    val street: String,
    val houseNumber: Int,
    val email: String,
    val phone: String,
    val avatar: String,
    val avatarThumbnail: String
)
