package com.sport.saransh.nvigationdrawer;

/**
 * Created by SARANSH on 6/22/2016.
 */

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by SARANSH on 4/17/2016.
 */
public class SingletonJava {
    private RequestQueue mrequestQueue;
    public static SingletonJava sInstance = null;
    private ImageLoader mImageLoader;

    private SingletonJava() {
        mrequestQueue = Volley.newRequestQueue(ApplicationContext.getAppContext());
        mImageLoader = new ImageLoader(mrequestQueue, new ImageLoader.ImageCache()
        {

            private LruCache<String,Bitmap> cach = new LruCache<String,Bitmap>(20);
            @Override
            public Bitmap getBitmap(String url) {
                return cach.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cach.put(url, bitmap);

            }
        });

    }
    public static SingletonJava getinstance() {
        if (sInstance == null) {
            sInstance = new SingletonJava();
        }
        return sInstance;

    }

    public RequestQueue getrequestqueue() {
        return mrequestQueue;

    }
    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
    public <T> void addToRequestQueue(Request<T> req) {
        getrequestqueue().add(req);
    }

}


