package Test;
import java.sql.*;
import java.util.*;

public class DBCon1 
{
  public static void main(String[]args)
  {
	  try
	  {
		  Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl1","system","tiger");
		  Statement stm=con.createStatement();
		  ResultSet rs=stm.executeQuery(" select * from customer60");
		  while(rs.next())
		  {
			  System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getInt(5));
		  }
	  }
	  catch(Exception e) {e.printStackTrace();}
  }
}