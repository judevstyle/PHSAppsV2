package com.ba.phsapps.view.medical

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ba.phsapps.data.models.HealthHistory
import com.ba.phsapps.data.models.ServiceCenter
import com.ba.phsapps.databinding.ItemHistoryBinding
import com.ba.phsapps.databinding.ItemHospitalBinding

class MedicalAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val VIEW_TYPE_ITEM = 0
    val VIEW_TYPE_LOADING = 1
    var onClickImageListener: ((ServiceCenter, Int) -> Unit)? = null

    var dataItem: List<ServiceCenter>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflate = LayoutInflater.from(parent.context)

            val binding = ItemHospitalBinding.inflate(inflate, parent, false)
            return HospitalViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is HospitalViewHolder) {
            val data = dataItem!!.get(position)
            holder.setData(data!!)
//            holder.itemView.setOnClickListener {
//
//            }

        }

    }


    fun setItem(data: List<ServiceCenter>) {

            dataItem = data
//            LogUtil.showLogError("size", "${data?.size}")


        notifyDataSetChanged()


    }

    override fun getItemViewType(position: Int): Int {
        return if (dataItem!!.get(position) == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }


    override fun getItemCount(): Int {
        return dataItem?.let {
            it.size
        } ?: kotlin.run {
            0
        }
    }




    inner class HospitalViewHolder(val itemsView: ItemHospitalBinding) :
        RecyclerView.ViewHolder(itemsView.root) {

        fun setData(data: ServiceCenter) {

//            itemsView.root.setOnClickListener {
//                onClickImageListener?.invoke(data,adapterPosition)
//            }
            itemsView.apply {


                nameTV.text = data.serviceName ?: ""
                telTV.text = data.servicePhone ?: ""
                descTV.text = data.serviceAddress ?: ""


//                Glide.with(itemsView.root)
//                    .load("${data.path}")
//                    .diskCacheStrategy(DiskCacheStrategy.DATA)
//                    .error(R.drawable.ic_no_img)
//                    .into(this.imageIM)

            }


        }

    }


}