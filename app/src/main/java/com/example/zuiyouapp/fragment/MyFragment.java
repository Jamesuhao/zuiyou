package com.example.zuiyouapp.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.zuiyouapp.my.MyAdapter;
import com.example.zuiyouapp.my.MyData;
import com.example.zuiyouapp.Constant;
import com.example.zuiyouapp.R;

public class MyFragment extends Fragment {
  private final String[] mBannerUri = {
      "https://img95.699pic.com/photo/40036/4383.jpg_wh860.jpg!/fw/562", // 3
      "https://www.fgkj.cc/uploads/allimg/180710/1-1PG01H345134.jpg",  //1
      "https://img95.699pic.com/photo/50023/7281.jpg_wh860.jpg!/fw/562", // 2
      "https://img95.699pic.com/photo/40036/4383.jpg_wh860.jpg!/fw/562", // 3
      "https://www.fgkj.cc/uploads/allimg/180710/1-1PG01H345134.jpg",  //1
  };

  public static MyFragment newInstance() {
    Bundle args = new Bundle();
    args.putString("my", "abc");
    MyFragment fragment = new MyFragment();
    fragment.setArguments(args);
    return fragment;
  }

  private List<MyData> initData() {
    List<MyData> dataList = new ArrayList<>();
    for (int i = 0; i < 6; i++) {
      if (i == 0) {
        dataList.add(initTopInteraction());
      } else if (i == 1) {
        dataList.add(initFunction());
      } else if (i == 2) {
        dataList.add(initBanner());
      } else if (i == 3) {
        dataList.add(initNormalItem1());
      } else if (i == 4) {
        dataList.add(initNormalItem2());
      } else if (i == 5) {
        dataList.add(initNormalItem3());
      }
    }
    return dataList;
  }

  private MyData initTopInteraction() {
    String[] number = {"10", "100", "300"};
    return new MyData(Constant.MY_TOP_INTERACTION, "X????????????????????????p", "",
        Constant.MAN_SEX, number, null, null, "");
  }

  private MyData initFunction() {
    return new MyData(Constant.MY_NO_TITLE_NORMAL_ITEM, "", "", -1,
        null, null, null, "");
  }

  private MyData initBanner() {
    List<ImageView> imageViews = new ArrayList<>();
    for (String uri : mBannerUri) {
      ImageView imageView = new ImageView(getContext());
      imageView.setScaleType(ImageView.ScaleType.CENTER);
      Glide.with(getContext()).load(uri)
          .apply(RequestOptions.bitmapTransform(new GranularRoundedCorners(600, 600, 600, 600)))
          .into(imageView);
      imageViews.add(imageView);
    }
    return new MyData(Constant.MY_BANNER, "", "", -1, null,
        imageViews, null, "");
  }

  private MyData initNormalItem1() {
    List<MyData.ChildData> childItemData = new ArrayList<>();
    childItemData.add(new MyData.ChildData(R.mipmap.write, "????????????"));
    childItemData.add(new MyData.ChildData(R.mipmap.medal, "????????????"));
    childItemData.add(new MyData.ChildData(R.mipmap.serach, "????????????"));
    childItemData.add(new MyData.ChildData(R.mipmap.shield, "????????????"));
    return new MyData(Constant.NORMAL, "", "", -1, null,
        null, childItemData, "????????????");
  }

  private MyData initNormalItem2() {
    List<MyData.ChildData> childItemData = new ArrayList<>();
    childItemData.add(new MyData.ChildData(R.mipmap.daily_time, "????????????"));
    childItemData.add(new MyData.ChildData(R.mipmap.normal_game, "?????????"));
    childItemData.add(new MyData.ChildData(R.mipmap.travel, "????????????"));
    childItemData.add(new MyData.ChildData(R.mipmap.resolution, "?????????"));
    childItemData.add(new MyData.ChildData(R.mipmap.family, "??????family"));
    childItemData.add(new MyData.ChildData(R.mipmap.shield, "???????????????"));
    return new MyData(Constant.NORMAL, "", "", -1, null,
        null, childItemData, "????????????");
  }

  private MyData initNormalItem3() {
    List<MyData.ChildData> childItemData = new ArrayList<>();
    childItemData.add(new MyData.ChildData(R.mipmap.dark_mode, "????????????"));
    childItemData.add(new MyData.ChildData(R.mipmap.history, "????????????"));
    childItemData.add(new MyData.ChildData(R.mipmap.video, "????????????"));
    childItemData.add(new MyData.ChildData(R.mipmap.teenager_mode, "???????????????"));
    childItemData.add(new MyData.ChildData(R.mipmap.attitude, "????????????"));
    childItemData.add(new MyData.ChildData(R.mipmap.help, "???????????????"));
    childItemData.add(new MyData.ChildData(R.mipmap.download, "????????????"));
    childItemData.add(new MyData.ChildData(R.mipmap.free_of_traffic, "???????????????"));
    return new MyData(Constant.NORMAL, "", "", -1, null,
        null, childItemData, "????????????");
  }

  @Nullable
  @Override

  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    RelativeLayout relativeLayout =
        (RelativeLayout) inflater.inflate(R.layout.my_layout, container, false);
    TextView title = (TextView) relativeLayout.getChildAt(1);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
    linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
    RecyclerView recyclerView = (RecyclerView) relativeLayout.getChildAt(0);
    recyclerView.setLayoutManager(linearLayoutManager);
    recyclerView.setAdapter(new MyAdapter(initData()));
    setRecyclerViewListener(title, recyclerView);
    return relativeLayout;
  }

  private void setRecyclerViewListener(TextView title, RecyclerView recyclerView) {
    title.setText(initTopInteraction().mUserName);
    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int position = layoutManager.findFirstVisibleItemPosition();
        if (position == 0) {
          View firstView = layoutManager.findViewByPosition(position);
          int firstHeight = firstView.getHeight();
          int scrollY = -firstView.getTop();
          int changeHeight = firstHeight / 3;
          if (scrollY < changeHeight) {
            title.setVisibility(View.GONE);
          } else {
            title.setVisibility(View.VISIBLE);
          }
        }
      }
    });
  }
}
