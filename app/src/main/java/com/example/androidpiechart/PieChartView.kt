package com.example.androidpiechart

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class PieChartView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val colors = listOf(Color.RED, Color.YELLOW, Color.BLUE)
    private val percentages = listOf(20f, 30f, 50f)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val width = width.toFloat()
        val height = height.toFloat()
        val rectF = RectF(0f, 0f, width, height)
        
        var startAngle = 0f
        for (i in percentages.indices) {
            paint.color = colors[i]
            val sweepAngle = 360 * (percentages[i] / 100)
            canvas.drawArc(rectF, startAngle, sweepAngle, true, paint)
            startAngle += sweepAngle
        }
    }
}
