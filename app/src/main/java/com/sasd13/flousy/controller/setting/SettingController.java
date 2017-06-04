package com.sasd13.flousy.controller.setting;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.flousy.R;
import com.sasd13.flousy.activity.MainActivity;
import com.sasd13.flousy.util.wrapper.UserUpdate;
import com.sasd13.flousy.controller.MainController;
import com.sasd13.flousy.scope.Scope;
import com.sasd13.flousy.scope.SettingScope;
import com.sasd13.flousy.service.ISessionStorageService;
import com.sasd13.flousy.service.IUserService;
import com.sasd13.flousy.service.IUserStorageService;
import com.sasd13.flousy.view.ISettingController;
import com.sasd13.flousy.view.fragment.setting.SettingFragment;

public class SettingController extends MainController implements ISettingController {

    private SettingScope scope;
    private ISessionStorageService sessionStorageService;
    private IUserStorageService userStorageService;
    private IUserService userService;
    private UserReadTask userReadTask;
    private UserUpdateTask userUpdateTask;

    public SettingController(MainActivity mainActivity, ISessionStorageService sessionStorageService, IUserStorageService userStorageService, IUserService userService) {
        super(mainActivity);

        scope = new SettingScope();
        this.sessionStorageService = sessionStorageService;
        this.userStorageService = userStorageService;
        this.userService = userService;
    }

    @Override
    public Scope getScope() {
        return scope;
    }

    @Override
    public void browse() {
        getActivity().clearHistory();
        scope.setUser(userStorageService.read());
        startFragment(SettingFragment.newInstance());
        readUser();
    }

    private void readUser() {
        scope.setLoading(true);

        if (userReadTask == null) {
            userReadTask = new UserReadTask(this, userService);
        }

        new Requestor(userReadTask).execute(sessionStorageService.getUserID());
    }

    void onReadUser(User user) {
        userStorageService.write(user);
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