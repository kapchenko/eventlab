package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.SQLException;

public class Price {
    private int ev_id;
    private int ticketprice;

    private static List<Price> priceList = new ArrayList<>();

    public Price() {
        ev_id =-1;
        ticketprice =-1;
    }

    public Price(int ev_id,int ticketprice) {
        this.ev_id = ev_id;
        this.ticketprice = ticketprice;
    }
    private static void priceAdd(Price p){
        priceList.add(p);
    }
    private static void getPriceFromDB() {
        Connection con = Connector.connect();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM price");
            while(rs.next()) {
                Price nPrice = new Price();
                nPrice.ev_id  = rs.getInt(1);
                nPrice.ticketprice= rs.getInt(2);

                if(!priceList.contains(nPrice)) {
                    priceAdd(nPrice);
                }
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Problem with getting price");
        } finally {
            Connector.closeConnection(con);
        }

    }
    public static Price getPrice(int ID) {
        getPriceFromDB();
        for (Price p : priceList) {
            if (p.ticketprice == ID) {
                return p;
            }

        }
        return null;
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return  true;
        if (o ==null || getClass() != o.getClass()) return false;

        Price price = (Price) o;

        if (ev_id !=price.ev_id)return false;
        if(ticketprice != price.ticketprice) return false;
        return ev_id == price.ticketprice ;

    }
}
