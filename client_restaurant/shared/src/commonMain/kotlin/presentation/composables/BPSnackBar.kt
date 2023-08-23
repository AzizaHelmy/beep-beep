package presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.beepbeep.designSystem.ui.theme.Theme
import org.jetbrains.compose.resources.ExperimentalResourceApi

@Composable
fun BPSnackBar(
    icon: Painter,
    modifier: Modifier = Modifier,
    iconBackgroundColor: Color,
    backgroundColor: Color = Theme.colors.background,
    content: @Composable () -> Unit,
) {
    Snackbar(
        modifier = modifier.padding(Theme.dimens.space16),
        shape = RoundedCornerShape(Theme.radius.medium),
        containerColor = backgroundColor
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Theme.dimens.space8)
        ) {
            Icon(
                modifier = Modifier.background(
                    color = iconBackgroundColor,
                    shape = RoundedCornerShape(Theme.radius.medium)
                ).padding(Theme.dimens.space8),
                painter = icon,
                contentDescription = null,
            )
            content()
        }
    }
}