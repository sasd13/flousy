package com.sasd13.flousy.controller.sign;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.flousy.activity.IdentityActivity;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.bean.user.UserCreate;
import com.sasd13.flousy.controller.IdentityController;
import com.sasd13.flousy.scope.Scope;
import com.sasd13.flousy.scope.SignScope;
import com.sasd13.flousy.service.ICustomerService;
import com.sasd13.flousy.service.IUserService;
import com.sasd13.flousy.view.ISignController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 27/12/2016.
 */

public class SignController extends IdentityController implements ISignController {

    public static final String USER_TO_CREATE = "USER";
    public static final String CUSTOMER_TO_CREATE = "CUSTOMER";

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
    public void sign(UserCreate userCreate, Customer customer) {
        Map<String, Object> parameters = new HashMap<>();

        parameters.put(USER_TO_CREATE, userCreate);
        parameters.put(CUSTOMER_TO_CREATE, customer);

        sign(parameters);
    }

    private void sign(Map<String, Object> parameters) {
        scope.setLoading(true);

        if (signTask == null) {
            signTask = new SignTask(this, userService, customerService);
        }

        new Requestor(signTask).execute(parameters);
    }

    void onSign() {
        scope.setLoading(false);
    }
}
