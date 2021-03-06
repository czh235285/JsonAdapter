## JsonAdapter
[![](https://jitpack.io/v/czh235285/JsonAdapter.svg)](https://jitpack.io/#czh235285/JsonAdapter)


* 可以直接传入JsonArray的adapter精简封装，占用体积小
* 简单页面，不想写bean类的时候，直接传入JsonArray。
* 减少Bean类文件，如果遇到经常修改字段的后台，用这个就不用到处改了。
* 复杂页面，经常修改数据的时候，JsonArray不方便修改，就不要使用这个了。

use Gradle:

```
repositories {
  maven { url "https://jitpack.io" }
  mavenCentral()
  google()
}
dependencies {
 implementation 'com.github.czh235285:JsonAdapter:1.2.0'
}
```
## 用法介绍

使用代码 :

```

class TestAdapter(mLayoutResId: Int, mData: JSONArray?) : JsonAdapter(mLayoutResId, mData) {
    override fun convert(holder: BaseViewHolder, item: JSONObject?) {
        holder.itemView.run {
            item?.optString("name")?.let { tvName.text = it }
        }
    }
}

```

支持空布局、添加头部、尾部:

```

   adapter.setEmptyView(layoutInflater.inflate(R.layout.empty,null))
   adapter.addHeaderView(layoutInflater.inflate(R.layout.head, null))
   adapter.addFooterView(layoutInflater.inflate(R.layout.foot, null))

```

item点击/长按事件:

```

    adapter.setOnItemClickListener { view, position, item ->
            Toast.makeText(this@MainActivity, "点击了position==$position,item=${item.optString("name")}", Toast.LENGTH_LONG).show()
        }
        
	adapter.setOnItemLongClickListener { view, position, item ->
            Toast.makeText(this@MainActivity, "长按了position==$position,item=${item.optString("name")}", Toast.LENGTH_LONG).show()
        }

```

刷新数据/加载更多 :


```

    adapter.replaceData(JSONArray)
    adapter.addData(JSONArray)

```
