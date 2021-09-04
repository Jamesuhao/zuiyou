package com.example.zuiyouapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.zuiyouapp.fragment.CommunityFragment;
import com.example.zuiyouapp.fragment.MessageFragment;
import com.example.zuiyouapp.fragment.MiddleFragment;
import com.example.zuiyouapp.fragment.MostRightFragment;
import com.example.zuiyouapp.fragment.MyFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  private Fragment[] mFragments;
  private Fragment mFragment;
  private LinearLayout mHomeTab;
  private LinearLayout mCommunityTab;
  private ImageView mAddView;
  private LinearLayout mMessageTab;
  private LinearLayout mMyTab;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    bindView();
    init();
  }

  private void bindView() {
    mHomeTab = findViewById(R.id.tab_home);
    mCommunityTab = findViewById(R.id.tab_community);
    mAddView = findViewById(R.id.add);
    mMessageTab = findViewById(R.id.tab_message);
    mMyTab = findViewById(R.id.tab_my);
  }

  private void init() {
    mFragments = getFragments();
    mFragment = mFragments[0];
    mHomeTab.setOnClickListener(this);
    mCommunityTab.setOnClickListener(this);
    mAddView.setOnClickListener(this);
    mMessageTab.setOnClickListener(this);
    mMyTab.setOnClickListener(this);
    mHomeTab.setSelected(true);
    getSupportFragmentManager().beginTransaction().replace(R.id.home_container, mFragment).commit();
  }

  @SuppressLint("NonConstantResourceId")
  @Override
  public void onClick(View v) {
    clearAllSelect();
    switch (v.getId()) {
      case R.id.tab_home:
        mHomeTab.setSelected(true);
        mFragment = mFragments[0];
        break;
      case R.id.tab_community:
        mCommunityTab.setSelected(true);
        mFragment = mFragments[1];
        break;
      case R.id.add:
        mAddView.setSelected(true);
        mFragment = mFragments[2];
        break;
      case R.id.tab_message:
        mMessageTab.setSelected(true);
        mFragment = mFragments[3];
        break;
      case R.id.tab_my:
        mMyTab.setSelected(true);
        mFragment = mFragments[4];
        break;
    }
    getSupportFragmentManager().beginTransaction().replace(R.id.home_container, mFragment).commit();
  }

  private void clearAllSelect() {
    mHomeTab.setSelected(false);
    mCommunityTab.setSelected(false);
    mMessageTab.setSelected(false);
    mMyTab.setSelected(false);
  }

  private Fragment[] getFragments() {
    Fragment[] fragments = new Fragment[5];
    fragments[0] = MostRightFragment.newInstance();
    fragments[1] = CommunityFragment.newInstance();
    fragments[2] = MiddleFragment.newInstance();
    fragments[3] = MessageFragment.newInstance();
    fragments[4] = MyFragment.newInstance();
    return fragments;
  }
}