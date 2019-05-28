
class Main {
    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val webServer = Injections.provideWebServer()
            webServer.start()
        }

    }
}

