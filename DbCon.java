package Test;
import java.util.*;
import java.sql.*;
public class DbCon 
{
public static void main(String[]args)
{
	try
	{
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl1","System","tiger");
		Statement stm=con.createStatement();
		ResultSet rs=stm.executeQuery("Select * from Product40");
		while(rs.next())
		{
			System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3));
		}
	}
	catch(Exception e) {e.printStackTrace();}
}
}
