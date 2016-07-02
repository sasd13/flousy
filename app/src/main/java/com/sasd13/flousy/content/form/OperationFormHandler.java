package com.sasd13.flousy.content.form;

import android.content.Context;
import android.text.InputType;

import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerModel;
import com.sasd13.androidex.gui.widget.recycler.form.RadioSpinModel;
import com.sasd13.androidex.gui.widget.recycler.form.TextModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 20/06/2016.
 */
public class OperationFormHandler extends FormHandler {

    private TextModel textModelDateRealization, textModelTitle, textModelAmount;
    private RadioSpinModel radioSpinModelType;

    public OperationFormHandler(Context context) {
        super(context);
    }

    public RecyclerHolder fabricate() {
        List<RecyclerModel> formModels = new ArrayList<>();

        textModelDateRealization = new TextModel();
        textModelDateRealization.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);
        textModelDateRealization.setLabel("Date de réalisation");
        formModels.add(textModelDateRealization);

        textModelTitle = new TextModel();
        textModelTitle.setLabel("Intitulé");
        textModelTitle.setHint(textModelTitle.getLabel().toLowerCase());
        formModels.add(textModelTitle);

        textModelAmount = new TextModel();
        textModelAmount.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        textModelAmount.setLabel("Montant");
        formModels.add(textModelAmount);

        radioSpinModelType = new RadioSpinModel();
        radioSpinModelType.setLabel("Type");
        radioSpinModelType.setItems(new String[] {"Débit", "Crédit"});
        formModels.add(radioSpinModelType);

        holder.add(formModels.toArray(new RecyclerModel[formModels.size()]));

        return holder;
    }

    public String getDateRealization() {
        return textModelDateRealization.getValue();
    }
    
    public void setDateRealization(String dateRealization) {
        textModelDateRealization.setValue(dateRealization);
    }

    public String getTitle() {
        return textModelTitle.getValue();
    }

    public void setTitle(String title) {
        textModelTitle.setValue(title);
    }

    public String getAmount() {
        return textModelAmount.getValue();
    }

    public void setAmount(String amount) {
        textModelAmount.setValue(amount);
    }

    public Integer getType() {
        return radioSpinModelType.getValue();
    }

    public void setType(String[] items) {
        setType(-1, items);
    }

    public void setType(Integer type, String[] items) {
        radioSpinModelType.setValue(type);
        radioSpinModelType.setItems(items);
    }
}
