package com.electro.fish.ui.util.extension

import androidx.compose.foundation.clickable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.scan

fun Modifier.dashedBorder(
    color: Color,
    shape: Shape,
    strokeWidth: Dp = 2.dp,
    dashLength: Dp = 4.dp,
    gapLength: Dp = 4.dp,
    cap: StrokeCap = StrokeCap.Round
) = dashedBorder(brush = SolidColor(color), shape, strokeWidth, dashLength, gapLength, cap)

fun Modifier.dashedBorder(
    brush: Brush,
    shape: Shape,
    strokeWidth: Dp = 2.dp,
    dashLength: Dp = 4.dp,
    gapLength: Dp = 4.dp,
    cap: StrokeCap = StrokeCap.Round
) = this.drawWithContent {

    val outline = shape.createOutline(size, layoutDirection, density = this)

    val dashedStroke = Stroke(
        cap = cap,
        width = strokeWidth.toPx(),
        pathEffect = PathEffect.dashPathEffect(
            intervals = floatArrayOf(dashLength.toPx(), gapLength.toPx())
        )
    )

    drawContent()

    drawOutline(
        outline = outline,
        style = dashedStroke,
        brush = brush
    )
}

fun Modifier.clickableWithoutIndication(onClick: () -> Unit) = this.clickable(
    onClick = onClick,
    indication = null,
    interactionSource = null
)



fun Modifier.onFocusLost(onFocusLost: () -> Unit): Modifier {
    data class FocusHistory(
        val previous: Boolean = false,
        val current: Boolean = false
    )

    return composed {
        val isFocusedState = remember { mutableStateOf(false) }
        val onFocusLostState = rememberUpdatedState(onFocusLost)

        LaunchedEffect(Unit) {
            snapshotFlow { isFocusedState.value }
                .scan(FocusHistory()) { history, next ->
                    FocusHistory(history.current, next)
                }
                .collect { (previous, current) ->
                    if (previous && !current) {
                        onFocusLostState.value()
                    }
                }
        }

        this.onFocusChanged { focusState ->
            isFocusedState.value = focusState.isFocused
        }
    }
}

