package br.edu.ifpe.alvarium.ui.components.details

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.math.max

@Composable
fun CoinChartWithGrid(
    points: List<Pair<Long, Double>>,
    days: Int
) {

    if (points.isEmpty()) {
        Text("Carregando gráfico...", color = Color.White, modifier = Modifier.padding(16.dp))
        return
    }

    val maxY = points.maxOf { it.second }.toFloat()
    val minY = points.minOf { it.second }.toFloat()
    val rangeY = max(maxY - minY, 1f)

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val stepX = if (points.size > 1) size.width / (points.size - 1) else size.width / 2f
        val gridLines = 6
        val stepY = size.height / gridLines

        val labelSize = 24f

        // GRID + Valores no eixo Y
        repeat(gridLines + 1) { i ->
            val y = stepY * i

            drawLine(
                color = Color.White.copy(alpha = 0.15f),
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = 1.dp.toPx()
            )

            val value = maxY - ((rangeY / gridLines) * i)
            drawContext.canvas.nativeCanvas.drawText(
                String.format("%.0f", value),
                0f,
                y - 5f,
                Paint().apply {
                    color = android.graphics.Color.WHITE
                    textSize = labelSize
                }
            )
        }

        val formatter = when (days) {
            1 -> SimpleDateFormat("HH:mm")
            7 -> SimpleDateFormat("dd/MM")
            30 -> SimpleDateFormat("dd/MM")
            else -> SimpleDateFormat("dd/MM")
        }

        val sizePoints = points.size
        val labelsCount = 6
        val stepLabel = max(1, sizePoints / labelsCount)

        // Labels do eixo X
        points.forEachIndexed { i, pair ->
            if (i % stepLabel == 0) {
                val x = i * stepX
                val text = formatter.format(Date(pair.first))

                drawContext.canvas.nativeCanvas.drawText(
                    text,
                    x - 20f,
                    size.height + 25f,
                    Paint().apply {
                        color = android.graphics.Color.LTGRAY
                        textSize = labelSize * 0.8f
                        isAntiAlias = true
                    }
                )
            }
        }

        // Linha do gráfico
        val path = Path()
        points.forEachIndexed { i, pair ->
            val x = i * stepX
            val y = size.height - ((pair.second.toFloat() - minY) / rangeY * size.height)

            if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
        }

        drawPath(
            path = path,
            color = Color(0xFFFF5252),
            style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round)
        )
    }
}