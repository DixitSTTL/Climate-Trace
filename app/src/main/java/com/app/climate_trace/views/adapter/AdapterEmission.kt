package com.app.climate_trace.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.climate_trace.businesslogic.pojo.emission.CountryData
import com.app.climate_trace.databinding.ItemLayoutEmissionBinding

class AdapterEmission : RecyclerView.Adapter<AdapterEmission.MyViewHolder>() {

    private var dataList: List<CountryData> = emptyList()

    inner class MyViewHolder(var mBinding: ItemLayoutEmissionBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind() {
            mBinding.pojo = dataList[adapterPosition]
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterEmission.MyViewHolder {

        val mBinding =
            ItemLayoutEmissionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: AdapterEmission.MyViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setList(dataList: List<CountryData>) {
        this.dataList = dataList
        notifyDataSetChanged()

    }
}