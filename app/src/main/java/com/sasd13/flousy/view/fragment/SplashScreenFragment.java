package com.sasd13.flousy.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sasd13.flousy.R;
import com.sasd13.flousy.activity.SplashScreenActivity;
import com.sasd13.flousy.view.ISplashScreenController;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public class SplashScreenFragment extends Fragment {

    private ISplashScreenController controller;

    public static SplashScreenFragment newInstance() {
        return new SplashScreenFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (ISplashScreenController) ((SplashScreenActivity) getActivity()).lookup(ISplashScreenController.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_splashscreen, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        buildLogo(view);
        run();
    }

    private void buildLogo(View view) {
        ImageView imageViewLogo = (ImageView) view.findViewById(R.id.splashscreen_imageview);
        imageViewLogo.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_app_logo));
    }

    private void run() {
        controller.run();
    }
}
