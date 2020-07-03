package tour.traveling.travel.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import tour.traveling.travel.R

class NotificationAdapter() : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    lateinit var callback: ProductCallback
//    var list: MutableList<NewArrivalsItem>? = null

    lateinit var binding: ViewDataBinding
    fun setData() {
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
         binding = DataBindingUtil.inflate(layoutInflator, R.layout.row_notification, p0, false)



        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder != null) {
            holder.bindItem(position)

            holder.itemView.setOnClickListener {
                callback.onProductCallback("1")
            }
//            holder.itemView.txtTitle.setText("" + list!!.get(position).title)


//            Utility.setImageViaGlide(
//                R.drawable.img_place_holder,
//                "" + list!!.get(position).product_images!!.get(0).image_path,
//                holder.itemView.imgProduct,
//                holder.itemView.context
//            )


        }
    }


    override fun getItemCount(): Int {
//        if (list != null && list!!.size > 0)
//            return list!!.size
//        return 0


        return 50
    }


    class ViewHolder constructor(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(position: Int) {
        }
    }

    interface ProductCallback {
        fun onProductCallback(p_id: String)
    }


}