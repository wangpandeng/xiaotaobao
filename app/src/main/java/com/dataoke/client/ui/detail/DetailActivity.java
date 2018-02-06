package com.dataoke.client.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dataoke.client.R;
import com.dataoke.client.adapter.MainGoodsAdapter;
import com.dataoke.client.adapter.PopWindowAdapter;
import com.dataoke.client.model.response.CommonGoodsResponse;
import com.dataoke.client.model.response.DetailResponse;
import com.dataoke.client.ui.MainActivity;
import com.dataoke.client.ui.base.BaseActivity;
import com.dataoke.client.ui.base.BasePresenter;
import com.dataoke.client.ui.detail.contract.DetailContract;
import com.dataoke.client.ui.detail.presenter.DetailPresenter;
import com.dataoke.client.ui.home.SearchActivity;
import com.dataoke.client.ui.personal.HistoryActivity;
import com.dataoke.client.ui.personal.ServiceActivity;
import com.dataoke.client.utils.ALibcUtil;
import com.dataoke.client.utils.DensityUtil;
import com.dataoke.client.utils.DividerGridItemDecoration;
import com.dataoke.client.utils.ShareUtil;
import com.dataoke.client.utils.StringUtil;
import com.dataoke.client.utils.TitleUtil;
import com.google.android.flexbox.FlexboxLayout;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * @author ddllxy
 * @date 2018/1/30 0030
 */

