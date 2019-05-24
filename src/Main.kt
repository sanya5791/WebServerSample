fun main(args : Array<String>) {
    val webServer = Injections.provideWebServer()
    webServer.start()
}
