package com.app.coroutinedemo.views.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.coroutinedemo.businesslogic.interfaces.GeneralItemClickListeners
import com.app.coroutinedemo.businesslogic.pojo.countries.PojoCountriesItem
import com.app.coroutinedemo.databinding.ItemLayoutCountriesBinding

class AdapterCountries(var generalItemClickListeners: GeneralItemClickListeners) :
    RecyclerView.Adapter<AdapterCountries.MyViewHolder>(), Filterable {

    private var fullList: ArrayList<PojoCountriesItem> = ArrayList()
    private var dataList: ArrayList<PojoCountriesItem> = ArrayList()

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

    fun setList(__dataList: ArrayList<PojoCountriesItem>) {
        fullList.clear()
        dataList.clear()
        fullList.addAll(__dataList)
        updateListItems(fullList)
    }

    fun updateListItems(updatedList: ArrayList<PojoCountriesItem>) {
        Log.d("listSizesss"," ${dataList.size}++++${updatedList.size}")
        val diffCallback = CountryDiffCallback(dataList, updatedList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        dataList.clear()
        dataList.addAll(updatedList)
        diffResult.dispatchUpdatesTo(this)
    }

    class CountryDiffCallback(
        var oldList: ArrayList<PojoCountriesItem>,
        var updatedList: ArrayList<PojoCountriesItem>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return updatedList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldData = oldList[oldItemPosition];
            val newData = updatedList[newItemPosition];
            return newData == oldData;
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldData = oldList[oldItemPosition];
            val newData = updatedList[newItemPosition];
            return newData == oldData;
        }

        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
            return super.getChangePayload(oldItemPosition, newItemPosition)
        }

    }

    override fun getFilter(): Filter {
        return customFilter
    }

    private val customFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            var middelList = ArrayList<PojoCountriesItem>()
             if (constraint.isNullOrEmpty()) {
                 middelList.clear()
                 middelList.addAll(fullList)
            } else {
                val query = constraint.toString().toLowerCase().trim()

                fullList.forEach {
                    if (it.name.toLowerCase().startsWith(query)) {
                        middelList.add(it)
                    }
                }

            }

            return FilterResults().apply { values = middelList }
        }

        override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
            updateListItems((filterResults?.values as ArrayList<PojoCountriesItem>))
        }

    }


}