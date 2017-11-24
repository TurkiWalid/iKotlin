package com.androidprojects.esprit.ikotlin.handlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.androidprojects.esprit.ikotlin.models.User;

import java.util.Calendar;

/**
 * Created by Odil on 19/11/2017.
 */

public class DataBaseHandler extends SQLiteOpenHelper {

    //Database version
    public static final int DATABASE_VERSION=1;
    //Database name
    public static final String DATABASE_NAME="ikotlin";
    //table user
    public static final String TABLE_USER="user";
    //singleton
    public static DataBaseHandler sInstance;

    //Table user columns
    public static final String KEY_ID="id";
    public static final String KEY_UID="uid";
    public static final String KEY_USERNAME="username";
    public static final String KEY_EMAIL="email";
    public static final String KEY_LASTLOGGED="last_logged";
    public static final String KEY_PICTURE="picture_url";
    public static final String KEY_SKILL_LEARNER="skill_learner";
    public static final String KEY_SKILL_CHALLENGER="skill_challenger";
    public static final String KEY_SKILL_CODER="skill_coder";
    public static final String KEY_CONFIRMED_ACCOUNT="confirmed";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DataBaseHandler getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DataBaseHandler(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_ARTICLE_TABLE="CREATE TABLE "+TABLE_USER+"("+
                KEY_ID+" INTEGER PRIMARY KEY, "+
                KEY_UID+" TEXT, "+
                KEY_USERNAME+" TEXT, "+
                KEY_EMAIL+" TEXT, "+
                KEY_CONFIRMED_ACCOUNT+" INTEGER, "+
                KEY_LASTLOGGED+" INTEGER, "+
                KEY_PICTURE+" TEXT, "+
                KEY_SKILL_LEARNER+" INTEGER, "+
                KEY_SKILL_CHALLENGER+" INTEGER, "+
                KEY_SKILL_CODER+" INTEGER "+
                ")";

        sqLiteDatabase.execSQL(CREATE_ARTICLE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //enlever la table si existe
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
        //creation de la table
        onCreate(sqLiteDatabase);
    }

    //USER METHODS
    public void saveUser (User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_UID, user.getId());
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_CONFIRMED_ACCOUNT, user.isConfirmed());
        values.put(KEY_LASTLOGGED, user.getLast_loggued().getTimeInMillis());
        values.put(KEY_PICTURE, user.getPictureURL());
        values.put(KEY_SKILL_LEARNER, user.getSkill_learner());
        values.put(KEY_SKILL_CHALLENGER, user.getSkill_challenger());
        values.put(KEY_SKILL_CODER, user.getSkill_coder());

        //INSERT
        db.insert(TABLE_USER,null,values);
        //CLOSE CONNECTION
        db.close();
    }

    public void updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_UID, user.getId());
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_CONFIRMED_ACCOUNT, user.isConfirmed());
        values.put(KEY_LASTLOGGED, user.getLast_loggued().getTimeInMillis());
        values.put(KEY_PICTURE, user.getPictureURL());
        values.put(KEY_SKILL_LEARNER, user.getSkill_learner());
        values.put(KEY_SKILL_CHALLENGER, user.getSkill_challenger());
        values.put(KEY_SKILL_CODER, user.getSkill_coder());
        //UPDATE
        db.update(TABLE_USER,values,"id = ?", new String[] {String.valueOf(user.getId())});
        //CLOSE CONNECTION
        db.close();
    }

    public void deleteUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER,null,null);
        db.close();
    }

    public boolean userExists(){
        SQLiteDatabase db= this.getReadableDatabase();
        String selectQuery="SELECT * FROM "+TABLE_USER;
        Cursor cursor = db.rawQuery(selectQuery, null);
        int cnt =cursor.getCount();
        cursor.close();
        if (cnt>0) return true;
        return false;
    }

    public User getUser(){
        if(userExists()){
            SQLiteDatabase db= this.getReadableDatabase();
            String selectQuery="SELECT * FROM "+TABLE_USER;
            Cursor cursor = db.rawQuery(selectQuery, null);
            cursor.moveToFirst();
            Calendar created= Calendar.getInstance();
            created.setTimeInMillis(cursor.getLong(5));
            Calendar last_log= Calendar.getInstance();
            last_log.setTimeInMillis(cursor.getLong(6));

            User user=new User(cursor.getString(1),cursor.getString(2),cursor.getString(3),
                    cursor.getString(4), created , last_log
                    ,(cursor.getInt(7) != 0), cursor.getInt(8),cursor.getInt(9),
                    cursor.getInt(10));
            return user;
        }
        return null;
    }
}
