package org.thechance.common.presentation.composables.scaffold

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import org.thechance.common.presentation.util.kms

@Composable
fun DashBoardScaffold(
    appbar: @Composable () -> Unit,
    sideBar: @Composable () -> Unit,
    content: @Composable (Dp) -> Unit,
) {
    Row(Modifier.fillMaxSize()) {
        sideBar()
        Column {
            appbar()
            content(40.kms)
        }
    }
}