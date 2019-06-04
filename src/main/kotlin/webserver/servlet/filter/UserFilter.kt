package webserver.servlet.filter

import util.Logger
import util.LoggerImpl
import javax.servlet.*
import javax.servlet.http.HttpServletResponse

class UserFilter : Filter {

    private var config: FilterConfig? = null
    private var isActive = false
    private lateinit var logger: Logger

    override fun init(filterConfig: FilterConfig?) {
        logger = LoggerImpl()
        config = filterConfig

        isActive = config?.getInitParameter("active")?.toUpperCase() == "TRUE"
    }

    override fun destroy() {
        config = null
    }

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        if (!isActive) {
            chain.doFilter(request, response)
            return
        }

        logAttributes(request)

        val login = request.getParameter("login")
        val password = request.getParameter("password")
        logger.out("login=$login, password=$password")

        if (login == "SanyaLogin" && password == "SanyaPassword") {
            chain.doFilter(request, response)
        } else {
            val errorMessage = "Wrong credentials for '$login'"
            (response as HttpServletResponse).sendRedirect("/register_user?message=$errorMessage")
        }
    }

    private fun logAttributes(request: ServletRequest) {
        logger.out("attributeNames:")
        for((key, values) in request.parameterMap) {
            for (value in values) {
                logger.out("key=$key, value=$value")
            }
        }
    }

}