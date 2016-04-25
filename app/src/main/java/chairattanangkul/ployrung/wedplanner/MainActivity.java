package chairattanangkul.ployrung.wedplanner;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private MyManage myManage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Request SQLite
        myManage = new MyManage(this);

        //Test Add Value
        //myManage.addEmail("testEmail", "testPass");

        //Delete All SQLite
        deleteAllSQLite();

        //Synchronize JSON to SQLite
        synJSON();

    }   // Main Method

    //Create Inner Class
    public class MyConnectedUser extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {

            try {

                OkHttpClient okHttpClient = new OkHttpClient();

                Request.Builder builder = new Request.Builder();
                Request request = builder.url("http://swiftcodingthai.com/ploy/php_get_email.php").build();
                Response response = okHttpClient.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                Log.d("25April", "errorJSON ==> " + e.toString());
                return null;
            }

        }   // DoInBack

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("25April", "JSON ==> " + s);

            try {

                JSONArray jsonArray = new JSONArray(s);
                for (int i=0;i<jsonArray.length();i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String strEmail = jsonObject.getString(MyManage.column_email);
                    String strPassword = jsonObject.getString(MyManage.column_password);
                    myManage.addEmail(strEmail, strPassword);


                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }   //onPost

    }   //MyConnected Class



    private void synJSON() {

        MyConnectedUser myConnectedUser = new MyConnectedUser();
        myConnectedUser.execute();


    }   // synJSON

    private void deleteAllSQLite() {


        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        sqLiteDatabase.delete(MyManage.user_table, null, null);

    }   //deleteAll

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));

    }   // clickSignUp ตั้ง ชื่อเอง

    public void clickSignInMain(View view) {

    }   // clickSignIn

}   // Main Class
