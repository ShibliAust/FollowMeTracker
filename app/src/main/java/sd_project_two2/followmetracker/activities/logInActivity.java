package sd_project_two2.followmetracker.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sd_project_two2.followmetracker.database.databaseHelper;
import sd_project_two2.followmetracker.others.hospitalInfo;
import sd_project_two2.followmetracker.R;


public class logInActivity extends AppCompatActivity {

    databaseHelper help = new databaseHelper(this);
    hospitalInfo addhospital = new hospitalInfo();

    public Button Register, login;

    public boolean checkNamePass( String name, String pass )
    {
        if( "".equals(name)  || "".equals(pass) ) return false;
        return true;
    }

    public void init()
    {
        Register = (Button)findViewById(R.id.Register_button);
        login = (Button)findViewById(R.id.LogIn_Button);
        login.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v) {

                EditText et1 = ( EditText ) findViewById(R.id.UserName_editText );
                EditText et2 = ( EditText ) findViewById(R.id.Password_editText );

                String name = et1.getText().toString();
                String pass = et2.getText().toString();
                boolean pp = true;
                pp = checkNamePass(name, pass );

                String password = null;
                if( pp )
                {
                    password = help.searchPass( name );
                }

                //Toast.makeText(getApplicationContext(), "ja paisi  : " + password , Toast.LENGTH_LONG).show();


                if( pp &&  pass.equals( password ) )
                {

                    Intent i = new Intent(logInActivity.this, homePageActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Incorrect Username or Password\n", Toast.LENGTH_LONG).show();

                }
            }
        });
        Register.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(logInActivity.this,registerActivity.class );
                startActivity(i);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        //addhospital.add_hospital();
        init();
    }





}
