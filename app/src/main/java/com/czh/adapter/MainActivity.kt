package com.czh.adapter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val s = """
{
  "a":[
    {
      "name" : "张三"
    },{
      "name" : "李四"
    },{
      "name" : "王麻子"
    }
    ]
}
        """

        rcv.layoutManager = LinearLayoutManager(this)
        val a = JSONObject(s).getJSONArray("a")
        val adapter = TestAdapter(R.layout.item, a)
        adapter.setEmptyView(layoutInflater.inflate(R.layout.empty, null))
        rcv.adapter = adapter

    }
}
