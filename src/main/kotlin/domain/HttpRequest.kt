package domain

inline class Resource(val get: String)
inline class Body(val get: String)

enum class ActionType { GET, POST, UPDATE, DELETE }

data class HttpRequest(
    val type: ActionType,
    val resource: Resource,
    val headers: List<String>,
    val body: Body
)