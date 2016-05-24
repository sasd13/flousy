package com.sasd13.flousy.dao.db.util;

/**
 * Created by Samir on 22/05/2016.
 */

import java.util.Map;

import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.flousy.dao.AccountDAO;
import com.sasd13.flousy.dao.CustomerDAO;
import com.sasd13.flousy.dao.OperationDAO;
import com.sasd13.flousy.util.Parameter;

public class SQLWhereClauseParser {

    private static final String OPERATOR_AND = "AND";
    private static final String OPERATOR_OR = "OR";

    public static String parse(Map<String, String[]> parameters, Class<? extends IEntityDAO<?>> mClass) throws SQLWhereClauseException {
        StringBuilder builder = new StringBuilder();

        boolean firstKey = true, firstValue = true;

        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            if (firstKey) {
                firstKey = false;
            } else {
                builder.append(" " + OPERATOR_AND + " ");
                firstValue = true;
            }

            builder.append("(");

            for (String value : entry.getValue()) {
                if (firstValue) {
                    firstValue = false;
                } else {
                    builder.append(" " + OPERATOR_OR + " ");
                }

                if (CustomerDAO.class.equals(mClass)) {
                    builder.append(fromCustomer(entry.getKey(), value));
                } else if (AccountDAO.class.equals(mClass)) {
                    builder.append(fromAccount(entry.getKey(), value));
                } else if (OperationDAO.class.equals(mClass)) {
                    builder.append(fromOperation(entry.getKey(), value));
                } else {
                    throw new SQLWhereClauseException("Class '" + mClass.getName() + ")' has no where clause parser");
                }
            }

            builder.append(")");
        }

        return builder.toString();
    }

    private static String fromCustomer(String key, String value) throws SQLWhereClauseException {
        if (Parameter.ID.getName().equalsIgnoreCase(key)) {
            try {
                return CustomerDAO.COLUMN_ID + " = " + Long.parseLong(value);
            } catch (NumberFormatException e) {
                throw new SQLWhereClauseException("Customer key '" + key + "' parameter parsing error");
            }
        } else if (Parameter.FIRSTNAME.getName().equalsIgnoreCase(key)) {
            return CustomerDAO.COLUMN_FIRSTNAME + " = '" + value + "'";
        } else if (Parameter.LASTNAME.getName().equalsIgnoreCase(key)) {
            return CustomerDAO.COLUMN_LASTNAME + " = '" + value + "'";
        } else if (Parameter.EMAIL.getName().equalsIgnoreCase(key)) {
            return CustomerDAO.COLUMN_EMAIL + " = '" + value + "'";
        } else {
            throw new SQLWhereClauseException("Customer key '" + key + "' is not a declared parameter");
        }
    }

    private static String fromAccount(String key, String value) throws SQLWhereClauseException {
        if (Parameter.ID.getName().equalsIgnoreCase(key)) {
            try {
                return AccountDAO.COLUMN_ID + " = " + Long.parseLong(value);
            } catch (NumberFormatException e) {
                throw new SQLWhereClauseException("Account key '" + key + "' parameter parsing error");
            }
        } else if (Parameter.DATEOPENING.getName().equalsIgnoreCase(key)) {
            return AccountDAO.COLUMN_DATEOPENING + " = '" + value + "'";
        } else if (Parameter.CUSTOMER.getName().equalsIgnoreCase(key)) {
            try {
                return AccountDAO.COLUMN_CUSTOMER_ID + " = " + Long.parseLong(value);
            } catch (NumberFormatException e) {
                throw new SQLWhereClauseException("Account key '" + key + "' parameter parsing error");
            }
        } else {
            throw new SQLWhereClauseException("Account key '" + key + "' is not a declared parameter");
        }
    }

    private static String fromOperation(String key, String value) throws SQLWhereClauseException {
        if (Parameter.ID.getName().equalsIgnoreCase(key)) {
            try {
                return OperationDAO.COLUMN_ID + " = " + Long.parseLong(value);
            } catch (NumberFormatException e) {
                throw new SQLWhereClauseException("Operation key '" + key + "' parameter parsing error");
            }
        } else if (Parameter.DATEREALIZATION.getName().equalsIgnoreCase(key)) {
            return OperationDAO.COLUMN_DATEREALIZATION + " = '" + value + "'";
        } else if (Parameter.TITLE.getName().equalsIgnoreCase(key)) {
            return OperationDAO.COLUMN_TITLE + " = '" + value + "'";
        } else if (Parameter.AMOUNT.getName().equalsIgnoreCase(key)) {
            try {
                return OperationDAO.COLUMN_AMOUNT + " = " + Long.parseLong(value);
            } catch (NumberFormatException e) {
                throw new SQLWhereClauseException("Operation key '" + key + "' parameter parsing error");
            }
        } else if (Parameter.ACCOUNT.getName().equalsIgnoreCase(key)) {
            try {
                return OperationDAO.COLUMN_ACCOUNT_ID + " = " + Long.parseLong(value);
            } catch (NumberFormatException e) {
                throw new SQLWhereClauseException("Account key '" + key + "' parameter parsing error");
            }
        } else {
            throw new SQLWhereClauseException("Operation key '" + key + "' is not a declared parameter");
        }
    }
}