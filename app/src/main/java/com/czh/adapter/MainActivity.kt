package com.czh.adapter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DensityUtils.setCustomDensity(this, application)
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


//        rcv.layoutManager = LinearLayoutManager(this)
//        rcv.adapter = DemoAdapter(DemoAdapterUI(),arrayListOf("1", "2", "3"))
        rcv.layoutManager = LinearLayoutManager(this)
        val a = JSONObject(s).getJSONArray("a")
        val adapter = TestAdapter(R.layout.item, a)
        adapter.setEmptyView(layoutInflater.inflate(R.layout.empty, null))

        rcv.adapter = adapter

        adapter.setHeaderFooterEmpty(true, false)

        adapter.setOnItemClickListener { view, position, item ->
            Toast.makeText(this@MainActivity, "点击了position==$position,item=${item.optString("name")}", Toast.LENGTH_LONG).show()
        }

        adapter.setOnItemLongClickListener { view, position, item ->
            Toast.makeText(this@MainActivity, "长按了position==$position,item=${item.optString("name")}", Toast.LENGTH_LONG).show()
        }
        tv1.setOnClickListener {
            adapter.replaceData(null)
        }
        tv2.setOnClickListener {
            adapter.addHeaderView(layoutInflater.inflate(R.layout.head, null))
        }
        tv3.setOnClickListener {
            adapter.addFooterView(layoutInflater.inflate(R.layout.foot, null))
        }
        tv4.setOnClickListener {
            adapter.addData(JSONObject(s).getJSONArray("a"))
        }
    }
    }
