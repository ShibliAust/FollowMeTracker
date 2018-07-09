package sd_project_two2.followmetracker.others;

import java.util.ArrayList;
import java.util.List;

public class  friendsInfo {


    public List <friends_info_add>  add_frinds()
    {
        List <friends_info_add> frinds = new ArrayList<friends_info_add>();;

        friends_info_add info1 = new friends_info_add() ;
        friends_info_add info2 = new friends_info_add() ;
        friends_info_add info3 = new friends_info_add() ;
        friends_info_add info4 = new friends_info_add() ;
        friends_info_add info5 = new friends_info_add() ;
        friends_info_add info6 = new friends_info_add() ;
        friends_info_add info7 = new friends_info_add() ;
        friends_info_add info8 = new friends_info_add() ;
        friends_info_add info9 = new friends_info_add() ;
        friends_info_add info10 = new friends_info_add() ;



        info1.setFLatitude("23.724192");
        info1.setFLongitude("90.397448");
        info1.setFname("Hafizul Islam Himel");
        info1.setaddress("Bonani");
        frinds.add( info1 );




        info2.setFLatitude("23.553079");
        info2.setFLongitude("90.351416");
        info2.setFname("Nafis Islam");
        info2.setaddress("Dhanmondi");
        frinds.add( info2 );



        info3.setFLatitude("23.763785");
        info3.setFLongitude("90.415467");
        info3.setFname("Swapnil Biswas");
        info3.setaddress("Kajipara,Farmgate");
        frinds.add( info3 );


        info4.setFLatitude("23.762971");
        info4.setFLongitude("90.473097");
        info4.setFname("Shahariar Shibli");
        info4.setaddress("Khilgaon");
        frinds.add( info4 );


        info5.setFLatitude("23.173075");
        info5.setFLongitude("90.168758");
        info5.setFname("Isthiaq Asif");
        info5.setaddress("Hatirjheel");
        frinds.add( info5 );


        info6.setFLatitude("23.773875");
        info6.setFLongitude("90.368858");
        info6.setFname("Saiful Islam Saif");
        info6.setaddress("Adomjee Cantonment");
        frinds.add( info6 );


        info7.setFLatitude("23.778075");
        info7.setFLongitude("90.688758");
        info7.setFname("HR Habib");
        info7.setaddress("Mouchak");
        frinds.add( info7 );

        info8.setFLatitude("23.373075");
        info8.setFLongitude("90.338758");
        info8.setFname("Habibunnabe A Rahman");
        info8.setaddress("Uttora");
        frinds.add( info8 );

        info9.setFLatitude("23.773075");
        info9.setFLongitude("90.368758");
        info9.setFname("Inzamamul Alam");
        info9.setaddress("Hatirjheel");
        frinds.add( info9 );

        info10.setFLatitude("23.273075");
        info10.setFLongitude("90.362758");
        info10.setFname("Rashid Abrar Ravee");
        info10.setaddress("Mohammadpur");
        frinds.add( info10 );


        return frinds;


    }
}
