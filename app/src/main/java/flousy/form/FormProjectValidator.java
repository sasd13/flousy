package flousy.form;

/**
 * Created by Samir on 13/03/2015.
 */
public class FormProjectValidator {

    private FormProjectValidator() {}

    public static void validForm(String title,
                                   String description) throws FormException {
        validTitle(title);
        validDescription(description);
    }

    private static void validTitle(String title) throws FormException {
        if (title.length() == 0) {
            throw new FormException("title is empty");
        }
    }

    private static void validDescription(String description) throws FormException {
        if (description.length() == 0) {
            throw new FormException("description is empty");
        }
    }
}
