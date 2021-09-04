package com.example.zuiyouapp.community;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zuiyouapp.Constant;
import com.example.zuiyouapp.R;

public class CommunityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private final List<CommunityData> mDataList;

  public CommunityAdapter(List<CommunityData> dataList) {
    mDataList = dataList;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    if (viewType == Constant.COMMUNITY_TOP_INTERACTION) {
      return new TopInteractionViewHolder(LayoutInflater.from(parent.getContext())
          .inflate(R.layout.community_imageview, parent, false));
    } else if (viewType == Constant.COMMUNITY_MY_TOPIC ||
        viewType == Constant.COMMUNITY_HOT_TOPIC) {
      return new TitleViewHolder(LayoutInflater.from(parent.getContext())
          .inflate(R.layout.community_title, parent, false));
    } else if (viewType == Constant.COMMUNITY_MY_TOPIC_CONTENT) {
      return new ContentViewHolder(LayoutInflater.from(parent.getContext())
          .inflate(R.layout.community_content, parent, false));
    } else {
      return new NormalViewHolder(LayoutInflater.from(parent.getContext())
          .inflate(R.layout.community_normal, parent, false));
    }
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof TitleViewHolder) {
      bindTitleItem((TitleViewHolder) holder);
    } else if (holder instanceof ContentViewHolder) {
      bindContentViewHolder((ContentViewHolder) holder, position);
    } else if (holder instanceof NormalViewHolder) {
      bindNormalItem((NormalViewHolder) holder, position);
    }
  }

  private void bindTitleItem(TitleViewHolder titleViewHolder) {
    if (titleViewHolder.getItemViewType() == Constant.COMMUNITY_MY_TOPIC) {
      titleViewHolder.mTopicNameTextView.setText("我的话题");
      titleViewHolder.mTopicContentTextView.setText("话题管理");
    } else {
      titleViewHolder.mTopicNameTextView.setText("热门话题");
      titleViewHolder.mTopicContentTextView.setText("话题广场");
    }
  }

  private void bindContentViewHolder(@NonNull ContentViewHolder holder, int position) {
    holder.mIcon.setImageResource(mDataList.get(position).mContentIconId);
    holder.mName.setText(mDataList.get(position).mContentName);
  }

  private void bindNormalItem(@NonNull NormalViewHolder holder, int position) {
    holder.mAuthor.setText(mDataList.get(position).mAuthor);
    holder.mContent.setText(mDataList.get(position).mContent);
    holder.mTopic.setText(mDataList.get(position).mTopic);
  }

  @Override
  public int getItemCount() {
    return mDataList.size();
  }

  public static class TopInteractionViewHolder extends RecyclerView.ViewHolder {

    public TopInteractionViewHolder(@NonNull View itemView) {
      super(itemView);
    }
  }

  public static class TitleViewHolder extends RecyclerView.ViewHolder {
    TextView mTopicNameTextView;
    TextView mTopicContentTextView;

    public TitleViewHolder(@NonNull View itemView) {
      super(itemView);
      mTopicNameTextView = itemView.findViewById(R.id.topic_name);
      mTopicContentTextView = itemView.findViewById(R.id.topic_content);
    }
  }

  public static class ContentViewHolder extends RecyclerView.ViewHolder {
    public ImageView mIcon;
    private final TextView mName;

    public ContentViewHolder(@NonNull View itemView) {
      super(itemView);
      mIcon = itemView.findViewById(R.id.my_topic);
      mName = itemView.findViewById(R.id.name);
    }
  }

  public static class NormalViewHolder extends RecyclerView.ViewHolder {
    TextView mTopic;
    TextView mContent;
    TextView mAuthor;
    ImageView mPhoto;

    public NormalViewHolder(@NonNull View itemView) {
      super(itemView);
      mTopic = itemView.findViewById(R.id.topic);
      mContent = itemView.findViewById(R.id.content);
      mAuthor = itemView.findViewById(R.id.author);
      mPhoto = itemView.findViewById(R.id.photo);
    }
  }

  @Override
  public int getItemViewType(int position) {
    return mDataList.get(position).mItemType;
  }
}
