package com.czh.adapter

import czh.adapter.AnkoAdapter
import czh.adapter.BaseViewHolder

class DemoAdapter(ui: DemoAdapterUI, mData: List<String>?) : AnkoAdapter<DemoAdapterUI, String>(ui, mData) {
    override fun convert(holder: BaseViewHolder, ui: DemoAdapterUI, item: String?) = with(ui) {
        tv.text = item
    }
}

