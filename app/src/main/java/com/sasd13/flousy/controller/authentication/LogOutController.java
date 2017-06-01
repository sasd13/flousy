package com.sasd13.flousy.controller.authentication;

import android.content.DialogInterface;
import android.content.Intent;

import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.flousy.R;
import com.sasd13.flousy.activity.IdentityActivity;
import com.sasd13.flousy.activity.MainActivity;
import com.sasd13.flousy.controller.MainController;
import com.sasd13.flousy.scope.Scope;
import com.sasd13.flousy.service.IAuthenticationService;
import com.sasd13.flousy.view.ILogOutController;

/**
 * Created by ssaidali2 on 05/12/2016.
 */
public class LogOutController extends MainController implements ILogOutController {

    private IAuthenticationService authenticationService;

    public LogOutController(MainActivity mainActivity, IAuthenticationService authenticationService) {
        super(mainActivity);

        this.authenticationService = authenticationService;
    }

    @Override
    public Scope getScope() {
        return null;
    }

    @Override
    public void actionLogOut() {
        logOut();
    }

    private void logOut() {
        OptionDialog.showOkCancelDialog(
                getActivity(),
                getActivity().getString(R.string.button_logout),
                getActivity().getString(R.string.message_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        exit();
                    }
                }
        );
    }

    private void exit() {
        authenticationService.logOut();
        goToIdentityActivity();
    }

    private void goToIdentityActivity() {
        startActivity(new Intent(getActivity(), IdentityActivity.class));
    }
}
