package com.sasd13.flousy.db.condition;

import com.sasd13.flousy.db.dao.ICustomerDAO;
import com.sasd13.flousy.util.EnumParameter;

/**
 * Created by ssaidali2 on 17/07/2016.
 */
public class CustomerConditionExpression implements IConditionExpression {

    @Override
    public String build(String key, String value) throws ConditionBuilderException {
        if (EnumParameter.ID.getName().equalsIgnoreCase(key)) {
            try {
                return ICustomerDAO.COLUMN_ID + " = " + Long.parseLong(value);
            } catch (NumberFormatException e) {
                throw new ConditionBuilderException("Customer key '" + key + "' parameter parsing error");
            }
        } else if (EnumParameter.FIRSTNAME.getName().equalsIgnoreCase(key)) {
            return ICustomerDAO.COLUMN_FIRSTNAME + " = '" + value + "'";
        } else if (EnumParameter.LASTNAME.getName().equalsIgnoreCase(key)) {
            return ICustomerDAO.COLUMN_LASTNAME + " = '" + value + "'";
        } else if (EnumParameter.EMAIL.getName().equalsIgnoreCase(key)) {
            return ICustomerDAO.COLUMN_EMAIL + " = '" + value + "'";
        } else {
            throw new ConditionBuilderException("Customer key '" + key + "' is not a declared parameter");
        }
    }
}
