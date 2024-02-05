package com.app.coroutinedemo.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.coroutinedemo.businesslogic.interfaces.GeneralItemClickListeners
import com.app.coroutinedemo.businesslogic.pojo.countries.PojoCountriesItem
import com.app.coroutinedemo.databinding.ItemLayoutCountriesBinding

class AdapterCountries(var generalItemClickListeners: GeneralItemClickListeners) : RecyclerView.Adapter<AdapterCountries.MyViewHolder>() {

    private var dataList: List<PojoCountriesItem> = emptyList()

    inner class MyViewHolder(var mBinding: ItemLayoutCountriesBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind() {
            mBinding.pojo = dataList[adapterPosition]
            mBinding.generalItemClickListener = generalItemClickListeners
            mBinding.currentPosition = adapterPosition
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterCountries.MyViewHolder {

        val mBinding =
            ItemLayoutCountriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: AdapterCountries.MyViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setList(dataList: List<PojoCountriesItem>) {
        this.dataList = dataList
        notifyDataSetChanged()

    }
}