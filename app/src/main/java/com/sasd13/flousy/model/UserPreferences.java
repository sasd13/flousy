package com.sasd13.flousy.model;

import com.sasd13.flousy.util.EnumPreference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 20/05/2017.
 */

public class UserPreferences {

    private List<UserPreference> preferences;
    private Map<EnumPreference, UserPreference> map = new HashMap<>();

    public UserPreferences(List<UserPreference> preferences) {
        this.preferences = preferences;

        for (EnumPreference mate : EnumPreference.values()) {
            for (UserPreference preference : preferences) {
                if (preference.matches(mate)) {
                    map.put(mate, preference);

                    break;
                }
            }
        }
    }

    public List<UserPreference> getPreferences() {
        return preferences;
    }

    public UserPreference findPreference(EnumPreference mate) {
        return map.get(mate);
    }

    public String findValue(EnumPreference mate) {
        return findPreference(mate).getValue();
    }
}
