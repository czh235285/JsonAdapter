package com.czh.adapter

import android.view.View
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


