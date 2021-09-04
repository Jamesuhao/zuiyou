package com.example.zuiyouapp.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zuiyouapp.Constant;
import com.example.zuiyouapp.R;
import com.example.zuiyouapp.community.CommunityAdapter;
import com.example.zuiyouapp.community.CommunityData;

public class CommunityFragment extends Fragment {

  public static CommunityFragment newInstance() {

    Bundle args = new Bundle();
    args.putString("community", "abc");
    CommunityFragment fragment = new CommunityFragment();
    fragment.setArguments(args);
    return fragment;
  }

  private List<CommunityData> initData() {
    List<CommunityData> dataList = new ArrayList<>();
    for (int i = 0; i < 50; i++) {
      if (i == 0) {
        dataList.add(new CommunityData(Constant.COMMUNITY_TOP_INTERACTION));
      } else if (i == 1) {
        dataList.add(new CommunityData(Constant.COMMUNITY_MY_TOPIC));
      } else if (i == 2) {
        CommunityData data = new CommunityData(Constant.COMMUNITY_MY_TOPIC_CONTENT);
        data.setData("", "", "", R.mipmap.game, "阴阳师手游",
            -1);
        dataList.add(data);
      } else if (i == 3) {
        dataList.add(new CommunityData(Constant.COMMUNITY_HOT_TOPIC));
      } else {
        CommunityData data = new CommunityData(Constant.NORMAL);
        data.setData("旅游去哪", "我想问一下你们不开心会做什么事情呢，来让自己开",
            "159633 漫迷", -1, "", R.mipmap.abc);
        dataList.add(data);
      }
    }
    return dataList;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    RelativeLayout relativeLayout =
        (RelativeLayout) inflater.inflate(R.layout.community_layout, container, false);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
    linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
    RecyclerView recyclerView = (RecyclerView) relativeLayout.getChildAt(1);
    recyclerView.setLayoutManager(linearLayoutManager);
    recyclerView.setAdapter(new CommunityAdapter(initData()));
    return relativeLayout;
  }
}
