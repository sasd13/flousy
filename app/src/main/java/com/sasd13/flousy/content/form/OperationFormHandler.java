package com.sasd13.flousy.content.form;

import android.content.Context;
import android.text.InputType;

import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerModel;
import com.sasd13.androidex.gui.widget.recycler.form.DateModel;
import com.sasd13.androidex.gui.widget.recycler.form.RadioSpinModel;
import com.sasd13.androidex.gui.widget.recycler.form.TextModel;
import com.sasd13.androidex.util.DateTimeHelper;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 20/06/2016.
 */
public class OperationFormHandler extends FormHandler {

    private DateModel dateModelDateRealization;
    private TextModel textModelTitle, textModelAmount;
    private RadioSpinModel radioSpinModelType;

    public OperationFormHandler(Context context) {
        super(context);
    }

    public RecyclerHolder fabricate() {
        List<RecyclerModel> formModels = new ArrayList<>();

        String pattern = DateTimeHelper.getLocaleDateFormatPattern(context, DateTimeHelper.Format.SHORT);

        dateModelDateRealization = new DateModel(pattern);
        dateModelDateRealization.setLabel("Date de réalisation");
        formModels.add(dateModelDateRealization);

        textModelTitle = new TextModel();
        textModelTitle.setLabel("Intitulé");
        textModelTitle.setHint(textModelTitle.getLabel().toLowerCase());
        formModels.add(textModelTitle);

        textModelAmount = new TextModel();
        textModelAmount.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        textModelAmount.setLabel("Montant");
        formModels.add(textModelAmount);

        radioSpinModelType = new RadioSpinModel();
        radioSpinModelType.setLabel("Type");
        radioSpinModelType.setItems(new String[] {"Débit", "Crédit"});
        formModels.add(radioSpinModelType);

        holder.add(formModels.toArray(new RecyclerModel[formModels.size()]));

        return holder;
    }

    public LocalDate getDateRealization() {
        return dateModelDateRealization.getValue();
    }
    
    public void setDateRealization(LocalDate dateRealization) {
        dateModelDateRealization.setValue(dateRealization);
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
