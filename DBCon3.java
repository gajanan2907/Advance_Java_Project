package Test;
import java.util.*;
import java.sql.*;
public class DBCon3 
{
public static void main(String[]args)
{
	try {
			Scanner s = new Scanner(System.in);
			Connection con = DriverManager.getConnection
			("jdbc:oracle:thin:@localhost:1521:orcl1","system","tiger");
			PreparedStatement ps1 = con.prepareStatement
			("insert into BookDetails50 values(?,?,?,?,?)");
			//Compiled
			PreparedStatement ps2 = con.prepareStatement
			("select * from BookDetails50");
			PreparedStatement ps3 = con.prepareStatement
			("select * from BookDetails50 where code=?");
			PreparedStatement ps4 = con.prepareStatement
			("update BookDetails50 set price=?,qty=qty+? where code=?");
			PreparedStatement ps5 = con.prepareStatement
			("delete from BookDetails50 where code=?");
			while(true) {
			System.out.println("*****Choice******");
			System.out.println("\t1.AddBookDetails"
			+ "\n\t2.ViewAllBookDetails"
			+ "\n\t3.ViewBookByCode"
			+ "\n\t4.UpdateBookDetails(price/qty)"
			+ "\n\t5.DeleteBookByCode"
			+ "\n\t6.exit");
			System.out.println("Enter the Choice:");
			int choice = Integer.parseInt(s.nextLine());
			switch(choice) {
			case 1:
			System.out.println("Enter the BookCode:");
			String code = s.nextLine();
			System.out.println("Enter the BookName:");
			String name = s.nextLine();
			System.out.println("Enter the BookAuthor:");
			String author = s.nextLine();
			System.out.println("Enter the BookPrice:");
			float price = Float.parseFloat(s.nextLine());
			System.out.println("Enter the BookQty:");
			int qty = Integer.parseInt(s.nextLine());

			//Adding data to PreparedStatement Object
			ps1.setString(1, code);
			ps1.setString(2, name);
			ps1.setString(3, author);
			ps1.setFloat(4, price);
			ps1.setInt(5, qty);

			int k = ps1.executeUpdate();//Execution
			if(k>0) {
			System.out.println("BookDetails inserted...");
			}
			break;
			case 2:
			ResultSet rs1 = ps2.executeQuery();//Execution
			while(rs1.next()) {
			System.out.println(rs1.getString(1)+
			"\t"+rs1.getString(2)+
			"\t"+rs1.getString(3)+
			"\t"+rs1.getFloat(4)+
			"\t"+rs1.getInt(5));
			}//end of loop
			break;
			case 3:
			System.out.println("Enter the BookCode:");
			String bCode = s.nextLine();
			ps3.setString(1, bCode);
			ResultSet rs2 = ps3.executeQuery();
			if(rs2.next()) {
			System.out.println(rs2.getString(1)+
			"\t"+rs2.getString(2)+
			"\t"+rs2.getString(3)+
			"\t"+rs2.getFloat(4)+
			"\t"+rs2.getInt(5));
			}else {
			System.out.println("Invalid BookCode..");
			}
			break;
			case 4:
			System.out.println("Enter the bookCode:");
			String bCode2 = s.nextLine();
			ps3.setString(1, bCode2);
			ResultSet rs3 = ps3.executeQuery();
			if(rs3.next()) {
			System.out.println("Old price:"+rs3.getFloat(4));
			System.out.println("Enter NewPrice:");
			float nPrice = Float.parseFloat(s.nextLine());
			System.out.println("Existing Qty:"+rs3.getInt(5));
			System.out.println("Enter the qty:");
			int nQty = Integer.parseInt(s.nextLine());

			ps4.setFloat(1, nPrice);
			ps4.setInt(2, nQty);
			ps4.setString(3, bCode2);

			int k1 = ps4.executeUpdate();
			if(k1>0) {
			System.out.println("BookDetails updated...");
			}
			}else {
			System.out.println("Invalid bookCode..");
			}
			break;
			case 5:
			System.out.println("Enter the BookCode:");
			String bCode3 = s.nextLine();
			ps3.setString(1, bCode3);
			ResultSet rs4 = ps3.executeQuery();
			if(rs4.next()) {
			ps5.setString(1, bCode3);
			int k3 = ps5.executeUpdate();
			if(k3>0) {
			System.out.println("BookDetails deleted..");
			}
			}else {
			System.out.println("Invalid bookCode...");
			}
			break;
			case 6:
			System.out.println("Operations Stoped...");
			System.exit(0);
			default:
			System.out.println("Invalid Choice..");
			}//end of switch
			}//end of loop
			}catch(Exception e) {e.printStackTrace();}
			}
			}
