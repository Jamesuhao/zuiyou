package com.example.zuiyouapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.zuiyouapp.R;

public class MiddleFragment extends Fragment {
  public static MiddleFragment newInstance() {
    Bundle args = new Bundle();

    MiddleFragment fragment = new MiddleFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @SuppressLint("InflateParams")
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.middle_add_layout, null);
  }
}
