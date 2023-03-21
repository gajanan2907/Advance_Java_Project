
package Test;
import java.sql.*;
import java.util.*;

public class DBCon7 {
	public static void main(String[] args) {
		try {
			Scanner s = new Scanner(System.in);
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl1", "system", "tiger");
			PreparedStatement ps1 = con.prepareStatement("select * from BankCustomer50 where accno=?");
//Compilation
			PreparedStatement ps2 = con.prepareStatement("update BankCustomer50 set balance=balance+? where accno=?");
//Compilation
			System.out.println("Commit Status : " + con.getAutoCommit());
			con.setAutoCommit(false);
			System.out.println("Commit Status : " + con.getAutoCommit());
			Savepoint sp = con.setSavepoint();
			System.out.println("Enter home accNo:");
			long hAccNo = s.nextLong();
			ps1.setLong(1, hAccNo);
			ResultSet rs1 = ps1.executeQuery();// Execution
			if (rs1.next()) {
				float bal = rs1.getFloat(4);
				System.out.println("Enter benefieciery AccNo:");
				long bAccNo = s.nextLong();
				ps1.setLong(1, bAccNo);
				ResultSet rs2 = ps1.executeQuery();// Execution
				if (rs2.next()) {
					System.out.println("Enter the amt to be transferred:");
					float amt = s.nextFloat();
					if (amt <= bal) {
//statement-1
						ps2.setFloat(1, -amt);
						ps2.setLong(2, hAccNo);
						int p = ps2.executeUpdate();// Buffer Updated

//Statement-2
						ps2.setFloat(1, amt);
						ps2.setLong(2, bAccNo);
						int q = ps2.executeUpdate();// Buffer Updated

						if (p == 1 && q == 1) {
							con.commit();// Update Database
							System.out.println("Transaction Successfull..");
						} else {
							System.out.println("Transaction failed...");
							con.rollback(sp);
						}
					} else {
						System.out.println("Insufficient fund...");
					}
				} else {
					System.out.println("Invalid bAccNo...");
				}
			} else {
				System.out.println("Invalid hAccNo...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
