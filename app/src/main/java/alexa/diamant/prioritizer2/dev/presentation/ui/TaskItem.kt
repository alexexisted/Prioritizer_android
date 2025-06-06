package alexa.diamant.prioritizer2.dev.presentation.ui

import alexa.diamant.prioritizer2.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import alexa.diamant.prioritizer2.dev.domain.model.Task

@Composable
@PreviewParameter(TaskPreviewProvider::class)
fun TaskItem(task: Task,
             navController: NavController,
             onCheckedTask: (Boolean) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                brush = SolidColor(Color.Black),
                shape = RoundedCornerShape(12.dp)
            )
            .background(color = Color.White),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(5.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = task.isDone,
                    onCheckedChange = onCheckedTask
                )

                Column(modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp)) {
                    Text(
                        text = task.name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(modifier = Modifier.padding(horizontal = 10.dp)) {
                        Text(
                            text = "Deadline: ${task.deadline}",
                            textDecoration = TextDecoration.Underline
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Priority: ${task.priority}",
                            color = Color.Red,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }
            }

            IconButton(
                onClick = { navController.navigate("task_detail/${task.id}") },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_arrow_right),
                    contentDescription = "Edit Task",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}


class TaskPreviewProvider : PreviewParameterProvider<Task> {
    override val values = sequenceOf(
        Task(
            id = 1,
            name = "Mock Task",
            description = "This is a preview task.",
            deadline = "2025-06-01",
            estimatedHours = 2,
            urgency = 4,
            importance = 5,
            priority = 5,
            isDone = false
        )
    )
}

