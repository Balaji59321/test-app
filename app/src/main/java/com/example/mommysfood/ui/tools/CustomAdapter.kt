package com.example.mommysfood.ui.tools

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.example.mommysfood.DataBase
import com.example.mommysfood.R

class CustomAdapter(context: Context) : BaseAdapter() {

    public val mContext: Context

    val databaseHandler: DataBase = DataBase(context)
    var availableDish1: Map<String, Int> = databaseHandler.getallDish1()
    var availableDish = databaseHandler.getallDish()

    var dish = ""

    // Global Rate
    val globeRate = 0

    init {
        mContext = context
    }

    override fun getCount(): Int {
        return availableDish.count()
    }

    override fun getItem(p0: Int): Any {
        return "Hello"
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View, p2: ViewGroup): View {
        var holder: ViewHolder = ViewHolder()
        if (p1 != null) {
            val inflater =
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val rowmain = inflater.inflate(R.layout.dish_list, p2, false)
            holder.dishname = rowmain.findViewById<TextView>(R.id.dishname)
            databaseHandler.close()
            val amount1 = rowmain.findViewById<TextView>(R.id.amount)
            val listView = rowmain.findViewById<ListView>(R.id.listview)
            dish = availableDish.get(p0)
            //holder.dishname.text = dish
            //amount = availableDish1[dish]!!
            holder.increment = rowmain.findViewById<Button>(R.id.increase)
            holder.decrement = rowmain.findViewById<Button>(R.id.decrease)
            holder.count = rowmain.findViewById<TextView>(R.id.quantity)
        }
        else{
            holder= p1.getTag() as ViewHolder
        }
        var counter = 0
        var rate = 0
        var amount = 0
//        databaseHandler.close()
//        val layoutInflater = LayoutInflater.from(mContext);
//        val rowmain = layoutInflater.inflate(R.layout.dish_list, p2, false)


//        val dishname = rowmain.findViewById<TextView>(R.id.dishname)
//        val amount1 = rowmain.findViewById<TextView>(R.id.textView6)
//        var amount = 0
//        val listView = rowmain.findViewById<ListView>(R.id.listview)
//        dish = availableDish.get(p0)
//        dishname.text = dish
//        amount = availableDish1[dish]!!
//        val increment = rowmain.findViewById<Button>(R.id.increase)
//        val decrement = rowmain.findViewById<Button>(R.id.decrease)
//        val count = rowmain.findViewById<TextView>(R.id.quantity)
//        var counter = 0
//        var rate = 0
        holder.increment?.setTag(R.integer.increment_view,p1)
        holder.increment?.setTag(R.integer.increment_pos,p1)
        holder.increment?.setOnClickListener {
            val tempview = holder.increment?.getTag(R.integer.increment_view) as View
            val tv = tempview.findViewById<View>(R.id.quantity) as TextView
            val pos = holder.increment?.getTag(R.integer.increment_pos) as Int

            val number = tv.text.toString().toInt() + 1
            tv.text = number.toString()
            ToolsFragment.modelArrayList.get(pos).quantity = number.toString()
        }
//        decrement.setOnClickListener {
//            if (counter > 0) {
//                flag = true
//                counter -= 1
//                rate -= amount
//                Dish.add(dishname.text.toString())
//                hashMap.put(dishname.text.toString(), counter)
//                Amount = Amount - (availableDish1[dish]!!)
//                amount1.text = rate.toString()
//                count.text = counter.toString()
//                hashMap.put(dish, counter)
//            } else {
//                Dish.remove(dishname.text.toString())
//            }
//        }
        return p1
    }
        class ViewHolder {
            lateinit var increment: Button
            lateinit var decrement: Button
            lateinit var dishname: TextView
            lateinit var count: TextView
            lateinit var globe_rate: TextView
        }

    }


//decrement.setOnClickListener {
//    if (counter > 0) {
//        flag = true
//        counter -= 1
//        rate -= amount
//        Dish.add(dishname.text.toString())
//        hashMap.put(dishname.text.toString(), counter)
//        Amount = Amount - (availableDish1[dish]!!)
//        amount1.text = rate.toString()
//        count.text = counter.toString()
//        hashMap.put(dish, counter)
//    } else {
//        Dish.remove(dishname.text.toString())
//    }
//}

//counter += 1
//if (counter > 0) {
//    flag = true
//    rate += amount
//    Dish.add(dishname.text.toString())
//    hashMap.put(dishname.text.toString(), counter)
//    Amount = Amount + (availableDish1[dish]!!)
//    amount1.text = rate.toString()
//    count.text = counter.toString()
//} else {
//    Dish.remove(dishname.text.toString())
//}