package webesrver

import util.Logger
import webesrver.responsehandler.ResponseResourceRouter
import java.io.IOException
import java.io.PrintWriter
import java.net.ServerSocket
import java.util.concurrent.Executors
import java.util.concurrent.Semaphore

const val PORT = 8080
const val MAX_OPENED_CONNECTIONS = 1

class WebServer(
    private val logger: Logger,
    private val htppRequestParser: HttpRequestParser,
    private val responseResourceRouter: ResponseResourceRouter,
    private val statusLineGenerator: StatusLineGenerator
) {
    private var isWorking: Boolean = false
    private val semaphore = Semaphore(MAX_OPENED_CONNECTIONS - 1)

    fun start() {
        logger.out(String.format("Simple web server is STARTED on %d port\n", PORT))
        isWorking = true

        val executor = Executors.newFixedThreadPool(5)

        try {
            ServerSocket(PORT).use { serverSocket ->
                var requestCounter = 0

                while (isWorking) {
                    logger.out("\n== REQUEST N_${++requestCounter} ======\n")
                    val worker = Runnable {
                        handleRequest(serverSocket)
                    }
                    executor.execute(worker)
                    semaphore.acquire()
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        executor.shutdown()
    }

    private fun handleRequest(serverSocket: ServerSocket) {
        serverSocket.accept().use { client ->
            semaphore.release()
            client.getInputStream().use { inputStream ->
                logger.out("Open StreamInput")
                val httpRequest = htppRequestParser.parse(inputStream)
                val responseHandler = responseResourceRouter.getResponseHandler(httpRequest.resource)
                val response = responseHandler.handle(httpRequest)

                logger.out(":= response START")

                client.getOutputStream().use { outputStream ->
                    val printWriter = PrintWriter(outputStream)
                    val statusLine = statusLineGenerator.create(response.statusCode)
                    printWriter.println(statusLine)
                    logger.out("")
                    printWriter.println("")
                    logger.out(response.body.get)
                    printWriter.println(response.body.get)

                    printWriter.flush()

                    if (ResponseResourceRouter.FINISH == response.resource.get) {
                        isWorking = false
                    }
                }
                logger.out(":= response END")
                logger.out("Close OutputStream")
            }
            logger.out("Close InputStream")
        }
        logger.out("Close Client")
    }

    fun stop() {
        isWorking = false
    }

}