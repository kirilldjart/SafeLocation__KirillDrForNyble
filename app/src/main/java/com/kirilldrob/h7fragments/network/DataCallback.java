package com.kirilldrob.h7fragments.network;

public interface DataCallback {
   void onDataReady();
   void onError(String msg);
}
