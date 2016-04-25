package chairattanangkul.ployrung.wedplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by pamie on 4/25/2016 AD.
 */
public class MyManage {

    //Explicit
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public static final String user_table = "userTable";
    public static final String column_id = "_id";
    public static final String column_email = "Email";
    public static final String column_password = "Password";

    public MyManage(Context context) {

        //Create SQLite
        myOpenHelper = new MyOpenHelper(context);
        sqLiteDatabase = myOpenHelper.getWritableDatabase();

    }   //Constructor

    public long addEmail(String strEmail,
                         String strPassword) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(column_email, strEmail);
        contentValues.put(column_password, strPassword);


        return sqLiteDatabase.insert(user_table, null, contentValues);
    }

}   //Main Class
