package com.sasd13.flousy.bean.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.sasd13.flousy.util.EnumPreference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 20/05/2017.
 */

public class UserPreferences implements Parcelable {

    public static final Creator<UserPreferences> CREATOR = new Creator<UserPreferences>() {
        @Override
        public UserPreferences createFromParcel(Parcel in) {
            return new UserPreferences(in);
        }

        @Override
        public UserPreferences[] newArray(int size) {
            return new UserPreferences[size];
        }
    };

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

    protected UserPreferences(Parcel in) {
        this(in.createTypedArrayList(UserPreference.CREATOR));
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(preferences);
    }
}
