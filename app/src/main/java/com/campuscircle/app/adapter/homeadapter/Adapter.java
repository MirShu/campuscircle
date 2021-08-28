package com.campuscircle.app.adapter.homeadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;


import com.campuscircle.app.R;
import com.campuscircle.app.model.ListModel;
import com.campuscircle.app.model.ResultModel;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int HEADER_VIEW = 0;
    private static final int PUBLISH_TYPE_1 = 40;
    private static final int PUBLISH_TYPE_2 = 50;
    private static final int PUBLISH_TYPE_3 = 60;
    private static final int PUBLISH_TYPE_4 = 70;
    private static final int PUBLISH_TYPE_5 = 80;
    private static final int PUBLISH_TYPE_6 = 90;

    private View mHeaderView;
    private List<ListModel.NewsInfoModelsDTO> listBeans;
    private OnClickListener mItemClickListener;
    BaseBindViewHolder baseBindViewHolder = new BaseBindViewHolder();
    private Context mContext;
    private String stringListType;

    public void setItemClickListener(OnClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }


    public Adapter(String type, List<ListModel.NewsInfoModelsDTO> list, Context context) {
        this.listBeans = list;
        this.mContext = context;
        this.stringListType = type;
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (listBeans.get(getPosition(position)).getNewsCategoryId() == 1) {         //实物
            return PUBLISH_TYPE_1;
        } else if (listBeans.get(getPosition(position)).getNewsCategoryId() == 2) {  //虚拟
            return PUBLISH_TYPE_2;
        } else if (listBeans.get(getPosition(position)).getNewsCategoryId() == 3) {   //出租
            return PUBLISH_TYPE_3;
        } else if (listBeans.get(getPosition(position)).getNewsCategoryId() == 4) {   //拼车
            return PUBLISH_TYPE_4;
        } else if (listBeans.get(getPosition(position)).getNewsCategoryId() == 5) {    //勤工俭学
            return PUBLISH_TYPE_5;
        } else {
            return PUBLISH_TYPE_6;       // 其他
        }
    }

    public int getPosition(int i) {
        return i;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == PUBLISH_TYPE_1) {
            return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type1, null, false));   //实物
        } else if (viewType == PUBLISH_TYPE_2) {
            return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type2, null, false));  //虚拟
        } else if (viewType == PUBLISH_TYPE_3) {
            return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type3, null, false));  //出租
        } else if (viewType == PUBLISH_TYPE_4) {
            return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type4, null, false));   //拼车
        } else if (viewType == PUBLISH_TYPE_5) {
            return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type5, null, false));   //勤工俭学
        } else {
            return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type6, null, false));   // 其他
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int pos = getRealPosition(holder);
        if (getItemViewType(position) == HEADER_VIEW) {
            if (holder instanceof BaseViewHolder) {
                baseBindViewHolder.onBindViewHolder0(holder, listBeans);
            }
            return;
        }

        if (getItemViewType(position) == PUBLISH_TYPE_1) {
            if (holder instanceof BaseViewHolder) {
                baseBindViewHolder.onBindViewHolder1(holder, listBeans);
                click(holder, pos);
            }
            return;
        }
        if (getItemViewType(position) == PUBLISH_TYPE_2) {
            if (holder instanceof BaseViewHolder) {
                baseBindViewHolder.onBindViewHolder2(holder, listBeans);
                click(holder, pos);
            }
            return;
        }

        if (getItemViewType(position) == PUBLISH_TYPE_3) {
            if (holder instanceof BaseViewHolder) {
                baseBindViewHolder.onBindViewHolder3(holder, listBeans);
                click(holder, pos);
            }
            return;
        }

        if (getItemViewType(position) == PUBLISH_TYPE_4) {
            if (holder instanceof BaseViewHolder) {
                baseBindViewHolder.onBindViewHolder4(holder, listBeans);
                click(holder, pos);
            }
            return;
        }


        if (getItemViewType(position) == PUBLISH_TYPE_5) {
            if (holder instanceof BaseViewHolder) {
                baseBindViewHolder.onBindViewHolder5(holder, listBeans);
                click(holder, pos);
            }
            return;
        }


        if (getItemViewType(position) == PUBLISH_TYPE_6) {
            if (holder instanceof BaseViewHolder) {
                baseBindViewHolder.onBindViewHolder6(holder, listBeans);
                click(holder, pos);
            }
            return;
        }
    }

    private void click(RecyclerView.ViewHolder holder, int pos) {
        if (mItemClickListener == null) {
            return;
        }
        holder.itemView.setOnClickListener(v -> mItemClickListener.onItemClick(v, pos));
    }


    // 获取条目的真实位置
    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return position;
    }

    public interface OnClickListener {
        void onItemClick(View view, int position);
    }

}
