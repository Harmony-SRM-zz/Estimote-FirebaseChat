package github.com.anurag145.srmhackathon;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class FirebaseActivity extends AppCompatActivity {
   int flag=0;
  EditText editText;
    FirebaseDatabase database;
    DatabaseReference myRef;
    StringTokenizer stringTokenizer;
    private static final String SHAREDPREFFILE = "temp";
    ArrayList<String> ob = new ArrayList<>();
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);
         database = FirebaseDatabase.getInstance();
        SharedPreferences prefs = getSharedPreferences(SHAREDPREFFILE, Context.MODE_PRIVATE);
        myRef = database.getReference("message");
        if((prefs.getString("city",null)==null))
        {
            myRef.child("Users").child("user"+linker.getEmail());
        }


        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                   if(flag==0) {
                       stringTokenizer = new StringTokenizer((snapshot.child("Users").child("user" + linker.getEmail()).getValue()).toString(), "-");


                       Log.e("jbssfks", (stringTokenizer.nextToken()));
                       try {
                           linker.bio = stringTokenizer.nextToken();

                           temp(stringTokenizer.nextToken());

                       } catch (Exception e) {
                           Log.e("heloo", e.toString());
                       }
                   }else
                   {
                       //do nothing
                   }


            }
            public void temp(String s)
            {
                     flag=1;
                   editText.setVisibility(View.GONE);
                (findViewById(R.id.button)).setVisibility(View.GONE);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        editText=(EditText)findViewById(R.id.editText1);

    }
    public void onClick(View view)
    {
        if(!editText.getText().toString().equalsIgnoreCase(""))
        {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("message");
            SharedPreferences prefs = getSharedPreferences(SHAREDPREFFILE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            editor.putString("city",editText.getText().toString());
            editor.commit();


            myRef.child("Users").child("user"+linker.getEmail()).setValue(linker.email+"-"+linker.bio+"-"+editText.getText().toString());

        }
    }
}
