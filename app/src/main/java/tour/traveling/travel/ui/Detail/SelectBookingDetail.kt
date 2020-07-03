package tour.traveling.travel.ui.Detail

import android.R.id.message
import android.app.DatePickerDialog
import android.content.Intent
import android.widget.DatePicker
import androidx.databinding.DataBindingUtil
import com.accountapp.accounts.constants.BundleConstant
import tour.traveling.travel.R
import tour.traveling.travel.base.BaseActivity
import tour.traveling.travel.databinding.ActivitySelectBookingDetailBinding
import java.util.*


class SelectBookingDetail : BaseActivity() {

    lateinit var binding: ActivitySelectBookingDetailBinding
    val mContext by lazy { this@SelectBookingDetail }


    override fun initUI() {
        binding = DataBindingUtil.setContentView(mContext, R.layout.activity_select_booking_detail)
        setToolbarWithBackIcon(binding.toolbar, "Select Booking Details")

        binding.txtQty.setText(""+ BundleConstant.SeletedPerson)
        binding.txtDate.setText("" + BundleConstant.SelectedDate)

        binding.txtEdit.setOnClickListener {
            val myCalendar = Calendar.getInstance()

            val date: DatePickerDialog.OnDateSetListener = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(
                    view: DatePicker, year: Int, monthOfYear: Int,
                    dayOfMonth: Int
                ) { // TODO Auto-generated method stub
                    myCalendar[Calendar.YEAR] = year
                    myCalendar.set(Calendar.MONTH, monthOfYear)
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    BundleConstant.SelectedDate=""+dayOfMonth + "-" + (monthOfYear +1) + "-" + year
                    binding.txtDate.setText("" + BundleConstant.SelectedDate)
                }
            }

            DatePickerDialog(
                this@SelectBookingDetail, R.style.DialogTheme, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()

        }

        binding.tvProAdd.setOnClickListener {
            if (binding.txtQty.text.toString().toInt() < 5) {
                var quantity: Int = binding.txtQty.text.toString().toInt() + 1
                binding.txtQty.setText("" + quantity)
            } else {
                com.accountapp.accounts.utils.Utility.showSnackBar(
                    binding.root,
                    "You can select Less then 5 Adults "
                )
            }

        }
        binding.tvProLess.setOnClickListener {
            if (binding.txtQty.text.toString().toInt() > 1) {
                var quantity: Int = binding.txtQty.text.toString().toInt() - 1
                binding.txtQty.setText("" + quantity)
            }else{
                com.accountapp.accounts.utils.Utility.showSnackBar(
                    binding.root,
                    "You can select minimum 1 Adults "
                )
            }
        }

        binding.btnDone.setOnClickListener {

            val intent = Intent()
            intent.putExtra("Date", ""+binding.txtDate.text.toString())
            intent.putExtra("members", ""+binding.txtQty.text.toString())
            setResult(101, intent)
            finish()
        }
    }


}
