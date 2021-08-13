package com.natife.trainee.users.data.network

import com.natife.trainee.users.data.network.model.UsersResponse
import com.natife.trainee.users.utils.USER_PAGE_SIZE
import retrofit2.http.GET

interface UserApiService {

    @GET("api/?results=$USER_PAGE_SIZE")
    suspend fun getUsers(): UsersResponse

}