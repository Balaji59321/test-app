package com.example.mommysfood.ui.gallery

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.mommysfood.DatabaseHandler
import com.example.mommysfood.EmpModelClass
import com.example.mommysfood.R
import com.example.mommysfood.ui.home.dishconsumedmodelclass
import com.example.mommysfood.ui.tools.MyCustomAdapter
import com.example.mommysfood.ui.tools.ToolsFragment
import com.example.mommysfood.ui.tools.dishmodelclass

class GalleryFragment : Fragment() {
    private lateinit var galleryViewModel: GalleryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        val db = DatabaseHandler(this.context!!)
        var data = ArrayList<EmpModelClass>()
        if (data == null) {
            Toast.makeText(root.context, "No Record", Toast.LENGTH_LONG).show()
        } else {
            val arrayAdapter: ArrayAdapter<*>

            val userlist = root.findViewById<ListView>(R.id.listview)

            //arrayAdapter = ArrayAdapter(root.context, R.layout.dish_consumed, status)
            val customAdapter = MyCustomAdapter1(root.context)
            userlist.adapter = customAdapter
        }
        return root
    }
}

private class MyCustomAdapter1(context: Context) : BaseAdapter() {

    val dishModelArrayList: ArrayList<dishconsumedmodelclass>
    private val mContext: Context
    private val layoutInflater = LayoutInflater.from(context)
    val databaseHandler: DatabaseHandler = DatabaseHandler(context)
    val status = databaseHandler.getallEmployee()
    val db = DatabaseHandler(context)
    var flag = db.getEmployee()

    init {
        mContext = context
        val status = databaseHandler.getallEmployee()
        val entry_list = ArrayList<dishconsumedmodelclass>()
        for (i in 0..status.count() - 1) {
            entry_list.add(dishconsumedmodelclass("", "", "", "", ""))
        }
        dishModelArrayList = entry_list
    }

    override fun getCount(): Int {
        return status.count()
    }

    override fun getItem(p0: Int): Any {
        return "Hello"
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View? {
        databaseHandler.close()
        val layoutInflater = LayoutInflater.from(mContext);
        val rowmain = layoutInflater.inflate(R.layout.dish_consumed, viewGroup, false)

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
                //flag.position = flag.position+1
            }
            name.text = employeeName
            id.text = employeeId
            period.text = currentDate
            amount.text = billAmount.toString()
            consumed.text = dishConsumed
        }
        return rowmain
    }
}
//        val viewHolder: ViewHolder
//        val rowView: View?
//        if (view == null) {
//            //rowView = layoutInflater.inflate(R.layout.list_item, viewGroup, false)
//            rowView = layoutInflater.inflate(R.layout.dish_consumed, viewGroup, false)
//            viewHolder = ViewHolder(rowView)
//            rowView.tag = viewHolder
//            //println("if executed")
//        } else {
//            rowView = view
//            viewHolder = rowView.tag as ViewHolder
//            //println("else executed")
//        }
//
//        val layoutInflater = LayoutInflater.from(mContext);
//        val rowmain = layoutInflater.inflate(R.layout.dish_consumed, viewGroup, false)
//
//        if (flag.count == 0) {
//            Toast.makeText(mContext, "No Transaction to show", Toast.LENGTH_SHORT).show()
//        } else {
//            val name = rowmain.findViewById<TextView>(R.id.empname)
//            val id = rowmain.findViewById<TextView>(R.id.identitynumber)
//            val period = rowmain.findViewById<TextView>(R.id.datetime)
//            val consumed = rowmain.findViewById<TextView>(R.id.dishconsumed)
//            val amount = rowmain.findViewById<TextView>(R.id.totalamount)
//            var employeeId = ""
//            var employeeName = ""
//            var dishConsumed = ""
//            var billAmount = 0
//            var currentDate = ""
//            flag.position + 1
//            if (flag.moveToNext()){
//                employeeId = flag.getString(flag.getColumnIndex("id"))
//                employeeName = flag.getString(flag.getColumnIndex("name"))
//                dishConsumed = flag.getString(flag.getColumnIndex("dish"))
//                billAmount = flag.getInt(flag.getColumnIndex("amount"))
//                currentDate = flag.getString(flag.getColumnIndex("date"))
//                //flag.position = flag.position+1
////                viewHolder.dishname.text = MyCustomAdapter1.dishModelArrayList.get(position).empanme
////                viewHolder.identitynumber.text = GalleryFragment.dishModelArrayList.get(position).empno
////                viewHolder.datetime.text = GalleryFragment.dishModelArrayList.get(position).datetime
////                viewHolder.dishconsumed.text = GalleryFragment.dishModelArrayList.get(position).dishconsumed
////                viewHolder.totalamount.text =  GalleryFragment.dishModelArrayList.get(position).price
//            }
//            name.text = employeeName
//            id.text = employeeId
//            period.text = currentDate
//            amount.text = billAmount.toString()
//            consumed.text = dishConsumed
////            GalleryFragment.dishModelArrayList.get(position).empanme = employeeName
////            GalleryFragment.dishModelArrayList.get(position).empno = employeeId
////            GalleryFragment.dishModelArrayList.get(position).datetime = currentDate
////            GalleryFragment.dishModelArrayList.get(position).dishconsumed = dishConsumed
////            GalleryFragment.dishModelArrayList.get(position).price = billAmount.toString()
//        }
//        return rowView
//    }
//    private class ViewHolder(view: View?) {
//        //val itemName = view?.findViewById(R.id.list_item_text_view) as TextView
//        val dishname = view?.findViewById(R.id.empname) as TextView
//        val identitynumber = view?.findViewById(R.id.identitynumber) as TextView
//        val datetime = view?.findViewById(R.id.datetime) as TextView
//        val dishconsumed = view?.findViewById(R.id.dishconsumed) as TextView
//        val totalamount = view?.findViewById(R.id.totalamount) as TextView
//    }
//}



