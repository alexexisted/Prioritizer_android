package alexa.diamant.prioritizer2.dev.viewModel

data class EditTaskUIState(

    val taskId: Int = 0,
    val errorMessage: String? = null,
    val taskName: String = "",
    val taskDescription: String = "",
    val deadline: String = "",
    val estimatedHours: Int = 1,
    val urgency: Int = 1,
    val importance: Int = 1,
    val priority: Int = 1


)
