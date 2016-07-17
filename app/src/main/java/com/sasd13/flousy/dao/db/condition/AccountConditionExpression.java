package com.sasd13.flousy.dao.db.condition;

import com.sasd13.flousy.dao.AccountDAO;
import com.sasd13.flousy.util.EnumParameter;
import com.sasd13.javaex.db.condition.ConditionBuilderException;
import com.sasd13.javaex.db.condition.IConditionExpression;

/**
 * Created by ssaidali2 on 17/07/2016.
 */
public class AccountConditionExpression implements IConditionExpression {

    @Override
    public String build(String key, String value) throws ConditionBuilderException {
        if (EnumParameter.ID.getName().equalsIgnoreCase(key)) {
            try {
                return AccountDAO.COLUMN_ID + " = " + Long.parseLong(value);
            } catch (NumberFormatException e) {
                throw new ConditionBuilderException("Account key '" + key + "' parameter parsing error");
            }
        } else if (EnumParameter.DATEOPENING.getName().equalsIgnoreCase(key)) {
            return AccountDAO.COLUMN_DATEOPENING + " = '" + value + "'";
        } else if (EnumParameter.CUSTOMER.getName().equalsIgnoreCase(key)) {
            try {
                return AccountDAO.COLUMN_CUSTOMER_ID + " = " + Long.parseLong(value);
            } catch (NumberFormatException e) {
                throw new ConditionBuilderException("Account key '" + key + "' parameter parsing error");
            }
        } else {
            throw new ConditionBuilderException("Account key '" + key + "' is not a declared parameter");
        }
    }
}
