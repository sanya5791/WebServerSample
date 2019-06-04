package webserver.responsehandler

import domain.Body
import domain.HttpRequest
import domain.HttpResponse
import domain.Resource
import kotlinx.html.*
import kotlinx.html.stream.appendHTML

class SanyaResponseHandler : ResponseHandler {
    override val uri: String
        get() = ResponseResourceRouter.SANYA

    override fun handle(request: HttpRequest): HttpResponse {
        val body = buildString {
            appendHTML().html {
                body {
                    h2 {
                        +"Hello, "
                        span {
                            attributes["style"] = "color:green"
                            +"Sanya!!!"
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