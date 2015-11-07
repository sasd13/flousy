package flousy.bean;

import flousy.util.List;

/**
 * Created by Samir on 17/10/2015.
 */
public class ListCategories extends List<Category> {

    @Override
    public Category get(Object id) {
        for (Category category : getList()) {
            if (category.getId() == (long) id) {
                return category;
            }
        }

        return null;
    }
}
