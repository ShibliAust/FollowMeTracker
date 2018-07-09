package sd_project_two2.followmetracker.activities;

import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.location.Address;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import sd_project_two2.followmetracker.gps_API.gpsTracker;
import sd_project_two2.followmetracker.R;

public class homePageActivity extends AppCompatActivity {
    public Button GetMyGps;
    gpsTracker gps;
    public Button getAddress, hospitalName, frndloc ,about;
    public Geocoder geocoder;


    public void init() {
        GetMyGps = (Button) findViewById(R.id.Location_Button);
        getAddress = (Button) findViewById(R.id.my_address_button);
        hospitalName = (Button) findViewById(R.id.Hospital_blood_button );
        frndloc = (Button) findViewById(R.id.friens_loc_button );
        about = (Button) findViewById(R.id.about_button );



        GetMyGps.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // create class object
                gps = new gpsTracker(homePageActivity.this);

                // check if GPS enabled
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    Toast.makeText(getApplicationContext(), "Location un available " , Toast.LENGTH_LONG).show();
                    gps.showSettingsAlert();
                }

            }
        });

        hospitalName.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(homePageActivity.this,hospitalNameActivity.class );
                startActivity(i);
            }
        });

        frndloc.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(homePageActivity.this,findMyFriendActivity.class );
                startActivity(i);
            }
        });



        about.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(homePageActivity.this,aboutusActivity.class );
                startActivity(i);
            }
        });




        getAddress.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Double lat = null;
                Double lon = null;
                // create class object
                gps = new gpsTracker(homePageActivity.this);
                boolean addOk = true;
                final ListView listResult = (ListView)findViewById(R.id.add_listResult);

                geocoder = new Geocoder(homePageActivity.this);

                // check if GPS enabled
                if(gps.canGetLocation()){

                    lat = gps.getLatitude();
                    lon = gps.getLongitude();
                    //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + lat + "\nLong: " + lon, Toast.LENGTH_LONG).show();




                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    addOk = false;
                    Toast.makeText(getApplicationContext(), "Location un_available " , Toast.LENGTH_LONG).show();
                    gps.showSettingsAlert();
                }

                if(addOk){
                    Toast.makeText(homePageActivity.this,
                            "find " + lat + " : " + lon,
                            Toast.LENGTH_LONG).show();

                    List<Address> geoResult = findGeocoder(lat, lon);
                    if(geoResult != null){
                        List<String> geoStringResult = new ArrayList<String>();
                        for(int i=0; i < geoResult.size(); i++){
                            Address thisAddress = geoResult.get(i);
                            String stringThisAddress = "";
                            for(int a=0; a < thisAddress.getMaxAddressLineIndex(); a++) {
                                stringThisAddress += thisAddress.getAddressLine(a) + "\n";
                            }

                            stringThisAddress +=
                                    "CountryName: " + thisAddress.getCountryName() + "\n"
                                            + "CountryCode: " + thisAddress.getCountryCode() + "\n"
                                            + "AdminArea: " + thisAddress.getAdminArea() + "\n"
                                            + "FeatureName: " + thisAddress.getFeatureName();
                            geoStringResult.add(stringThisAddress);
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(homePageActivity.this,
                                android.R.layout.simple_list_item_1, android.R.id.text1, geoStringResult);

                        listResult.setAdapter(adapter);
                    }

                }

            }
        });






    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        init();


    }


    private List<Address> findGeocoder(Double lat, Double lon){
        final int maxResults = 1;
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(lat, lon, maxResults);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return addresses;
    }
}
