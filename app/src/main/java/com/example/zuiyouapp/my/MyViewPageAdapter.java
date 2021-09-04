package com.example.zuiyouapp.my;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class MyViewPageAdapter extends PagerAdapter {

  private final List<ImageView> mImageViews;

  public MyViewPageAdapter(List<ImageView> imageViews) {
    mImageViews = imageViews;
  }

  @Override
  public int getCount() {
    return mImageViews.size();
  }

  @Override
  public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
    return view == object;
  }

  @NonNull
  @Override
  public Object instantiateItem(@NonNull ViewGroup container, int position) {
    ImageView imageView = mImageViews.get(position);
    container.addView(imageView);
    return imageView;
  }

  @Override
  public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    container.removeView(mImageViews.get(position));
  }
}
