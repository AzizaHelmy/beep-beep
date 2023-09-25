package org.thechance.common.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
//import androidx.compose.ui.res.painterResource
import com.beepbeep.designSystem.ui.theme.Theme
import org.thechance.common.presentation.util.kms
import org.thechance.common.presentation.resources.Resources

@Composable
fun SnackBar(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(color = Theme.colors.surface)
            .width(512.kms)
            .border(
                width = 1.kms, color = Theme.colors.divider,
                shape = RoundedCornerShape(Theme.radius.medium),
            )
    ) {
        content()
        Spacer(modifier = Modifier.weight(1f))
//        Image(
//            painter = painterResource(Resources.Drawable.close),
//            contentDescription = null,
//            colorFilter = ColorFilter.tint(color = Theme.colors.contentPrimary),
//            modifier = Modifier.padding(Theme.dimens.space16).clickable(
//                onClick = onDismiss
//            )
//        )
    }
}

//Row(
//horizontalArrangement = Arrangement.SpaceBetween,
//verticalAlignment = Alignment.CenterVertically,
//modifier = modifier
//.background(color = Theme.colors.surface)
//.width(512.dp)
//.border(
//width = 1.dp, color = Theme.colors.divider,
//shape = RoundedCornerShape(Theme.radius.medium),
//)
//) {
//    Image(
//        painter = painterResource("ic_download_mark.svg"),
//        contentDescription = null,
//        colorFilter = ColorFilter.tint(color = Theme.colors.success),
//        modifier = Modifier.padding(Theme.dimens.space16)
//    )
//    Text(
//        text = "Your file download was successful.",
//        style = Theme.typography.titleMedium,
//        color = Theme.colors.success,
//    )
//    Spacer(modifier = Modifier.weight(1f))
//    Image(
//        painter = painterResource("ic_close.svg"),
//        contentDescription = null,
//        colorFilter = ColorFilter.tint(color = Theme.colors.contentPrimary),
//        modifier = Modifier.padding(Theme.dimens.space16).clickable(
//            onClick = onDismiss
//        )
//    )
//
//}