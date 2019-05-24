package domain

import java.net.HttpURLConnection

data class HttpResponse(
    val resource: Resource,
    val body: Body,
    val statusCode: Int = HttpURLConnection.HTTP_OK
)