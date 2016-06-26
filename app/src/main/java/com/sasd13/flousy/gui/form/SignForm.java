package com.sasd13.flousy.gui.form;

import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerModel;
import com.sasd13.androidex.gui.widget.recycler.form.BooleanModel;
import com.sasd13.androidex.gui.widget.recycler.form.CheckboxSpinModel;
import com.sasd13.androidex.gui.widget.recycler.form.FormModel;
import com.sasd13.androidex.gui.widget.recycler.form.PasswordModel;
import com.sasd13.androidex.gui.widget.recycler.form.RadioSpinModel;
import com.sasd13.androidex.gui.widget.recycler.form.TextModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 20/06/2016.
 */
public class SignForm {

    public static final int ID_FIRSTNAME = 0;
    public static final int ID_LASTNAME = 1;
    public static final int ID_EMAIL = 2;
    public static final int ID_PASSWORD = 3;
    public static final int ID_TERMS = 4;

    private RecyclerHolder holder;

    public SignForm() {
        holder = new RecyclerHolder();

        List<RecyclerModel> formModelsIdentity = new ArrayList<>();

        FormModel formModel;

        formModel = new TextModel();
        formModel.setId(ID_FIRSTNAME);
        formModel.setLabel("First name");
        ((TextModel) formModel).setHint(formModel.getLabel().toLowerCase());
        formModelsIdentity.add(formModel);

        formModel = new TextModel();
        formModel.setId(ID_LASTNAME);
        formModel.setLabel("Last name");
        ((TextModel) formModel).setHint(formModel.getLabel().toLowerCase());
        formModelsIdentity.add(formModel);

        holder.add("Identité", formModelsIdentity.toArray(new FormModel[formModelsIdentity.size()]));

        List<RecyclerModel> formModelsAccount = new ArrayList<>();

        formModel = new CheckboxSpinModel();
        formModel.setLabel("Type");
        ((CheckboxSpinModel) formModel).setItems(new String[]{ "Samir", "Sam", "S"});
        formModel.setValue(new Integer[]{ 0, 2 });
        formModelsAccount.add(formModel);

        formModel = new RadioSpinModel();
        formModel.setLabel("Model");
        ((RadioSpinModel) formModel).setItems(new String[]{ "Yes", "No", "None"});
        formModel.setValue(1);
        formModelsAccount.add(formModel);

        formModel = new TextModel();
        formModel.setId(ID_EMAIL);
        formModel.setLabel("Email");
        ((TextModel) formModel).setHint(formModel.getLabel().toLowerCase());
        formModelsAccount.add(formModel);

        formModel = new PasswordModel();
        formModel.setId(ID_PASSWORD);
        formModel.setLabel("Password");
        formModelsAccount.add(formModel);

        formModel = new BooleanModel();
        formModel.setId(ID_TERMS);
        formModel.setLabel("Terms of use");
        formModelsAccount.add(formModel);

        holder.add("Compte", formModelsAccount.toArray(new FormModel[formModelsAccount.size()]));
    }

    public RecyclerHolder getHolder() {
        return holder;
    }
}
