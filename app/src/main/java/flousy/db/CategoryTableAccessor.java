package flousy.db;

import flousy.bean.Category;
import flousy.bean.ListCategories;

/**
 * Created by Samir on 11/06/2015.
 */
public interface CategoryTableAccessor extends TableAccessor<Category> {

    String CATEGORY_TABLE_NAME = "categories";

    String CATEGORY_ID = "category_id";
    String CATEGORY_NAME = "category_name";

    ListCategories selectAll();
}
