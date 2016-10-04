package github.com.anurag145.srmhackathon;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class weby extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weby);
        Uri uri = Uri.parse("https://web.telegram.org/#/im?p=@tiknaktiknak_bot");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
        finish();
    }
}
