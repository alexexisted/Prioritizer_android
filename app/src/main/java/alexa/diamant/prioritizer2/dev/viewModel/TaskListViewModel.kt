package alexa.diamant.prioritizer2.dev.viewModel

import alexa.diamant.prioritizer2.dev.model.Task
import alexa.diamant.prioritizer2.dev.model.toTask
import alexa.diamant.prioritizer2.dev.repository.TaskRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.alexa.dev.utils.execute
import org.alexa.dev.viewModel.TaskListUiState
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val repository: TaskRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(TaskListUiState())
    val state = _uiState.asStateFlow()

    fun loadTasks() {
        viewModelScope.execute(
            source = {
                repository.getAllTasks()
            },
            onSuccess = { tasks ->
                _uiState.update {
                    it.copy(
                        tasks = tasks.map { task -> task.toTask() }
                    )
                }
            }
        )
    }

}