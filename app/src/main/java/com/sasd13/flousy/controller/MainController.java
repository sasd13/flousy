package com.sasd13.flousy.controller;

import android.support.v4.app.Fragment;

import com.sasd13.flousy.activity.MainActivity;
import com.sasd13.flousy.util.Constants;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public abstract class MainController extends Controller {

    protected MainController(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    public MainActivity getActivity() {
        return (MainActivity) super.getActivity();
    }

    protected void startFragment(Fragment fragment) {
        getActivity().startFragment(fragment);
    }

    protected String getUserIDFromSession() {
        return getActivity().getSessionStorage().get(Constants.USERID);
    }

    protected String getIntermediaryFromSession() {
        return getActivity().getSessionStorage().get(Constants.INTERMEDIARY);
    }
}
