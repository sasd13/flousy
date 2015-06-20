package flousy.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Samir on 20/06/2015.
 */
public class IdGenerator {

    private static final String ID_PREFERENCES = "id_preferences";
    private static final String ID_CLIENT = "id_client";
    private static final String ID_PAYMENTSACCOUNT = "id_payments_account";
    private static final String ID_PAYMENT = "id_payment";
    private static final String ID_SPENDSACCOUNT = "id_spends_account";
    private static final String ID_SPEND = "id_spend";
    private static final String ID_ARTICLE = "id_article";

    protected IdGenerator() {}

    public static String get(Context context, IdGeneratorType type) {
        String id = null, key = null;

        switch (type) {
            case CLIENT :
                key = ID_CLIENT;
                id = "client-";
                break;
            case PAYMENTSACCOUNT:
                key = ID_PAYMENTSACCOUNT;
                id = "account-payments-";
                break;
            case PAYMENT:
                key = ID_PAYMENT;
                id = "payment-";
                break;
            case SPENDSACCOUNT:
                key = ID_SPENDSACCOUNT;
                id = "account-spends-";
                break;
            case SPEND:
                key = ID_SPEND;
                id = "spend-";
                break;
            case ARTICLE:
                key = ID_ARTICLE;
                id = "article-";
                break;
        }

        SharedPreferences preferences = context.getSharedPreferences(ID_PREFERENCES, Context.MODE_PRIVATE);

        int count = preferences.getInt(key, 0);
        count++;
        id = id + count;

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, count);
        editor.apply();

        return id;
    }
}
