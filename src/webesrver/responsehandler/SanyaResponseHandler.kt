package webesrver.responsehandler

import domain.HttpResponse
import domain.HttpRequest
import domain.Body
import domain.Resource

class SanyaResponseHandler : ResponseHandler {
    override val uri: String
        get() = ResponseResourceRouter.SANYA

    override fun handle(request: HttpRequest): HttpResponse {
        return HttpResponse(
            Resource(uri),
            Body("Hello Sanya!!!")
        )
    }

}