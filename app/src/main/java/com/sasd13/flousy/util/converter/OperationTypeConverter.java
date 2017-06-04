package com.sasd13.flousy.util.converter;

import com.sasd13.flousy.util.EnumOperationType;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * Created by ssaidali2 on 03/06/2017.
 */

public class OperationTypeConverter implements PropertyConverter<EnumOperationType, String> {

    @Override
    public EnumOperationType convertToEntityProperty(String databaseValue) {
        return EnumOperationType.find(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(EnumOperationType entityProperty) {
        return entityProperty.getCode();
    }
}
