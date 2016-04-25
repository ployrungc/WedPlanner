package chairattanangkul.ployrung.wedplanner;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private MyManage myManage;

    private EditText userEditText, passwordEditText;
    private String userString, passwordString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        userEditText = (EditText) findViewById(R.id.editText3);
        passwordEditText = (EditText) findViewById(R.id.editText4);

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

        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        if (userString.equals("")|| passwordString.equals("")) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "Have Space", "Please Fill All Every Blank");
        } else {
            checkUser();
        }

    }   // clickSignIn

    private void checkUser() {
        try {
            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name, MODE_PRIVATE, null);

            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABLE WHERE Email = " + "'" + userString + "'", null);
            cursor.moveToFirst();
            String[] resultStrings = new String[cursor.getColumnCount()];
            for (int i=0;i<cursor.getColumnCount();i++) {
                resultStrings[i] = cursor.getString(i);

            }   //for
            cursor.close();

            // Check Password
            if (passwordString.equals(resultStrings[2])) {
                //Password True
                Toast.makeText(this, "Welcome to my Service", Toast.LENGTH_SHORT).show();

            } else {
                //Password False
                MyAlert myAlert = new MyAlert();
                myAlert.myDialog(this, "Password False",
                        "Please Try Again Password False");
            }



        } catch (Exception e) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "No This User",
                    "No " + userString + "in my Database");
        }


    }   // checkUser

}   // Main Class
