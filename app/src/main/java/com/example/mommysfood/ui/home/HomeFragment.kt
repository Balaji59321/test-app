package com.example.mommysfood.ui.home

import android.Manifest
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mommysfood.DataBase
import com.example.mommysfood.DatabaseHandler
import com.example.mommysfood.R
import com.example.mommysfood.ui.tools.*
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.fragment_home.*
import org.osmdroid.DefaultResourceProxyImpl
//import com.google.android.gms.maps.model.Marker
import org.osmdroid.views.MapController
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.MyLocationOverlay
import org.w3c.dom.Text
import java.util.*
import org.osmdroid.views.MapView as MapView2

//import org.osmdroid.bonuspack.overlays.Marker
object temp {
    var Price = 0
}

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    val TAG = "PermissionDemo"
    val RECORD_REQUEST_CODE = 101

    companion object {
        val bill = 0
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val usr_name = root.findViewById<EditText>(R.id.user_identity)
        val get_data = root.findViewById<Button>(R.id.get_bill)
        val remove_entry = root.findViewById<Button>(R.id.fullpaid)
        var user_name = ""
        val user_amount = root.findViewById<TextView>(R.id.useramount)
        val userlist = root.findViewById<ListView>(R.id.listview_user)
        val shareBill = root.findViewById<Button>(R.id.share_bill)
        if (user_amount.text.toString() <= 0.toString()) {
            shareBill.isEnabled = false
        }
        get_data.setOnClickListener {
            user_name = usr_name.text.toString()
            user_amount.text = 0.toString()
            val customAdapter =
                MyCustomAdapter(root.context, user_name, user_amount, shareBill)
            userlist.adapter = customAdapter
            if (user_amount.text.toString() <= 0.toString()) {
                shareBill.isEnabled = false
            }
        }
        remove_entry.setOnClickListener {
            val databaseHandler = DatabaseHandler(context!!)
            val returns = databaseHandler.amountPaid(user_name)
            temp.Price = 0
            user_amount.text = 0.toString()
            user_identity.text.clear()
            val customAdapter =
                MyCustomAdapter(root.context, user_name, user_amount, shareBill)
            userlist.adapter = customAdapter
            Toast.makeText(root.context, "Bill Paid Successfully", Toast.LENGTH_SHORT).show()
            if (user_amount.text.toString() <= 0.toString()) {
                shareBill.isEnabled = false
            }
        }
        shareBill.setOnClickListener {
            val c = Calendar.getInstance()
            val df = SimpleDateFormat("yyyy-MM-dd")
            val formattedDate: String = df.format(c.time)
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Download this App")
            val app_url = "Hi there !!\nTotal Due Bill Amount as of ${formattedDate} is ₹${temp.Price}"
            shareIntent.putExtra(Intent.EXTRA_TEXT, app_url)
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
        return root
    }
}

private class MyCustomAdapter(
    context: Context, username: String, useramount: TextView, val shareBill: Button
) : BaseAdapter() {

    private val mContext: Context
    private val user_amount: TextView
    val databaseHandler: DatabaseHandler = DatabaseHandler(context)
    var flag = databaseHandler.getUser(username)

    init {
        mContext = context
        user_amount = useramount
        temp.Price = 0
    }

    override fun getCount(): Int {
        return flag.count
    }

    override fun getItem(p0: Int): Any {
        return "Hello"
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        databaseHandler.close()
        val layoutInflater = LayoutInflater.from(mContext);

        val rowmain = layoutInflater.inflate(R.layout.dish_consumed, p2, false)
        if (flag.count == 0) {
            Toast.makeText(mContext, "No Transaction to show", Toast.LENGTH_SHORT).show()
        } else {
            val name = rowmain.findViewById<TextView>(R.id.empname)
            val id = rowmain.findViewById<TextView>(R.id.identitynumber)
            val period = rowmain.findViewById<TextView>(R.id.datetime)
            val consumed = rowmain.findViewById<TextView>(R.id.dishconsumed)
            val amount = rowmain.findViewById<TextView>(R.id.totalamount)
            var employeeId = ""
            var employeeName = ""
            var dishConsumed = ""
            var billAmount = 0
            var currentDate = ""
            flag.position + 1
            if (flag.moveToNext()) {
                employeeId = flag.getString(flag.getColumnIndex("id"))
                employeeName = flag.getString(flag.getColumnIndex("name"))
                dishConsumed = flag.getString(flag.getColumnIndex("dish"))
                billAmount = flag.getInt(flag.getColumnIndex("amount"))
                currentDate = flag.getString(flag.getColumnIndex("date"))
                temp.Price = temp.Price + billAmount
            }
            id.text = employeeId
            name.text = employeeName
            period.text = currentDate
            amount.text = billAmount.toString()
            consumed.text = dishConsumed
        }
        user_amount.text = temp.Price.toString()
        if (user_amount.text.toString() <= 0.toString()) {
            shareBill.isEnabled = false
        } else {
            shareBill.isEnabled = true
        }
        return rowmain
    }
}