package sd_project_two2.followmetracker.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sd_project_two2.followmetracker.gps_API.gpsTracker;
import sd_project_two2.followmetracker.R;
import sd_project_two2.followmetracker.others.friendsInfo;
import sd_project_two2.followmetracker.others.friends_info_add;

public class findMyFriendActivity extends AppCompatActivity {

    Button search;
    gpsTracker gps;


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


    void init()
    {
        search = (Button)findViewById(R.id.search_button );



        search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0) {
                Double latitude = 0.0 , longitude = 0.0;

                gps = new gpsTracker(findMyFriendActivity.this);

                if(gps.canGetLocation()){

                    latitude = gps.getLatitude();
                    longitude = gps.getLongitude();

                    //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                }else{

                    Toast.makeText(getApplicationContext(), "Location un available " , Toast.LENGTH_LONG).show();
                    gps.showSettingsAlert();
                }

                friendsInfo infoo = new friendsInfo();

                final ListView listResult = (ListView)findViewById(R.id.frinds_listView );

                List<friends_info_add> friends = infoo.add_frinds();

                List <String> showresult = new ArrayList<String>();;

                for( int i = 0; i < friends.size(); i++ )
                {
                    friends_info_add id = friends.get(i);
                    String temp = "";

                    String lat = id.getFLatitude();
                    String lon = id.getFLongitude();
                    String name = id.getFname();
                    String address = id.getaddress();

                    Double dis = getdistance(latitude.toString(), longitude.toString(), lat, lon );

                    //Toast.makeText(getApplicationContext(), "Distance : \n" + dis + "\n" , Toast.LENGTH_LONG).show();

                    temp += ( name + "\n" + dis + "Km from Your Location\n Address at: " + address + "\n" );


                    showresult.add(temp);


                    Double p1 = Double.parseDouble(lat);
                    Double p2 = Double.parseDouble(lon);



                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(findMyFriendActivity.this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, showresult);

                listResult.setAdapter(adapter);




            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_my_friend);
        init();
    }
}
