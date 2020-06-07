package rs.raf.projekat2.nemanja_tesic_30_17.presentation.view.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import rs.raf.projekat2.nemanja_tesic_30_17.R
import rs.raf.projekat2.nemanja_tesic_30_17.data.model.custom.ChartData
import rs.raf.projekat2.nemanja_tesic_30_17.extensions.toPx
import timber.log.Timber

class ChartView : View {

    constructor(context: Context)
            : super(context)
    constructor(context: Context, attrs: AttributeSet?)
            : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    val chartData: MutableList<ChartData> = mutableListOf()

    private var rect: Rect = Rect()

    private val whiteStrokePaint: Paint = Paint().also {
        it.isAntiAlias = true
        it.color = ContextCompat.getColor(context, R.color.white)
        it.style = Paint.Style.STROKE
        it.strokeWidth = 4.toPx().toFloat()
    }

    private val blueFillPaint: Paint = Paint().also {
        it.isAntiAlias = true
        it.color = ContextCompat.getColor(context, R.color.chart_blue)
        it.style = Paint.Style.FILL
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Timber.e("On measure")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Timber.e("On layout")
//        val frag: Fragment = FragmentManager.findFragment(this)
//        chartColumns.observe(frag.viewLifecycleOwner, Observer {
//            invalidate()
//        })
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Timber.e("On draw")
        Timber.e("MOJA LISTICA $chartData")

        if (chartData.size == 0)
            return

        val offsetX = width / 5
        var left = 0
        val bottom = height
        var right = offsetX
        var top = 0
        var maxCount = 0

        maxCount = chartData.maxBy { it.count }?.count!!
        if (maxCount == 0) return

        chartData.forEach {
            top = height - ((height / maxCount) * it.count)
            rect.set(left, top, right, bottom)

            canvas?.drawRect(rect, blueFillPaint)
            canvas?.drawRect(rect, whiteStrokePaint)

            left += offsetX
            right += offsetX
        }
    }
}