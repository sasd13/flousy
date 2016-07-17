package com.sasd13.flousy.dao.db.condition;

import com.sasd13.flousy.dao.OperationDAO;
import com.sasd13.flousy.util.EnumParameter;
import com.sasd13.javaex.db.condition.ConditionBuilderException;
import com.sasd13.javaex.db.condition.IConditionExpression;

/**
 * Created by ssaidali2 on 17/07/2016.
 */
public class OperationConditionExpression implements IConditionExpression {

    @Override
    public String build(String key, String value) throws ConditionBuilderException {
        if (EnumParameter.ID.getName().equalsIgnoreCase(key)) {
            try {
                return OperationDAO.COLUMN_ID + " = " + Long.parseLong(value);
            } catch (NumberFormatException e) {
                throw new ConditionBuilderException("Operation key '" + key + "' parameter parsing error");
            }
        } else if (EnumParameter.DATEREALIZATION.getName().equalsIgnoreCase(key)) {
            return OperationDAO.COLUMN_DATEREALIZATION + " = '" + value + "'";
        } else if (EnumParameter.TITLE.getName().equalsIgnoreCase(key)) {
            return OperationDAO.COLUMN_TITLE + " = '" + value + "'";
        } else if (EnumParameter.AMOUNT.getName().equalsIgnoreCase(key)) {
            try {
                return OperationDAO.COLUMN_AMOUNT + " = " + Long.parseLong(value);
            } catch (NumberFormatException e) {
                throw new ConditionBuilderException("Operation key '" + key + "' parameter parsing error");
            }
        } else if (EnumParameter.ACCOUNT.getName().equalsIgnoreCase(key)) {
            try {
                return OperationDAO.COLUMN_ACCOUNT_ID + " = " + Long.parseLong(value);
            } catch (NumberFormatException e) {
                throw new ConditionBuilderException("Account key '" + key + "' parameter parsing error");
            }
        } else {
            throw new ConditionBuilderException("Operation key '" + key + "' is not a declared parameter");
        }
    }
}
