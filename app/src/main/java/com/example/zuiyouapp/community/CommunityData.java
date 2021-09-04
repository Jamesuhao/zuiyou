package com.example.zuiyouapp.community;

public class CommunityData {
  public int mItemType;
  public String mTopic;
  public String mContent;
  public String mAuthor;
  public int mContentIconId;
  public String mContentName;
  public int mNormalIconId;

  public CommunityData(int itemType) {
    mItemType = itemType;
  }

  public void setData(String topic, String content, String author, int contentIconId,
      String contentName, int normalIconId) {
    mTopic = topic;
    mContent = content;
    mAuthor = author;
    mContentIconId = contentIconId;
    mContentName = contentName;
    mNormalIconId = normalIconId;
  }
}
