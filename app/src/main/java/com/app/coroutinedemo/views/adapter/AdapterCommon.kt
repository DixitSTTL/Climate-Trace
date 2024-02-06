package com.app.coroutinedemo.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.coroutinedemo.businesslogic.interfaces.GeneralItemClickListeners
import com.app.coroutinedemo.databinding.ItemLayoutCommonBinding

class AdapterCommon(var generalItemClickListeners: GeneralItemClickListeners): RecyclerView.Adapter<AdapterCommon.MyViewHolder>() {

    private var dataList: ArrayList<String> = ArrayList()

    inner class MyViewHolder(var mBinding: ItemLayoutCommonBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind() {
            mBinding.str = dataList[adapterPosition]
            mBinding.currentPosition = adapterPosition
            mBinding.generalItemClickListener = generalItemClickListeners
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterCommon.MyViewHolder {

        val mBinding =
            ItemLayoutCommonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: AdapterCommon.MyViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setList(dataList: ArrayList<String>) {
        updateListItems(dataList)

    }

    fun updateListItems(updatedList: List<String>) {
        val diffCallback = EmployeeDiffCallback(this.dataList, updatedList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.dataList.clear()
        this.dataList.addAll(updatedList)
        diffResult.dispatchUpdatesTo(this)
    }
}

class EmployeeDiffCallback(var dataList: ArrayList<String>,var updatedList: List<String>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return dataList.size
    }

    override fun getNewListSize(): Int {
        return updatedList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = dataList[oldItemPosition];
        val  newEmployee = updatedList[newItemPosition];
        return oldEmployee == newEmployee;
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = dataList[oldItemPosition];
        val  newEmployee = updatedList[newItemPosition];
        return oldEmployee == newEmployee;
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }

}