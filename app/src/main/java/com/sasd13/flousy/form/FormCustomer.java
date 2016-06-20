package com.sasd13.flousy.form;

import com.sasd13.androidex.gui.widget.recycler.form.FormModel;
import com.sasd13.androidex.gui.widget.recycler.form.FormModelBoolean;
import com.sasd13.androidex.gui.widget.recycler.form.FormModelPassword;
import com.sasd13.androidex.gui.widget.recycler.form.FormModelText;
import com.sasd13.androidex.util.recycler.RecyclerHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 20/06/2016.
 */
public class FormCustomer {

    public static final int ID_FIRSTNAME = 0;
    public static final int ID_LASTNAME = 1;
    public static final int ID_EMAIL = 2;
    public static final int ID_PASSWORD = 3;
    public static final int ID_TERMS = 4;

    private RecyclerHolder<FormModel> holder;

    public FormCustomer() {
        holder = new RecyclerHolder();

        List<FormModel> formModelsIdentity = new ArrayList<>();

        FormModel formModel;

        formModel = new FormModelText();
        formModel.setId(ID_FIRSTNAME);
        formModel.setLabel("First name");
        ((FormModelText) formModel).setHint(formModel.getLabel().toLowerCase());
        formModelsIdentity.add(formModel);

        formModel = new FormModelText();
        formModel.setId(ID_LASTNAME);
        formModel.setLabel("Last name");
        ((FormModelText) formModel).setHint(formModel.getLabel().toLowerCase());
        formModelsIdentity.add(formModel);

        holder.add("Identité", formModelsIdentity);

        List<FormModel> formModelsAccount = new ArrayList<>();

        formModel = new FormModelText();
        formModel.setId(ID_EMAIL);
        formModel.setLabel("Email");
        ((FormModelText) formModel).setHint(formModel.getLabel().toLowerCase());
        formModelsAccount.add(formModel);

        formModel = new FormModelPassword();
        formModel.setId(ID_PASSWORD);
        formModel.setLabel("Password");
        formModelsAccount.add(formModel);

        formModel = new FormModelBoolean();
        formModel.setId(ID_TERMS);
        formModel.setLabel("Terms of use");
        formModelsAccount.add(formModel);

        holder.add("Compte", formModelsAccount);
    }

    public RecyclerHolder<FormModel> getHolder() {
        return holder;
    }
}
