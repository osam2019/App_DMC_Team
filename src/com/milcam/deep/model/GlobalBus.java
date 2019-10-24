package com.milcam.deep.model;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

public class GlobalBus extends Bus {
    private final Handler mHandler;

    public GlobalBus(){
        mHandler  = new Handler(Looper.getMainLooper());
    }
}