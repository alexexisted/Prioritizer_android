package alexa.diamant.prioritizer2.dev.model

data class Task(
    val id: Int,
    val name: String,
    val description: String?,
    val deadline: String,
    val estimatedHours: Int,
    val urgency: Int,
    val importance: Int,
    val priority: Int,
    val isDone: Boolean
)
