package tour.traveling.travel.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.accountapp.accounts.utils.Utility
import kotlinx.android.synthetic.main.row_category.view.*
import tour.traveling.travel.R
import tour.traveling.travel.model.response.DetailsItem
import tour.traveling.travel.model.response.ResultItemCategory

class CategoryAdapter() : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    lateinit var callback: ProductCallback
    var list: MutableList<ResultItemCategory>? = null

    lateinit var binding: ViewDataBinding
    fun setData(list :MutableList<ResultItemCategory>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun setViewCallback(callback: ProductCallback) {
        this.callback = callback
    }



    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val layoutInflator: LayoutInflater = LayoutInflater.from(p0!!.context)
            binding = DataBindingUtil.inflate(layoutInflator, R.layout.row_category, p0, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder != null) {
            holder.bindItem(position)

            holder.itemView.txtCatagoryName.setText("" + list!!.get(position).category)

            Utility.setImageViaGlide(
                R.drawable.product_placeholder,
                "" + list!!.get(position).category_image,
                holder.itemView.s1_14,
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
        fun onProductCallbackNewArrival(p_id: String)
    }


}