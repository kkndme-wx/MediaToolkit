package com.example.media.toolkit.view;

import androidx.fragment.app.Fragment;

public abstract class BaseFragment  extends Fragment {
    protected abstract void onServiceReady(boolean ready);
    protected  boolean serviceReady = false;

    @Override
    public void onResume() {
        super.onResume();
        //最后调用


    }

    public void serviceReady(boolean ready) {
        serviceReady = true;
        onServiceReady(ready);
    }

    public abstract String getName();
}
