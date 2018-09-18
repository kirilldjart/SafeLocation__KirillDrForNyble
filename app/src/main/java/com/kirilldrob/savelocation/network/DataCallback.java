package com.kirilldrob.savelocation.network;

public interface DataCallback {
   void onDataReady();
   void onError(String msg);
}
