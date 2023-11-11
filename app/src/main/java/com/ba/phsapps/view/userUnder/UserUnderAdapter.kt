package com.ba.phsapps.view.userUnder

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ba.phsapps.data.models.HealthHistory
import com.ba.phsapps.data.models.ServiceCenter
import com.ba.phsapps.data.models.UserModels
import com.ba.phsapps.databinding.ItemHistoryBinding
import com.ba.phsapps.databinding.ItemHospitalBinding
import com.ba.phsapps.databinding.ItemUserBinding


enum class EventUnder{
    History,Add


}

class UserUnderAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val VIEW_TYPE_ITEM = 0
    val VIEW_TYPE_LOADING = 1
    var onClickImageListener: ((ServiceCenter, Int) -> Unit)? = null

    var onClickListener: ((EventUnder, UserModels) -> Unit)? = null


    var dataItem: List<UserModels>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflate = LayoutInflater.from(parent.context)

            val binding = ItemUserBinding.inflate(inflate, parent, false)
            return UserViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is UserViewHolder) {
            val data = dataItem!!.get(position)
            holder.setData(data!!)

            holder.itemsView.addAction.setOnClickListener {
                onClickListener?.invoke(EventUnder.Add,data)


            }
            holder.itemsView.historyAction.setOnClickListener {

                onClickListener?.invoke(EventUnder.History,data)

            }
//            holder.itemView.setOnClickListener {
//
//            }

        }

    }


    fun setItem(data: List<UserModels>) {

            dataItem = data
//            LogUtil.showLogError("size", "${data?.size}")


        notifyDataSetChanged()


    }

//    override fun getItemViewType(position: Int): Int {
//        return if (dataItem!!.get(position) == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
//    }
//

    override fun getItemCount(): Int {
        return dataItem?.let {
            it.size
        } ?: kotlin.run {
            0
        }
    }




    inner class UserViewHolder(val itemsView: ItemUserBinding) :
        RecyclerView.ViewHolder(itemsView.root) {

        fun setData(data: UserModels) {

//            itemsView.root.setOnClickListener {
//                onClickImageListener?.invoke(data,adapterPosition)
//            }
            itemsView.apply {
                nameTV.text = data.uName ?: ""


            }


        }

    }


}