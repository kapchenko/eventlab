package classes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.*;

public class Events {
    private int id;
    private String name;
    private Date date;
    private int org_id;
    private static List<Events> eventList = new ArrayList<Events>();

    public Events (int id, String name,Date date, int org_id){
        this.id = id;
        this.name =name;
        this.date =date;
        this.org_id= org_id;
    }
    public Events (){
        id = -1;
        name = "xxxxxx";
        date =null;
        org_id =-1;
    }
    private static void eventAdd(Events ev){
        eventList.add(ev);
    }

    private static void getEventsFromDB(){
        Connection con = Connector.connect();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM events ");
            while (rs.next()) {
                Events nEvents = new Events();
                nEvents.id = rs.getInt(1);
                nEvents.name=rs.getString(2);
                nEvents.date=rs.getDate(3);
                nEvents.org_id=rs.getInt(4);
                if(!eventList.contains(nEvents)) {
                    eventAdd(nEvents);
                }
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Problem with getting events");
            e.printStackTrace();
        } finally {
            Connector.closeConnection(con);
        }
    }
    public static Events getEvents(int ID) {
        getEventsFromDB();
        for (Events ev : eventList) {
            if (ev.id == ID) {
                return ev;
            }

        }
        return null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Events event = (Events) o;

        if (id != event.id ||name != event.name || date!= event.date || org_id != event.org_id )
            return false;

        return name != null ? name.equals(event.name) : event.name == null;
    }

    @Override
    public String toString() {
        return String.format(id + name + date + org_id);
    }



}
