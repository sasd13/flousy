package com.sasd13.flousy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class Resolver {

    private Map<Class, Object> container;

    public Resolver() {
        container = new HashMap<>();
    }

    public void register(Class mClass, Object object) {
        container.put(mClass, object);
    }

    public Object resolve(Class mClass) {
        return container.get(mClass);
    }
}
