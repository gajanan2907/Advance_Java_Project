package Test;

import java.util.*;
import java.sql.*;

public class DBCon10 {
	public static void main(String[] args) {
		try {
			Scanner s = new Scanner(System.in);
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl1", "system", "tiger");
			PreparedStatement ps = con.prepareStatement("insert into Product50 values(?,?,?,?)");

			System.out.println("Enter the number of Products:");
			int n = Integer.parseInt(s.nextLine());

			System.out.println("Enter " + n + " Product details");
			for (int i = 1; i <= n; i++) {
				System.out.println("****Product-" + i + "****");
				System.out.println("Enter the code:");
				ps.setString(1, s.nextLine());
				System.out.println("Enter the name:");
				ps.setString(2, s.nextLine());
				System.out.println("Enter the Price:");
				ps.setFloat(3, Float.parseFloat(s.nextLine()));
				System.out.println("Enter the qty:");
				ps.setInt(4, Integer.parseInt(s.nextLine()));

				ps.addBatch();
			} // end of loop
			int k[] = ps.executeBatch();
			for (int i = 0; i < k.length; i++) {
				System.out.println("Executed Successfully...");
			}
			ps.clearBatch();
			con.close();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
