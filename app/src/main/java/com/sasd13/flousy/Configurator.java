package com.sasd13.flousy;

import android.app.Activity;

import com.sasd13.androidex.util.SessionStorage;

/**
 * Created by ssaidali2 on 15/05/2017.
 */

public class Configurator {

    public static class Config {

        private Resolver resolver;
        private Repository repository;
        private Provider provider;
        private Router router;

        public Provider getProvider() {
            return provider;
        }

        public Router getRouter() {
            return router;
        }
    }

    public static Config init(Activity activity) {
        Config config = new Config();
        config.resolver = new Resolver();
        config.repository = new Repository(config.resolver, activity);
        config.provider = new Provider(config.resolver, config.repository);
        config.router = new Router(config.resolver, config.provider);

        config.resolver.register(Repository.class, config.repository);
        config.resolver.register(Provider.class, config.provider);
        config.resolver.register(Router.class, config.router);
        config.resolver.register(SessionStorage.class, new SessionStorage(activity));

        return config;
    }
}