public class DetailActivity extends BaseActivity<DetailPresenter> implements DetailContract.IView {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_coupon_price)
    TextView tvCouponPrice;
    @BindView(R.id.tv_buyer_number)
    TextView tvBuyerNumber;
    @BindView(R.id.fbl_tag)
    FlexboxLayout fblTag;
    @BindView(R.id.recyclerView_more)
    RecyclerView recyclerViewMore;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    ImageView ivMore;
    @BindView(R.id.relative_main_title)
    RelativeLayout rlTopMainTitle;
    @BindView(R.id.tv_buy)
    TextView tvBuy;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @BindView(R.id.videoplayer)
    JZVideoPlayerStandard jzVideoPlayerStandard;
    private int titleHeight;
    private String goodsId;
    private MainGoodsAdapter goodsAdapter;
    private Bundle noticeBundle;
    private boolean isNotice = false;
    private PopupWindow popupWindow;

    public static void startDetailActivity(Context context, String goodsId) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("goodsId", goodsId);
        context.startActivity(intent);
    }

    public static void startDetailActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return new DetailPresenter();
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        TitleUtil.setTitleBackListener(this);
        TitleUtil.setTitleColor(this, R.color.black);
        TitleUtil.setDividerLineVisible(this, false);
        TitleUtil.setRightImageVisible(this, true);
        goodsId = getIntent().getStringExtra("goodsId");
        noticeBundle = getIntent().getExtras();
        if (noticeBundle != null) {
            isNotice = noticeBundle.getBoolean("notice");
        }
        if (isNotice) {
            tvBuy.setEnabled(false);
            tvBuy.setText("即将开始");
        } else {
            tvBuy.setEnabled(true);
        }

        rlTopMainTitle.getBackground().setAlpha(0);
        tvTitle.setAlpha(0);
        ivBack.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.back_icon));

        recyclerViewMore.setNestedScrollingEnabled(false);
        recyclerViewMore.setHasFixedSize(true);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        DividerGridItemDecoration decor = new DividerGridItemDecoration(this);
        decor.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_bg_gray_line));
        recyclerViewMore.addItemDecoration(decor);
        recyclerViewMore.setLayoutManager(manager);
        goodsAdapter = new MainGoodsAdapter(R.layout.item_grid_goods_layout, null);
        recyclerViewMore.setAdapter(goodsAdapter);

        initData();

        int mWidth = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int mHeight = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        rlTopMainTitle.measure(mWidth, mHeight);
        titleHeight = rlTopMainTitle.getMeasuredHeight();

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY <= 0) {
                    rlTopMainTitle.getBackground().setAlpha(0);
                    tvTitle.setAlpha(0);
                    ivBack.setImageDrawable(ContextCompat.getDrawable(DetailActivity.this, R.drawable.back_icon));
                    ivMore.setImageDrawable(ContextCompat.getDrawable(DetailActivity.this, R.drawable.icon_title_more_white));

                } else if (scrollY < titleHeight) {
                    float scale = (float) scrollY / titleHeight;
                    int mAlpha = (int) (255 * scale);
                    rlTopMainTitle.getBackground().setAlpha(mAlpha);
                    tvTitle.setAlpha(scale);
                } else {
                    //滑动的距离大于或等于标题的高度时
                    rlTopMainTitle.getBackground().setAlpha(255);
                    tvTitle.setAlpha(1);
                    ivBack.setImageDrawable(ContextCompat.getDrawable(DetailActivity.this, R.drawable.icon_title_back_black));
                    ivMore.setImageDrawable(ContextCompat.getDrawable(DetailActivity.this, R.drawable.icon_title_more_black));
                }
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        goodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CommonGoodsResponse.CommonGoods commonGoods = (CommonGoodsResponse.CommonGoods) adapter.getItem(position);
                DetailActivity.startDetailActivity(DetailActivity.this, commonGoods.goods_id);
            }
        });

    }

    private void initData() {
        mPresenter.getDetailInfo();
        mPresenter.getRecommendData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail_layout;
    }

    @Override
    public void setRecommendData(CommonGoodsResponse response) {
        goodsAdapter.setNewData(response.data);
    }

    @Override
    public void setDetailInfo(DetailResponse detailResponse) {
        jzVideoPlayerStandard.backButton.setVisibility(View.GONE);

        DetailResponse.DetailData data = detailResponse.data;
        String videoUrl = "http://tbm.alicdn.com/95yjMXbrAHe0LBhRoVD/LbPCp7dXkuByul6Xo5i%40%40hd.mp4";
//        if (TextUtils.equals(data.is_video, "1")) {
        jzVideoPlayerStandard.setUp(videoUrl
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
        jzVideoPlayerStandard.setEnabled(true);
//        } else {
//            jzVideoPlayerStandard.startButton.setVisibility(View.GONE);
//            jzVideoPlayerStandard.setEnabled(false);
//        }
        Glide.with(this).load(data.image).into(jzVideoPlayerStandard.thumbImageView);

        tvName.setText(data.title);
        tvDescription.setText(data.details);
        StringBuilder sb = new StringBuilder("￥");
        sb.append(data.price).append("\t劵后");
        Spannable strPrice = StringUtil.setInnerTextColorAndSize(sb.toString(), "劵后", getString(R.string.font_less_gray), 12);
        tvPrice.setText(strPrice);

        tvCouponPrice.setText("￥" + data.quan_price);
        tvBuyerNumber.setText(data.sell_num);

        List<String> tags = data.goods_tag;
        for (String tag : tags) {
            TextView cellTextView = getTagView(tag);
            fblTag.addView(cellTextView);
        }


        tvBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ALibcUtil.showDetail(DetailActivity.this, goodsId, true);
            }
        });

        ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopWindow(DetailActivity.this);
            }
        });
    }

    private void showPopWindow(Context context) {
        if (popupWindow == null) {
            View contentView = LayoutInflater.from(DetailActivity.this).inflate(R.layout.popwindow_layout, null);
            RecyclerView recyclerView = contentView.findViewById(R.id.recyclerView_pop);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            List<String> data = Arrays.asList("首页", "搜索", "浏览记录", "客服服务", "分享");
            PopWindowAdapter popWindowAdapter = new PopWindowAdapter(R.layout.item_popwindow, data);
            recyclerView.setAdapter(popWindowAdapter);
            popupWindow = new PopupWindow(contentView, DensityUtil.dip2px(150), ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setOutsideTouchable(false);
            popupWindow.setFocusable(true);

            popWindowAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    if (position == 0) {
                        MainActivity.startMainActivity(context);
                    } else if (position == 1) {
                        SearchActivity.startSearchActivity(context);
                    } else if (position == 2) {
                        HistoryActivity.startHistoryActivity(context);
                    } else if (position == 3) {
                        ServiceActivity.startServiceActivity(context);
                    } else if (position == 4) {
                        ShareUtil.shareText(context, "分享给", "小淘宝下载地址：www.baidu.com");
                    }
                    popupWindow.dismiss();
                }
            });

        }
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            popupWindow.showAsDropDown(ivMore);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (popupWindow != null) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @NonNull
    private TextView getTagView(String text) {
        TextView cellTextView = new TextView(this);
        FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT, FlexboxLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(DensityUtil.dip2px(5), DensityUtil.dip2px(5), DensityUtil.dip2px(5), DensityUtil.dip2px(5));
        cellTextView.setPadding(DensityUtil.dip2px(3), 0, DensityUtil.dip2px(3), 0);
        cellTextView.setBackgroundResource(R.drawable.shape_bg_less_red);
        cellTextView.setTextColor(ContextCompat.getColor(this, R.color.color_red));
        cellTextView.setGravity(Gravity.CENTER);
        cellTextView.setText(text);
        cellTextView.setTextSize(12);
        cellTextView.setLayoutParams(params);
        return cellTextView;
    }

    @Override
    protected void onDestroy() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
}
