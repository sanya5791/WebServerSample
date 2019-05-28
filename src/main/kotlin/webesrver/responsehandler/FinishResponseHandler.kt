package webesrver.responsehandler

import domain.HttpResponse
import domain.HttpRequest
import domain.Body
import domain.Resource

class FinishResponseHandler : ResponseHandler {
    override val uri: String
        get() = ResponseResourceRouter.FINISH

    override fun handle(request: HttpRequest): HttpResponse {
        return HttpResponse(
            Resource(uri),
            Body("Small Web Server is DOWN.")
        )
    }

}