package webesrver

import util.Logger
import webesrver.responsehandler.ResponseResourceRouter
import java.io.IOException
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

const val PORT = 8080

class WebServer(
    private val logger: Logger,
    private val htppRequestParser: HttpRequestParser,
    private val responseResourceRouter: ResponseResourceRouter,
    private val statusLineGenerator: StatusLineGenerator,
    private val threadExecutor: ThreadExecutor
) {
    private var isWorking: Boolean = false

    fun start() {
        logger.out(String.format("Simple web server is STARTED on %d port\n", PORT))
        isWorking = true

        try {
            ServerSocket(PORT).use { serverSocket ->
                var requestCounter = 0

                while (isWorking) {
                    logger.out("\n== REQUEST N_${++requestCounter} ======\n")
                    val client = serverSocket.accept()
                    threadExecutor.execute {
                        handleRequest(client)
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        threadExecutor.shutdown()
    }

    private fun handleRequest(client: Socket) {

        client.getInputStream().use { inputStream ->
            val httpRequest = htppRequestParser.parse(inputStream)
            val responseHandler = responseResourceRouter.getResponseHandler(httpRequest.resource)
            val response = responseHandler.handle(httpRequest)

            client.getOutputStream().use { outputStream ->
                val printWriter = PrintWriter(outputStream)
                val statusLine = statusLineGenerator.create(response.statusCode)
                printWriter.println(statusLine)
                printWriter.println("")
                printWriter.println(response.body.get)

                printWriter.flush()

                if (ResponseResourceRouter.FINISH == response.resource.get) {
                    isWorking = false
                }
            }
        }
        client.close()
    }

    fun stop() {
        isWorking = false
    }

}