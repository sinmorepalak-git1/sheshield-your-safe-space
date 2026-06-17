package com.example.sheshield.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow as composeShadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A wrapper for Modifier.shadow to provide easier access and consistent defaults.
 */
fun Modifier.shadow(
    elevation: Dp,
    shape: Shape = RoundedCornerShape(0.dp),
    clip: Boolean = elevation > 0.dp
): Modifier = this.composeShadow(
    elevation = elevation,
    shape = shape,
    clip = clip
)
