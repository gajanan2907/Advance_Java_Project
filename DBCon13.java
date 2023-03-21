package Test;

import java.sql.*;

public class DBCon13 {
	public static void main(String[] args) {
		try {
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl1", "system", "tiger");
			DatabaseMetaData dmd = con.getMetaData();
			System.out.println("Version:" + dmd.getDatabaseProductVersion());
			PreparedStatement ps1 = con.prepareStatement("insert into Product50 values(?,?,?,?)");
			ParameterMetaData pmd = ps1.getParameterMetaData();
			System.out.println("count:" + pmd.getParameterCount());
			PreparedStatement ps2 = con.prepareStatement("select code,price from Product50");
			ResultSet rs = ps2.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				System.out.println(rs.getString(1) + "\t" + rs.getFloat(2));
			} // end of loop
			System.out.println("Col-Count:" + rsmd.getColumnCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
