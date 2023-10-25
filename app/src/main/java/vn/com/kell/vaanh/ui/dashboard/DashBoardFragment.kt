package vn.com.kell.vaanh.ui.dashboard

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.CombinedChart.DrawOrder
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.BubbleData
import com.github.mikephil.charting.data.BubbleDataSet
import com.github.mikephil.charting.data.BubbleEntry
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import com.github.mikephil.charting.data.CombinedData
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.ScatterData
import com.github.mikephil.charting.data.ScatterDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import kell.com.example.vaanh.databinding.FragmentDashBoardBinding
import kotlin.random.Random


class DashBoardFragment : Fragment() {


    private val count = 12
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDashBoardBinding.inflate(layoutInflater).apply {
            val layout = LinearLayout(context)
            layout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            layout.orientation = LinearLayout.VERTICAL
//            chart = CombinedChart(context)
            chart1.apply {
                description.isEnabled = false
                setBackgroundColor(Color.WHITE)
                setDrawGridBackground(false)
                setDrawBarShadow(false)
                isHighlightFullBarEnabled = false
                // draw bars behind lines
                drawOrder = arrayOf(
                    DrawOrder.BAR,
                    DrawOrder.BUBBLE,
                    DrawOrder.CANDLE,
                    DrawOrder.LINE,
                    DrawOrder.SCATTER
                )
            }

//
//            val legend = chart1.legend
//            legend.apply {
//                isWordWrapEnabled = true
//                verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
//                horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
//                orientation = Legend.LegendOrientation.HORIZONTAL
//                setDrawInside(false)
//            }


            val rightAxis = chart1.axisRight
            rightAxis.setDrawGridLines(false)
            rightAxis.axisMinimum = 0f // this replaces setStartAtZero(true)


            val leftAxis = chart1.axisLeft
            leftAxis.setDrawGridLines(false)
            leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)


            val xAxis = chart1.xAxis
            xAxis.position = XAxisPosition.BOTH_SIDED
            xAxis.axisMinimum = 0f
            xAxis.granularity = 1f

            val months = listOf(
                "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
            )
            xAxis.valueFormatter = object : IndexAxisValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return months.getOrNull(value.toInt()) ?: ""
                }
            }

            val data = CombinedData()

            data.setData(generateLineData())
            data.setData(generateBarData())
//            data.setData(generateBubbleData())
//            data.setData(generateScatterData())
//            data.setData(generateCandleData())
//            data.setValueTypeface(tfLight)
            xAxis.axisMaximum = data.xMax + 0.5f

            chart1.data = data
            chart1.zoom(2f, 1f, 0f, 0f)
            xAxis.setCenterAxisLabels(true);
            chart1.invalidate()
            chart1.postInvalidate()
//            layout.addView(chart)
        }.root
    }

    private fun generateLineData(): LineData {
        val d = LineData()
        val entries = ArrayList<Entry>()

        for (index in 0 until count) {
            entries.add(Entry(index + 0.5f, Random.nextInt(5, 15).toFloat()))
        }

        val dataSet = LineDataSet(entries, "Line DataSet")
        dataSet.apply {
            color = Color.rgb(240, 238, 70)
            lineWidth = 2.5f
            setCircleColor(Color.rgb(240, 238, 70))
            circleRadius = 5f
            fillColor = Color.rgb(240, 238, 70)
            mode = LineDataSet.Mode.CUBIC_BEZIER
            setDrawValues(true)
            valueTextSize = 10f
            valueTextColor = Color.rgb(240, 238, 70)
            axisDependency = YAxis.AxisDependency.LEFT
        }

        d.addDataSet(dataSet)
        return d
    }

    private fun generateBarData(): BarData {
        val entries1 = ArrayList<BarEntry>()
        val entries2 = ArrayList<BarEntry>()

        for (index in 0 until count) {
            entries1.add(BarEntry(0f, Random.nextInt(5, 25).toFloat()))

            // stacked
            entries2.add(
                BarEntry(
                    0f,
                    floatArrayOf(Random.nextInt(8, 15).toFloat(), Random.nextInt(8, 15).toFloat())
                )
            )
        }
        val set1 = BarDataSet(entries1.toMutableList(), "Bar 1")
        set1.color = Color.rgb(60, 220, 78)
        set1.valueTextColor = Color.rgb(60, 220, 78)
        set1.valueTextSize = 10f
        set1.axisDependency = YAxis.AxisDependency.LEFT

        val set2 = BarDataSet(entries2, "")
        set2.stackLabels = arrayOf("Stack 1", "Stack 2")
        set2.setColors(Color.rgb(61, 165, 255), Color.rgb(23, 197, 255))
        set2.valueTextColor = Color.rgb(61, 165, 255)
        set2.valueTextSize = 10f
        set2.axisDependency = YAxis.AxisDependency.LEFT
        val groupSpace = 0.06f
        val barSpace = 0.02f // x2 dataset
        val barWidth = 0.45f // x2 dataset
        // (0.45 + 0.02) * 2 + 0.06 = 1.00 -> interval per "group"
        val d = BarData(set1, set2)
        d.barWidth = barWidth

        // make this BarData object grouped
        d.groupBars(0f, groupSpace, barSpace) // start at x = 0
        return d
    }

    private fun generateScatterData(): ScatterData {
        val d = ScatterData()
        val entries = ArrayList<Entry>()
        var index = 0f
        while (index < count) {
            entries.add(Entry(index + 0.25f, Random.nextInt(10, 55).toFloat()))
            index += 0.5f
        }
        val set = ScatterDataSet(entries, "Scatter DataSet")
        set.setColors(*ColorTemplate.MATERIAL_COLORS)
        set.scatterShapeSize = 7.5f
        set.setDrawValues(false)
        set.valueTextSize = 10f
        d.addDataSet(set)
        return d
    }

    private fun generateCandleData(): CandleData {
        val d = CandleData()
        val entries = ArrayList<CandleEntry>()
        var index = 0
        while (index < count) {
            entries.add(CandleEntry(index + 1f, 90f, 70f, 85f, 75f))
            index += 2
        }
        val set = CandleDataSet(entries, "Candle DataSet")
        set.decreasingColor = Color.rgb(142, 150, 175)
        set.shadowColor = Color.DKGRAY
        set.barSpace = 0.3f
        set.valueTextSize = 10f
        set.setDrawValues(false)
        d.addDataSet(set)
        return d
    }

    private fun generateBubbleData(): BubbleData {
        val bd = BubbleData()
        val entries = ArrayList<BubbleEntry>()
        for (index in 0 until count) {
            val y: Float = Random.nextInt(10, 105).toFloat()
            val size: Float = Random.nextInt(100, 105).toFloat()
            entries.add(BubbleEntry(index + 0.5f, y, size))
        }
        val set = BubbleDataSet(entries, "Bubble DataSet")
        set.setColors(*ColorTemplate.VORDIPLOM_COLORS)
        set.valueTextSize = 10f
        set.valueTextColor = Color.WHITE
        set.highlightCircleWidth = 1.5f
        set.setDrawValues(true)
        bd.addDataSet(set)
        return bd
    }

    fun saveToGallery() { /* Intentionally left empty */
    }
}