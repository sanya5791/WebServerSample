package webserver.servlet

import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class SanyaServlet : HttpServlet() {

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val pw = resp?.writer!!

        val body = buildString {
            appendHTML().html {
                body {
                    h2 {
                        +"Hello, "
                        span {
                            attributes["style"] = "color:green"
                            +"Sanya!!!"
                        }

                    }
                }
            }
        }
        pw.println(body)
    }

}