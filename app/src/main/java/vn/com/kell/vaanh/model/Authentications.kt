package vn.com.kell.vaanh.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthenticationRequest(
    val username: String,
    val password: String,
)


data class AuthenticationResponse(
    @SerializedName("accessToken") @Expose val accessToken: String,
    @SerializedName("refreshToken") @Expose val refreshToken: String,
    @SerializedName("type") @Expose val type: String,
    @SerializedName("username") @Expose val username: String,
    @SerializedName("authorities") @Expose val authorities: List<String>
)

data class AccountProfile(
    val fullName: String,
    val phoneNumber: String,
    val dob: String,
    val gender: String,
    val avatarUrl: String
)

data class SignUpRequest(
    val email: String,
    val username: String,
    val password: String,
)

data class SignUpResponse(
    val message: String,
)

data class ResponseUtils<T>(
    val message: String,
    val data: T,
) {

}