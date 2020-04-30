package xyz.computingabc.ibmsprotal.Student.StudentLogin.Api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import xyz.computingabc.ibmsprotal.Student.StudentLogin.Data.LoginRespons

interface Api {
    @FormUrlEncoded
    @POST("student-login-api.php")
    fun userLogin(
        @Field("uni_reg_number") uni_reg_number:String,
        @Field("password") password: String
    ): Call<LoginRespons>
}