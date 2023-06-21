
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.motogpcompose.Navigation.NavigationItem
import com.example.motogpcompose.Navigation.Screen
import com.example.motogpcompose.R
import com.example.motogpcompose.screen.HomeScreen
import com.example.motogpcompose.screen.detail.DetailScreen
import com.example.motogpcompose.screen.profile.ProfileScreen
import com.example.motogpcompose.ui.theme.MotogpComposeTheme

@Composable
fun MotogpHome(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = {
            BottomBar(navController)
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { heroId->
                        navController.navigate(Screen.DetailHero.createRoute(heroId))
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    name = " Fadlan Prabaswara",
                    email =" Prabaswarafadlan@gmail.com")
            }
            composable(
                route = Screen.DetailHero.route,
                arguments = listOf(navArgument("heroId"){type = NavType.LongType}),
            ){
                val id = it.arguments?.getLong("heroId")?:-1L
                DetailScreen(
                    heroId =id,
                    navigateBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )
        BottomNavigation {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = { Text(item.title) },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MotogpPreview(){
    MotogpComposeTheme{
        MotogpHome()
    }
}

