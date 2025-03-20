package com.example.expensetrackerapp.expense_feature.presentation.user

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp


@Composable
fun ExpenseChart(balance: Double, spent: Double) {
    val total = balance + spent
    val spentPercentage = if (total > 0) spent / total else 0f
    val balancePercentage = 1f - spentPercentage.toFloat()

    // Animated values for smooth transitions
    val animatedSpentPercentage by animateFloatAsState(
        targetValue = spentPercentage.toFloat(),
        animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing),
        label = "Spent Animation"
    )

    val animatedBalancePercentage by animateFloatAsState(
        targetValue = balancePercentage,
        animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing),
        label = "Balance Animation"
    )

    Canvas(modifier = Modifier.size(120.dp)) {
        val size = size.minDimension
        val strokeWidth = size * 0.25f
        val gapAngle = 8f
        val startAngle = -90f

        drawArc(
            color = Color(0xFF8BC34A),
            startAngle = startAngle,
            sweepAngle = animatedBalancePercentage * 360f - gapAngle,
            useCenter = false,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Butt),
            size = Size(size, size),
            topLeft = Offset((this.size.width - size) / 2, (this.size.height - size) / 2)
        )

        drawArc(
            color = Color(0xFFFFAB91),
            startAngle = startAngle + animatedBalancePercentage * 360f,
            sweepAngle = animatedSpentPercentage * 360f - gapAngle,
            useCenter = false,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Butt),
            size = Size(size, size),
            topLeft = Offset((this.size.width - size) / 2, (this.size.height - size) / 2)
        )
    }
}

