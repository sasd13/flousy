package flousy.data.dao.accessor;

import flousy.content.finance.Income;
import flousy.content.finance.ListIncomes;

/**
 * Created by Samir on 11/06/2015.
 */
public interface IncomeAccessor {

    void insertIncome(Income income, String clientId);

    void updateIncome(Income income);

    void deleteIncome(Income income);

    Income selectIncome(String incomeId);

    ListIncomes selectIncomesOfClient(String clientId);
}
