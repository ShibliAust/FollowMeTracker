package sd_project_two2.followmetracker.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sd_project_two2.followmetracker.database.databaseHelper;
import sd_project_two2.followmetracker.others.userInfo;
import sd_project_two2.followmetracker.R;


public class registerActivity extends AppCompatActivity {

    databaseHelper help = new databaseHelper(this);

    public Button home;
    public EditText rname, uname, pass, cpass, mail;


    public boolean checking( String temp )
    {
        boolean okk = true;
        for( int i = 0; i < temp.length(); i++ )
        {
            if( ( temp.charAt(i) >= 'a' && temp.charAt(i) <= 'z' ) || ( temp.charAt(i) >= 'A' && temp.charAt(i) <= 'Z' ) || ( temp.charAt(i) >= '0' && temp.charAt(i) <= '9' ) )
                okk = true;
            else
            {
                okk = false;
                return false;
            }
        }
        return true;
    }



    public void init() {
        home = (Button) findViewById(R.id.signup_button);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rname = (EditText) findViewById(R.id.Name_editText );
                uname = (EditText) findViewById(R.id.UserName_editText ) ;
                pass = (EditText) findViewById(R.id.Pass_editText );
                cpass = (EditText) findViewById(R.id.confirmPass_editText );
                mail = (EditText) findViewById(R.id.Email_editText );

                String rnm = rname.getText().toString();
                String unm = uname.getText().toString();
                String ps = pass.getText().toString();
                String cps = cpass.getText().toString();
                String ml = mail.getText().toString();

                boolean pp = false;

                if( "".equals(rnm) || "".equals(unm) ||  "".equals(ps) || "".equals(ml)  )
                {
                    Toast.makeText(getApplicationContext(), "Please Fill up the Information!\n", Toast.LENGTH_LONG).show();
                    pp = false;


                }
                else pp = true;


                if( pp && ps.equals(cps ) )
                {
                    userInfo info = new userInfo();

                    info.setUsername( unm );
                    info.setName( rnm );
                    info.setEmail( ml );
                    info.setPassword( ps );

                    help.insertContact(info);

                    Toast.makeText(getApplicationContext(), "Successfully Registered!\n", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(registerActivity.this, logInActivity.class);
                    startActivity(i);


                }

                else
                {
                    Toast.makeText(getApplicationContext(), "Password Doesn't MATCHED\n", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }
}
