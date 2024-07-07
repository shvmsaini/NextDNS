package io.github.shvmsaini.nextdns

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.github.shvmsaini.nextdns.nav.Action
import io.github.shvmsaini.nextdns.nav.Destinations
import io.github.shvmsaini.nextdns.nav.Screen
import io.github.shvmsaini.nextdns.ui.InputTextDialog
import io.github.shvmsaini.nextdns.ui.denylist.DenylistViewModel
import io.github.shvmsaini.nextdns.ui.home.Home
import io.github.shvmsaini.nextdns.ui.logs.LogsViewModel
import io.github.shvmsaini.nextdns.ui.theme.NextDNSTheme
import io.github.shvmsaini.nextdns.ui.view.DenylistScreen
import io.github.shvmsaini.nextdns.ui.view.LogsScreen
import io.github.shvmsaini.nextdns.utils.Theme

const val TAG = "NavCompose"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavCompose(
    theme: Theme
) {
    val items = listOf(
        Screen.Home,
        Screen.Allowlist,
        Screen.Denylist,
        Screen.Logs
    )

    val context = LocalContext.current
    val navController = rememberNavController()
    val currentScreen =
        navController.currentBackStackEntryFlow.collectAsState(initial = navController.currentBackStackEntry)
    val actions = remember(navController) { Action(navController) }
    val showDialog = remember { mutableStateOf(false) }

    NextDNSTheme(
        theme = theme,
    ) {
        if (showDialog.value) {
            showInputTextDialog(showDialog, currentScreen.value?.destination?.route)
        }

        Scaffold(
            bottomBar = {
                NavigationBar {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    items.forEach { screen ->
                        NavigationBarItem(
                            label = { Text(text = stringResource(id = screen.resourceId)) },
                            selected = currentDestination?.route.equals(screen.route, true),
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = when (screen) {
                                        Screen.Home -> Icons.Default.Home
                                        Screen.Logs -> Icons.Filled.List
                                        Screen.Allowlist -> Icons.Default.Check
                                        else -> Icons.Default.Clear
                                    },
                                    contentDescription = null,
                                )
                            })
                    }
                }
            },
            floatingActionButton = {
                when (currentScreen.value?.destination?.route) {
                    Destinations.Denylist, Destinations.Allowlist -> {
                        AnimatedVisibility(
                            visible = true
                        ) {
                            FloatingActionButton(
                                onClick = {
                                    showDialog.value = true
                                },
                            ) {
                                Icon(Icons.Filled.Add, contentDescription = "Add")
                            }
                        }
                    }
                }
            },
        ) { innerPadding ->
            val modifier = Modifier.padding(innerPadding)

            NavHost(navController = navController, startDestination = "home", modifier = modifier) {
                composable(Destinations.Home) {
                    Home(modifier = Modifier) {
                        val intent = Intent(context, SettingsActivity::class.java)
                        context.startActivity(intent)
                    }
                }

                composable(Destinations.Allowlist) {
                    DenylistScreen(
                        isAllowlist = true,
                        modifier = Modifier,
                        id = "982c83",
                        viewModel = hiltViewModel()
                    )
                }

                composable(Destinations.Denylist) {
                    DenylistScreen(
                        isAllowlist = false,
                        modifier = Modifier,
                        id = "982c83",
                        viewModel = hiltViewModel<DenylistViewModel>()
                    )
                }

                composable(Destinations.Logs) {
                    LogsScreen(
                        modifier = Modifier,
                        id = "982c83",
                        viewModel = hiltViewModel<LogsViewModel>()
                    )
                }
            }
        }
    }
}

@Composable
fun showInputTextDialog(showDialog: MutableState<Boolean>, route: String?) {
    val title = "Add a url to ${if (route == Destinations.Allowlist) "Allow" else "Deny"}list"
    val label= "Domain Name"

    InputTextDialog(title = title, label = label, showDialog = true, onDismiss = {
        showDialog.value = false
    }) {
        showDialog.value = false
    }
}