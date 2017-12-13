package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.*;


public class Organisators {
    private int id;
    private String name;
    private String phone;
    private static List<Organisators> organisatorsList = new ArrayList<>();

    public Organisators() {
        id =-1;
        name= "xxxx";
        phone ="xxxx";
    }

    public Organisators(int id, String name , String phone) {
       this.id = id;
       this.name = name;
       this.phone = phone;
    }
    private static void organisatorsAdd(Organisators org){
        organisatorsList.add(org);
    }
    private static void getOrganisatorsFromDB() {
        Connection con = Connector.connect();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM organisators");
            while(rs.next()) {
                Organisators nOrganisators = new Organisators();
                nOrganisators.id  = rs.getInt(1);
                nOrganisators.name= rs.getString(2);
                nOrganisators.phone = rs.getString(3);
                if(!organisatorsList.contains(nOrganisators)) {
                    organisatorsAdd(nOrganisators);
                }
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Problem with getting organisators");
        } finally {
            Connector.closeConnection(con);
        }

    }
    public static Organisators getOrganisators(int ID) {
        getOrganisatorsFromDB();
        for(Organisators ec : organisatorsList) {
            if(ec.id == ID) {
                return ec;
            }
        }
        return null;
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return  true;
        if (o ==null || getClass() != o.getClass()) return false;

        Organisators organisators = (Organisators) o;

        if (id !=organisators.id)return false;

        return name !=null ? name.equals(organisators.name) : organisators.name == null;

    }
}
