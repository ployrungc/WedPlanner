package chairattanangkul.ployrung.wedplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

        } else {
            //No Space
        }


    }   //clickSignUp

}   //Main Class
