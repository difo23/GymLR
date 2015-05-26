package com.smartphone.lizandro.gymlr.mvp.view;

import android.app.Activity;
import android.os.Bundle;
import com.smartphone.lizandro.gymlr.ApplicationClass;


public class InjectedActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((ApplicationClass) getApplicationContext()).inject(this);
    }
}
