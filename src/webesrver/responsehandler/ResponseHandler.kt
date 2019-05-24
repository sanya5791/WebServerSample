package webesrver.responsehandler

import domain.HttpResponse
import domain.HttpRequest

interface ResponseHandler {
    val uri: String
    fun handle(request: HttpRequest): HttpResponse
}