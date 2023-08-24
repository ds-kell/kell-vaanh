package kell.com.example.vaanh.model

data class AuthenticationRequest(
    val username: String,
    val password: String,
)


data class AuthenticationResponse(
    val token: String,
    val type: String,
    val username: String,
    val authorities: List<String>
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

data class ResponseUtil<T>(
    val message: String,
    val data: T,
)