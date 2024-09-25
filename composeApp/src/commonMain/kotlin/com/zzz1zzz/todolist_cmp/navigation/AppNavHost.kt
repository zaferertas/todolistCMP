package com.zzz1zzz.todolist_cmp.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.zzz1zzz.todolist_cmp.ui.addtask.AddTaskRoute
import com.zzz1zzz.todolist_cmp.ui.main.MainRoute
import com.zzz1zzz.todolist_cmp.ui.taskdetails.TaskDetailsRoute


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Route.Main
    ) {
        composable<Route.Main>() {
            MainRoute(
                modifier = modifier,
                onAddTaskClick = {
                    navController.navigate(
                        Route.AddTask
                    )
                },
                onTaskClick = {
                    navController.navigate(
                        Route.TaskDetails(taskId = it)
                    )
                },
            )
        }

        composable<Route.AddTask>(
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down) },
        ) {
            AddTaskRoute(
                modifier = modifier,
                onBackClick = navController::popBackStack,
                onDone = navController::popBackStack
            )
        }

        composable<Route.TaskDetails>(
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right) },
        ) { backStackEntry ->
            val taskId = backStackEntry.toRoute<Route.TaskDetails>().taskId
            TaskDetailsRoute(
                taskId = taskId,
                modifier = modifier,
                onBackClick = navController::popBackStack
            )
        }
    }
}