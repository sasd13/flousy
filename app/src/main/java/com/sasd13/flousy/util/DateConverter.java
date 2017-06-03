package com.sasd13.flousy.util;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.Date;

/**
 * Created by ssaidali2 on 03/06/2017.
 */

public class DateConverter implements PropertyConverter<Date, Long> {

    @Override
    public Date convertToEntityProperty(Long time) {
        return new Date(time);
    }

    @Override
    public Long convertToDatabaseValue(Date date) {
        return date.getTime();
    }
}
