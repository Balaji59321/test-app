package com.example.mommysfood.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
//import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.example.mommysfood.DataBase
import com.example.mommysfood.R
import kotlinx.android.synthetic.main.fragment_slideshow.*


class SlideshowFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        slideshowViewModel =
            ViewModelProviders.of(this).get(SlideshowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)

        val databaseHandler: DataBase = DataBase(this.context!!)
        val response = databaseHandler.getallDish()
        databaseHandler.close()
        if(response.count()>=0) {
            val checkBox1 = root.findViewById<CheckBox>(R.id.checkBox1).text
            val checkBox2 = root.findViewById<CheckBox>(R.id.checkBox2).text
            val checkBox3 = root.findViewById<CheckBox>(R.id.checkBox3).text
            val checkBox4 = root.findViewById<CheckBox>(R.id.checkBox4).text
            val checkBox5 = root.findViewById<CheckBox>(R.id.checkBox5).text
            val checkBox6 = root.findViewById<CheckBox>(R.id.checkBox6).text
            val checkBox7 = root.findViewById<CheckBox>(R.id.checkBox7).text
            val checkBox8 = root.findViewById<CheckBox>(R.id.checkBox8).text
            val checkBox9 = root.findViewById<CheckBox>(R.id.checkBox9).text
            val checkBox10 = root.findViewById<CheckBox>(R.id.checkBox10).text

            for (i in response) {
                if (checkBox1.toString() == i) {
                    root.findViewById<CheckBox>(R.id.checkBox1).isChecked = true
                }
                else if (checkBox2.toString() == i) {
                    root.findViewById<CheckBox>(R.id.checkBox2).isChecked = true
                }
                else if (checkBox3.toString() == i) {
                    root.findViewById<CheckBox>(R.id.checkBox3).isChecked = true
                }
                else if (checkBox4.toString() == i) {
                    root.findViewById<CheckBox>(R.id.checkBox4).isChecked = true
                }
                else if (checkBox5.toString() == i) {
                    root.findViewById<CheckBox>(R.id.checkBox5).isChecked = true
                }
                else if (checkBox6.toString() == i) {
                    root.findViewById<CheckBox>(R.id.checkBox6).isChecked = true
                }
                else if (checkBox7.toString() == i) {
                    root.findViewById<CheckBox>(R.id.checkBox7).isChecked = true
                }
                else if (checkBox8.toString() == i) {
                    root.findViewById<CheckBox>(R.id.checkBox8).isChecked = true
                }
                else if (checkBox9.toString() == i) {
                    root.findViewById<CheckBox>(R.id.checkBox9).isChecked = true
                }
                else if (checkBox10.toString() == i) {
                    root.findViewById<CheckBox>(R.id.checkBox10).isChecked = true
                }
            }
        }

        val save = root.findViewById<Button>(R.id.save)
        save.setOnClickListener()
        {
            val databaseHandler: DataBase = DataBase(this.context!!)
            for (i in 20 downTo 1) {
                when (i) {
                    1 -> {
                        val CheckBox1 = root.findViewById<CheckBox>(R.id.checkBox1);
                        if (CheckBox1.isChecked()) {

                            databaseHandler.addDish(
                                DbModelClass(
                                    CheckBox1.getText().toString(),
                                    30,
                                    1
                                )
                            )
                            Toast.makeText(this.context, "Saved Successfully", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            databaseHandler.addDish(
                                DbModelClass(
                                    CheckBox1.getText().toString(),
                                    30,
                                    0
                                )
                            )
                            Toast.makeText(this.context, "Saved Successfully", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    2 -> {
                        val CheckBox2 = root.findViewById<CheckBox>(R.id.checkBox2);
                        if (CheckBox2.isChecked()) {

                            databaseHandler.addDish(
                                DbModelClass(
                                    CheckBox2.getText().toString(),
                                    40,
                                    1
                                )
                            )
                            Toast.makeText(this.context, "Saved Successfully", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            databaseHandler.addDish(
                                DbModelClass(
                                    CheckBox2.getText().toString(),
                                    40,
                                    0
                                )
                            )
                            Toast.makeText(this.context, "Saved Successfully", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    3 -> {
                        val CheckBox3 = root.findViewById<CheckBox>(R.id.checkBox3);
                        if (CheckBox3.isChecked()) {

                            databaseHandler.addDish(
                                DbModelClass(
                                    CheckBox3.getText().toString(),
                                    40,
                                    1
                                )
                            )
                            Toast.makeText(this.context, "Saved Successfully", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            databaseHandler.addDish(
                                DbModelClass(
                                    CheckBox3.getText().toString(),
                                    40,
                                    0
                                )
                            )
                            Toast.makeText(this.context, "Saved Successfully", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    4 -> {
                        val CheckBox4 = root.findViewById<CheckBox>(R.id.checkBox4);
                        if (CheckBox4.isChecked()) {

                            databaseHandler.addDish(
                                DbModelClass(
                                    CheckBox4.getText().toString(),
                                    40,
                                    1
                                )
                            )
                            Toast.makeText(this.context, "Saved Successfully", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            databaseHandler.addDish(
                                DbModelClass(
                                    CheckBox4.getText().toString(),
                                    40,
                                    0
                                )
                            )
                            Toast.makeText(this.context, "Saved Successfully", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    5 -> {
                        val CheckBox5 = root.findViewById<CheckBox>(R.id.checkBox5);
                        if (CheckBox5.isChecked()) {

                            databaseHandler.addDish(
                                DbModelClass(
                                    CheckBox5.getText().toString(),
                                    40,
                                    1
                                )
                            )
                            Toast.makeText(this.context, "Saved Successfully", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            databaseHandler.addDish(
                                DbModelClass(
                                    CheckBox5.getText().toString(),
                                    40,
                                    0
                                )
                            )
                            Toast.makeText(this.context, "Saved Successfully", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    6 -> {
                        val CheckBox6 = root.findViewById<CheckBox>(R.id.checkBox6);
                        if (CheckBox6.isChecked()) {

                            databaseHandler.addDish(
                                DbModelClass(
                                    CheckBox6.getText().toString(),
                                    40,
                                    1
                                )
                            )
                            Toast.makeText(this.context, "Saved Successfully", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            databaseHandler.addDish(
                                DbModelClass(
                                    CheckBox6.getText().toString(),
                                    40,
                                    0
                                )
                            )
                            Toast.makeText(this.context, "Saved Successfully", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    7 -> {
                        val CheckBox7 = root.findViewById<CheckBox>(R.id.checkBox7);
                        if (CheckBox7.isChecked()) {

                            databaseHandler.addDish(
                                DbModelClass(
                                    CheckBox7.getText().toString(),
                                    40,
                                    1
                                )
                            )
                            Toast.makeText(this.context, "Saved Successfully", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            databaseHandler.addDish(
                                DbModelClass(
                                    CheckBox7.getText().toString(),
                                    40,
                                    0
                                )
                            )
                            Toast.makeText(this.context, "Saved Successfully", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    8 -> {
                        val CheckBox8 = root.findViewById<CheckBox>(R.id.checkBox8);
                        if (CheckBox8.isChecked()) {

                            databaseHandler.addDish(
                                DbModelClass(
                                    CheckBox8.getText().toString(),
                                    40,
                                    1
                                )
                            )
                            Toast.makeText(this.context, "Saved Successfully", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            databaseHandler.addDish(
                                DbModelClass(
                                    CheckBox8.getText().toString(),
                                    40,
                                    0
                                )
                            )
                            Toast.makeText(this.context, "Saved Successfully", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    9 -> {
                        val CheckBox9 = root.findViewById<CheckBox>(R.id.checkBox9);
                        if (CheckBox9.isChecked()) {

                            databaseHandler.addDish(
                                DbModelClass(
                                    CheckBox9.getText().toString(),
                                    40,
                                    1
                                )
                            )
                            Toast.makeText(this.context, "Saved Successfully", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            databaseHandler.addDish(
                                DbModelClass(
                                    CheckBox9.getText().toString(),
                                    40,
                                    0
                                )
                            )
                            Toast.makeText(this.context, "Saved Successfully", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    10 -> {
                        val CheckBox10 = root.findViewById<CheckBox>(R.id.checkBox10);
                        if (CheckBox10.isChecked()) {

                            databaseHandler.addDish(
                                DbModelClass(
                                    CheckBox10.getText().toString(),
                                    40,
                                    1
                                )
                            )
                            Toast.makeText(this.context, "Saved Successfully", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            databaseHandler.addDish(
                                DbModelClass(
                                    CheckBox10.getText().toString(),
                                    40,
                                    0
                                )
                            )
                            Toast.makeText(this.context, "Saved Successfully", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
        databaseHandler.close()
        return root
    }
}
