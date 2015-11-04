package flousy.content.spend;

import flousy.util.FlousyList;

/**
 * Created by Samir on 19/06/2015.
 */
public class ListSpends extends FlousyList<Spend> {

    @Override
    public Spend get(long id) {
        for (Spend spend : this.list) {
            if (spend.getId() == id) {
                return spend;
            }
        }

        return null;
    }
}
