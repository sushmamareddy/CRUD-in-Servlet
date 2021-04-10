import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpDao {
	public static Connection getConnection()
	{
		Connection con = null;
		try
		{
			String url = "jdbc:mysql://localhost:3306/practice1";
			String user = "root";
			String pass="Sriya@2002";
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pass);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return con;
	}
	
	public static int save(Emp e)
	{
		int status =0;
		try
		{
			Connection con= EmpDao.getConnection();
			String query = "insert into user(name,password,email,country) values(?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,e.getName());
			ps.setString(2, e.getPassword());
			ps.setString(3,e.getEmail());
			ps.setString(4, e.getCountry());
			status = ps.executeUpdate();
			con.close();
		}
		catch(Exception e1)
		{
			System.out.println(e1);
		}
		return status;
	}
	
	public static int update(Emp e)
	{
		int status =0;
		try
		{
			Connection con = EmpDao.getConnection();
			String query = "update User set name=?,password=?,email=?,country=? where id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, e.getName());
			ps.setString(2, e.getPassword());
			ps.setString(3, e.getEmail());
			ps.setString(4, e.getCountry());
			ps.setInt(5, e.getId());
			status = ps.executeUpdate();
			con.close();
		}
		
		catch(Exception e1)
		{
			System.out.println(e1);
		}
		return status;
	}
	
	public static int delele(int id)
	{
		int status =0;
		try
		{
			Connection con = EmpDao.getConnection();
			String query = "delete from User where id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			status = ps.executeUpdate();
			
			con.close();
		}
		
		catch(Exception e1)
		{
			System.out.println(e1);
		}
		return status;
	}
	
	public static Emp getEmployeeById(int id)
	{
		Emp e = new Emp();
		try
		{
			Connection con = EmpDao.getConnection();
			String query = "select * from User where id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPassword(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setCountry(rs.getString(5));
			}
			con.close();
		}
		
		catch(Exception e1)
		{
			System.out.println(e1);
		}
		return e;
	}
	
	public static List<Emp> getAllEmployees()
	{
		List<Emp> list = new ArrayList<Emp>();
		try{  
            Connection con=EmpDao.getConnection();  
            PreparedStatement ps=con.prepareStatement("select * from user");  
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
                Emp e=new Emp();  
                e.setId(rs.getInt(1));  
                e.setName(rs.getString(2));  
                e.setPassword(rs.getString(3));  
                e.setEmail(rs.getString(4));  
                e.setCountry(rs.getString(5));  
                list.add(e);  
            }  
            con.close();  
        }catch(Exception e){e.printStackTrace();}  
          
		return list;
	}
}
