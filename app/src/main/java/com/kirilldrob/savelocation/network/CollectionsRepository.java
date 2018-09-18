package com.kirilldrob.savelocation.network;

import java.util.List;

public class CollectionsRepository {
     private  static  CollectionsRepository INSTANCE=new CollectionsRepository();
     public List<CuratedCollection> collectionList;

       public static CollectionsRepository getInstance() {
        return INSTANCE;
    }

}
