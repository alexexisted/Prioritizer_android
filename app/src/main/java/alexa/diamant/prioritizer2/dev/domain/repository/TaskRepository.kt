package alexa.diamant.prioritizer2.dev.domain.repository

import alexa.diamant.prioritizer2.dev.data.TaskDao
import alexa.diamant.prioritizer2.dev.data.TaskEntity
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {

    suspend fun getAllTasks(): List<TaskEntity> = taskDao.getAllTasks()

    suspend fun getTaskById(id: Int): TaskEntity? = taskDao.getTaskById(id)

    suspend fun insertTask(task: TaskEntity): Long = taskDao.insertTask(task)

    suspend fun updateTask(task: TaskEntity) = taskDao.updateTask(task)

    suspend fun deleteTask(task: TaskEntity) = taskDao.deleteTask(task)
}
