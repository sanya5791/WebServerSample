package webserver

import domain.*
import util.Logger
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception

class HttpRequestParser(
    private val logger: Logger
) {

    @Throws(IOException::class)
    fun parse(inputStream: InputStream): HttpRequest {
        val lines = read(BufferedReader(InputStreamReader(inputStream)))

        if (lines.isEmpty()) {
            throw RequestLineHttpException()
        }

        val iterator = lines.iterator()
        val requestLineString = iterator.next()

        val requestLine = getRequestLine(requestLineString)
        val headers = getHeaders(iterator)
        val body = getBody(iterator)

        return HttpRequest(
            requestLine.type,
            requestLine.resource,
            headers,
            body)
    }

    private fun getRequestLine(requestLine: String): RequestLine {
        val split = requestLine.split(" ")
        if (split.size != 3) {
            throw RequestLineHttpException()
        }

        val type = ActionType.valueOf(split[0])
        val resource = Resource(split[1])
        return RequestLine(type, resource)
    }

    private fun getHeaders(iterator: Iterator<String>): List<String> {
        val headers = ArrayList<String>()

        while (iterator.hasNext()) {
            val line = iterator.next()
            val isHeadersBlockFinished = line.isEmpty()
            if (isHeadersBlockFinished) {
                break
            }

            headers.add(line)
        }

        return headers
    }

    private fun getBody(iterator: Iterator<String>): Body {
        val sb = StringBuilder()

        while (iterator.hasNext()) {
            sb.appendln(iterator.next())
        }

        return Body(sb.toString())
    }

    @Throws(IOException::class)
    private fun read(reader: BufferedReader): List<String> {
        logger.out("=== HttpRequest: START")

        val lines = ArrayList<String>()
        var line = reader.readLine()
        while (line != null) {
            if (line.isEmpty()) {
                break
            }
            logger.out(line)
            lines.add(line)
            line = reader.readLine()
        }

        logger.out("=== HttpRequest: END\n")

        return lines
    }

}

class RequestLine(val type: ActionType, val resource: Resource)

class RequestLineHttpException : Exception(
    "First Query String should have 'Method Uri HTTP/Version' format.\n" +
            "For example 'GET / HTTP/1.1'"
)