package alexa.diamant.prioritizer2.dev.presentation.state

sealed interface CreateTaskUiAction {
    object SuccessCreation : CreateTaskUiAction
    data class ShowError(val message: String) : CreateTaskUiAction
}
