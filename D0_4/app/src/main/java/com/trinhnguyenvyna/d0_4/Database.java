package com.trinhnguyenvyna.d0_4;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public static final String DB_NAME = "book.sqlite";
    public static final int DB_VERSION = 1;
    public static final String TBL_NAME = "Book";
    public static final String COL_CODE = "BookCode";
    public static final String COL_NAME = "BookName";
    public static final String COL_PRICE = "BookPrice";
    private Context context;


    public Database(@Nullable Context context) {

        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME + " (" + COL_CODE+" VARCHAR(50) PRIMARY KEY, " +COL_NAME+" VARCHAR(50), "+ COL_PRICE+" REAL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBL_NAME);
        onCreate(db);
    }
    //SELECT
    public Cursor queryData(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql,null);
    }
    //INSERT, UPDATE, DELETE
    public void execSql(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }
    public int getNumberOfRows(){
        Cursor cursor = queryData("SELECT * FROM "+ TBL_NAME);
        int numberOfRows = cursor.getCount();
        cursor.close();
        return numberOfRows;
    }
    public void createSampleData(){
        if(getNumberOfRows()==0){
            try {
                execSql("INSERT INTO "+ TBL_NAME+" VALUES('M1111','Tam Thể', 20000)");
                execSql("INSERT INTO "+ TBL_NAME+" VALUES('M1112','Phía xa kia nơi loài tôm hát ', 19000)");
                execSql("INSERT INTO "+ TBL_NAME+" VALUES('M1113', 'Khi hơi thở hóa thinh không',18000)");
                execSql("INSERT INTO "+ TBL_NAME+" VALUES('M1114', 'Không gia đình', 22000)");
                execSql("INSERT INTO "+ TBL_NAME+" VALUES('M1115', 'Zoo', 24000)");

            }catch (Exception e){
                Log.e("Error: ",e.getMessage().toString());
            }
        }
    }
}
