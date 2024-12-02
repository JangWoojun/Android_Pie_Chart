package com.example.androidpiechart

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class PieChartView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var colors: List<Int> = listOf(Color.RED, Color.YELLOW, Color.BLUE)
    private var values: List<Int> = listOf(3, 2, 1)
    private val rectF = RectF()

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.PieChartView, 0, 0).apply {
            try {
                val percentagesResId = getResourceId(R.styleable.PieChartView_percentages, 0)
                if (percentagesResId != 0) {
                    val percentagesArray = resources.getIntArray(percentagesResId)
                    values = percentagesArray.toList()
                }

                val colorsResId = getResourceId(R.styleable.PieChartView_colors, 0)
                if (colorsResId != 0) {
                    val colorsArray = resources.getIntArray(colorsResId)
                    colors = colorsArray.toList()
                }
            } finally {
                recycle()
            }
        }
    }

    fun setData(values: List<Int>, colors: List<Int>) {
        this.values = values
        this.colors = colors
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF.set(0f, 0f, w.toFloat(), h.toFloat()) // rectF 재사용
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val total = values.sum().toFloat()
        var startAngle = 0f
        for (i in values.indices) {
            val sweepAngle = 360 * (values[i] / total)
            paint.color = colors[i]
            canvas.drawArc(rectF, startAngle, sweepAngle, true, paint)
            startAngle += sweepAngle
        }
    }
}
