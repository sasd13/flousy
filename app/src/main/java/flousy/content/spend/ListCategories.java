package flousy.content.spend;

import flousy.util.FlousyList;

/**
 * Created by Samir on 17/10/2015.
 */
public class ListCategories extends FlousyList<Category> {

    @Override
    public Category get(long id) {
        for (Category category : this.list) {
            if (category.getId() == id) {
                return category;
            }
        }

        return null;
    }
}
