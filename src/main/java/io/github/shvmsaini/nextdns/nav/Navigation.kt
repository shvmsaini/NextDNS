package io.github.shvmsaini.nextdns.nav

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavController
import io.github.shvmsaini.nextdns.R
import io.github.shvmsaini.nextdns.nav.Destinations.Allowlist
import io.github.shvmsaini.nextdns.nav.Destinations.Denylist
import io.github.shvmsaini.nextdns.nav.Destinations.Home
import io.github.shvmsaini.nextdns.nav.Destinations.Logs

object Destinations {
    const val Home = "home"
    const val Denylist = "denylist"
    const val Allowlist = "allowlist"
    const val Logs = "logs"
}

class Action(navController: NavController) {
    val home: () -> Unit = { navController.navigate(Home) }
    val logs: () -> Unit = { navController.navigate(Logs) }
    val allowlist: () -> Unit = { navController.navigate(Allowlist) }
    val denylist: () -> Unit = { navController.navigate(Denylist) }
}

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val drawableRes: Int
) {
    object Home : Screen("home", R.string.home, R.drawable.ic_launcher_background)
    object Logs : Screen("logs", R.string.logs, R.drawable.ic_launcher_background)
    object Allowlist : Screen("allowlist", R.string.allowlist, R.drawable.ic_launcher_background)
    object Denylist : Screen("denylist", R.string.denylist, R.drawable.ic_launcher_background)
}