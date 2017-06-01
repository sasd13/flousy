package com.sasd13.flousy.controller.sign;

import android.content.Intent;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.flousy.activity.IdentityActivity;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.bean.user.UserCreate;
import com.sasd13.flousy.controller.IdentityController;
import com.sasd13.flousy.scope.Scope;
import com.sasd13.flousy.scope.SignScope;
import com.sasd13.flousy.service.ICustomerService;
import com.sasd13.flousy.service.IUserService;
import com.sasd13.flousy.util.wrapper.UserSignWrapper;
import com.sasd13.flousy.view.ISignController;
import com.sasd13.flousy.view.fragment.sign.SignFragment;

/**
 * Created by ssaidali2 on 27/12/2016.
 */

public class SignController extends IdentityController implements ISignController {

    private SignScope scope;
    private IUserService userService;
    private ICustomerService customerService;
    private SignTask signTask;

    public SignController(IdentityActivity identityActivity, IUserService userService, ICustomerService customerService) {
        super(identityActivity);

        scope = new SignScope();
        this.userService = userService;
        this.customerService = customerService;
    }

    @Override
    public Scope getScope() {
        return scope;
    }

    @Override
    public void browse() {
        startFragment(SignFragment.newInstance());
    }

    @Override
    public void sign(UserCreate userCreate, Customer customer) {
        UserSignWrapper wrapper = new UserSignWrapper();

        wrapper.setUserCreate(userCreate);
        wrapper.setCustomer(customer);

        sign(wrapper);
    }

    private void sign(UserSignWrapper wrapper) {
        scope.setLoading(true);

        if (signTask == null) {
            signTask = new SignTask(this, userService, customerService);
        }

        new Requestor(signTask).execute(wrapper);
    }

    void onSign() {
        scope.setLoading(false);
        goToIdentityActivity();
    }

    private void goToIdentityActivity() {
        startActivity(new Intent(getActivity(), IdentityActivity.class));
    }
}
