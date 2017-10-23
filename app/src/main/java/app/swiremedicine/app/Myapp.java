package app.swiremedicine.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by 王坤 on 2017/10/23.
 */

public class Myapp extends Application {
    public static Context appcontext;

    @Override
    public void onCreate() {
        super.onCreate();
        appcontext = this;
    }

    public static Context getAppcontext() {
        return appcontext;
    }
}
