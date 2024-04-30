package com.example.myapplication

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatImageView

class RoundImageView : AppCompatImageView{
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    private val path: Path = Path().apply {

    }

    var radius: Int = 0
    init {
        radius = dpToPx(10f)
    }

    override fun onDraw(canvas: Canvas) {
//        path.moveTo(0f, 0f)
//        path.lineTo(0f, height.toFloat())
//        path.moveTo(radius.toFloat(), height.toFloat())
//        path.quadTo(RectF(0f, (height - radius).toFloat(), radius.toFloat() * 2, height.toFloat() + radius), 360f, 0f)
//        path.lineTo((width - radius).toFloat(), (height - radius).toFloat())
//        path.arcTo(RectF((width - radius * 2).toFloat(), (height - radius * 2).toFloat(), width.toFloat(), height.toFloat()), 90f, 270f)
//        path.lineTo(width.toFloat(), 0f)
//        path.lineTo(0f, 0f)
//        canvas?.clipPath(path)
        super.onDraw(canvas)
    }

    private fun dpToPx(dp: Float) = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics()))

}