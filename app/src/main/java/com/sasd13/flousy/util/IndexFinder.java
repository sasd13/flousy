package com.sasd13.flousy.util;

/**
 * Created by ssaidali2 on 06/11/2016.
 */
public class IndexFinder {

    public static int indexOf(String pattern, String[] values) {
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(pattern)) {
                return i;
            }
        }

        return -1;
    }

    public static int indexOf(float pattern, String[] values) {
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(String.valueOf((int) pattern))) {
                return i;
            }
        }

        return -1;
    }
}
