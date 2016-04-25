package chairattanangkul.ployrung.wedplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
        myManage.addEmail("testEmail", "testPass");

    }   // Main Method

    public void clickSignUpMain(View view) {
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));

    }   // clickSignUp ตั้ง ชื่อเอง

    public void clickSignInMain(View view) {

    }   // clickSignIn

}   // Main Class
