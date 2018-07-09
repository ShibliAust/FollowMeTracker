package sd_project_two2.followmetracker.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.location.Geocoder;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import android.location.Address;

import sd_project_two2.followmetracker.gps_API.gpsTracker;
import sd_project_two2.followmetracker.R;
import sd_project_two2.followmetracker.others.hospitalInfo;
import sd_project_two2.followmetracker.others.hospitalInfoAdd;

public class hospitalNameActivity extends AppCompatActivity {

    public Button start;
    gpsTracker gps;
    public Geocoder geocoder;


    public double getdistance(String mylat,String mylon, String hoslat, String hoslon)
    {

        Double lat1 = Double.parseDouble(mylat);
        Double lon1 = Double.parseDouble(mylon);
        Double lat2 = Double.parseDouble(hoslat);
        Double lon2 = Double.parseDouble(hoslon);

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }



    public void init()
    {
        start = ( Button ) findViewById(R.id.start_button );


        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Double latitude = 0.0 , longitude = 0.0;

                // create class object
                gps = new gpsTracker(hospitalNameActivity.this);
                boolean ok = true;

                // check if GPS enabled
                if(gps.canGetLocation()){

                    latitude = gps.getLatitude();
                    longitude = gps.getLongitude();

                    // \n is for new line
                    //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    //Toast.makeText(getApplicationContext(), "Location un available " , Toast.LENGTH_LONG).show();
                    ok = false;
                    gps.showSettingsAlert();
                }
                if( ok ) {

                    hospitalInfo infoo = new hospitalInfo();

                    final ListView listResult = (ListView) findViewById(R.id.hospitalName_listView);

                    List<hospitalInfoAdd> hospitals = infoo.add_hospital();

                    List<String> showresult = new ArrayList<String>();
                    ;

                    for (int i = 0; i < hospitals.size(); i++) {
                        hospitalInfoAdd id = hospitals.get(i);
                        String temp = "";

                        String lat = id.getLatitude();
                        String lon = id.getLongitude();
                        String name = id.getHname();

                        Double dis = getdistance(latitude.toString(), longitude.toString(), lat, lon);

                        //Toast.makeText(getApplicationContext(), "Distance : \n" + dis + "\n" , Toast.LENGTH_LONG).show();


                        if (dis <= 2.00) {
                            temp += (name + "\n" + dis + "Km from Your Location\n");

                            showresult.add(temp);
                        }


                        Double p1 = Double.parseDouble(lat);
                        Double p2 = Double.parseDouble(lon);

                    /*


                    List<Address> geoResult = findGeocoder(p1, p2);

                    if(geoResult != null){
                       // List<String> geoStringResult = new ArrayList<String>();
                        for(int ii=0; ii < geoResult.size(); ii++){
                            Address thisAddress = geoResult.get(i);
                            String stringThisAddress = "";
                            for(int a=0; a < thisAddress.getMaxAddressLineIndex(); a++) {
                                stringThisAddress += thisAddress.getAddressLine(a) + "\n";
                            }


                            showresult.add(stringThisAddress);
                        }


                    }
                    */


                    }

                    //Toast.makeText(getApplicationContext(), "Strings\n" + showresult + "\n" , Toast.LENGTH_LONG).show();


                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(hospitalNameActivity.this,
                            android.R.layout.simple_list_item_1, android.R.id.text1, showresult);

                    listResult.setAdapter(adapter);

                }



            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_name);
        init();
    }


    private List<Address> findGeocoder(Double lat, Double lon){
        final int maxResults = 2;
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
