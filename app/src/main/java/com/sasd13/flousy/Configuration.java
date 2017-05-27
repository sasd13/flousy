package com.sasd13.flousy;

import android.content.Context;

/**
 * Created by ssaidali2 on 15/05/2017.
 */

public class Configuration {

    public static Router init(Context context) {
        Resolver resolver = new Resolver();
        Repository repository = new Repository(resolver, context);
        Provider provider = new Provider(resolver, repository);

        return new Router(resolver, provider);
    }
}
