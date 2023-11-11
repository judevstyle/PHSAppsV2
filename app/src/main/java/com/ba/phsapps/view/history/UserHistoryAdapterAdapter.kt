package com.ba.phsapps.view.history

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ba.phsapps.data.models.HealthHistory
import com.ba.phsapps.databinding.ItemHistoryBinding

class UserHistoryAdapterAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val VIEW_TYPE_ITEM = 0
    val VIEW_TYPE_LOADING = 1
    var onClickImageListener: ((HealthHistory, Int) -> Unit)? = null

    var dataItem: List<HealthHistory>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflate = LayoutInflater.from(parent.context)

            val binding = ItemHistoryBinding.inflate(inflate, parent, false)
            return HistoryViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is HistoryViewHolder) {
            val data = dataItem!!.get(position)
            holder.setData(data!!)
//            holder.itemView.setOnClickListener {
//
//            }

        }

    }


    fun setItem(data: List<HealthHistory>) {

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




    inner class HistoryViewHolder(val itemsView: ItemHistoryBinding) :
        RecyclerView.ViewHolder(itemsView.root) {

        fun setData(data: HealthHistory) {

//            itemsView.root.setOnClickListener {
//                onClickImageListener?.invoke(data,adapterPosition)
//            }
            itemsView.apply {


                dateTV.text = "${data.createDate ?: "-"}"
                va1TV.text = "${data.healthPresSure ?: "-"}"
                va2TV.text = "${data.healthPulse ?: 0}"
                va3TV.text = "${data.height ?: 0}"
                va4TV.text = "${data.weight ?: 0}"


//                Glide.with(itemsView.root)
//                    .load("${data.path}")
//                    .diskCacheStrategy(DiskCacheStrategy.DATA)
//                    .error(R.drawable.ic_no_img)
//                    .into(this.imageIM)

            }


        }

    }


}