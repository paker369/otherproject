package com.example.zhugeyouliao.ui.home.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhugeyouliao.R;
import com.example.zhugeyouliao.adapter.AFinalRecyclerViewAdapter;
import com.example.zhugeyouliao.adapter.BaseRecyclerViewHolder;
import com.example.zhugeyouliao.ui.home.bena.BallTextLiveBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author： longyu
 * @Time： 2021/1/19 11:31
 * @description： 文字直播
 */
public class TextLiveAdapter extends AFinalRecyclerViewAdapter<BallTextLiveBean> {

    public TextLiveAdapter(Activity ctx) {
        super(ctx);
    }

    @Override
    protected BaseRecyclerViewHolder onCreateCustomerViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(m_Inflater.inflate(R.layout.item_text_live, parent, false));
    }

    @Override
    protected void onBindCustomerViewHolder(BaseRecyclerViewHolder holder, int position) {
        ((ViewHolder) holder).show(position);
    }

    public class ViewHolder extends BaseRecyclerViewHolder {

        @BindView(R.id.iv_text_live_img)
        ImageView ivTextLiveImg;
        @BindView(R.id.tv_text_live)
        TextView tvTextLive;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void show(int position) {
            BallTextLiveBean item = getItem(position);
            if (item != null) {
                tvTextLive.setText(item.getData());
                switch (item.getType()){
                    case 0:
                        ivTextLiveImg.setImageResource(R.mipmap.ic_football_text_vs);
                        break;
                    case 1:
                        ivTextLiveImg.setImageResource(R.mipmap.ic_football_text_ru);
                        break;
                    case 2:
                        ivTextLiveImg.setImageResource(R.mipmap.ic_football_text_jiao);
                        break;
                    case 3:
                        ivTextLiveImg.setImageResource(R.mipmap.ic_football_text_yellow_card);
                        break;
                    case 4:
                        ivTextLiveImg.setImageResource(R.mipmap.ic_football_text_red_card);
                        break;
                    case 9:
                        ivTextLiveImg.setImageResource(R.mipmap.ic_football_text_huan);
                        break;
                    case 15:
                        ivTextLiveImg.setImageResource(R.mipmap.ic_football_text_yellow_card);
                        break;
                    case 17:
                        ivTextLiveImg.setImageResource(R.mipmap.ic_football_text_wu);
                        break;
                    case 16:
                    case 27:
                    case 29:
                    case 30:
                        ivTextLiveImg.setImageResource(R.mipmap.ic_football_text_dian);
                        break;
                }
            }
        }
    }

}
