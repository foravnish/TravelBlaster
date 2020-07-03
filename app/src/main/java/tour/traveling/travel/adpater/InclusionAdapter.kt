package tour.traveling.travel.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.accountapp.accounts.utils.Utility
import kotlinx.android.synthetic.main.row_hot_places.view.*
import kotlinx.android.synthetic.main.row_inclusion.view.*
import tour.traveling.travel.R
import tour.traveling.travel.model.response.AmenitiesDetailsItem
import tour.traveling.travel.model.response.ResultItemBanner

class InclusionAdapter() : RecyclerView.Adapter<InclusionAdapter.ViewHolder>() {

    lateinit var callback: ProductCallback
    var list: MutableList<AmenitiesDetailsItem>? = null

    lateinit var binding: ViewDataBinding
    fun setData(list :MutableList<AmenitiesDetailsItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun setViewCallback(callback: ProductCallback) {
        this.callback = callback
    }

//    fun setViewCallbackWishList(callbackWishList: WishListCallback) {
//        this.callbackWishList = callbackWishList
//    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val layoutInflator: LayoutInflater = LayoutInflater.from(p0!!.context)
         binding = DataBindingUtil.inflate(layoutInflator, R.layout.row_inclusion, p0, false)



        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder != null) {
            holder.bindItem(position)

//            holder.itemView.setOnClickListener {
//                callback.onProductCallback("1")
//            }
            holder.itemView.txt_inclusion.setText("" + list!!.get(position).amenities_name)


            Utility.setImageViaGlide(
                R.drawable.product_placeholder,
                list!!.get(position).amenities_logo,
                holder.itemView.inc_image,
                holder.itemView.context
            )


        }
    }


    override fun getItemCount(): Int {
        if (list != null && list!!.size > 0)
            return list!!.size
        return 0

    }


    class ViewHolder constructor(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(position: Int) {
        }
    }

    interface ProductCallback {
        fun onProductCallback(p_id: String)
    }


}