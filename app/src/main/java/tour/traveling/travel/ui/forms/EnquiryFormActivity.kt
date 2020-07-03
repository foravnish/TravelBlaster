package tour.traveling.travel.ui.forms

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.widget.DatePicker
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.accountapp.accounts.utils.Prefences
import tour.traveling.travel.R
import tour.traveling.travel.base.BaseActivity
import tour.traveling.travel.databinding.ActivityEnquiryFormBinding
import tour.traveling.travel.ui.profile.ProfileViewModel
import java.util.*


class EnquiryFormActivity : BaseActivity() {

    lateinit var binding: ActivityEnquiryFormBinding
    val mContext by lazy { this@EnquiryFormActivity }
    val model by lazy { ViewModelProviders.of(this).get(ProfileViewModel::class.java) }

    override fun initUI() {

        binding = DataBindingUtil.setContentView(mContext, R.layout.activity_enquiry_form)
        setToolbarWithBackIcon(binding.toolbar, "Enquiry")

        val myCalendar = Calendar.getInstance()

        val date: OnDateSetListener = object : OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) { // TODO Auto-generated method stub
                myCalendar[Calendar.YEAR] = year
                myCalendar.set(Calendar.MONTH, monthOfYear)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                binding.edtDate.setText(""+ dayOfMonth+"-"+(monthOfYear + 1)+"-"+year)
            }
        }


        val name=Prefences.getUserName(mContext).toString()
        binding.edtName.setText(""+name.substring(0, 1).toUpperCase()+  name.substring(1))
        binding.edtMobile.setText(""+ Prefences.getUserMobile(mContext))
        binding.edtEmail.setText(""+ Prefences.getUserEmailId(mContext))



        binding.edtDate.setOnClickListener {
            DatePickerDialog(
                this@EnquiryFormActivity,  R.style.DialogTheme, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()

        }

    }
}
