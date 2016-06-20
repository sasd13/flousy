package com.sasd13.flousy;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;
import com.sasd13.androidex.gui.widget.recycler.form.Form;
import com.sasd13.androidex.gui.widget.recycler.form.FormItemBoolean;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.constant.Extra;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.util.SessionHelper;
import com.sasd13.javaex.db.LayeredPersistor;

public class SettingActivity extends MotherActivity implements RecyclerItem.OnClickListener, FormItemBoolean.OnCheckedChangeListener {

    private static class FormHolder {
        static final int FORMIDENTITY_ID_FIRSTNAME = 0;
        static final int FORMIDENTITY_ID_LASTNAME = 1;
        static final int FORMIDENTITY_ID_EMAIL = 2;
        static final int FORMIDENTITY_ID_PASSWORD = 3;
        static final int FORMIDENTITY_ID_ACCOUNT = 4;

        int[] formIdentityIds = {
                FORMIDENTITY_ID_FIRSTNAME,
                FORMIDENTITY_ID_LASTNAME,
                FORMIDENTITY_ID_EMAIL,
                FORMIDENTITY_ID_PASSWORD,
                FORMIDENTITY_ID_ACCOUNT
        };

        Form formIdentity;
    }

    private FormHolder formHolder;

    private Customer customer;
    private LayeredPersistor persistor = new LayeredPersistor(SQLiteDAO.getInstance());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);
        GUIHelper.colorTitles(this);

        createFormCustomer();
    }

    private void createFormCustomer() {
        formHolder = new FormHolder();

        //formHolder.formIdentity = new Form((RecyclerView) findViewById(R.id.setting_form_user_recyclerview_identity));
        formHolder.formIdentity.setScrollingDisabled(true);

        addFormIdentityItems();
    }

    private void addFormIdentityItems() {
        /*FormItem formItem = null;

        for (int id : formHolder.formIdentityIds) {
            switch (id) {
                case FormHolder.FORMIDENTITY_ID_FIRSTNAME:
                    formItem = new FormItemText();
                    formItem.setLabel("Prénom");
                    ((FormItemText) formItem).setMessage("Votre prénom");
                    ((FormItemText) formItem).setHint("prenom");
                    formItem.setOnClickListener(this);
                    break;
                case FormHolder.FORMIDENTITY_ID_LASTNAME:
                    formItem = new FormItemText();
                    formItem.setLabel("Nom");
                    ((FormItemText) formItem).setMessage("Votre nom");
                    ((FormItemText) formItem).setHint("nom");
                    formItem.setOnClickListener(this);
                    break;
                case FormHolder.FORMIDENTITY_ID_EMAIL:
                    formItem = new FormItemText();
                    formItem.setLabel("Email");
                    ((FormItemText) formItem).setMessage("Votre email");
                    ((FormItemText) formItem).setHint("email");
                    formItem.setOnClickListener(this);
                    break;
                case FormHolder.FORMIDENTITY_ID_ACCOUNT:
                    formItem = new FormItemSwitch();
                    formItem.setLabel("Activer compte");
                    ((FormItemBinary) formItem).setOnCheckedChangeListener(this);
                    break;
            }

            if (formItem != null) {
                formItem.setId(id);

                formHolder.formIdentity.add(formItem);
            }

            formItem = null;
        }*/
    }

    @Override
    protected void onStart() {
        super.onStart();

        customer = persistor.read(SessionHelper.getExtraIdFromSession(Extra.CUSTOMER_ID), Customer.class);

        fillFormIdentity();
    }

    private void fillFormIdentity() {
        /*for (FormItem formItem : formHolder.formIdentity.getItems()) {
            switch (formItem.getId()) {
                case FormHolder.FORMIDENTITY_ID_FIRSTNAME:
                    formItem.getInput().setValue(customer.getFirstName());
                    break;
                case FormHolder.FORMIDENTITY_ID_LASTNAME:
                    formItem.getInput().setValue(customer.getLastName());
                    break;
                case FormHolder.FORMIDENTITY_ID_EMAIL:
                    formItem.getInput().setValue(customer.getEmail());
                    break;
                default:
                    break;
            }
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sign, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sign_action_done:
                updateCustomer();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void updateCustomer() {
        String[] tabFormErrors = validFormCustomer();

        if (true) {
            tryToPerformUpdateCustomer();
        } else {
            OptionDialog.showOkDialog(this, "Error form", tabFormErrors[0]);
        }
    }

    private String[] validFormCustomer() {
        //TODO

        return null;
    }

    private void tryToPerformUpdateCustomer() {
        /*String email = (String) ((FormItem) formHolder.formIdentity
                .findItemById(FormHolder.FORMIDENTITY_ID_EMAIL))
                .getInput().getValue();

        Map<String, String[]> parameters = new HashMap<>();
        parameters.put(Parameter.EMAIL.getName(), new String[]{ email });

        List<Customer> customers = persistor.read(parameters, Customer.class);
        if (customers.isEmpty() || customers.get(0).getId() == SessionHelper.getExtraIdFromSession(Extra.CUSTOMER_ID)) {
            performUpdate();
        } else {
            OptionDialog.showOkDialog(this, "Error update", "Email (" + email + ") already exists");
        }*/
    }

    private void performUpdate() {
        editCustomerWithForm(customer);
        persistor.update(customer);
        Toast.makeText(this, R.string.message_saved, Toast.LENGTH_SHORT).show();
    }

    private void editCustomerWithForm(Customer customer) {
        /*for (FormItem formItem : formHolder.formIdentity.getItems()) {
            switch (formItem.getId()) {
                case FormHolder.FORMIDENTITY_ID_FIRSTNAME:
                    customer.setFirstName((String) formItem.getInput().getValue());
                    break;
                case FormHolder.FORMIDENTITY_ID_LASTNAME:
                    customer.setLastName((String) formItem.getInput().getValue());
                    break;
                case FormHolder.FORMIDENTITY_ID_EMAIL:
                    customer.setEmail((String) formItem.getInput().getValue());
                    break;
            }
        }*/
    }

    @Override
    public void onClick(RecyclerItem recyclerItem) {
        /*final FormItem formItem = (FormItem) recyclerItem;

        if (formItem.getId() == FormHolder.FORMIDENTITY_ID_FIRSTNAME) {
            String[] firstnames = {"Samir", "Sam", "S"};
            SpinDialog spinDialog = new SpinDialogRadio(this);

            spinDialog.setTitle(formItem.getLabel());

            if (spinDialog instanceof SpinDialogRadio) {
                spinDialog.setItems(firstnames);

                for (int i=0; i<firstnames.length; i++) {
                    if (firstnames[i].equals(formItem.getInput().getValue())) {
                        ((SpinDialogRadio) spinDialog).setSelectedPosition(i);
                    }
                }

                ((SpinDialogRadio) spinDialog).setOnItemSelectedListener(new SpinDialog.OnItemSelectedListener() {
                    @Override
                    public void onItemSelectedOnSpinDialog(SpinDialog spinDialog, int position) {
                        spinDialog.dismiss();

                        formItem.getInput().setValue(spinDialog.getItems()[position]);
                    }
                });
            } else {
                String input = formItem.getInput().getStringValue();
                String[] f = input.split(",");
                if (f.length == 0 || f[0].isEmpty()) {
                    spinDialog.setItems(firstnames);
                } else {
                    Integer[] checked = new Integer[f.length];
                    for (int i=0; i<checked.length; i++) {
                        for (int j=0; j<firstnames.length; j++) {
                            if (f[i].trim().equals(firstnames[j])) {
                                checked[i] = j;
                                break;
                            }
                        }
                    }

                    ((SpinDialogCheckbox) spinDialog).setItems(firstnames, checked);
                }

                ((SpinDialogCheckbox) spinDialog).setOnItemCheckedListener(new SpinDialog.OnItemCheckedListener() {
                    @Override
                    public void onItemCheckedOnSpinDialog(SpinDialog spinDialog, int position, boolean checked) {
                        SpinDialogCheckbox spinDialogMulti = (SpinDialogCheckbox) spinDialog;

                        if (checked) {
                            spinDialogMulti.addToCheckedPositions(position);
                        } else {
                            spinDialogMulti.removeFromCheckedPositions(position);
                        }
                    }
                });

                ((SpinDialogCheckbox) spinDialog).setOnButtonPositiveClickListener(new SpinDialogCheckbox.OnClickListener() {
                    @Override
                    public void onClickOnDialog(Dialog dialog) {
                        dialog.dismiss();

                        SpinDialogCheckbox spinDialogMulti = (SpinDialogCheckbox) dialog;
                        String value = Arrays.toString(spinDialogMulti.getCheckedItems());

                        formItem.getInput().setValue(value.substring(1, value.length() - 1));
                    }
                });
            }

            spinDialog.show();
        } else {
            EditorDialog editorDialog = new EditorDialog(this);
            //editorDialog.setMessage(((FormItemText) formItem).getMessage());
            editorDialog.setHint(((FormItemText) formItem).getHint());

            if (formItem.getInput() != null) {
                editorDialog.setText(formItem.getInput().getStringValue());
            } else {
                editorDialog.setText(null);
            }

            editorDialog.setOnButtonPositiveClickListener(new EditorDialog.OnClickListener() {
                @Override
                public void onClickOnDialog(Dialog dialog) {
                    EditorDialog editorDialog = (EditorDialog) dialog;

                    formItem.getInput().setValue(editorDialog.getText());

                    editorDialog.dismiss();
                }
            });

            editorDialog.show();
        }*/
    }

    @Override
    public void onCheckedChange(FormItemBoolean formItemBoolean, boolean checked) {

    }
}