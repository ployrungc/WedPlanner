package chairattanangkul.ployrung.wedplanner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by pamie on 4/25/2016 AD.
 */
public class MyManage {

    //Explicit
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public MyManage(Context context) {

        //Create SQLite
        myOpenHelper = new MyOpenHelper(context);
        sqLiteDatabase = myOpenHelper.getWritableDatabase();

    }   //Constructor
}   //Main Class
