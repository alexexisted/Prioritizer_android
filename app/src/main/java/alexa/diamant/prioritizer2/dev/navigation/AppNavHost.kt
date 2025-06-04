package alexa.diamant.prioritizer2.dev.navigation

import alexa.diamant.prioritizer2.dev.screens.AddTaskScreen
import alexa.diamant.prioritizer2.dev.screens.TaskDetailScreen
import alexa.diamant.prioritizer2.dev.screens.TaskListScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.alexa.dev.navigation.navEnum

/**
 * Central navigation controller for android app
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(startDestination: String = navEnum.task_list.toString()) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable("task_list") {
            TaskListScreen(navController)
        }

        composable("task_detail/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
            taskId?.let {
                TaskDetailScreen(navController = navController)
            }

            composable("add_task") {
                AddTaskScreen(
//                onBack = { navController.popBackStack() }
                )
            }
        }
    }
}