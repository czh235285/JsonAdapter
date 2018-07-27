package czh.library;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class JsonAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    protected int mLayoutResId;
    protected JSONArray mData;
    protected Context mContext;
    protected LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    //empty
    private FrameLayout mEmptyLayout;
    private boolean mIsUseEmpty = true;

    public static final int EMPTY_VIEW = 0x00000123;

    public int getEmptyViewCount() {
        if (mEmptyLayout == null || mEmptyLayout.getChildCount() == 0) {
            return 0;
        }
        if (!mIsUseEmpty) {
            return 0;
        }
        if (mData.length() != 0) {
            return 0;
        }
        return 1;
    }


    public JsonAdapter(int mLayoutResId, JSONArray mData) {
        this.mLayoutResId = mLayoutResId;
        this.mData = mData == null ? new JSONArray() : mData;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        this.mLayoutInflater = LayoutInflater.from(mContext);
        BaseViewHolder holder = new BaseViewHolder(mLayoutInflater.inflate(mLayoutResId, parent,
                false));
        bindViewClickListener(holder);
        return getEmptyViewCount() == 1 ? new BaseViewHolder(mEmptyLayout) : holder;
    }


    private void bindViewClickListener(final BaseViewHolder baseViewHolder) {
        if (baseViewHolder == null) {
            return;
        }
        final View view = baseViewHolder.itemView;
        if (view == null) {
            return;
        }
        if (getOnItemClickListener() != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getOnItemClickListener().onItemClick(JsonAdapter.this, v, baseViewHolder
                            .getLayoutPosition());
                }
            });
        }
        if (getOnItemLongClickListener() != null) {
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return getOnItemLongClickListener().onItemLongClick(JsonAdapter.this, v,
                            baseViewHolder.getLayoutPosition());
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        if (getItemViewType(position) != EMPTY_VIEW) {
            convert(holder, mData.optJSONObject(position));
        }
    }

    @Override
    public int getItemCount() {
        return getEmptyViewCount() == 1 ? 1 : mData.length();
    }


    @Override
    public int getItemViewType(int position) {

        return getEmptyViewCount() == 1 ? EMPTY_VIEW : super.getItemViewType(position);
    }

    public void setEmptyView(View emptyView) {
        boolean insert = false;
        if (mEmptyLayout == null) {
            mEmptyLayout = new FrameLayout(emptyView.getContext());
            final RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams
                    (RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams
                            .MATCH_PARENT);
            final ViewGroup.LayoutParams lp = emptyView.getLayoutParams();
            if (lp != null) {
                layoutParams.width = lp.width;
                layoutParams.height = lp.height;
            }
            mEmptyLayout.setLayoutParams(layoutParams);
            insert = true;
        }
        mEmptyLayout.removeAllViews();
        mEmptyLayout.addView(emptyView);
        mIsUseEmpty = true;
        if (insert) {
            if (getEmptyViewCount() == 1) {
                int position = 0;
                notifyItemInserted(position);
            }
        }
    }


    /**
     * 刷新数据
     */
    public void replaceData(JSONArray data) {
        // 不是同一个引用才清空列表
        if (data != mData) {
            mData = data == null ? new JSONArray() : data;
        }
        notifyDataSetChanged();
    }


    /**
     * 加载更多
     */
    public void addData(JSONArray data) throws JSONException {
        for (int i = 0; i < data.length(); i++) {
            mData.put(mData.length(), data.optJSONObject(i));
        }
        notifyDataSetChanged();
    }

    public JSONArray getmData() {
        return mData;
    }

    public OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public OnItemLongClickListener getOnItemLongClickListener() {
        return mOnItemLongClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }

    /**
     * item点击事件监听
     */
    interface OnItemClickListener {
        void onItemClick(JsonAdapter adapter, View view, int position);
    }

    /**
     * item长按事件监听
     */
    interface OnItemLongClickListener {
        boolean onItemLongClick(JsonAdapter adapter, View view, int position);
    }

    protected abstract void convert(BaseViewHolder holder, JSONObject item);
}
