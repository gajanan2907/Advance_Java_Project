package Test;

import java.util.*;
import java.sql.*;
import javax.sql.rowset.*;

public class DBCon12 {
	public static void main(String[] args) {
		try {
			Scanner s = new Scanner(System.in);
			RowSetFactory rsf = RowSetProvider.newFactory();
			System.out.println("====Choice====");
			System.out.println("\t1.JdbcRowSet" + "\n\t2.CachedRowSet");
			System.out.println("Enter the Choice:");
			switch (s.nextInt()) {
			case 1:
				JdbcRowSet jrs = rsf.createJdbcRowSet();
				jrs.setUrl("jdbc:oracle:thin:@localhost:1521:orcl1");
				jrs.setUsername("system");
				jrs.setPassword("tiger");
				jrs.setCommand("select * from Product50");
				jrs.execute();
				while (jrs.next()) {
					System.out.println(
							jrs.getString(1) + "\t" + jrs.getString(2) + "\t" + jrs.getFloat(3) + "\t" + jrs.getInt(4));
				} // end of loop
				jrs.close();
				break;
			case 2:
				CachedRowSet crs = rsf.createCachedRowSet();
				crs.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
				crs.setUsername("system");
				crs.setPassword("manager");
				crs.setCommand("select * from Product50");
				crs.execute();
				while (crs.next()) {
					System.out.println(
							crs.getString(1) + "\t" + crs.getString(2) + "\t" + crs.getFloat(3) + "\t" + crs.getInt(4));
				} // end of loop
				crs.close();

				break;
			default:
				System.out.println("Invalid Choice...");
			}// end of switch
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
