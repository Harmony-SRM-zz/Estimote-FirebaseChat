package github.com.anurag145.srmhackathon;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }
    public  void onclick(View view)
    {
        switch(view.getId())
        {
            case R.id.button1 : startActivity(new Intent(Home.this,MainActivity.class));
                                  break;
            case R.id.button2 : startActivity(new Intent(Home.this,FirebaseActivity.class));
                break;


        }
    }
}

