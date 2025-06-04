package alexa.diamant.prioritizer2.dev.viewModel

import alexa.diamant.prioritizer2.dev.model.Task
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(): ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(mockTasks())
    val tasks: StateFlow<List<Task>> = _tasks

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

}