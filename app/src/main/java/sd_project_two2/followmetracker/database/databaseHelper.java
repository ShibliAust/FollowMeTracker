package sd_project_two2.followmetracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import sd_project_two2.followmetracker.others.userInfo;


public class databaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="log_in.db";
    private static final String TABLE_NAME="log_in";

    private static final String COL_ID="id";
    private static final String COL_USERNAME="username";
    private static final String COL_PASSWORD="password";
    private static final String COL_NAME="name";
    private static final String COL_EMAIL="email";



    SQLiteDatabase db;
    private static final String LOGIN_TABLE_CREATE="create table log_in(id integer primary key not null ,"+
            "username text not null,password text not null,name text not null,email text not null);";


    public databaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LOGIN_TABLE_CREATE);
        this.db=db;
    }


    public void insertContact(userInfo info){

        db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query="select * from log_in";
        Cursor cursor = db.rawQuery(query,null);
        int count=cursor.getCount();


        values.put(COL_ID,count);
        values.put(COL_USERNAME,info.getUsername() );
        values.put(COL_PASSWORD,info.getPassword() );
        values.put(COL_NAME,info.getName() );
        values.put(COL_EMAIL,info.getEmail() );



        db.insert(TABLE_NAME,null,values);
        db.close();

    }


    public String searchPass(String uname){
        db = this.getWritableDatabase();
        String query = "select username,password from log_in";
        Cursor cursor = db.rawQuery(query,null);
        String user,pass;
        pass ="not found";
        if(cursor.moveToFirst()){
            do{
                user = cursor.getString(0);

                if(user.equals(uname)){
                    pass = cursor.getString(1);
                    break;
                }
            }while (cursor.moveToNext());
        }
        return pass;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query="DROP TABLE IS EXISTS"+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}