package com.sasd13.flousy.db.condition;

import com.sasd13.flousy.db.dao.IOperationDAO;
import com.sasd13.flousy.util.EnumParameter;

/**
 * Created by ssaidali2 on 17/07/2016.
 */
public class OperationConditionExpression implements IConditionExpression {

    @Override
    public String build(String key, String value) throws ConditionBuilderException {
        if (EnumParameter.ID.getName().equalsIgnoreCase(key)) {
            try {
                return IOperationDAO.COLUMN_ID + " = " + Long.parseLong(value);
            } catch (NumberFormatException e) {
                throw new ConditionBuilderException("Operation key '" + key + "' parameter parsing error");
            }
        } else if (EnumParameter.DATEREALIZATION.getName().equalsIgnoreCase(key)) {
            return IOperationDAO.COLUMN_DATEREALIZATION + " = '" + value + "'";
        } else if (EnumParameter.TITLE.getName().equalsIgnoreCase(key)) {
            return IOperationDAO.COLUMN_TITLE + " = '" + value + "'";
        } else if (EnumParameter.AMOUNT.getName().equalsIgnoreCase(key)) {
            try {
                return IOperationDAO.COLUMN_AMOUNT + " = " + Long.parseLong(value);
            } catch (NumberFormatException e) {
                throw new ConditionBuilderException("Operation key '" + key + "' parameter parsing error");
            }
        } else if (EnumParameter.ACCOUNT.getName().equalsIgnoreCase(key)) {
            try {
                return IOperationDAO.COLUMN_ACCOUNT_ID + " = " + Long.parseLong(value);
            } catch (NumberFormatException e) {
                throw new ConditionBuilderException("Account key '" + key + "' parameter parsing error");
            }
        } else {
            throw new ConditionBuilderException("Operation key '" + key + "' is not a declared parameter");
        }
    }
}
