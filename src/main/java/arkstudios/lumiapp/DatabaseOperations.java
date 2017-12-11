package arkstudios.lumiapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseOperations extends SQLiteOpenHelper {

    public static final int database_version = 1;

    public DatabaseOperations(Context context){
        super(context, TableData.TableInfo.DATABASE_NAME, null, database_version);
        Log.d("Database operations", "Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TableData.TableInfo.TABLE_NAME + " (SERIAL_NO STRING PRIMARY KEY, SERIAL_PASS, TEXT)");
        Log.d("Database operations", "Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TableData.TableInfo.TABLE_NAME);
        onCreate(db);
    }

    public void putInformation(DatabaseOperations dop, String number, String pass){

        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.SERIAL_NO, number);
        cv.put(TableData.TableInfo.SERIAL_PASS, pass);
        long k = SQ.insert(TableData.TableInfo.TABLE_NAME,null,cv);
        Log.d("Database operations", "One row inserted");

    }



}
