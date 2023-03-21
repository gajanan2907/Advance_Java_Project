package Test;

import java.sql.*;
import java.util.*;

public class DBCon6 {
	public static void main(String[] args) {
		try {
			Scanner s = new Scanner(System.in);
			System.out.println("Enter the accNo:");
			long accNo = s.nextLong();
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl1", "system", "tiger");
			CallableStatement cs = con.prepareCall("{call ?:=RetrieveBal50(?)}");
			cs.registerOutParameter(1, Types.FLOAT);
			cs.setLong(2, accNo);
			cs.execute();
			System.out.println("AccNo:" + accNo);
			System.out.println("Balance:" + cs.getFloat(1));

			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
