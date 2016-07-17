package com.sasd13.flousy.content.form;

import android.content.Context;
import android.text.InputType;

import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.DateItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.EditTextItemModel;
import com.sasd13.androidex.gui.widget.recycler.form.RadioSpinItemModel;
import com.sasd13.androidex.util.DateTimeHelper;
import com.sasd13.flousy.R;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.bean.OperationType;

import org.joda.time.LocalDate;

import java.sql.Timestamp;
import java.util.Arrays;

/**
 * Created by ssaidali2 on 20/06/2016.
 */
public class OperationForm extends Form {

    private Context context;
    private String[] operationTypes;
    private String debit, credit;
    private DateItemModel modelDateRealization;
    private EditTextItemModel modelTitle, modelAmount;
    private RadioSpinItemModel modelTypes;

    public OperationForm(Context context) {
        super();

        this.context = context;
        this.operationTypes = context.getResources().getStringArray(R.array.operation_types);
        debit = context.getResources().getString(R.string.operation_type_debit);
        credit = context.getResources().getString(R.string.operation_type_credit);

        String pattern = DateTimeHelper.getLocaleDateFormatPattern(context, DateTimeHelper.Format.SHORT);

        modelDateRealization = new DateItemModel(pattern);
        modelDateRealization.setLabel(context.getResources().getString(R.string.operation_label_date));
        holder.add(new RecyclerHolderPair(modelDateRealization));

        modelTitle = new EditTextItemModel();
        modelTitle.setLabel(context.getResources().getString(R.string.operation_label_title));
        modelTitle.setHint(modelTitle.getLabel().toLowerCase());
        holder.add(new RecyclerHolderPair(modelTitle));

        modelAmount = new EditTextItemModel();
        modelAmount.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        modelAmount.setLabel(context.getResources().getString(R.string.operation_label_amount));
        holder.add(new RecyclerHolderPair(modelAmount));

        modelTypes = new RadioSpinItemModel();
        modelTypes.setLabel(context.getResources().getString(R.string.operation_label_type));
        modelTypes.setItems(context.getResources().getStringArray(R.array.operation_types));
        holder.add(new RecyclerHolderPair(modelTypes));
    }

    public void bindOperation(Operation operation) {
        if (operation.getDateRealization() != null) {
            modelDateRealization.setValue(new LocalDate(operation.getDateRealization()));
        }

        if (operation.getTitle() != null) {
            modelTitle.setValue(operation.getTitle());
        }

        if (operation.getAmount() != Double.valueOf(0)) {
            modelAmount.setValue(String.valueOf(Math.abs(operation.getAmount())));
        }

        if (OperationType.DEBIT.equals(operation.getType())) {
            modelTypes.setValue(Arrays.asList(operationTypes).indexOf(debit));
        } else if (OperationType.CREDIT.equals(operation.getType())) {
            modelTypes.setValue(Arrays.asList(operationTypes).indexOf(credit));
        }
    }

    public Operation getEditable() throws FormException {
        Operation operation = new Operation();

        operation.setDateRealization(new Timestamp(modelDateRealization.getValue().toDate().getTime()));
        operation.setTitle(modelTitle.getValue());
        operation.setAmount(Double.valueOf(modelAmount.getValue()));

        if (operationTypes[modelTypes.getValue()].equals(debit)) {
            operation.setType(OperationType.DEBIT);
            operation.setAmount(0 - operation.getAmount());
        } else if (operationTypes[modelTypes.getValue()].equals(credit)) {
            operation.setType(OperationType.CREDIT);
        } else {
            throw new FormException(context.getResources().getString(R.string.form_operation_message_error_type));
        }

        return operation;
    }
}
