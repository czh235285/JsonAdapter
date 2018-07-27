## JsonAdapter

简单页面adapter封装，直接传入JsonArray。
减少Bean类文件，如果遇到经常修改字段的后台，用这个就不用到处改了。
复杂页面，经常修改数据的时候，JsonArray不方便修改，就不要使用这个了。

* 仅适用kotlin开发者
* 仅适用kotlin开发者
* 仅适用kotlin开发者
* 重要事情说三遍，只是用于自己开发，kotlin可以直接用xml中的id，所以ViewHolder我也没继续封装。。。懒癌晚期

use Gradle:

```
repositories {
  maven { url "https://jitpack.io" }
  mavenCentral()
  google()
}
dependencies {
  implementation 'com.github.czh235285:JsonAdapter:1.0.1'
}
```
## 介绍

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

支持空布局（添加头部、尾部未写，有需要的Issues） :

```

   adapter.setEmptyView(layoutInflater.inflate(R.layout.empty,null))

```

item点击事件

```

   adapter.setOnItemClickListener { adapter, view, position ->
            TODO()
        }

```

刷新数据/加载更多 :


```

    adapter.replaceData(JSONArray)
    adapter.addData(JSONArray)

```