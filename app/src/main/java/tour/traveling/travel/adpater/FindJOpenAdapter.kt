package tour.traveling.travel.adpater

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.accountapp.accounts.constants.BundleConstant
import com.accountapp.accounts.utils.Utility
import kotlinx.android.synthetic.main.row_open_jobs.view.*
import tour.traveling.travel.R
import tour.traveling.travel.databinding.RowOpenJobsBinding
import tour.traveling.travel.model.response.ResultItemHistory
import tour.traveling.travel.ui.Detail.HistoryDetailActivity
import java.io.Serializable

/**
 */
class FindJOpenAdapter() : RecyclerView.Adapter<FindJOpenAdapter.ViewHolder>() {

    var mCallback: ItemClick? = null
    var list: List<ResultItemHistory>? = null
    var mJobType = ""

    fun setData(list: List<ResultItemHistory>) {
        this.list = list
        notifyDataSetChanged()
    }
    fun setViewCallback(mCallback: ItemClick) {
        this.mCallback = mCallback
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val layoutInflator: LayoutInflater = LayoutInflater.from(p0!!.context)
        val binding: RowOpenJobsBinding = RowOpenJobsBinding.inflate(layoutInflator, p0, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder != null && list != null) {
//            holder.bindItem(position)

            Log.d("dfgdgdfgdfg",mJobType)
            Log.d("bfbgfcbgcfb",""+list!!.get(position).status)


//            if (mJobType.equals("1") && list!!.get(position).package_status==1){
//                holder.itemView.visibility= View.VISIBLE
                openhistory(position,holder)
//            }

//            else{
//                holder.itemView.visibility= View.GONE
//            }

//            if(mJobType.equals("2") && list!!.get(position).package_status==0){
//                holder.itemView.cardView.visibility= View.VISIBLE
//                openhistory(position,holder)
//            }else{
//                holder.itemView.cardView.visibility= View.GONE
//            }
//            if(mJobType.equals("3") && list!!.get(position).package_status==2){
//                holder.itemView.cardView.visibility= View.VISIBLE
//                openhistory(position,holder)
//            }else{
//                holder.itemView.cardView.visibility= View.GONE
//            }

        }
    }

    private fun openhistory(position: Int,holder: ViewHolder) {

        holder.itemView.setOnClickListener {

            mCallback!!.onItemClick(position)

            val intent = Intent(holder.itemView.context, HistoryDetailActivity::class.java)
            intent.putExtra(BundleConstant.DETAIL_DATA,list!!.get(position) as Serializable)

            holder.itemView.context.startActivity(intent)

        }


        if (list!!.get(position).package_status==0){
            holder.itemView.rate_driver.setText("Canceled")
        }else if(list!!.get(position).package_status==1){
            holder.itemView.rate_driver.setText("Open")
        }else if(list!!.get(position).package_status==2){
            holder.itemView.rate_driver.setText("Completed")
        }

//            val data = list!!.get(position)
        holder.itemView.job_title_txtVw.text =
            list!!.get(position).package_id!!.get(0).package_name
        holder.itemView.job_address_txtVw.text =
            "" + list!!.get(position)!!.package_id!!.get(0).country_details!!.get(0).name
        holder.itemView.job_address_txtVw_pic_up.text =
            "" + list!!.get(position).package_id!!.get(0).package_days + " Days " + list!!.get(
                position
            ).package_id!!.get(0).package_night + " Nights"
//            holder.itemView.job_date_txtVw.text = "" + Utility.getNewDate("" + data.deliveryD
//            ate, holder.itemView.context)


        holder.itemView.job_date_txtVw.text = "" + list!!.get(position).journey_date
        holder.itemView.bid_cost_txtVw.text = "र " + list!!.get(position).package_cost

        Utility.setImageViaGlide(
            R.drawable.product_placeholder,
            list!!.get(position).package_id!!.get(0).package_images!!.get(0).image,
            holder.itemView.user_image_view,
            holder.itemView.context
        )

    }

    override fun getItemCount(): Int {
        if (list != null && list!!.size > 0)
            return list!!.size

        return 0
    }

    class ViewHolder constructor(binding: RowOpenJobsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(position: Int) {
        }
    }

    interface ItemClick {
        fun onItemClick(position: Int)
    }

}