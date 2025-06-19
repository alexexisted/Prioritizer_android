package alexa.diamant.prioritizer2.dev.navigation

import alexa.diamant.prioritizer2.dev.presentation.create_task.TaskCreateScreen
import alexa.diamant.prioritizer2.dev.presentation.edit_task.TaskDetailScreen
import alexa.diamant.prioritizer2.dev.presentation.tasks.TaskListScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.alexa.dev.navigation.navEnum

/**
 * Central navigation controller for android app
 */
@Composable
fun AppNavHost(startDestination: String = navEnum.task_list.toString()) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable("task_list") {
            TaskListScreen(navController)
        }

        composable(
            route = "task_detail/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) {
            TaskDetailScreen(navController = navController)
        }

            composable("add_task") {
                TaskCreateScreen(navController)
            }
        }
    }