package webserver.servlet

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class DefaultServlet : HttpServlet() {

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val pw = resp?.writer!!
        val headerNames = req?.headerNames?.toList()!!
        val headers = headerNames.map { headerName -> "$headerName: ${req.getHeader(headerName)}" }
        pw.println("<body>")
        pw.println("<h2>")
        pw.println("HEADERS LIST:")

        headers.forEach { header -> pw.println(header) }

        pw.println("</h2>")
        pw.println("</body>")
    }
}