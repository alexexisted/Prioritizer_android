package alexa.diamant.prioritizer2.dev.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String?,
    val deadline: String,
    val estimatedHours: Int,
    val urgency: Int,
    val importance: Int,
    val priority: Int,
    val isDone: Boolean
)
