package com.github.api.api


import com.github.api.data.DetailUserResponse
import com.github.api.data.User
import com.github.api.data.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 82a89fc050cac80098636af139d2ce10cdb9ccda")
    fun getSearchUsers(
        @Query("q") query : String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 82a89fc050cac80098636af139d2ce10cdb9ccda")
    fun getSearchUsersDetail(
       @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 82a89fc050cac80098636af139d2ce10cdb9ccda")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 82a89fc050cac80098636af139d2ce10cdb9ccda")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>


}