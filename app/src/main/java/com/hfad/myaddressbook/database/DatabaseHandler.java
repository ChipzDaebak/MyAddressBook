package com.hfad.myaddressbook.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.hfad.myaddressbook.model.AddressBook;

import java.util.Vector;

public class DatabaseHandler extends SQLiteOpenHelper {

    private final static String dbName = "MY_ADDRESS_BOOK";
    private final static int dbVersion = 1;

    private final static String tableName = "Address_Book";
    private final static String columnName_AddressBookID = "Address_Book_ID";
    private final static String columnName_FirstName = "First_Name";
    private final static String columnName_LastName = "Last_Name";
    private final static String columnName_City = "City";
    private final static String columnName_State = "State";
    private final static String columnName_Phone = "Phone";
    private final static String columnName_Email = "Email";
    private final static String columnName_Picture = "Picture";

    private final static String sqlCreate = "CREATE TABLE " +
            tableName + " (" + columnName_AddressBookID + "INTEGER PRIMARY KEY, " +
            columnName_FirstName + "TEXT, " + columnName_LastName + "TEXT, " +
            columnName_City + "TEXT, " + columnName_State + "TEXT, " +
            columnName_Phone + "TEXT, " + columnName_Email + "TEXT, " +
            columnName_Picture + "TEXT" + ")";

    private final static String sqlDelete = "DROP TABLE IF EXISTS " + tableName;

    public DatabaseHandler(@Nullable Context context){
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldV, int newV) {
        if(oldV != newV){
            sqLiteDatabase.execSQL(sqlDelete);
            onCreate(sqLiteDatabase);
        }
    }

    public void createAddressBook(AddressBook addBook){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues content = new ContentValues();
        content.put(columnName_FirstName, addBook.getFirstName());
        content.put(columnName_LastName, addBook.getLastName());
        content.put(columnName_City, addBook.getCity());
        content.put(columnName_State, addBook.getState());
        content.put(columnName_Phone, addBook.getPhone());
        content.put(columnName_Email, addBook.getEmail());
        content.put(columnName_Picture, addBook.getPicture());

        database.insert(tableName, null, content);
        database.close();
    }

    public Vector<AddressBook> getAddressBook(){
        SQLiteDatabase database = this.getWritableDatabase();
        Vector<AddressBook> addBook = new Vector<>();
        String que = "SELECT * FROM " + tableName;
        Cursor cursor = database.rawQuery(que, null);
        if(cursor.moveToFirst()){
            do{
                AddressBook addressBook = new AddressBook(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7)
                );

                addBook.add(addressBook);
            } while(cursor.moveToNext());

            return addBook;
        }

        return null;
    }
}
