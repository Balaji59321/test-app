package com.example.mommysfood.ui.tools

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.mommysfood.DataBase
import com.example.mommysfood.DatabaseHandler
import com.example.mommysfood.EmpModelClass
import com.example.mommysfood.R
import kotlinx.android.synthetic.main.dish_list.*
import kotlinx.android.synthetic.main.fragment_tools.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


var Dish = mutableSetOf<String>()
val hashMap: HashMap<String, Int> = HashMap<String, Int>()
var Amount = 0
var flag = false

class ToolsFragment() : Fragment() {

    private lateinit var toolsViewModel: ToolsViewModel

    companion object {
        lateinit var modelArrayList: ArrayList<dishmodelclass>
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        toolsViewModel =
            ViewModelProviders.of(this).get(ToolsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_tools, container, false)

        val button1: EditText? = root.findViewById(R.id.u_date)
        val c = Calendar.getInstance()
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val formattedDate: String = df.format(c.time)
        val netRate = root.findViewById<TextView>(R.id.globe_rate)
        val dish = ArrayList<String>()
        val lv: ListView = root.findViewById(R.id.listview)
        button1?.setText(formattedDate)

        val btn = root.findViewById<View>(R.id.save) as Button
        btn.setOnClickListener {
            if (flag == true) {
                //print("if executed")
                //println(Dish)
                //println(Amount)
                netRate.text = Amount.toString()
            } else {
                //print("else executed")
                //println(Dish)
                //println(Amount)
            }
            val id = u_empid.text.toString()
            val name: String = u_name.text.toString()
//            val dish = u_dish.text.toString()
//            val amount: Int = u_bill.text.toString().toInt()
            val date: String = u_date.text.toString()
            val databaseHandler: DatabaseHandler = DatabaseHandler(this.context!!)
            if (id.trim() != "" && name.trim() != "" && date.trim() != "") {
                val status =
                    databaseHandler.addEmployee(
                        EmpModelClass(
                            id,
                            name,
                            Dish.toString(),
                            Amount,
                            date
                        )
                    )
                if (status > -1) {
                    Toast.makeText(this.context, "Saved Successfully", Toast.LENGTH_LONG).show()
                    u_empid.text.clear()
                    u_name.text.clear()
//                    u_dish.text.clear()
//                    u_bill.text.clear()
                    val c = Calendar.getInstance()
                    val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    val formattedDate: String = df.format(c.time)
                    u_date.setText(formattedDate)
                    Amount=0
                    Dish.clear()
                    val Amount = root.findViewById<TextView>(R.id.globe_rate)
                    Amount.text = 0.toString()
                    flag = true
                    val list: ArrayList<dishmodelclass> = ArrayList<dishmodelclass>()
                    val databaseHandler: DataBase = DataBase(this.context!!)
                    val availableDish = databaseHandler.getallDish()
                    for (i in 0..availableDish.count()-1) {
                        val model = dishmodelclass( availableDish[i], "0","0")
                        list.add(model)
                    }
                    modelArrayList=list
                    val customAdapter = MyCustomAdapter(root.context,list,amount)
                    lv.adapter=customAdapter
                    flag = false
                }
            } else {
                Toast.makeText(
                    this.context,
                    "id or name or email cannot be blank",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        val textView = root.findViewById<Button>(R.id.add_dish)
        textView.setOnClickListener {
            val listview = root.findViewById<ListView>(R.id.listview)
            //listview.adapter = MyCustomAdapter(root.context)
            //modelArrayList = getModel()
            val names = mutableListOf<String>()
            val list: ArrayList<dishmodelclass> = ArrayList<dishmodelclass>()
            val databaseHandler: DataBase = DataBase(this.context!!)
            val availableDish = databaseHandler.getallDish()
            for (i in 0..availableDish.count()-1) {
                val model = dishmodelclass( availableDish[i], "0","0")
                list.add(model)
            }
            modelArrayList=list
            val amount = root.findViewById<TextView>(R.id.globe_rate)
            amount.text=0.toString()
            val customAdapter = MyCustomAdapter(root.context,list,amount)
            lv.adapter = customAdapter

        }
        return root
    }

//    private fun getModel(): ArrayList<dishmodelclass> {
//        val list: ArrayList<dishmodelclass> = ArrayList<dishmodelclass>()
//        val databaseHandler: DataBase = DataBase(this.context!!)
//        val availableDish = databaseHandler.getallDish()
//        for (i in 0..9) {
//            val model = dishmodelclass( "hello", "1")
//            list.add(model)
//        }
//        return list
//    }
}

class MyCustomAdapter(context: Context,val items: ArrayList<dishmodelclass>,val amount: TextView) : BaseAdapter() {
    private val layoutInflater = LayoutInflater.from(context)
    val databaseHandler: DataBase = DataBase(context)
    var availableDish1: Map<String, Int> = databaseHandler.getallDish1()

    override fun getCount(): Int {
        return items.size
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View? {
        val viewHolder: ViewHolder
        val rowView: View?
        if (flag) {

            if (view == null) {
                //rowView = layoutInflater.inflate(R.layout.list_item, viewGroup, false)
                rowView = layoutInflater.inflate(R.layout.dish_list, viewGroup, false)
                viewHolder = ViewHolder(rowView)
                rowView.tag = viewHolder
                //println("if executed")
            } else {
                rowView = view
                viewHolder = rowView.tag as ViewHolder
                //println("else executed")
            }

//            viewHolder.dishname.text = ToolsFragment.modelArrayList.get(position).dish
//            viewHolder.quantity.text =  ToolsFragment.modelArrayList.get(position).quantity
//            //viewHolder.price.text = items.toString()
//            viewHolder.price.text= ToolsFragment.modelArrayList.get(position).amount

            viewHolder.increment?.setOnClickListener {
                val tv = rowView?.findViewById<View>(R.id.quantity) as TextView
                val tv1 = rowView?.findViewById<View>(R.id.amount) as TextView
                val number = 0
                var number1 = ""
                if (number > 0) {
                    Dish.add(ToolsFragment.modelArrayList.get(position).dish)
                    number1 =
                        (number * (availableDish1[ToolsFragment.modelArrayList.get(position).dish]!!)).toString()
                    //viewHolder.price.text = number1.toString()
                    Amount = 0
                    amount.text = Amount.toString()
                    tv.text = number.toString()
                    tv1.text = number1
                    ToolsFragment.modelArrayList.get(position).quantity = number.toString()
                    ToolsFragment.modelArrayList.get(position).amount = number1
                }
            }

            viewHolder.decrement?.setOnClickListener {
                val tv = rowView?.findViewById<View>(R.id.quantity) as TextView
                val tv1 = rowView?.findViewById<View>(R.id.amount) as TextView
                var number1 = ""
                if (tv.text.toString().toInt() > 0) {
                    val number = 0
                    number1 =
                        (number * (availableDish1[ToolsFragment.modelArrayList.get(position).dish]!!)).toString()
                    viewHolder.price.text = number1.toString()
                    Amount = 0
                    amount.text = Amount.toString()
                    tv.text = number.toString()
                    tv1.text = number1
                    ToolsFragment.modelArrayList.get(position).quantity = number.toString()
                    ToolsFragment.modelArrayList.get(position).amount = number1
                } else {
                    Dish.remove(ToolsFragment.modelArrayList.get(position).dish)
                }

            }
            return rowView
        }


        if (view == null) {
            //rowView = layoutInflater.inflate(R.layout.list_item, viewGroup, false)
            rowView = layoutInflater.inflate(R.layout.dish_list, viewGroup, false)
            viewHolder = ViewHolder(rowView)
            rowView.tag = viewHolder
            //println("if executed")
        } else {
            rowView = view
            viewHolder = rowView.tag as ViewHolder
            //println("else executed")
        }

        viewHolder.dishname.text = ToolsFragment.modelArrayList.get(position).dish
        viewHolder.quantity.text =  ToolsFragment.modelArrayList.get(position).quantity
        //viewHolder.price.text = items.toString()
        viewHolder.price.text= ToolsFragment.modelArrayList.get(position).amount

        viewHolder.increment?.setOnClickListener {
            val tv = rowView?.findViewById<View>(R.id.quantity) as TextView
            val tv1 = rowView?.findViewById<View>(R.id.amount) as TextView
            val number = tv.text.toString().toInt() + 1
            var number1 = ""
            if(number > 0) {
                Dish.add(ToolsFragment.modelArrayList.get(position).dish)
                number1 = (number  * (availableDish1[ToolsFragment.modelArrayList.get(position).dish]!!)).toString()
                //viewHolder.price.text = number1.toString()
                Amount += (availableDish1[ToolsFragment.modelArrayList.get(position).dish]!!)
                amount.text= Amount.toString()
                tv.text = number.toString()
                tv1.text = number1
                ToolsFragment.modelArrayList.get(position).quantity = number.toString()
                ToolsFragment.modelArrayList.get(position).amount = number1
            }
        }

        viewHolder.decrement?.setOnClickListener {
            val tv = rowView?.findViewById<View>(R.id.quantity) as TextView
            val tv1 = rowView?.findViewById<View>(R.id.amount) as TextView
            var number1 = ""
            if(tv.text.toString().toInt() > 0){
                val number = tv.text.toString().toInt() - 1
                number1 = (number  * (availableDish1[ToolsFragment.modelArrayList.get(position).dish]!!)).toString()
                viewHolder.price.text = number1.toString()
                Amount -= (availableDish1[ToolsFragment.modelArrayList.get(position).dish]!!)
                amount.text= Amount.toString()
                tv.text = number.toString()
                tv1.text =  number1
                ToolsFragment.modelArrayList.get(position).quantity = number.toString()
                ToolsFragment.modelArrayList.get(position).amount = number1
            }
            else{
                Dish.remove(ToolsFragment.modelArrayList.get(position).dish)
            }

        }
        return rowView
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    private class ViewHolder(view: View?) {
        //val itemName = view?.findViewById(R.id.list_item_text_view) as TextView
        val dishname = view?.findViewById(R.id.dishname) as TextView
        val increment = view?.findViewById(R.id.increase) as Button
        val decrement = view?.findViewById(R.id.decrease) as Button
        val quantity = view?.findViewById(R.id.quantity) as TextView
        val price = view?.findViewById(R.id.amount) as TextView
    }
}
//    private class MyCustomAdapter(context: Context) : BaseAdapter() {
////
////        private val mContext: Context
////
////        val databaseHandler: DataBase = DataBase(context)
////        val availableDish = databaseHandler.getallDish()
////        init {
////            mContext = context
////        }
////
////        override fun getCount(): Int {
////            return availableDish.count()
////        }
////
////        override fun getItem(p0: Int): Any {
////            return "Hello"
////        }
////
////        override fun getItemId(p0: Int): Long {
////            return p0.toLong()
////        }
////
////        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
////            val layoutInflater = LayoutInflater.from(mContext);
////            val rowmain = layoutInflater.inflate(R.layout.dish_list, p2, false)
////
////            val dishname = rowmain.findViewById<TextView>(R.id.dishname)
//////                dishname.text=names.get(p0)
////            // print(availableDish[p0])
////
////            dishname.text = availableDish.get(p0)
////            val increment = rowmain.findViewById<Button>(R.id.increase)
////            val decrement = rowmain.findViewById<Button>(R.id.decrease)
////            val count = rowmain.findViewById<TextView>(R.id.quantity)
////            var counter = 0
////            increment.setOnClickListener {
////                counter += 1
////                count.text = counter.toString()
////            }
////            decrement.setOnClickListener {
////                if (counter > 0) {
////                    counter -= 1
////                    count.text = counter.toString()
////                }
////            }
////            val Dish = ArrayList<String>()
////            if (counter > 0) {
////                Dish.add(dishname.text.toString())
////            }
////            println(Dish)
////        return rowmain
////    }
////}