package github.com.anurag145.srmhackathon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class FirebaseActivity extends AppCompatActivity {
   int flag=0;
  EditText editText;
    FirebaseDatabase database;
    DatabaseReference myRef;
    TextView textView;
    EditText editText2;
    Button button;
    static String s1;
    static String s2;
    Button button1;
      View view1;
    static DataSnapshot snppy;
    ScrollView scrollView;
    StringTokenizer stringTokenizer;
    private static final String SHAREDPREFFILE = "temp";
    ArrayList<String> ob = new ArrayList<>();
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);
        editText2=(EditText)findViewById(R.id.editText2);
        button=(Button)findViewById(R.id.button);
        button1=(Button)findViewById(R.id.button1);
        textView=(TextView)findViewById(R.id.textView);
        scrollView=(ScrollView)findViewById(R.id.scrollView);
        view1=(View)findViewById(R.id.lays);
         database = FirebaseDatabase.getInstance();
        SharedPreferences prefs = getSharedPreferences(SHAREDPREFFILE, Context.MODE_PRIVATE);
        myRef = database.getReference("message");
        if((prefs.getString("city",null)==null))
        {
            myRef.child("Users").child("user"+linker.bio);
        }


        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                   if(flag==0) {
                       stringTokenizer = new StringTokenizer((snapshot.child("Users").child("user" + linker.bio).getValue()).toString(), "-");


                       Log.e("jbssfks", (stringTokenizer.nextToken()));
                       try {
                           linker.bio = stringTokenizer.nextToken();

                           snppy=snapshot;
                           temp(stringTokenizer.nextToken());


                       } catch (Exception e) {
                           Log.e("heloo", e.toString());
                       }
                   }else
                   {
                       try {

                           snppy=snapshot;
                           textView.setText((snapshot.child(s1).getValue()).toString());
                           scrollView.fullScroll(View.FOCUS_DOWN);

                       }catch (Exception e)
                       {
                       }
                       //do nothing
                   }            }


            public void temp(String s)
            {  s1=s;
                     flag=1;
                   editText.setVisibility(View.GONE);
                (findViewById(R.id.button)).setVisibility(View.GONE);
                view1.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.VISIBLE);
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       s2=linker.email+" : "+editText2.getText().toString();
                        Log.e("hello",s1);
                        if(!(snppy.child(s1).getValue()==null))
                        myRef.child(s1).setValue(snppy.child(s1).getValue()+"\n"+s2);
                        else
                            myRef.child(s1).setValue(s2);

                        editText2.setText("");

                    }
                });


            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        editText=(EditText)findViewById(R.id.editText1);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hero, menu);
        return true;
    }
    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id==R.id.action_settings)
        {  flag=0;
          myRef.child("Users").child("user"+linker.bio).setValue(linker.email+"-"+linker.bio);
            editText.setVisibility(View.VISIBLE);
            (findViewById(R.id.button)).setVisibility(View.VISIBLE);
            view1.setVisibility(View.GONE);
            textView.setText("");
            scrollView.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(),"You left the group",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
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


            myRef.child("Users").child("user"+linker.bio).setValue(linker.email+"-"+linker.bio+"-"+editText.getText().toString());

        }
    }
}
