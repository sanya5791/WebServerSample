package webserver.servlet

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class SanyaServlet : HttpServlet() {

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val pw = resp?.writer!!
        pw.println("<body>")
        pw.println("<h2>")
        pw.println("Hello, <span style=color:green;>Sanya!!!</span>")
        pw.println("</h2>")
        pw.println("</body>")
    }
}