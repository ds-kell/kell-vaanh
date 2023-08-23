package kell.com.example.vaanh.model

data class AuthenticationRequest(
    val username: String,
    val password: String,
)

data class AuthenticationResponse(
    val message: String,
    val data: AuthenticationResponseData
)


data class AuthenticationResponseData(
    val token: String,
    val type: String,
    val username: String,
    val authorities: List<String>
)


data class UserProfile(
    val username: String,
    val dob: Long,
    val fullName: String,
    val gender: String,
    val phoneNumber: String,
)

data class SignUpRequest(
    val email: String,
    val username: String,
    val password: String,
)

data class SignUpResponse(
    val message: String,
)

data class ResponseUtil(val message: String)