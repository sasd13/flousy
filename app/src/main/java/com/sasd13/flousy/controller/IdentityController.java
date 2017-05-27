package com.sasd13.flousy.controller;

import android.support.v4.app.Fragment;

import com.sasd13.flousy.activity.IdentityActivity;

/**
 * Created by ssaidali2 on 04/12/2016.
 */
public abstract class IdentityController extends Controller {

    protected IdentityController(IdentityActivity identityActivity) {
        super(identityActivity);
    }

    @Override
    public IdentityActivity getActivity() {
        return (IdentityActivity) super.getActivity();
    }

    protected void startFragment(Fragment fragment) {
        getActivity().startFragment(fragment);
    }
}
