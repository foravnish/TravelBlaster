package tour.traveling.travel.adpater

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.accountapp.accounts.utils.Utility
import kotlinx.android.synthetic.main.row_itinenary_detail.view.*
import org.greenrobot.eventbus.EventBus
import tour.traveling.travel.R
import tour.traveling.travel.constants.AppConstants
import tour.traveling.travel.event.ItiernaryPriceEvent
import tour.traveling.travel.model.response.ItineraryDetailsItem


class ItinenaryDetailAdapter() : RecyclerView.Adapter<ItinenaryDetailAdapter.ViewHolder>() {

    lateinit var callback: GalleryCallback
    var list: MutableList<ItineraryDetailsItem>? = null
    lateinit var binding: ViewDataBinding
    var txtPrice:Float = 0.0f
    fun setData(list :MutableList<ItineraryDetailsItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun setViewCallback(callback: GalleryCallback) {
        this.callback = callback
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val layoutInflator: LayoutInflater = LayoutInflater.from(p0!!.context)
         binding = DataBindingUtil.inflate(layoutInflator, R.layout.row_itinenary_detail, p0, false)



        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder != null) {
            holder.bindItem(position)

//            holder.itemView.setOnClickListener {
//                callback.onGalleryCallback(position)
//            }

//            holder.itemView.txtDesc.setText("" + Utility.html2text(list!!.get(position).item_details!!.get(0).Itinerary_description))


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.itemView.txtDesc.setText("" + Html.fromHtml(list!!.get(position).item_details!!.get(0).Itinerary_description, Html.FROM_HTML_MODE_COMPACT))
            } else {
                holder.itemView.txtDesc.setText("" + Html.fromHtml(list!!.get(position).item_details!!.get(0).Itinerary_description))
            }

            holder.itemView.txtItiTitle.setText("" + list!!.get(position).item_details!!.get(0).itinerary_name)
            holder.itemView.txtItiPrice.setText("" + Utility.getIndianRupee(list!!.get(position).itinerary_cost))
            holder.itemView.txtCityState.setText("" +list!!.get(position).item_details!!.get(0).city_details!!.get(0).name+", "+list!!.get(position).item_details!!.get(0).state_details!!.get(0).name)
            holder.itemView.txtCountry.setText("" + list!!.get(position).item_details!!.get(0).country_details!!.get(0).name)

            if (list!!.get(position).itinerary_default_status==0){
                holder.itemView.txtStatus.setText("Included in this package")
                holder.itemView.checkBox.isChecked=true
                holder.itemView.checkBox.isEnabled=false
//                AppConstants.iteData.add(list!!.get(position).id)
            }else{
                holder.itemView.txtStatus.setText("You can select in this package")
                holder.itemView.checkBox.isEnabled=true
//                AppConstants.iteData.remove(list!!.get(position).id)
            }

            holder.itemView.checkBox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->

                txtPrice=list!!.get(position).itinerary_cost.toFloat()
                if (isChecked){
                    EventBus.getDefault().post(ItiernaryPriceEvent(txtPrice,true))
                    AppConstants.iteData.add(list!!.get(position).id)

                }else{
                    EventBus.getDefault().post(ItiernaryPriceEvent(txtPrice,false))
                    AppConstants.iteData.remove(list!!.get(position).id)

                }

            }

            )
;

            Utility.setImageViaGlide(
                R.drawable.product_placeholder,
                "" + list!!.get(position).item_details!!.get(0).Itinerary_image,
                holder.itemView.imgItinery,
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
        fun onGalleryCallback(p_id: Int)
    }


}