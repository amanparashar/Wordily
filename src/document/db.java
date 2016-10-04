package document;
import java.util.*;
import java.sql.*;  // for sql queries
import trie.*;
public class db {
	public static Trie t=new Trie();
	public static void Main() throws ClassNotFoundException, SQLException  //if sql driver if not found
	{
	Class.forName("com.mysql.jdbc.Driver");    //sql driver of jdbc
	String CONNECT="jdbc:mysql://localhost:3306/words";   //local host ip adreess
	Properties p=new Properties();    //For user name and password
	p.put("user", "root");
	p.put("password", "");
	Connection c=	DriverManager.getConnection(CONNECT, p);  //this is making a connection
Statement s=c.createStatement(); //statement object is being made from which the query will be executed
ResultSet r=s.executeQuery("select * from words ");  //all the result from query is stored here

while(r.next())   //untill result set is not over
{
	String word=r.getString(2);//from first column words are taken
	t.insert(word);

}
	}

}

