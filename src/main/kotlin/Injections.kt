import util.LoggerImpl
import webserver.HttpRequestParser
import webserver.StatusLineGenerator
import webserver.ThreadExecutor
import webserver.WebServer
import webserver.responsehandler.ResponseResourceRouter

object Injections {

    fun provideWebServer(isSingleton: Boolean = true): WebServer {
        return if (isSingleton) {
            Singleton.webServer
        } else {
            provideWebServer()
        }
    }

    private fun provideWebServer() = WebServer(
        provideLogger(),
        provideHttpRequestParser(),
        provideResponseResourceRouter(),
        provideStatusLineGenerator(),
        provideThreadExecutor()
    )

    private fun provideHttpRequestParser() =
        HttpRequestParser(provideLogger())

    private fun provideResponseResourceRouter() =
        ResponseResourceRouter()

    private fun provideLogger() = LoggerImpl()

    private fun provideStatusLineGenerator() = StatusLineGenerator()

    private fun provideThreadExecutor() = ThreadExecutor()

    object Singleton {
        val webServer = provideWebServer()
        val logger = provideLogger()
        val httpRequestParser = provideHttpRequestParser()
        val responseResourceRouter = provideResponseResourceRouter()
        val statusLineGenerator = provideStatusLineGenerator()
        val threadExecutor = provideThreadExecutor()
    }
}