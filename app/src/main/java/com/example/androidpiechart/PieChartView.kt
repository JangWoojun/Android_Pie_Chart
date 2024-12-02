package com.example.androidpiechart

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class PieChartView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var colors: List<Int> = listOf(Color.RED, Color.YELLOW, Color.BLUE)
    private var percentages: List<Float> = listOf(20f, 30f, 50f)
    private val rectF = RectF()

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.PieChartView, 0, 0).apply {
            try {
            } finally {
                recycle()
            }
        }
    }

    fun setData(percentages: List<Float>, colors: List<Int>) {
        this.percentages = percentages
        this.colors = colors
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF.set(0f, 0f, w.toFloat(), h.toFloat())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var startAngle = 0f
        for (i in percentages.indices) {
            paint.color = colors[i]
            val sweepAngle = 360 * (percentages[i] / 100)
            canvas.drawArc(rectF, startAngle, sweepAngle, true, paint)
            startAngle += sweepAngle
        }
    }
}
