package chairattanangkul.ployrung.wedplanner;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {

    //Explicit ประกาดตัวแปร
    private EditText emailEditText, passwordEditText;
    private String emailString, passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bind Widget ผูกตัวแปรที่ประกาศกับactivity
        emailEditText = (EditText) findViewById(R.id.editText);
        passwordEditText = (EditText) findViewById(R.id.editText2);


    }   // Main Method

    public void clickSignUpSign(View view) {

        emailString = emailEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //Check Space ไม่มีช่องว่าง
        if (emailString.equals("") || passwordString.equals("")) {
            // Have Space

            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "มีช่องว่าง", "กรุณากรอกทุกช่อง");

        } else if (checkEmail()) {

            //True email ไม่ซ้ำ
            upLoadValueToServer();
        } else {
            // false Email duplicate
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "Email ซ้ำ", emailString + "ซ้ำ ให้เปลี่ยนใหม่");

        }


    }   //clickSignUp

    private boolean checkEmail() {

        boolean bolsult;

        try {

            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                    MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABLE WHERE Email = " + "'" +emailString + "'", null);
            cursor.moveToFirst();
            Log.d("25April", "Email ==> " + cursor.getString(2));
            bolsult = false;

            bolsult = false;

        } catch (Exception e) {
            bolsult = true;
            return true;

        } // try
        return bolsult;

    }

    private void upLoadValueToServer() {

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("isAdd", "true")
                .add("Email", emailString)
                .add("Password", passwordString)
                .build();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url("http://swiftcodingthai.com/ploy/php_add_email.php").post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

                try {
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }   //upload to server

}   //Main Class
