import util.LoggerImpl
import webesrver.HttpRequestParser
import webesrver.StatusLineGenerator
import webesrver.WebServer
import webesrver.responsehandler.ResponseResourceRouter

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
        provideStatusLineGenerator()
    )

    private fun provideHttpRequestParser() =
        HttpRequestParser(provideLogger())

    private fun provideResponseResourceRouter() =
        ResponseResourceRouter()

    private fun provideLogger() = LoggerImpl()

    private fun provideStatusLineGenerator() = StatusLineGenerator()

    object Singleton {
        val webServer = provideWebServer()
        val logger = provideLogger()
        val httpRequestParser = provideHttpRequestParser()
        val responseResourceRouter = provideResponseResourceRouter()
        val statusLineGenerator = provideStatusLineGenerator()
    }
}