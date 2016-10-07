package sellit.soict.com.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import sellit.soict.com.model.Address;
import sellit.soict.com.model.Category;

public class DatabaseManager {
    private SQLiteDatabase sqlDB;
    private String pathDB;
    private Context context;
    private static final String DB_NAME = "sellit.sqlite";
    private static final String TAG = "DatabaseManager";
    private ContentValues contentValues = new ContentValues();

    public DatabaseManager(Context context) {
        this.context = context;
        pathDB = Environment.getDataDirectory() + "/data/"
                + context.getPackageName() + "/databases/";

    }

    public void coppyDB() {
        Log.d(TAG, "coppyDB");
        try {
            File file = new File(pathDB);
            file.mkdir();
            file = new File(pathDB + DB_NAME);
            if (file.exists()) {
                Log.d(TAG, "File " + file.toString() + " exists");
                return;
            }
            InputStream inputStream = context.getAssets().open(DB_NAME);
            FileOutputStream outputStream = new FileOutputStream(file);
            int len;
            byte b[] = new byte[1024];
            while ((len = inputStream.read(b)) != -1) {
                outputStream.write(b, 0, len);
            }
            outputStream.close();
            inputStream.close();
            Log.d(TAG, "Write ok");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openDB() {
        sqlDB = SQLiteDatabase.openDatabase(pathDB + DB_NAME, null,
                SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDB() {
        if (sqlDB != null && sqlDB.isOpen()) {
            sqlDB.close();
        }
    }


//    public int insertToDBBlackListAddress(String address) {
//        String stanAddress = ContactsManager.standardizedPhoneNumber(address);
//        if (!checkMathAddress(stanAddress)) {
//            int id = -1;
//            contentValues.clear();
//            contentValues.put("address", stanAddress);
//            openDB();
//            id = (int) sqlDB.insert(ComonValues.TBL_BLACKLIST_ADDRESS, null, contentValues);
//            closeDB();
//            setApplicationBlacklistAddress();
//            return id;
//        }
//        return -1;
//    }


//    public int delete(String tableName, int _id) {
//        int id_del = -1;
//        openDB();
//        String[] id = {String.valueOf(_id)};
//        id_del = sqlDB.delete(tableName, "_id=?", id);
//        closeDB();
//        switch (tableName) {
//            case ComonValues.TBL_BLACKLIST_ADDRESS:
//                setApplicationBlacklistAddress();
//                break;
//            case ComonValues.TBL_BLACKLIST_TEXT:
//                setApplicationBlacklistText();
//                break;
//            case ComonValues.TBL_WHITELIST_ADDRESS:
//                setApplicationWhitelistAddress();
//                break;
//            case ComonValues.TBL_WHITELIST_TEXT:
//                setApplicationWhitelistText();
//                break;
//        }
//
//
//        return id_del;
//    }


    public Cursor getCursor(String tableName) {
        openDB();
        String sql = "Select * from " + tableName;
        Cursor cursor = sqlDB.rawQuery(sql, null);
//        if (cursor == null) {
//            return null;
//        }
        return cursor;
    }

    public Cursor getCursorWithPackageName(String packageName) {
        openDB();
        String sql = "Select * from  apps where package like '%" + packageName + "%'";
        Cursor cursor = sqlDB.rawQuery(sql, null);
//        if (cursor == null) {
//            return null;
//        }
        return cursor;

    }

    public ArrayList<Address.AddressGroup> getAddressGroup() {
        ArrayList<Address.AddressGroup> addressGroups = new ArrayList<>();
        openDB();
        String sql = "Select * from  address_group";
        Cursor cursor = sqlDB.rawQuery(sql, null);
        if (cursor == null || cursor.getCount() <= 0) {
            return addressGroups;
        }
        while (cursor.moveToNext()) {
            long idGroup = 0;
            String nameGroup = null;
            try {
                idGroup = cursor.getInt(cursor.getColumnIndex("_id"));
                nameGroup = cursor.getString(cursor.getColumnIndex("group_name"));
            } catch (Exception e) {

            }
            Address.AddressGroup addressGroup = new Address.AddressGroup(idGroup, nameGroup);
            addressGroups.add(addressGroup);
        }

        return addressGroups;
    }

    public Address getAddress(String _id) {
        Address address = null;
        openDB();
        String sql = "Select * from  address  where _id = '" + _id+"'";
        Cursor cursor = sqlDB.rawQuery(sql, null);
        if (cursor == null || cursor.getCount() <= 0) {
            return null;
        }
        cursor.moveToFirst();
//        while (cursor.moveToNext()) {
            String id = "";
            String name = null;
            try {
                id = cursor.getString(cursor.getColumnIndex("_id"));
                name = cursor.getString(cursor.getColumnIndex("name"));
            } catch (Exception e) {

            }
            address = new Address(id, name, null);
//        }
        return address;
    }

    public Category getCategory(String _id) {
        Category category = null;
        openDB();
        String sql = "Select * from  category where _id = '" + _id+"'";
        Cursor cursor = sqlDB.rawQuery(sql, null);
        if (cursor == null || cursor.getCount() <= 0) {
            return null;
        }
        cursor.moveToFirst();
//        while (cursor.moveToNext()) {
            String id = "";
            String name = null;
            try {
                id = cursor.getString(cursor.getColumnIndex("_id"));
                name = cursor.getString(cursor.getColumnIndex("name"));
            } catch (Exception e) {

            }
            category = new Category(id, name, null);
//        }
        return category;
    }

    public ArrayList<Address> getAddressFromGroup(long idGroup) {
        ArrayList<Address> addresss = new ArrayList<>();
        openDB();
        String sql = "Select * from  address where id_group = " + idGroup;
        Cursor cursor = sqlDB.rawQuery(sql, null);
        if (cursor == null || cursor.getCount() <= 0) {
            return addresss;
        }
        while (cursor.moveToNext()) {
            String id = "";
            String name = null;
            try {
                id = cursor.getString(cursor.getColumnIndex("_id"));
                name = cursor.getString(cursor.getColumnIndex("name"));
            } catch (Exception e) {

            }
            Address address = new Address(id, name, new Address.AddressGroup(idGroup, ""));
            addresss.add(address);
        }
        return addresss;
    }

    public ArrayList<Category.CategoryGroup> getCategoryGroup() {
        ArrayList<Category.CategoryGroup> categoryGroups = new ArrayList<>();
        openDB();
        String sql = "Select * from  category_group";
        Cursor cursor = sqlDB.rawQuery(sql, null);
        if (cursor == null || cursor.getCount() <= 0) {
            return categoryGroups;
        }
        while (cursor.moveToNext()) {
            long idGroup = 0;
            String nameGroup = null;
            try {
                idGroup = cursor.getInt(cursor.getColumnIndex("_id"));
                nameGroup = cursor.getString(cursor.getColumnIndex("group_name"));
            } catch (Exception e) {

            }
            Category.CategoryGroup categoryGroup = new Category.CategoryGroup(idGroup, nameGroup);
            categoryGroups.add(categoryGroup);
        }

        return categoryGroups;
    }

    public ArrayList<Category> getCategoryFromGroup(long idGroup) {
        ArrayList<Category> categories = new ArrayList<>();
        openDB();
        String sql = "Select * from  category where id_group = " + idGroup;
        Cursor cursor = sqlDB.rawQuery(sql, null);
        if (cursor == null || cursor.getCount() <= 0) {
            return categories;
        }
        while (cursor.moveToNext()) {
            String id = "";
            String name = null;
            try {
                id = cursor.getString(cursor.getColumnIndex("_id"));
                name = cursor.getString(cursor.getColumnIndex("name"));
            } catch (Exception e) {

            }
            Category category = new Category(id, name, new Category.CategoryGroup(idGroup, ""));
            categories.add(category);
        }
        return categories;
    }

    public String getAddressFromId(String id) {
        openDB();
        String sql = "Select * from  address where _id = \'" + id + "\'";
        Cursor cursor = sqlDB.rawQuery(sql, null);
        if (cursor == null || cursor.getCount() <= 0) {
            return "";
        }
        cursor.moveToFirst();
        String address = cursor.getString(cursor.getColumnIndex("name"));
        return address;
    }

    public String getCategoryFromId(String id) {
        openDB();
        String sql = "Select * from  category where _id = \'" + id + "\'";
        Cursor cursor = sqlDB.rawQuery(sql, null);
        if (cursor == null || cursor.getCount() <= 0) {
            return "";
        }
        cursor.moveToFirst();
        String address = cursor.getString(cursor.getColumnIndex("name"));
        return address;
    }

    public void editDB() {

    }


//    public List<Blacklist> getListAddress(String tableName) {
//        List<Blacklist> lists = new ArrayList<>();
//        openDB();
//        String sql = "Select * from " + tableName;
//        Cursor cursor = sqlDB.rawQuery(sql, null);
//        if (cursor == null) {
//            return null;
//        }
//        while (cursor.moveToNext()) {
//            int id = 0;
//            String address = null;
//            try {
//                id = cursor.getInt(cursor.getColumnIndex("_id"));
//                address = cursor.getString(cursor.getColumnIndex("address"));
//            } catch (Exception e) {
//
//            }
//            Blacklist blacklist = new Blacklist(id, address);
//            lists.add(blacklist);
//        }
//        return lists;
//    }


//  public void setApplicationWhitelistText() {
//        List<Blacklist> blacklists = getListText(ComonValues.TBL_WHITELIST_TEXT);
//        PaddyApplication.WHITELIST_TEXT = blacklists;
//    }


//    public void insertToDBLogSms(int thread_id, String address, String body,
//                                 long date, int type, int sim_id) {
//        contentValues.clear();
//        contentValues.put("thread_id", thread_id);
//        contentValues.put("address", address);
//        contentValues.put("body", body);
//        contentValues.put("date", date);
//        contentValues.put("type", type);
//        contentValues.put("sim_id", sim_id == 0 ? 1 : 2);
//
//        // contentValues.put("thread_id", thread_id);
//        openDB();
//        sqlDB.insert(ComonValues.TBL_SMS_SPAM, null, contentValues);
//        Log.d(TAG, "insert: " + thread_id);
//
//    }


//    public int getTotalCountSms() {
//
//        int message_count = 0;
//        String sql = "select count(_id) as count from "
//                + ComonValues.TBL_SMS_SPAM;
//        openDB();
//        Cursor cursor = sqlDB.rawQuery(sql, null);
//        if (cursor != null && cursor.moveToFirst()) {
//            message_count = cursor.getInt(0);
//            cursor.close();
//        }
//        return message_count;
//    }


    public void clear(String tableName) {
        sqlDB.execSQL("delete from " + tableName);
    }


}
