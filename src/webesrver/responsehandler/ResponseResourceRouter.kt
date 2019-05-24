package webesrver.responsehandler

import domain.Resource


class ResponseResourceRouter {

    val defaultResponseHandler = DefaultResponseHandler()
    private val responseHandlers =  HashMap<Resource, ResponseHandler>().apply {
            put(Resource(DEFAULT), defaultResponseHandler)
            put(Resource(SANYA), SanyaResponseHandler())
            put(Resource(FINISH), FinishResponseHandler())
        }

    fun getResponseHandler(resource: Resource): ResponseHandler {
        return responseHandlers.getOrDefault(resource, defaultResponseHandler)
    }

    companion object Resources {
        const val DEFAULT = "/"
        const val SANYA = "/sanya"
        const val FINISH = "/finish"
    }

}