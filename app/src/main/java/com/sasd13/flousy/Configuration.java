package com.sasd13.flousy;

/**
 * Created by ssaidali2 on 15/05/2017.
 */

public class Configuration {

    public static Router init() {
        Resolver resolver = new Resolver();
        Provider provider = new Provider(resolver);

        return new Router(resolver, provider);
    }
}
