package com.example.zuiyouapp.my;

import java.util.List;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.zuiyouapp.Constant;
import com.example.zuiyouapp.R;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private final List<MyData> mDataList;

  public MyAdapter(List<MyData> dataList) {
    mDataList = dataList;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    if (viewType == Constant.MY_TOP_INTERACTION) {
      return new TopItemViewHolder(LayoutInflater.from(parent.getContext())
          .inflate(R.layout.my_imageview, parent, false));
    } else if (viewType == Constant.MY_NO_TITLE_NORMAL_ITEM) {
      return new FunctionViewHolder(LayoutInflater.from(parent.getContext())
          .inflate(R.layout.my_function, parent, false));
    } else if (viewType == Constant.MY_BANNER) {
      return new BannerViewHolder(LayoutInflater.from(parent.getContext())
          .inflate(R.layout.my_banner, parent, false));
    } else {
      return new NormalViewHolder(LayoutInflater.from(parent.getContext())
          .inflate(R.layout.my_normal, parent, false));
    }
  }

  @Override
  @SuppressLint("InflateParams")
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof TopItemViewHolder) {
      // 顶部item
      bindTopItemViewHolder((TopItemViewHolder) holder);
    } else if (holder instanceof BannerViewHolder) {
      // banner
      bindBannerViewHolder((BannerViewHolder) holder);
    } else if (holder instanceof NormalViewHolder) {
      // 普通item
      bindNormalViewHolder(position, (NormalViewHolder) holder);
    }
  }

  // 设置顶部item数据
  private void bindTopItemViewHolder(TopItemViewHolder topItemViewHolder) {
    String[] number = {"获赞", "关注", "粉丝"};
    topItemViewHolder.mUserName.setText(mDataList.get(0).mUserName); // 设置用户用
    if (mDataList.get(0).mSex == Constant.WOMAN_SEX) { // 设置性别标识符
      topItemViewHolder.mSexIcon.setImageResource(R.mipmap.woman);
    }
    if (!mDataList.get(0).mSign.isEmpty()) { // 设置个性签名
      topItemViewHolder.mSign.setText(mDataList.get(0).mSign);
    }
    // 设置获赞、关注、粉丝数量
    RelativeLayout TopItemView = (RelativeLayout) topItemViewHolder.itemView;
    int index = 0;
    for (int i = 3; i > 0; i--) {
      RelativeLayout userInfo =
          (RelativeLayout) TopItemView.getChildAt(TopItemView.getChildCount() - i);
      TextView count = (TextView) userInfo.getChildAt(0); // 设置数量
      TextView type = (TextView) userInfo.getChildAt(1); // 设置种类（种类：获赞、关注、粉丝）
      count.setText(mDataList.get(0).mNumber[index]);
      type.setText(number[index++]);
    }
  }

  private void bindBannerViewHolder(BannerViewHolder bannerViewHolder) {
    List<ImageView> banner = mDataList.get(2).mBannerList;
    bannerViewHolder.mBanner.setAdapter(new MyViewPageAdapter(banner));
    bannerViewHolder.mBanner.setCurrentItem(1, false);
    // 初始化banner水平指示器
    for (int i = 0; i < bannerViewHolder.mCircularIndicator.getChildCount(); i++) {
      ImageView imageView = (ImageView) bannerViewHolder.mCircularIndicator.getChildAt(i);
      if (i == 0) {
        imageView.setImageResource(R.mipmap.my_banner_select);
      } else {
        imageView.setImageResource(R.mipmap.my_banner_unselect);
      }
    }

    // 设置viewPager变化监听
    bannerViewHolder.mBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

      @Override
      public void onPageScrollStateChanged(int state) {}

      @Override
      public void onPageSelected(int position) {
        if (position == 0) { // 滑到最左边
          bannerViewHolder.mBanner.setCurrentItem(banner.size() - 2, false);
        } else if (position == banner.size() - 1) { // 滑到最右边
          bannerViewHolder.mBanner.setCurrentItem(1, false);
        }
        // 滑动过程设置banner水平指示器
        for (int i = 0; i < bannerViewHolder.mCircularIndicator.getChildCount(); i++) {
          ImageView imageView = (ImageView) bannerViewHolder.mCircularIndicator.getChildAt(i);
          if (i == bannerViewHolder.mBanner.getCurrentItem() - 1) {
            imageView.setImageResource(R.mipmap.my_banner_select);
          } else {
            imageView.setImageResource(R.mipmap.my_banner_unselect);
          }
        }
      }
    });
    RotationHandler rotationHandler = new RotationHandler(bannerViewHolder.mBanner);
    rotationHandler.sendEmptyMessageDelayed(1, 2000);
  }

  private static class RotationHandler extends Handler {
    private final ViewPager mBanner;

    public RotationHandler(ViewPager banner) {
      mBanner = banner;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
      super.handleMessage(msg);
      sendEmptyMessageDelayed(1, 2000);
      mBanner.setCurrentItem(mBanner.getCurrentItem() + 1);
    }
  }

  @SuppressLint("InflateParams")
  private void bindNormalViewHolder(int position, NormalViewHolder normalViewHolder) {
    List<MyData.ChildData> childData = mDataList.get(position).mChildItem;
    normalViewHolder.mNormalItemTitle.setText(mDataList.get(position).mNormalItemTitle);
    int rowCount = childData.size() % 4 == 0 ? childData.size() / 4 : childData.size() / 4 + 1;
    normalViewHolder.mItem.setRowCount(rowCount);
    int count = 0;
    for (int i = 0; i < rowCount; ++i) {
      for (int j = 0; j < 4; ++j) {
        if (count < childData.size()) {
          RelativeLayout relativeLayout =
              (RelativeLayout) LayoutInflater.from(normalViewHolder.mItem.getContext())
                  .inflate(R.layout.my_normal_item_content, null, false);
          ImageView icon = (ImageView) relativeLayout.getChildAt(0);
          ImageView redPoint = (ImageView) relativeLayout.getChildAt(1);
          TextView iconText = (TextView) relativeLayout.getChildAt(2);
          if ((j == 1 || j == 2) && i != 0) {
            redPoint.setVisibility(View.VISIBLE);
          }
          icon.setImageResource(childData.get(count).mIconId);
          iconText.setText(childData.get(count).mIconText);
          GridLayout.Spec rowSpec = GridLayout.spec(i, 1.0f);
          GridLayout.Spec columnSpec = GridLayout.spec(j, 1.0f);
          GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(rowSpec, columnSpec);
          normalViewHolder.mItem.addView(relativeLayout, layoutParams);
          count++;
        } else {
          break;
        }
      }
    }
  }

  @Override
  public int getItemCount() {
    return mDataList.size();
  }

  public static class TopItemViewHolder extends RecyclerView.ViewHolder {
    private final TextView mUserName;
    private final ImageView mSexIcon;
    private final TextView mSign;

    public TopItemViewHolder(@NonNull View itemView) {
      super(itemView);
      mUserName = itemView.findViewById(R.id.user_name);
      mSexIcon = itemView.findViewById(R.id.set_icon);
      mSign = itemView.findViewById(R.id.sign);
    }
  }

  public static class FunctionViewHolder extends RecyclerView.ViewHolder {

    public FunctionViewHolder(@NonNull View itemView) {
      super(itemView);
    }
  }

  public static class BannerViewHolder extends RecyclerView.ViewHolder {
    private final ViewPager mBanner;
    private final LinearLayout mCircularIndicator;

    public BannerViewHolder(@NonNull View itemView) {
      super(itemView);
      mBanner = itemView.findViewById(R.id.banner);
      mCircularIndicator = itemView.findViewById(R.id.circular_indicator);
    }
  }

  public static class NormalViewHolder extends RecyclerView.ViewHolder {
    private final GridLayout mItem;
    private final TextView mNormalItemTitle;

    public NormalViewHolder(@NonNull View itemView) {
      super(itemView);
      mItem = itemView.findViewById(R.id.normal_item_content);
      mNormalItemTitle = itemView.findViewById(R.id.title_text);
    }
  }

  @Override
  public int getItemViewType(int position) {
    return mDataList.get(position).mItemType;
  }
}
