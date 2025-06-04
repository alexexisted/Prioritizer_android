package alexa.diamant.prioritizer2.dev.screens

import alexa.diamant.prioritizer2.dev.viewModel.SharedTaskViewModel
import android.app.DatePickerDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    navController: NavController,
    viewModel: SharedTaskViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    var name = state.taskName
    var description = state.taskDescription
    var deadline = LocalDate.parse(state.deadline)

    val context = LocalContext.current
    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                deadline = LocalDate.of(year, month + 1, dayOfMonth)
            },
            deadline.year,
            deadline.monthValue - 1,
            deadline.dayOfMonth
        )
    }

    var estimatedHours = state.estimatedHours
    var urgency = state.urgency
    var importance = state.importance

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text("Tasks")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
            HorizontalDivider(
                modifier = Modifier,
                thickness = 5.dp,
                color = Color.Black
            )
        },
    ) { innerPadding ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding),
            color = Color.White
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Task Name") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text("Description") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Deadline: $deadline",
                        fontSize = 20.sp,
                        modifier = Modifier
                            .clickable { datePickerDialog.show() }
                            .padding(vertical = 8.dp),
                        textDecoration = TextDecoration.Underline
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    LabeledNumberSelector("Estimated hours", 1..10, estimatedHours) {
                        estimatedHours = it
                    }

                    LabeledNumberSelector("Urgency", 1..5, urgency) {
                        urgency = it
                    }

                    LabeledNumberSelector("Importance", 1..5, importance) {
                        importance = it
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        OutlinedButton(
                            onClick = { /* TODO: Delete logic */ },
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = Color.Red
                            ),
                            border = BorderStroke(1.dp, Color.Red)
                        ) {
                            Text("Delete")
                        }

                        OutlinedButton(
                            onClick = { /* TODO: Save logic */ },
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = Color.Blue
                            ),
                            border = BorderStroke(1.dp, Color.Blue)
                        ) {
                            Text("Save")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LabeledNumberSelector(
    label: String,
    range: IntRange,
    selected: Int,
    onSelect: (Int) -> Unit
) {
    Column {
        Text(
            text = label,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyRow {
            items(range.toList()) { number ->
                val isSelected = number == selected
                OutlinedButton(
                    onClick = { onSelect(number) },
                    border = BorderStroke(1.dp, Color.Black),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (isSelected) Color.LightGray else Color.Transparent
                    ),
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(50.dp)
                ) {
                    Text(
                        text = number.toString(),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.Black
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}


