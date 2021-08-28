package com.campuscircle.app.adapter.stateadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.campuscircle.app.R;
import com.campuscircle.app.adapter.homeadapter.BaseBindViewHolder;
import com.campuscircle.app.adapter.homeadapter.BaseViewHolder;
import com.campuscircle.app.model.ListModel;
import com.campuscircle.app.model.ResultModel;

import java.util.List;

public class StateAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int HEADER_VIEW = 0;
    private static final int NORMAL_VIEW = 1;
    private static final int TYPE_MIDDLE = 2;
    private static final int TYPE_MIDDLE_TWO = 3;

    private static final int PUBLISH_TYPE_1 = 40;
    private static final int PUBLISH_TYPE_2 = 50;
    private static final int PUBLISH_TYPE_3 = 60;
    private static final int PUBLISH_TYPE_4 = 70;
    private static final int PUBLISH_TYPE_5 = 80;
    private static final int PUBLISH_TYPE_6 = 90;
    private static final int PUBLISH_TYPE_7 = 100;

    private View mHeaderView;
    private View mMiddleView;
    private View mMiddleView2;
    private List<ListModel.NewsInfoModelsDTO> listBeans;
    private StateAdapter.OnClickListener mItemClickListener;
    BaseBindViewHolder baseBindViewHolder = new BaseBindViewHolder();
    private Context mContext;
    private String stringListType;

    public void setItemClickListener(StateAdapter.OnClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }


    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
    }

    public void setMiddleView(View middleView) {
        mMiddleView = middleView;
    }

    public void setMiddleView2(View middleView2) {
        mMiddleView2 = middleView2;
    }

    public StateAdapter(String type, List<ListModel.NewsInfoModelsDTO> list, Context context) {
        this.listBeans = list;
        this.mContext = context;
        this.stringListType = type;
    }


    @Override
    public int getItemCount() {
        if (listBeans != null && listBeans.size() != 0) {
            return listBeans.size() + 3;
        }
        return 0;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER_VIEW;
        }
        if (position == 5) {
            return TYPE_MIDDLE;
        }
        if (position == 10) {
            return TYPE_MIDDLE_TWO;
        } else {
            if (stringListType.equals("publish")) {
                if (listBeans.get(getPosition(position)).getNewsCategoryId() == 1) {
                    return PUBLISH_TYPE_1;
                } else if (listBeans.get(getPosition(position)).getNewsCategoryId() == 2) {
                    return PUBLISH_TYPE_2;
                } else if (listBeans.get(getPosition(position)).getNewsCategoryId() == 3) {
                    return PUBLISH_TYPE_3;
                } else if (listBeans.get(getPosition(position)).getNewsCategoryId() == 4) {
                    return PUBLISH_TYPE_4;
                } else if (listBeans.get(getPosition(position)).getNewsCategoryId() == 5) {
                    return PUBLISH_TYPE_5;
                } else if (listBeans.get(getPosition(position)).getNewsCategoryId() == 6) {
                    return PUBLISH_TYPE_6;
                }

            } else if (stringListType.equals("needlist")) {

            } else {


            }
            return PUBLISH_TYPE_7;
        }
    }

    public int getPosition(int i) {
        if (i < 4) {
            return i - 1;
        }
        if (i > 4 && i < 10) {
            return i - 2;
        } else {
            return i - 3;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == HEADER_VIEW) {
            return new BaseViewHolder(mHeaderView);
        }
        if (mMiddleView != null && viewType == TYPE_MIDDLE) {
            return new BaseViewHolder(mMiddleView);
        }
        if (mMiddleView2 != null && viewType == TYPE_MIDDLE_TWO) {
            return new BaseViewHolder(mMiddleView2);
        }

        if (viewType == PUBLISH_TYPE_1) {
            return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_state01, null, false));
        } else if (viewType == PUBLISH_TYPE_2) {
            return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_state02, null, false));
        } else if (viewType == PUBLISH_TYPE_3) {
            return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_state03, null, false));
        } else if (viewType == PUBLISH_TYPE_4) {
            return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_state04, null, false));
        } else if (viewType == PUBLISH_TYPE_5) {
            return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_state05, null, false));
        } else if (viewType == PUBLISH_TYPE_6) {
            return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_state06, null, false));
        } else  if (viewType == PUBLISH_TYPE_7){
            return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_state07, null, false));
        }else {
            return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_state08, null, false));
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
        if (position < 5) {
            return position - 1;
        }
        if (position > 5 && position < 10) {
            return position - 2;
        } else {
            return position - 3;
        }

    }

    public interface OnClickListener {
        void onItemClick(View view, int position);
    }

}
