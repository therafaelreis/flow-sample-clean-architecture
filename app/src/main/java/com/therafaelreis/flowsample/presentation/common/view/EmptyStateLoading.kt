package com.therafaelreis.flowsample.presentation.common.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.therafaelreis.flowsample.R

class EmptyStateLoading(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val myPaint = Paint()
    private val rect = RectF()
    private val animator: ValueAnimator = ValueAnimator.ofFloat(1f, 0.5f)

    init {
        animator.duration = 800
        animator.addUpdateListener { alpha = it.animatedValue as Float }
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.REVERSE
        animator.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        rect.right = width.toFloat()
        rect.bottom = height.toFloat()
        myPaint.color = ResourcesCompat.getColor(resources, R.color.emptyStateColor, null)
        myPaint.style = Paint.Style.FILL
        myPaint.isAntiAlias = true

        canvas.drawRoundRect(rect, 10f, 10f, myPaint)
    }
}