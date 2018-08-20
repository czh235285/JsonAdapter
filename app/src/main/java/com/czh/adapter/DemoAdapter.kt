package com.czh.adapter

import android.view.Gravity
import android.view.View
import android.widget.TextView
import czh.library.SimpleAnkoAdapter
import czh.library.BaseViewHolder
import org.jetbrains.anko.*

class DemoAdapter(mData: List<String>?) : SimpleAnkoAdapter<String>(mData) {

    val ui = DemoAdapterUI()
    override fun convert(holder: BaseViewHolder, item: String?) = with(ui) {
        tv.text = item
    }

    override fun ankoLayout(): View {
        return ui.createView(AnkoContext.createReusable(mContext, this))
    }

}

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
