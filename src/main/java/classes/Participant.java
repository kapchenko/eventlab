package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.SQLException;


public class Participant {
    private int id;
    private String name;

    private static List<Participant> participantList = new ArrayList<>();

    public Participant() {
        id =-1;
        name= "xxxx";

    }

    public Participant(int id, String name ) {
        this.id = id;
        this.name = name;

    }
    private static void participantsAdd(Participant pa){
        participantList.add(pa);
    }
    private static void getParticipantFromDB() {
        Connection con = Connector.connect();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM participant");
            while(rs.next()) {
                Participant nParticipant = new Participant();
                nParticipant.id  = rs.getInt(1);
                nParticipant.name= rs.getString(2);

                if(!participantList.contains(nParticipant)) {
                    participantsAdd(nParticipant);
                }
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Problem with getting participant");
        } finally {
            Connector.closeConnection(con);
        }

    }
    public static Participant getParticipant(int ID) {
        getParticipantFromDB();
        for(Participant ec : participantList) {
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

        Participant participants = (Participant) o;

        if (id !=participants.id)return false;

        return name !=null ? name.equals(participants.name) : participants.name == null;

    }
}
