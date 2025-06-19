package alexa.diamant.prioritizer2.dev.presentation.create_task

import alexa.diamant.prioritizer2.dev.data.TaskEntity
import alexa.diamant.prioritizer2.dev.domain.repository.TaskRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.alexa.dev.utils.execute
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class CreateTaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateTaskUIState())
    val uiState = _uiState.asStateFlow()

    private val _uiAction = MutableSharedFlow<CreateTaskUiAction>()
    val uiAction: SharedFlow<CreateTaskUiAction> = _uiAction.asSharedFlow()

    init {
        setNowLocalDate()
    }

    /**
     * Main Logic
     */

    private fun calculatePriority(deadline: String, urgency: Int, importance: Int): Int {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val deadlineDate = LocalDate.parse(deadline, formatter)

        val daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), deadlineDate).coerceAtLeast(0)
        val deadlineScore = (1.0 / (daysLeft + 1)).coerceIn(0.0, 1.0)

        val urgencyScore = urgency.coerceIn(1, 5) / 5.0
        val importanceScore = importance.coerceIn(1, 5) / 5.0

        val raw = (
                0.4 * deadlineScore +
                        0.3 * urgencyScore +
                        0.3 * importanceScore
                ) * 10

        return raw.roundToInt().coerceIn(1, 10)
    }

    private fun setPriority() {
        _uiState.update {
            it.copy(
                priority = calculatePriority(
                    _uiState.value.deadline,
                    _uiState.value.urgency,
                    _uiState.value.importance
                )
            )
        }
    }

    fun createTask() {
        if (!validateData()) {
            viewModelScope.launch {
                _uiAction.emit(CreateTaskUiAction.ShowError("Date can't be in the past and all fields must be filled!"))
            }
        } else {
            setPriority()
            viewModelScope.execute(
                source = {
                    repository.insertTask(
                        TaskEntity(
                            name = _uiState.value.taskName,
                            description = _uiState.value.taskDescription,
                            deadline = _uiState.value.deadline,
                            estimatedHours = _uiState.value.estimatedHours,
                            urgency = _uiState.value.urgency,
                            importance = _uiState.value.importance,
                            priority = _uiState.value.priority,
                            isDone = false
                        )
                    )
                },
                onSuccess = {
                    viewModelScope.launch {
                        _uiAction.emit(CreateTaskUiAction.SuccessCreation)
                    }
                },
                onError = {
                    viewModelScope.launch {
                        _uiAction.emit(CreateTaskUiAction.ShowError("Task can not be created! Please try again!"))
                    }

                }
            )
        }
    }

    private fun isDeadlineValid(deadline: String): Boolean {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return try {
            val deadlineDate = LocalDate.parse(deadline, formatter)
            val today = LocalDate.now()
            !deadlineDate.isBefore(today)
        } catch (_: Exception) {
            false
        }
    }

    private fun validateData(): Boolean {
        return (_uiState.value.taskName.isNotBlank() &&
                isDeadlineValid(_uiState.value.deadline) &&
                _uiState.value.taskDescription.isNotBlank())
    }


    /**
     * UI setters part
     */

    private fun setNowLocalDate() {
        val date = LocalDate.now().toString()
        _uiState.update {
            it.copy(
                deadline = date
            )
        }
    }

    fun changeDeadline(newValue: String) {
        _uiState.update {
            it.copy(
                deadline = newValue
            )
        }
    }

    fun changeImportance(newValue: Int) {
        _uiState.update {
            it.copy(
                importance = newValue
            )
        }
    }

    fun changeUrgency(newValue: Int) {
        _uiState.update {
            it.copy(
                urgency = newValue
            )
        }
    }

    fun changeEstimatedTime(newValue: Int) {
        _uiState.update {
            it.copy(
                estimatedHours = newValue
            )
        }
    }

    fun updateName(text: String) {
        _uiState.update {
            it.copy(
                taskName = text
            )
        }
    }

    fun updateDescription(text: String) {
        _uiState.update {
            it.copy(
                taskDescription = text
            )
        }
    }
}