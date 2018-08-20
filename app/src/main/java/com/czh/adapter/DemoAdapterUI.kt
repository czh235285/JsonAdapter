package com.czh.adapter

import android.view.Gravity
import android.view.View
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.design.tabLayout

class DemoAdapterUI : AnkoComponent<DemoAdapter> {
    lateinit var tv: TextView

    override fun createView(ui: AnkoContext<DemoAdapter>) = with(ui) {
        verticalLayout {
            tv = textView("测试") {
                textSize = 20f
                setShape("#cccc00", 10f)
                setPadding(20, 20, 20, 20)
                colorString("#ffffff")
                gravity = Gravity.CENTER


            }.lparams(dip(180)) {

                setMargins(20, 20, 20, 20)
            }

        }
    }
}