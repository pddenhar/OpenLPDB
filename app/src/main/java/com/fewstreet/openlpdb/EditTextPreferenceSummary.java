package com.fewstreet.openlpdb;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Peter on 4/8/2015.
 */
public class EditTextPreferenceSummary extends android.preference.EditTextPreference {

    public EditTextPreferenceSummary(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        setSummary(getSummary());
    }

    @Override
    public CharSequence getSummary() {
        return this.getText();
    }
}