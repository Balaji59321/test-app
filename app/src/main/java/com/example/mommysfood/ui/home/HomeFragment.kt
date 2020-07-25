package com.example.mommysfood.ui.home

import android.Manifest
import android.content.ContentValues.TAG
import android.content.Context
import android.content.pm.PackageManager
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
import org.osmdroid.views.MapView as MapView2

//import org.osmdroid.bonuspack.overlays.Marker
var Price = 0

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    val TAG = "PermissionDemo"
    val RECORD_REQUEST_CODE = 101

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
        get_data.setOnClickListener {
            user_name = usr_name.text.toString()
            user_amount.text=0.toString()
            val customAdapter = MyCustomAdapter(root.context, user_name,user_amount)
            userlist.adapter = customAdapter
        }
        remove_entry.setOnClickListener {
            val databaseHandler = DatabaseHandler(context!!)
            val returns =  databaseHandler.amountPaid(user_name)
            Price=0
            user_amount.text=0.toString()
            user_name.trim()
            val customAdapter = MyCustomAdapter(root.context, user_name,user_amount)
            userlist.adapter = customAdapter
            Toast.makeText(root.context,"Bill Paid Successfully",Toast.LENGTH_SHORT).show()
        }
        return root
    }
}

private class MyCustomAdapter(context: Context, username: String,val useramount : TextView) : BaseAdapter() {

    private val mContext: Context
    val databaseHandler: DatabaseHandler = DatabaseHandler(context)
    var flag = databaseHandler.getUser(username)
    init {
        mContext = context
        Price=0
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
            val name = rowmain.findViewById<TextView>(R.id.dishname)
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
                Price = Price+billAmount
            }
            name.text = employeeName
            id.text = employeeId
            period.text = currentDate
            amount.text = billAmount.toString()
            consumed.text = dishConsumed
        }
        useramount.text = Price.toString()
        return rowmain
    }
}