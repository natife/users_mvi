package com.natife.trainee.users.data.network.model

import com.google.gson.annotations.SerializedName
import com.natife.trainee.domain.model.User

data class UserDto(
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("name")
    val name: UserNameDto?,
    @SerializedName("location")
    val location: LocationDto?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("login")
    val login: LoginDto?,
    @SerializedName("dob")
    val dateOfBirth: UserDate?,
    @SerializedName("registered")
    val registered: UserDate?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("cell")
    val cell: String?,
    @SerializedName("picture")
    val picture: PictureDto?
)

fun UserDto.toDomain() = User(
    id = login?.sha1 ?: "",
    title = name?.title ?: "",
    firstName = name?.firstName ?: "",
    lastName = name?.lastName ?: "",
    dateOfBirth = dateOfBirth?.date ?: "",
    age = dateOfBirth?.age ?: 0,
    country = location?.country ?: "",
    state = location?.state ?: "",
    city = location?.city ?: "",
    street = location?.street?.name ?: "",
    houseNumber = location?.street?.number ?: 0,
    email = email ?: "",
    phone = phone ?: "",
    avatar = picture?.large ?: "",
    avatarThumbnail = picture?.thumbnail ?: ""
)