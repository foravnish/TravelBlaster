package tour.traveling.travel.adpater

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.accountapp.accounts.constants.BundleConstant
import com.accountapp.accounts.utils.Utility
import kotlinx.android.synthetic.main.row_hot_places.view.*
import tour.traveling.travel.R
import tour.traveling.travel.model.response.DetailsItem
import tour.traveling.travel.ui.Detail.ProductDetailCategoryActivity
import java.io.Serializable

class VisaAdapter() : RecyclerView.Adapter<VisaAdapter.ViewHolder>() {

    lateinit var callback: ProductCallback
    var list: MutableList<DetailsItem>? = null

    lateinit var binding: ViewDataBinding
    fun setData(list :MutableList<DetailsItem>) {
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
         binding = DataBindingUtil.inflate(layoutInflator, R.layout.row_hot_places, p0, false)



        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder != null) {
            holder.bindItem(position)

            holder.itemView.setOnClickListener {
                callback.onProductCallback("1")
            }
            holder.itemView.txtName.setText("" + list!!.get(position).package_name)
            holder.itemView.txtDays!!.setText(""+list!!.get(position).package_days+" Days "+list!!.get(position).package_night+" Nights")


            holder.itemView!!.setOnClickListener {

                val intent = Intent(holder.itemView.context, ProductDetailCategoryActivity::class.java)
                intent.putExtra(BundleConstant.DETAIL_DATA,list!!.get(position) as Serializable)

                holder.itemView.context.startActivity(intent)

            }


            Utility.setImageViaGlide(
                R.drawable.product_placeholder,
                list!!.get(position).package_images!!.get(0).image,
                holder.itemView.imgProduct,
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