package sellit.soict.com;

import android.app.Application;

import sellit.soict.com.utils.DatabaseManager;

/**
 * Created by Dang Hien on 08/04/2016.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseManager databaseManager = new DatabaseManager(this);
        databaseManager.coppyDB();
    }
}
