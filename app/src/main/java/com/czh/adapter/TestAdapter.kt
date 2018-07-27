package com.czh.adapter

import org.json.JSONArray
import org.json.JSONObject

import czh.library.BaseViewHolder
import czh.library.JsonAdapter
import kotlinx.android.synthetic.main.item.view.*

class TestAdapter(mLayoutResId: Int, mData: JSONArray?) : JsonAdapter(mLayoutResId, mData) {

    override fun convert(holder: BaseViewHolder, item: JSONObject?) {
        holder.itemView.run {
            item?.getString("name")?.let { name.text = it }
        }
    }
}
