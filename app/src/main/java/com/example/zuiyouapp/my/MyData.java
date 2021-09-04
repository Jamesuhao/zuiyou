package com.example.zuiyouapp.my;

import java.util.List;

import android.widget.ImageView;

public class MyData {
  public MyData(int itemType, String userName, String sign, int sex, String[] number,
      List<ImageView> bannerList, List<ChildData> childItem, String normalItemTitle) {
    mItemType = itemType;
    mUserName = userName;
    mSign = sign;
    mSex = sex;
    mNumber = number;
    mBannerList = bannerList;
    mChildItem = childItem;
    mNormalItemTitle = normalItemTitle;
  }

  public int mItemType;
  public String mUserName;
  public String mSign;
  public int mSex;
  public String[] mNumber; // 获赞、关注、粉丝的数量
  public List<ImageView> mBannerList; // banner图片列表
  public List<ChildData> mChildItem; // 子item列表
  public String mNormalItemTitle;


  public static class ChildData {
    public int mIconId;
    public String mIconText;

    public ChildData(int icon, String iconText) {
      mIconId = icon;
      mIconText = iconText;
    }
  }
}
