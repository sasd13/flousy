package com.sasd13.flousy;

import android.app.Activity;

import com.sasd13.flousy.view.IController;

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

    public IController dispatch(Class mClass, Activity activity) {
        IController controller = (IController) resolver.resolve(mClass);

        if (controller == null) {
            controller = make(mClass, activity);

            resolver.register(mClass, controller);
        }

        return controller;
    }

    private IController make(Class<? extends IController> mClass, Activity activity) {
        if (ISplashScreenController.class.equals(mClass)) {
            return new SplashScreenController(
                    (SplashScreenActivity) activity,
                    (IUserService) provider.provide(IUserService.class)
            );
        } else if (ILogInController.class.equals(mClass)) {
            return new LogInController(
                    (IdentityActivity) activity,
                    (IAuthenticationService) provider.provide(IAuthenticationService.class)
            );
        } else if (ISettingController.class.equals(mClass)) {
            return new SettingController(
                    (MainActivity) activity,
                    (IUserService) provider.provide(IUserService.class)
            );
        } else if (IProjectController.class.equals(mClass)) {
            return new ProjectController(
                    (MainActivity) activity,
                    (IProjectService) provider.provide(IProjectService.class),
                    (IRunningService) provider.provide(IRunningService.class),
                    (ITeacherService) provider.provide(ITeacherService.class)
            );
        } else if (ITeamController.class.equals(mClass)) {
            return new TeamController(
                    (MainActivity) activity,
                    (ITeamService) provider.provide(ITeamService.class),
                    (IStudentTeamService) provider.provide(IStudentTeamService.class)
            );
        } else if (IStudentController.class.equals(mClass)) {
            return new StudentController(
                    (MainActivity) activity,
                    (IStudentService) provider.provide(IStudentService.class),
                    (IStudentTeamService) provider.provide(IStudentTeamService.class)
            );
        } else if (IRunningController.class.equals(mClass)) {
            return new RunningController(
                    (MainActivity) activity,
                    (IRunningService) provider.provide(IRunningService.class)
            );
        } else if (IRunningTeamController.class.equals(mClass)) {
            return new RunningTeamController(
                    (MainActivity) activity,
                    (IRunningTeamService) provider.provide(IRunningTeamService.class),
                    (IReportService) provider.provide(IReportService.class)
            );
        } else if (IReportController.class.equals(mClass)) {
            return new ReportController(
                    (MainActivity) activity,
                    (IReportService) provider.provide(IReportService.class),
                    (ILeadEvaluationService) provider.provide(ILeadEvaluationService.class),
                    (IIndividualEvaluationService) provider.provide(IIndividualEvaluationService.class),
                    (IRunningTeamService) provider.provide(IRunningTeamService.class)
            );
        } else if (ILogOutController.class.equals(mClass)) {
            return new LogOutController((MainActivity) activity);
        } else {
            return null;
        }
    }
}
