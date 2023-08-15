package kell.com.example.vaanh.model

data class AuthenticationRequest(
    val username: String,
    val password: String,
)

data class AuthenticationResponse(
    val accessToken: Token,
)

data class Token(
    val token: String,
)
