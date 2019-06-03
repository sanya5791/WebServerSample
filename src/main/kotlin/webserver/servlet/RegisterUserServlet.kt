package webserver.servlet

import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class RegisterUserServlet : HttpServlet() {

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val parameterMessage = req?.getParameter("message")
        val pw = resp?.writer!!

        val body = buildString {
            appendHTML().html {
                body {
                    parameterMessage?.apply {
                        p {
                            unsafe { +"<span style=color:red;>$parameterMessage</span>" }
                        }
                    }
                    h2 { +"Register New User:" }
                    textArea { +"Not Implemented yet" }
                }
            }
        }
        pw.println(body)
    }
}