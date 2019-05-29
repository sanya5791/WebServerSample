package webserver.responsehandler

import domain.HttpResponse
import domain.HttpRequest
import domain.Body
import domain.Resource

class DefaultResponseHandler : ResponseHandler {

    override val uri: String
        get() = ResponseResourceRouter.DEFAULT

    override fun handle(request: HttpRequest): HttpResponse {
        val sb = StringBuilder("Supported commands:\n")
            .appendln("\\sanya, \\finish")
            .appendln()
            .appendln("Headers List:")

        for (header in request.headers) {
            sb.appendln(header)
        }

        val body = sb.toString()
        return HttpResponse(
            Resource(uri),
            Body(body)
        )
    }

}