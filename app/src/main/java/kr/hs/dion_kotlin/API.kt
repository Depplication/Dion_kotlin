package kr.hs.dion_kotlin

import retrofit2.Call
import retrofit2.http.*

interface API {
    @POST("user/sign-up")
    fun JoinPost(@Body userData: UserData) : Call<Void>

    @POST("user/sign-in")
    fun LoginPost(@Body loginData: LoginData) : Call<LoginResponse>

    @GET("user/{id}")
    fun GetUserData(@Path("id") id:Int)

    @PATCH("user/{id}")
    fun UserModifyPost(@Path("id") id:Int, @Body updateData: ModifyData) : Call<UserDataX>

    @DELETE("user/")
    fun DeleteUser(@Query("id") id:Int) : Call<Void>

    @GET("advertising/category/{category}")
    fun GetAdvertisingList(@Path("category") category: Category) : Call<AdvertisingListData>
}