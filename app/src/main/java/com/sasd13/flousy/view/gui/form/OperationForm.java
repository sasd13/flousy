package com.sasd13.flousy.view.gui.form;

import android.content.Context;
import android.text.InputType;

import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.DateItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.SpinRadioItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.androidex.util.DateTimeHelper;
import com.sasd13.flousy.R;
import com.sasd13.flousy.util.EnumOperationType;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;

import java.sql.Timestamp;
import java.util.Arrays;

/**
 * Created by ssaidali2 on 20/06/2016.
 */
public class OperationForm extends Form {

    private String[] operationTypes;
    private DateItemModel modelDateRealization;
    private TextItemModel modelTitle, modelAmount;
    private SpinRadioItemModel modelTypes;

    public OperationForm(Context context) {
        super(context);

        operationTypes = context.getStringArray(R.array.operation_types);

        String pattern = DateTimeHelper.getLocaleDateFormatPattern(context, DateTimeHelper.EnumFormat.SHORT);

        modelDateRealization = new DateItemModel(pattern);
        modelDateRealization.setLabel(context.getString(R.string.operation_label_date));
        holder.add(new RecyclerHolderPair(modelDateRealization));

        modelTitle = new TextItemModel();
        modelTitle.setLabel(context.getString(R.string.operation_label_title));
        modelTitle.setHint(modelTitle.getLabel().toLowerCase());
        holder.add(new RecyclerHolderPair(modelTitle));

        modelAmount = new TextItemModel(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        modelAmount.setLabel(context.getString(R.string.operation_label_amount));
        holder.add(new RecyclerHolderPair(modelAmount));

        modelTypes = new SpinRadioItemModel();
        modelTypes.setLabel(context.getString(R.string.operation_label_type));
        modelTypes.setItems(context.getResources().getStringArray(R.array.operation_types));
        holder.add(new RecyclerHolderPair(modelTypes));
    }

    public void bindOperation(Operation operation) {
        modelDateRealization.setValue(new LocalDate(operation.getDateRealization()));
        modelTitle.setValue(operation.getTitle());

        if (operation.getAmount() != Double.valueOf(0)) {
            modelAmount.setValue(String.valueOf(Math.abs(operation.getAmount())));
        }

        if (EnumOperationType.DEBIT == operation.getType()) {
            modelTypes.setValue(Arrays.asList(operationTypes).indexOf(context.getString(EnumOperationType.DEBIT.getStringRes())));
        } else if (EnumOperationType.CREDIT == operation.getType()) {
            modelTypes.setValue(Arrays.asList(operationTypes).indexOf(context.getString(EnumOperationType.CREDIT.getStringRes())));
        }
    }

    public Operation getEditable() throws FormException {
        validForm();

        Operation operation = new Operation();

        operation.setDateRealization(new Timestamp(modelDateRealization.getValue().toDate().getTime()));
        operation.setTitle(modelTitle.getValue().trim());
        operation.setAmount(Double.valueOf(modelAmount.getValue().trim()));

        if (operationTypes[modelTypes.getValue()].equals(context.getString(EnumOperationType.DEBIT.getStringRes()))) {
            operation.setType(EnumOperationType.DEBIT);
            operation.setAmount(0 - operation.getAmount());
        } else if (operationTypes[modelTypes.getValue()].equals(context.getString(EnumOperationType.CREDIT.getStringRes()))) {
            operation.setType(EnumOperationType.CREDIT);
        }

        return operation;
    }

    private void validForm() throws FormException {
        validTitle();
        validAmount();
        validType();
    }

    private void validTitle() throws FormException {
        if (StringUtils.isBlank(modelTitle.getValue())) {
            throw new FormException(context, R.string.form_operation_message_error_title);
        }
    }

    private void validAmount() throws FormException {
        if (StringUtils.isBlank(modelAmount.getValue()) || Double.valueOf(modelAmount.getValue()) < 0) {
            throw new FormException(context, R.string.form_operation_message_error_amount);
        }
    }

    private void validType() throws FormException {
        if (modelTypes.getValue() == -1) {
            throw new FormException(context, R.string.form_operation_message_error_type);
        }
    }
}
