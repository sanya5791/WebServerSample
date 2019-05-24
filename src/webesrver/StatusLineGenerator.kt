package webesrver

import java.net.HttpURLConnection

private const val HTTP_VERSION = "HTTP/1.1"
class StatusLineGenerator {

    fun create(httpStatusCode: Int): String {
        val status = when (httpStatusCode) {
            HttpURLConnection.HTTP_OK -> "200 OK"
            else -> "501 Not Implemented"
        }

        return "$HTTP_VERSION $status"
    }
}