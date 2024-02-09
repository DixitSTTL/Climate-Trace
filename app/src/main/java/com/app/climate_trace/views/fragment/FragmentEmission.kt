package com.app.climate_trace.views.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.climate_trace.R
import com.app.climate_trace.businesslogic.viewmodel.fragment.ViewModelEmission
import com.app.climate_trace.databinding.FragmentEmissionBinding
import com.app.climate_trace.utils.Constants.myMediumBrightColors
import com.app.climate_trace.views.adapter.AdapterEmission
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentEmission : FragmentBase() {

    private lateinit var mBinding: FragmentEmissionBinding
    private lateinit var mViewModel: ViewModelEmission
    private var mAdapter: AdapterEmission = AdapterEmission()
    private val args: FragmentEmissionArgs by navArgs()
    private var pieChart: PieChart? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_emission, container, false)
        mViewModel = ViewModelProvider(mActivityMain!!)[ViewModelEmission::class.java]
        init()
        observe()

        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.mViewModel = mViewModel
        mViewModel.fetchApi()

    }

    private fun init() {
        pieChart = mBinding.pieChart //this is our piechart

        val mLinearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mBinding.recSubSectors.adapter = mAdapter
        mBinding.recSubSectors.layoutManager = mLinearLayoutManager
        mViewModel.dataCountry.set(args.countryItem)
    }

    private fun observe() {
        mViewModel.dataList.observe(viewLifecycleOwner, Observer {

            mAdapter.setList(it)
            setPieChart()
        })

    }

    private fun setPieChart() {
        val tLRegular = ResourcesCompat.getFont(mApplication, R.font.lato_regular)

        pieChart!!.setUsePercentValues(true)
        pieChart!!.description.isEnabled = false
        pieChart!!.setExtraOffsets(5F, 10F, 5F, 80F)
        pieChart!!.dragDecelerationFrictionCoef = 0.95f

        pieChart!!.setCenterTextTypeface(tLRegular)
        pieChart!!.setCenterTextSize(20f)
        pieChart!!.setCenterTextColor(resources.getColor(R.color.mWbRevers))
        pieChart!!.setHoleColor(resources.getColor(R.color.mWb))

        pieChart!!.centerText = mViewModel.dataCountry.get()?.name ?: ""

        pieChart!!.isDrawHoleEnabled = true

        pieChart!!.setTransparentCircleColor(Color.WHITE)
        pieChart!!.setTransparentCircleAlpha(110)

        pieChart!!.holeRadius = 58f
        pieChart!!.transparentCircleRadius = 61f

        pieChart!!.setDrawCenterText(true)

        pieChart!!.rotationAngle = 0.toFloat()
        // enable rotation of the chart by touch
        pieChart!!.isRotationEnabled = true
        pieChart!!.isHighlightPerTapEnabled = true


        pieChart!!.animateY(1400, Easing.EaseInOutQuad)
        // pieChart.spin(2000, 0, 360);

        pieChart!!.spin(2000, 0F, 180F, Easing.EaseInBack)
        val l = pieChart!!.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.form = Legend.LegendForm.CIRCLE
        l.typeface = tLRegular
        l.textColor = resources.getColor(R.color.mWbRevers)
        l.isWordWrapEnabled = true
        l.setDrawInside(true)
        l.xEntrySpace = 7f
        l.yEntrySpace = 0f
        l.yOffset = 0f

        // entry label styling
        pieChart!!.setDrawEntryLabels(false)
        pieChart!!.setEntryLabelColor(Color.BLACK)
        pieChart!!.setEntryLabelTypeface(tLRegular)
        pieChart!!.setEntryLabelTextSize(12f)
        setPieChartData()
    }

    fun setPieChartData() {
        val tLRegular = ResourcesCompat.getFont(mApplication, R.font.lato_regular)

        val entries: ArrayList<PieEntry> = ArrayList()

        mViewModel.dataList.value?.forEach { data ->
            entries.add(PieEntry(data.AssetCount.toFloat(), data.Sector))
        }
        //you can test above by adding random dummy data to the pie chart or passing the data from the backend.


        val dataSet = PieDataSet(entries, "")
        dataSet.setDrawIcons(true)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0F, 40F)
        dataSet.selectionShift = 5f
        dataSet.selectionShift = 5f

        // add colors


        dataSet.colors = myMediumBrightColors
        //dataSet.setSelectionShift(0f);
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(12f)
        data.setValueTextColor(resources.getColor(R.color.mWb))
        data.setValueTypeface(tLRegular)
        pieChart!!.setData(data)

        // undo all highlights
        pieChart!!.highlightValues(null)
        pieChart!!.invalidate()
    }

}