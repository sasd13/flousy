package com.sasd13.flousy.content.form;

import android.content.Context;
import android.text.InputType;

import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.form.DateItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.RadioSpinItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.androidex.util.DateTimeHelper;
import com.sasd13.flousy.R;

import org.joda.time.LocalDate;

/**
 * Created by ssaidali2 on 20/06/2016.
 */
public class OperationForm extends Form {

    private DateItemModel dateItemModelDateRealization;
    private TextItemModel textItemModelTitle, textItemModelAmount;
    private RadioSpinItemModel radioSpinItemModelType;

    public OperationForm(Context context) {
        super(context);
    }

    public RecyclerHolder fabricate() {
        String pattern = DateTimeHelper.getLocaleDateFormatPattern(context, DateTimeHelper.Format.SHORT);

        dateItemModelDateRealization = new DateItemModel(pattern);
        dateItemModelDateRealization.setLabel(context.getResources().getString(R.string.operation_label_date));
        holder.add(dateItemModelDateRealization);

        textItemModelTitle = new TextItemModel();
        textItemModelTitle.setLabel(context.getResources().getString(R.string.operation_label_title));
        textItemModelTitle.setHint(textItemModelTitle.getLabel().toLowerCase());
        holder.add(textItemModelTitle);

        textItemModelAmount = new TextItemModel();
        textItemModelAmount.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        textItemModelAmount.setLabel(context.getResources().getString(R.string.operation_label_amount));
        holder.add(textItemModelAmount);

        radioSpinItemModelType = new RadioSpinItemModel();
        radioSpinItemModelType.setLabel(context.getResources().getString(R.string.operation_label_type));
        holder.add(radioSpinItemModelType);

        return holder;
    }

    public LocalDate getDateRealization() {
        return dateItemModelDateRealization.getValue();
    }
    
    public void setDateRealization(LocalDate dateRealization) {
        dateItemModelDateRealization.setValue(dateRealization);
    }

    public String getTitle() {
        return textItemModelTitle.getValue();
    }

    public void setTitle(String title) {
        textItemModelTitle.setValue(title);
    }

    public String getAmount() {
        return textItemModelAmount.getValue();
    }

    public void setAmount(String amount) {
        textItemModelAmount.setValue(amount);
    }

    public Integer getType() {
        return radioSpinItemModelType.getValue();
    }

    public void setType(String[] items) {
        setType(items, -1);
    }

    public void setType(String[] items, Integer selected) {
        radioSpinItemModelType.setItems(items);
        radioSpinItemModelType.setValue(selected);
    }
}
