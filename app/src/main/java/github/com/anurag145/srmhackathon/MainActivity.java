package github.com.anurag145.srmhackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.estimote.sdk.SystemRequirementsChecker;
import java.util.Arrays;
import github.com.anurag145.srmhackathon.estimote.BeaconID;
import github.com.anurag145.srmhackathon.estimote.EstimoteCloudBeaconDetails;
import github.com.anurag145.srmhackathon.estimote.EstimoteCloudBeaconDetailsFactory;
import github.com.anurag145.srmhackathon.estimote.ProximityContentManager;

public class MainActivity extends AppCompatActivity {
    private ProximityContentManager proximityContentManager;

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView =(TextView)findViewById(R.id.textView);

        proximityContentManager = new ProximityContentManager(this,
                Arrays.asList(

                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 44248, 25292),
                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 25373, 24529),
                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 3, 3)),
                new EstimoteCloudBeaconDetailsFactory());
        proximityContentManager.setListener(new ProximityContentManager.Listener() {
            @Override
            public void onContentChanged(Object content) {


                if (content != null) {
                    EstimoteCloudBeaconDetails beaconDetails = (EstimoteCloudBeaconDetails) content;

                          textView.setText("FOUND 1 "+beaconDetails.getBeaconName());

                } else {
                    textView.setText("Kya dekh raha hai bhai niche scroll kar");

                }

            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        if (!SystemRequirementsChecker.checkWithDefaultDialogs(this)) {
            //wont be an issue (Say Amen)!
        } else {

            proximityContentManager.startContentUpdates();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        proximityContentManager.stopContentUpdates();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        proximityContentManager.destroy();
    }
    }

