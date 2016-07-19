package com.sasd13.flousy.content.form;

import android.content.Context;
import android.text.InputType;

import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.DateItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.SpinRadioItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.androidex.util.DateTimeHelper;
import com.sasd13.flousy.R;
import com.sasd13.flousy.bean.EnumOperationType;
import com.sasd13.flousy.bean.Operation;

import org.joda.time.LocalDate;

import java.sql.Timestamp;
import java.util.Arrays;

/**
 * Created by ssaidali2 on 20/06/2016.
 */
public class OperationForm extends Form {

    private String[] operationTypes;
    private String debit, credit;
    private DateItemModel modelDateRealization;
    private TextItemModel modelTitle, modelAmount;
    private SpinRadioItemModel modelTypes;

    public OperationForm(Context context) {
        super(context);

        this.operationTypes = context.getResources().getStringArray(R.array.operation_types);
        debit = context.getResources().getString(R.string.operation_type_debit);
        credit = context.getResources().getString(R.string.operation_type_credit);

        String pattern = DateTimeHelper.getLocaleDateFormatPattern(context, DateTimeHelper.EnumFormat.SHORT);

        modelDateRealization = new DateItemModel(pattern);
        modelDateRealization.setLabel(context.getResources().getString(R.string.operation_label_date));
        holder.add(new RecyclerHolderPair(modelDateRealization));

        modelTitle = new TextItemModel();
        modelTitle.setLabel(context.getResources().getString(R.string.operation_label_title));
        modelTitle.setHint(modelTitle.getLabel().toLowerCase());
        holder.add(new RecyclerHolderPair(modelTitle));

        modelAmount = new TextItemModel();
        modelAmount.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        modelAmount.setLabel(context.getResources().getString(R.string.operation_label_amount));
        holder.add(new RecyclerHolderPair(modelAmount));

        modelTypes = new SpinRadioItemModel();
        modelTypes.setLabel(context.getResources().getString(R.string.operation_label_type));
        modelTypes.setItems(context.getResources().getStringArray(R.array.operation_types));
        holder.add(new RecyclerHolderPair(modelTypes));
    }

    public void bindOperation(Operation operation) {
        modelDateRealization.setValue(new LocalDate(operation.getDateRealization()));
        modelTitle.setValue(operation.getTitle());

        if (operation.getAmount() != Double.valueOf(0)) {
            modelAmount.setValue(String.valueOf(Math.abs(operation.getAmount())));
        }

        if (EnumOperationType.DEBIT.equals(operation.getType())) {
            modelTypes.setValue(Arrays.asList(operationTypes).indexOf(debit));
        } else if (EnumOperationType.CREDIT.equals(operation.getType())) {
            modelTypes.setValue(Arrays.asList(operationTypes).indexOf(credit));
        }
    }

    public Operation getEditable() throws FormException {
        validForm();

        Operation operation = new Operation();

        operation.setDateRealization(new Timestamp(modelDateRealization.getValue().toDate().getTime()));
        operation.setTitle(modelTitle.getValue().trim());
        operation.setAmount(Double.valueOf(modelAmount.getValue().trim()));

        if (operationTypes[modelTypes.getValue()].equals(debit)) {
            operation.setType(EnumOperationType.DEBIT);
            operation.setAmount(0 - operation.getAmount());
        } else if (operationTypes[modelTypes.getValue()].equals(credit)) {
            operation.setType(EnumOperationType.CREDIT);
        }

        return operation;
    }

    private void validForm() throws FormException {
        if (modelTitle.getValue() == null
                || modelTitle.getValue().trim().isEmpty()) {
            throw new FormException(context.getResources().getString(R.string.form_operation_message_error_title));
        }

        if (modelAmount.getValue() == null
                || modelTitle.getValue().trim().isEmpty()
                || Double.valueOf(modelAmount.getValue()) < 0) {
            throw new FormException(context.getResources().getString(R.string.form_operation_message_error_amount));
        }

        if (modelTypes.getValue() == -1) {
            throw new FormException(context.getResources().getString(R.string.form_operation_message_error_type));
        }
    }
}
