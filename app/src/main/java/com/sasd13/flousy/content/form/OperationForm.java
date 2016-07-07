package com.sasd13.flousy.content.form;

import android.content.Context;
import android.text.InputType;

import com.sasd13.androidex.gui.widget.recycler.IRecyclerModel;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.form.DateModel;
import com.sasd13.androidex.gui.widget.recycler.form.RadioSpinModel;
import com.sasd13.androidex.gui.widget.recycler.form.TextModel;
import com.sasd13.androidex.util.DateTimeHelper;
import com.sasd13.flousy.R;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 20/06/2016.
 */
public class OperationForm extends Form {

    private DateModel dateModelDateRealization;
    private TextModel textModelTitle, textModelAmount;
    private RadioSpinModel radioSpinModelType;

    public OperationForm(Context context) {
        super(context);
    }

    public RecyclerHolder fabricate() {
        List<IRecyclerModel> formModels = new ArrayList<>();

        String pattern = DateTimeHelper.getLocaleDateFormatPattern(context, DateTimeHelper.Format.SHORT);

        dateModelDateRealization = new DateModel(pattern);
        dateModelDateRealization.setLabel(context.getResources().getString(R.string.operation_label_date));
        formModels.add(dateModelDateRealization);

        textModelTitle = new TextModel();
        textModelTitle.setLabel(context.getResources().getString(R.string.operation_label_title));
        textModelTitle.setHint(textModelTitle.getLabel().toLowerCase());
        formModels.add(textModelTitle);

        textModelAmount = new TextModel();
        textModelAmount.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        textModelAmount.setLabel(context.getResources().getString(R.string.operation_label_amount));
        formModels.add(textModelAmount);

        radioSpinModelType = new RadioSpinModel();
        radioSpinModelType.setLabel(context.getResources().getString(R.string.operation_label_type));
        formModels.add(radioSpinModelType);

        holder.add(formModels.toArray(new IRecyclerModel[formModels.size()]));

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
        setType(items, -1);
    }

    public void setType(String[] items, Integer selected) {
        radioSpinModelType.setItems(items);
        radioSpinModelType.setValue(selected);
    }
}
