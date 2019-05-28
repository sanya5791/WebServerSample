package webesrver.responsehandler

import domain.Body
import domain.HttpRequest
import domain.HttpResponse
import domain.Resource
import kotlinx.html.body
import kotlinx.html.h2
import kotlinx.html.html
import kotlinx.html.stream.appendHTML
import kotlinx.html.unsafe

class SanyaResponseHandler : ResponseHandler {
    override val uri: String
        get() = ResponseResourceRouter.SANYA

    override fun handle(request: HttpRequest): HttpResponse {
        val body = buildString {
            appendHTML().html {
                body {
                    h2 {
                        unsafe {
                            +"Hello, <span style=color:green;>Sanya!!!</span>"
                        }
                    }
                }
            }
        }
        return HttpResponse(
            Resource(uri),
            Body(body)
        )
    }

}