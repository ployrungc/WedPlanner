package chairattanangkul.ployrung.wedplanner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pamie on 4/25/2016 AD.
 */
public class MyOpenHelper extends SQLiteOpenHelper{

    //Explicit
    public static final String database_name = "wedplanner.db";
    private static final int database_version = 1;

    private static final String create_user_table = "create table userTable (" +
            "_id integer primary key, " +
            "Email text, " +
            "Password text);";


    public MyOpenHelper(Context context) {
        super(context, database_name, null, database_version);
    }   //Constructor

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.execSQL(create_user_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}   //Main Class
