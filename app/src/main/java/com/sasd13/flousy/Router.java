package com.sasd13.flousy;

import android.app.Activity;

import com.sasd13.flousy.activity.IdentityActivity;
import com.sasd13.flousy.activity.MainActivity;
import com.sasd13.flousy.activity.SplashScreenActivity;
import com.sasd13.flousy.controller.account.AccountController;
import com.sasd13.flousy.controller.authentication.LogInController;
import com.sasd13.flousy.controller.authentication.LogOutController;
import com.sasd13.flousy.controller.customer.CustomerController;
import com.sasd13.flousy.controller.home.HomeController;
import com.sasd13.flousy.controller.operation.OperationController;
import com.sasd13.flousy.controller.setting.SettingController;
import com.sasd13.flousy.controller.sign.SignController;
import com.sasd13.flousy.controller.splashscreen.SplashScreenController;
import com.sasd13.flousy.service.IAccountService;
import com.sasd13.flousy.service.IAuthenticationService;
import com.sasd13.flousy.service.ICustomerService;
import com.sasd13.flousy.service.ISessionStorageService;
import com.sasd13.flousy.service.IUserService;
import com.sasd13.flousy.service.IUserStorageService;
import com.sasd13.flousy.view.IAccountController;
import com.sasd13.flousy.view.IController;
import com.sasd13.flousy.view.ICustomerController;
import com.sasd13.flousy.view.IHomeController;
import com.sasd13.flousy.view.ILogInController;
import com.sasd13.flousy.view.ILogOutController;
import com.sasd13.flousy.view.IOperationController;
import com.sasd13.flousy.view.ISettingController;
import com.sasd13.flousy.view.ISignController;
import com.sasd13.flousy.view.ISplashScreenController;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class Router {

    private Resolver resolver;
    private Provider provider;

    public Router(Resolver resolver, Provider provider) {
        this.resolver = resolver;
        this.provider = provider;
    }

    public IController navigate(Class mClass, Activity activity) {
        IController controller = (IController) resolver.resolve(mClass);

        if (controller == null) {
            controller = make(mClass, activity);

            resolver.register(mClass, controller);
        }

        return controller;
    }

    private IController make(Class<? extends IController> mClass, Activity activity) {
        if (IAccountController.class.equals(mClass)) {
            return new AccountController(
                    (MainActivity) activity,
                    (IAccountService) provider.provide(IAccountService.class)
            );
        } else if (ICustomerController.class.equals(mClass)) {
            return new CustomerController(
                    (MainActivity) activity,
                    (ICustomerService) provider.provide(ICustomerService.class)
            );
        } else if (IHomeController.class.equals(mClass)) {
            return new HomeController(
                    (MainActivity) activity
            );
        } else if (ILogInController.class.equals(mClass)) {
            return new LogInController(
                    (IdentityActivity) activity,
                    (IAuthenticationService) provider.provide(IAuthenticationService.class)
            );
        } else if (ILogOutController.class.equals(mClass)) {
            return new LogOutController(
                    (MainActivity) activity,
                    (IAuthenticationService) provider.provide(IAuthenticationService.class)
            );
        } else if (IOperationController.class.equals(mClass)) {
            return new OperationController(
                    (MainActivity) activity,
                    (IAccountService) provider.provide(IAccountService.class)
            );
        } else if (ISettingController.class.equals(mClass)) {
            return new SettingController(
                    (MainActivity) activity,
                    (ISessionStorageService) provider.provide(ISessionStorageService.class),
                    (IUserStorageService) provider.provide(IUserStorageService.class),
                    (IUserService) provider.provide(IUserService.class)
            );
        } else if (ISignController.class.equals(mClass)) {
            return new SignController(
                    (IdentityActivity) activity,
                    (IUserService) provider.provide(IUserService.class),
                    (ICustomerService) provider.provide(ICustomerService.class)
            );
        } else if (ISplashScreenController.class.equals(mClass)) {
            return new SplashScreenController(
                    (SplashScreenActivity) activity,
                    (ISessionStorageService) provider.provide(ISessionStorageService.class),
                    (IAuthenticationService) provider.provide(IAuthenticationService.class),
                    (IUserService) provider.provide(IUserService.class)
            );
        } else {
            return null;
        }
    }
}
