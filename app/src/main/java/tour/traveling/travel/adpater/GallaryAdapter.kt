package tour.traveling.travel.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.accountapp.accounts.utils.Utility
import kotlinx.android.synthetic.main.row_gallary.view.*
import tour.traveling.travel.R
import tour.traveling.travel.model.response.PackageImagesItem
import tour.traveling.travel.model.response.ResultItemBanner

class GallaryAdapter() : RecyclerView.Adapter<GallaryAdapter.ViewHolder>() {

    lateinit var callback: GalleryCallback
    var list: MutableList<PackageImagesItem>? = null

    lateinit var binding: ViewDataBinding

    fun setData(list :MutableList<PackageImagesItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun setViewCallback(callback: GalleryCallback) {
        this.callback = callback
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val layoutInflator: LayoutInflater = LayoutInflater.from(p0!!.context)
         binding = DataBindingUtil.inflate(layoutInflator, R.layout.row_gallary, p0, false)



        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder != null) {
            holder.bindItem(position)

            holder.itemView.setOnClickListener {
                callback.onGalleryCallback(position,list!!.get(position).image_title)
            }
            holder.itemView.txtName.setText("" + list!!.get(position).image_title)


            Utility.setImageViaGlide(
                R.drawable.product_placeholder,
                "" + list!!.get(position).image,
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

    interface GalleryCallback {
        fun onGalleryCallback(p_id: Int, name:String)
    }


}