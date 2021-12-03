package p2ch05;

import java.sql.*;

public class DbDemo {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql:dev";
        Connection db = DriverManager.getConnection(url, "dev", "dev");

        DatabaseMetaData meta = db.getMetaData();
        ResultSet mrs = meta.getTables(null, "corejava", null, new String[] { "TABLE"});
        while (mrs.next()) {
            System.out.printf("catalog: %s, schema: %s, table name: %s%n",
                    mrs.getString("TABLE_CAT"),
                    mrs.getString("TABLE_SCHEM"),
                    mrs.getString("TABLE_NAME"));
        }

        Statement stat = db.createStatement();
        String query = "select\n" +
                "   p.pet_name as pet,\n" +
                "   f.food_name as food\n" +
                "from \n" +
                "   corejava.foods f ,\n" +
                "   corejava.eats e ,\n" +
                "   corejava.pets p \n" +
                "where \n" +
                "   p.id = e.pet and \n" +
                "   f.id = e.eats\n" +
                "order by pet";

        ResultSet rs = stat.executeQuery(query);
        while (rs.next()) {
            System.out.printf("%s szereti a %s-t%n", rs.getString("pet"), rs.getString("food"));
        }
        
        Statement ownerStat = db.createStatement();
        String ownerQuery = "select * from corejava.owners";
        ResultSet ownerResult = ownerStat.executeQuery(ownerQuery);
        while (ownerResult.next()) {
            System.out.printf("Owner: %s, Phone number: %s%n", ownerResult.getString("name"), ownerResult.getString("phone"));
        }



        db.close();
    }
}
