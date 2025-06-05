package alexa.diamant.prioritizer2.dev.model

import alexa.diamant.prioritizer2.dev.db.TaskEntity

data class Task(
    val id: Int,
    val name: String,
    val description: String?,
    val deadline: String,
    val estimatedHours: Int,
    val urgency: Int,
    val importance: Int,
    val priority: Int,
    val isDone: Boolean
)

fun TaskEntity.toTask() = Task(
    id = id,
    name = name,
    description = description,
    deadline = deadline,
    estimatedHours = estimatedHours,
    urgency = urgency,
    importance = importance,
    priority = priority,
    isDone = isDone
)

