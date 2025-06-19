package alexa.diamant.prioritizer2.dev.presentation.tasks

import alexa.diamant.prioritizer2.dev.domain.model.Task
import alexa.diamant.prioritizer2.dev.domain.model.toTask
import alexa.diamant.prioritizer2.dev.domain.repository.TaskRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.alexa.dev.utils.execute
import org.alexa.dev.viewModel.TaskListUiState
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TaskListUiState())
    val state = _uiState.asStateFlow()

    fun loadTasks() {
        viewModelScope.execute(
            source = {
                repository.getAllTasks()
            },
            onSuccess = { tasks ->
                val sortedTasks = tasks
                    .map { it.toTask() }
                    .sortedWith(
                        compareBy<Task> { it.isDone } // false (not done) comes first
                            .thenByDescending { it.priority } // higher priority first
                    )
                _uiState.update {
                    it.copy(
                        tasks = sortedTasks
                    )
                }
            }
        )
    }

    fun checkTheTask(id: Int, isDone: Boolean) {
        viewModelScope.execute(
            source = { repository.getTaskById(id) },
            onSuccess = { task ->
                viewModelScope.launch {
                    repository.updateTask(task!!.copy(isDone = isDone))
                }
            },
            onComplete = { loadTasks() }
        )
    }

}