package alexa.diamant.prioritizer2.dev.viewModel

data class CreateTaskUIState(
    val errorMessage: String? = null,
    var taskName: String = "",
    var taskDescription: String = "",
    val deadline: String = "2025-06-06",
    var estimatedHours: Int = 1,
    var urgency: Int = 1,
    var importance: Int = 1,
    val priority: Int = 1
)
