package com.sasd13.flousy.view.gui.form;

import android.content.Context;
import android.text.InputType;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.SpinRadioItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.flousy.R;
import com.sasd13.flousy.bean.user.User;
import com.sasd13.flousy.util.EnumPreference;
import com.sasd13.flousy.util.IndexFinder;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by ssaidali2 on 20/06/2016.
 */
public class UserForm extends Form {

    private final String[] DATES;

    private TextItemModel modelEmail;
    private SpinRadioItemModel modelDate;

    public UserForm(Context context) {
        super(context);

        DATES = context.getResources().getStringArray(R.array.dates);

        String title = context.getString(R.string.title_identity);

        modelEmail = new TextItemModel(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        modelEmail.setLabel(context.getString(R.string.label_email));
        modelEmail.setHint(modelEmail.getLabel().toLowerCase());
        modelEmail.setReadOnly(true);
        holder.add(title, new RecyclerHolderPair(modelEmail));

        title = context.getString(R.string.title_parameters);

        modelDate = new SpinRadioItemModel();
        modelDate.setLabel(context.getString(R.string.label_date));
        holder.add(title, new RecyclerHolderPair(modelDate));
    }

    public void bindUser(User user) {
        if (user != null) {
            modelEmail.setValue(user.getEmail());
            bindPreferenceDate(DATES, user.getUserPreferences().findValue(EnumPreference.GENERAL_DATE));
        } else {
            bindPreferenceDate(DATES);
        }
    }

    private void bindPreferenceDate(String[] values) {
        modelDate.setItems(values);
    }

    private void bindPreferenceDate(String[] values, String pattern) {
        bindPreferenceDate(values);

        modelDate.setValue(IndexFinder.indexOf(pattern, values));
    }

    public String getEmail() throws FormException {
        if (StringUtils.isBlank(modelEmail.getValue())) {
            throw new FormException(context, R.string.form_settings_message_error_email);
        }

        return modelEmail.getValue();
    }

    public String getPreferenceDate() throws FormException {
        if (modelDate.getValue() < 0) {
            throw new FormException(context, R.string.form_settings_message_error_preference_date);
        }

        return modelDate.getStringValue();
    }
}
