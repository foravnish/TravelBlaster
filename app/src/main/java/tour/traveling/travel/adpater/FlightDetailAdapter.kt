package tour.traveling.travel.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.accountapp.accounts.utils.Utility
import kotlinx.android.synthetic.main.row_flight_data.view.*
import tour.traveling.travel.R
import tour.traveling.travel.model.response.FlightDetailsItem


class FlightDetailAdapter() : RecyclerView.Adapter<FlightDetailAdapter.ViewHolder>() {

    lateinit var callback: ProductCallback
    var list: MutableList<FlightDetailsItem>? = null

    lateinit var binding: ViewDataBinding
    fun setData(list :MutableList<FlightDetailsItem>) {
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
         binding = DataBindingUtil.inflate(layoutInflator, R.layout.row_flight_data, p0, false)


        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder != null) {
            holder.bindItem(position)

//            holder.itemView.setOnClickListener {
//                callback.onProductCallback("1")
//            }

            holder.itemView.txtFlightPlace.setText("" + list!!.get(position).flight_from + " to "+list!!.get(position).flight_to)

            holder.itemView.txtFlightName.setText(""+list!!.get(position).itinerary_name)
            holder.itemView.txtFlightDesc.setText(""+HtmlCompat.fromHtml(list!!.get(position).Itinerary_description,0))

            holder.itemView.txtFlightNumber.setText("Flight Number: "+list!!.get(position).flight_number)

            holder.itemView.txtFlightTimeArr.setText(""+list!!.get(position).flight_date_time)
            holder.itemView.txtFlightTimeDepr.setText(""+list!!.get(position).flight_date_time)



            Utility.setImageViaGlide(
                R.drawable.product_placeholder,
                list!!.get(position).Itinerary_image,
                holder.itemView.flight_logo,
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