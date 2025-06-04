package org.alexa.dev.viewModel

import alexa.diamant.prioritizer2.dev.model.Task

data class TaskListUiState(
    val tasks: List<Task> = emptyList(),

    val errorMessage: String? = null
)
