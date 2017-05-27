package com.sasd13.flousy.controller.setting;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.bean.user.User;
import com.sasd13.proadmin.android.bean.user.UserUpdate;
import com.sasd13.proadmin.android.controller.MainController;
import com.sasd13.proadmin.android.scope.Scope;
import com.sasd13.proadmin.android.scope.SettingScope;
import com.sasd13.proadmin.android.service.IUserService;
import com.sasd13.proadmin.android.view.ISettingController;
import com.sasd13.proadmin.android.view.fragment.setting.SettingFragment;

public class SettingController extends MainController implements ISettingController {

    private SettingScope scope;
    private IUserService userService;
    private UserReadTask userReadTask;
    private UserUpdateTask userUpdateTask;

    public SettingController(MainActivity mainActivity, IUserService userService) {
        super(mainActivity);

        scope = new SettingScope();
        this.userService = userService;
    }

    @Override
    public Scope getScope() {
        return scope;
    }

    @Override
    public void browse() {
        getActivity().clearHistory();
        startFragment(SettingFragment.newInstance());
        actionLoadUser();
    }

    @Override
    public void actionLoadUser() {
        readUser();
    }

    private void readUser() {
        scope.setLoading(true);

        if (userReadTask == null) {
            userReadTask = new UserReadTask(this, userService);
        }

        new Requestor(userReadTask).execute(getUserIDFromSession());
    }

    void onReadUser(User user) {
        getActivity().setUser(user);
        scope.setUser(user);
        scope.setLoading(false);
    }

    @Override
    public void actionUpdateUser(UserUpdate userUpdate) {
        if (userUpdateTask == null) {
            userUpdateTask = new UserUpdateTask(this, userService);
        }

        new Requestor(userUpdateTask).execute(userUpdate);
    }

    void onUpdateUser() {
        display(R.string.message_updated);
    }
}