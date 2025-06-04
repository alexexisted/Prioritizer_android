package alexa.diamant.prioritizer2.dev.viewModel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import alexa.diamant.prioritizer2.dev.model.Task
import org.alexa.dev.viewModel.EditTaskUIState
import javax.inject.Inject

@HiltViewModel
class SharedTaskViewModel @Inject constructor() : ViewModel() {


    private val _uiState = MutableStateFlow(EditTaskUIState())
    val uiState = _uiState.asStateFlow()

    init {
        loadTaskById(uiState.value.chosenTaskId)
    }
    private fun mockTasks(): List<Task> = listOf(
        Task(
            id = 1,
            name = "Write report",
            description = "End of month finance report",
            deadline = "2025-06-01",
            estimatedHours = 3,
            urgency = 4,
            importance = 5,
            priority = 5,
            isDone = false
        ),
        Task(
            id = 2,
            name = "Call supplier",
            description = null,
            deadline = "2025-06-03",
            estimatedHours = 1,
            urgency = 2,
            importance = 3,
            priority = 3,
            isDone = false
        ),
        Task(
            id = 3,
            name = "Eat",
            description = null,
            deadline = "2025-06-03",
            estimatedHours = 1,
            urgency = 2,
            importance = 3,
            priority = 3,
            isDone = false
        ),
        Task(
            id = 4,
            name = "Jrat'",
            description = null,
            deadline = "2025-06-03",
            estimatedHours = 1,
            urgency = 2,
            importance = 3,
            priority = 3,
            isDone = false
        ),
        Task(
            id = 5,
            name = "AAAAAAAAA",
            description = null,
            deadline = "2025-06-03",
            estimatedHours = 1,
            urgency = 2,
            importance = 3,
            priority = 3,
            isDone = false
        ),
        Task(
            id = 1,
            name = "Write report",
            description = "End of month finance report",
            deadline = "2025-06-01",
            estimatedHours = 3,
            urgency = 4,
            importance = 5,
            priority = 5,
            isDone = false
        ),
        Task(
            id = 2,
            name = "Call supplier",
            description = null,
            deadline = "2025-06-03",
            estimatedHours = 1,
            urgency = 2,
            importance = 3,
            priority = 3,
            isDone = false
        ),
        Task(
            id = 3,
            name = "Eat",
            description = null,
            deadline = "2025-06-03",
            estimatedHours = 1,
            urgency = 2,
            importance = 3,
            priority = 3,
            isDone = false
        ),
        Task(
            id = 4,
            name = "Jrat'",
            description = null,
            deadline = "2025-06-03",
            estimatedHours = 1,
            urgency = 2,
            importance = 3,
            priority = 3,
            isDone = false
        ),
        Task(
            id = 5,
            name = "AAAAAAAAA",
            description = null,
            deadline = "2025-06-03",
            estimatedHours = 1,
            urgency = 2,
            importance = 3,
            priority = 3,
            isDone = false
        )
    )

    private fun loadTaskById(id: Int) {
        val task = mockTasks().find { it.id == id }
        task?.let {
            _uiState.update {
                it.copy(
                    taskName = task.name,
                    taskDescription = task.description
                        ?: "No description for now, but you can add it any time!",
                    deadline = task.deadline,
                    estimatedHours = task.estimatedHours,
                    urgency = task.urgency,
                    importance = task.importance
                )
            }
        }
    }
}