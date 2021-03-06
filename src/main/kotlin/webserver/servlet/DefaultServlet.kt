package webserver.servlet

import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class DefaultServlet : HttpServlet() {

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val pw = resp?.writer!!
        val headerNames = req?.headerNames?.toList()!!

        val body = buildString {
            appendHTML().html {
                body {
                    h2 { +"HEADERS LIST:" }

                    headerNames.forEach { h ->
                        br {
                            strong { +"${h.capitalize()}: " }; +req.getHeader(h)
                        }
                    }
                }
            }
        }
        pw.println(body)
    }
}