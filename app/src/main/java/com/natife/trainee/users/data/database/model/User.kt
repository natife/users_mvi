package com.natife.trainee.users.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.natife.trainee.domain.model.User as DomainUser

@Entity(
    tableName = User.TABLE_NAME
)
data class User(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: String,
    @ColumnInfo(name = COLUMN_TITLE)
    val title: String,
    @ColumnInfo(name = COLUMN_FIRST_NAME)
    val firstName: String,
    @ColumnInfo(name = COLUMN_LAST_NAME)
    val lastName: String,
    @ColumnInfo(name = COLUMN_NAME_DATE_OF_BIRTH)
    val dateOfBirth: String,
    @ColumnInfo(name = COLUMN_NAME_AGE)
    val age: Int,
    @ColumnInfo(name = COLUMN_NAME_COUNTRY)
    val country: String,
    @ColumnInfo(name = COLUMN_NAME_STATE)
    val state: String,
    @ColumnInfo(name = COLUMN_NAME_CITY)
    val city: String,
    @ColumnInfo(name = COLUMN_NAME_STREET_NAME)
    val street: String,
    @ColumnInfo(name = COLUMN_NAME_HOUSE_NUMBER)
    val houseNumber: Int,
    @ColumnInfo(name = COLUMN_NAME_EMAIL)
    val email: String,
    @ColumnInfo(name = COLUMN_NAME_PHONE)
    val phone: String,
    @ColumnInfo(name = COLUMN_NAME_AVATAR)
    val avatar: String,
    @ColumnInfo(name = COLUMN_NAME_AVATAR_THUMBNAIL)
    val avatarThumbnail: String
) {

    companion object {

        const val TABLE_NAME = "users"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_FIRST_NAME = "first_name"
        const val COLUMN_LAST_NAME = "last_name"
        const val COLUMN_NAME_DATE_OF_BIRTH = "date_of_birth"
        const val COLUMN_NAME_AGE = "age"
        const val COLUMN_NAME_COUNTRY = "country"
        const val COLUMN_NAME_STATE = "state"
        const val COLUMN_NAME_CITY = "city"
        const val COLUMN_NAME_STREET_NAME = "street_name"
        const val COLUMN_NAME_HOUSE_NUMBER = "house_number"
        const val COLUMN_NAME_EMAIL = "email"
        const val COLUMN_NAME_PHONE = "phone"
        const val COLUMN_NAME_AVATAR = "avatar"
        const val COLUMN_NAME_AVATAR_THUMBNAIL = "avatar_thumbnail"

    }
}

fun User.toDomain(): DomainUser = DomainUser(
    id = id,
    title = title,
    firstName = firstName,
    lastName = lastName,
    dateOfBirth = dateOfBirth,
    age = age,
    country = country,
    state = state,
    city = city,
    street = street,
    houseNumber = houseNumber,
    email = email,
    phone = phone,
    avatar = avatar,
    avatarThumbnail = avatarThumbnail
)

fun DomainUser.toEntity(): User = User(
    id = id,
    title = title,
    firstName = firstName,
    lastName = lastName,
    dateOfBirth = dateOfBirth,
    age = age,
    country = country,
    state = state,
    city = city,
    street = street,
    houseNumber = houseNumber,
    email = email,
    phone = phone,
    avatar = avatar,
    avatarThumbnail = avatarThumbnail
)
