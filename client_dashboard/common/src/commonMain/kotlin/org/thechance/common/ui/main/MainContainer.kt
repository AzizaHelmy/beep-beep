package org.thechance.common.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.beepbeep.designSystem.ui.theme.BpTheme
import com.beepbeep.designSystem.ui.theme.Theme
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.thechance.common.ui.composables.scaffold.BpSideBarItem
import org.thechance.common.ui.composables.scaffold.DashBoardScaffold
import org.thechance.common.ui.composables.scaffold.DashboardAppbar
import org.thechance.common.ui.composables.scaffold.DashboardSideBar
import org.thechance.common.ui.login.LoginScreenModel

object MainContainer : Screen , KoinComponent {

    private val screenModel: MainScreenModel by inject()
    @Composable
    override fun Content() {
        TabNavigator(OverviewTab) {
            val tabNavigator = LocalTabNavigator.current
            BpTheme {
                DashBoardScaffold(
                    appbar = {
                        DashboardAppbar(tabNavigator.current.options.title, "asia", { false })
                    },
                    sideBar = {
                        DashboardSideBar(
                            currentItem = tabNavigator.current.options.index.toInt()
                        ) { sideBarWidth, mainMenuIsExpanded, itemHeight ->

                            TabNavigationItem(
                                tab = OverviewTab,
                                selectedIconResource = "ic_overview_fill.svg",
                                unSelectedIconResource = "ic_overview_empty.svg",
                                sideBarWidth = sideBarWidth,
                                mainMenuIsExpanded = mainMenuIsExpanded,
                                modifier = Modifier.onGloballyPositioned {
                                    itemHeight(it.boundsInParent().height)
                                }
                            )
                            TabNavigationItem(
                                tab = TaxisTab,
                                selectedIconResource = "ic_taxi_fill.svg",
                                unSelectedIconResource = "ic_taxi_empty.xml",
                                sideBarWidth = sideBarWidth,
                                mainMenuIsExpanded = mainMenuIsExpanded,
                                modifier = Modifier.onGloballyPositioned {
                                    itemHeight(it.boundsInParent().height)
                                }
                            )
                            TabNavigationItem(
                                tab = RestaurantsTab,
                                selectedIconResource = "ic_restaurant_fill.svg",
                                unSelectedIconResource = "ic_restaurant_empty.svg",
                                sideBarWidth = sideBarWidth,
                                mainMenuIsExpanded = mainMenuIsExpanded,
                                modifier = Modifier.onGloballyPositioned {
                                    itemHeight(it.boundsInParent().height)
                                }
                            )
                            TabNavigationItem(
                                tab = UsersTab,
                                selectedIconResource = "ic_users_fill.svg",
                                unSelectedIconResource = "ic_users_empty.svg",
                                sideBarWidth = sideBarWidth,
                                mainMenuIsExpanded = mainMenuIsExpanded,
                                modifier = Modifier.onGloballyPositioned {
                                    itemHeight(it.boundsInParent().height)
                                }
                            )
                        }
                    },
                    content = {
                        Box(Modifier.background(Theme.colors.surface).padding(it)) {
                            CurrentTab()
                        }
                    },
                )
            }
        }
    }


}


@Composable
fun ColumnScope.TabNavigationItem(
    tab: Tab,
    selectedIconResource: String,
    unSelectedIconResource: String,
    sideBarWidth: Dp,
    mainMenuIsExpanded: Boolean,
    modifier: Modifier = Modifier,
) {
    val tabNavigator = LocalTabNavigator.current
    BpSideBarItem(
        onClick = { tabNavigator.current = tab },
        isSelected = tabNavigator.current == tab,
        itemWidth = 24.dp,
        label = tab.options.title,
        selectedIconResource = selectedIconResource,
        unSelectedIconResource = unSelectedIconResource,
        sideBarWidth = sideBarWidth,
        mainMenuIsExpanded = mainMenuIsExpanded,
        modifier = modifier
    )
}

