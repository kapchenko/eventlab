package classes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Event_Participant {
    private int particip_id;
    private int org_id;
    private static List<Event_Participant> event_participantsList = new ArrayList<Event_Participant>();

    public Event_Participant (int particip_id, int org_id){
        this.particip_id = particip_id;
        this.org_id= org_id;
    }
    public Event_Participant (){
        particip_id = -1;
        org_id =-1;
    }
    private static void eventAdd(Event_Participant ev_par){
        event_participantsList.add(ev_par);
    }

    private static void getEvent_ParticipantFromDB(){
        Connection con = Connector.connect();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM event_participant ");
            while (rs.next()) {
                Event_Participant nEvent_participant = new Event_Participant();
                nEvent_participant.particip_id = rs.getInt(1);
                nEvent_participant.org_id=rs.getInt(2);
                if(!event_participantsList.contains(nEvent_participant)) {
                    eventAdd(nEvent_participant);
                }
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Problem with getting event_partcipant");
            e.printStackTrace();
        } finally {
            Connector.closeConnection(con);
        }
    }
    public static Event_Participant getEvent_Participant(int ID) {
        getEvent_ParticipantFromDB();
        for(Event_Participant o : event_participantsList) {
            if(o.particip_id == ID) {
                return o;
            }
        }
        return null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event_Participant ev_particip = (Event_Participant) o;

        if (particip_id != ev_particip.particip_id) return false;
        if (org_id != ev_particip.org_id) return false;
        return org_id == ev_particip.org_id;
    }


}
