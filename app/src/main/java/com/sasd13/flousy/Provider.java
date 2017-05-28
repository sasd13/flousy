package com.sasd13.flousy;

import com.sasd13.androidex.util.SessionStorage;
import com.sasd13.flousy.dao.IAccountDAO;
import com.sasd13.flousy.dao.ICustomerDAO;
import com.sasd13.flousy.dao.IOperationDAO;
import com.sasd13.flousy.dao.IUserDAO;
import com.sasd13.flousy.service.IAccountService;
import com.sasd13.flousy.service.IAuthenticationService;
import com.sasd13.flousy.service.ICustomerService;
import com.sasd13.flousy.service.ISessionStorageService;
import com.sasd13.flousy.service.IUserService;
import com.sasd13.flousy.service.IUserStorageService;
import com.sasd13.flousy.service.impl.AccountService;
import com.sasd13.flousy.service.impl.AuthenticationService;
import com.sasd13.flousy.service.impl.CustomerService;
import com.sasd13.flousy.service.impl.SessionStorageService;
import com.sasd13.flousy.service.impl.UserService;
import com.sasd13.flousy.service.impl.UserStorageService;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class Provider {

    private Resolver resolver;
    private Repository repository;

    public Provider(Resolver resolver, Repository repository) {
        this.resolver = resolver;
        this.repository = repository;
    }

    public Object provide(Class mClass) {
        Object service = resolver.resolve(mClass);

        if (service == null) {
            service = make(mClass);

            resolver.register(mClass, service);
        }

        return service;
    }

    private Object make(Class mClass) {
        if (IAccountService.class.isAssignableFrom(mClass)) {
            return new AccountService(
                    (IAccountDAO) repository.getDAO(IAccountDAO.class),
                    (IOperationDAO) repository.getDAO(IOperationDAO.class)
            );
        } else if (IAuthenticationService.class.isAssignableFrom(mClass)) {
            return new AuthenticationService(
                    (ISessionStorageService) provide(ISessionStorageService.class),
                    (IUserStorageService) provide(IUserStorageService.class),
                    (IUserDAO) repository.getDAO(IUserDAO.class)
            );
        } else if (ICustomerService.class.isAssignableFrom(mClass)) {
            return new CustomerService(
                    (ICustomerDAO) repository.getDAO(ICustomerDAO.class)
            );
        } else if (ISessionStorageService.class.isAssignableFrom(mClass)) {
            return new SessionStorageService(
                    (SessionStorage) resolver.resolve(SessionStorage.class)
            );
        } else if (IUserService.class.isAssignableFrom(mClass)) {
            return new UserService(
                    (IUserDAO) repository.getDAO(IUserDAO.class)
            );
        } else if (IUserStorageService.class.isAssignableFrom(mClass)) {
            return new UserStorageService();
        } else {
            return null;
        }
    }
}
