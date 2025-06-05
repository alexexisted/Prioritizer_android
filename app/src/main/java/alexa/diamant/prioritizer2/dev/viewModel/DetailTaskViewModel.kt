package alexa.diamant.prioritizer2.dev.viewModel

import alexa.diamant.prioritizer2.dev.repository.TaskRepository
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.alexa.dev.utils.execute
import javax.inject.Inject

@HiltViewModel
class DetailTaskViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: TaskRepository
) : ViewModel() {


    private val _uiState = MutableStateFlow(EditTaskUIState())
    val uiState = _uiState.asStateFlow()

    private val taskId: Int? = savedStateHandle.get<Int>("taskId")

    init {
        taskId?.let {
            loadTaskById(taskId)
        }

    }

    private fun loadTaskById(id: Int) {
        viewModelScope.execute(
            source = {
                repository.getTaskById(id)
            },
            onSuccess = { task ->
                if (task != null) {
                    _uiState.update {
                        it.copy(
                            taskName = task.name,
                            taskDescription = task.description ?: "no description yet",
                            deadline = task.deadline,
                            estimatedHours = task.estimatedHours,
                            urgency = task.urgency,
                            importance = task.importance,
                            priority = task.priority
                        )
                    }
                }
            }
        )
    }
}