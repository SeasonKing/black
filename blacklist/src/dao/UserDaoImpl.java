package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBConnection;
import bean.User;

public class UserDaoImpl implements UserDao {

	private Connection conn;//连接数据库
	
	public UserDaoImpl(Connection conn){
		this.conn=conn;
	}
	
	@Override
	public void addUser(User user) {
		
		String sql="insert into User(name,password,email) values(?,?,?)";
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		
		String sql2=" delete from user where id="+id;
		Statement st =  null;
      
		try {
			st=conn.createStatement();
			st.execute(sql2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				st.close();
				//conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}   
       
	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		String sql="update User set name=?,password=?,email=? where id=?";
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setInt(4, user.getId());
			ps.execute();//执行

	         
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
	
	}

	@Override
	public User finById(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from user where id=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user=null;
		try {
			// 数据库执行对象
			ps = conn.prepareStatement(sql);
			// 设置参数
			ps.setInt(1, id);
			// 获取结果集
		   rs=ps.executeQuery();
		   
		   while (rs.next()) {
				 user = new User();
				int id1 = rs.getInt(1);
				String name = rs.getString(2);
				String password = rs.getString(3);
				String email = rs.getString(4);
				int status=rs.getInt(5);
				
				user.setId(id);
				user.setName(name);
				user.setPassword(password);
				user.setEmail(email);
				user.setStatus(status);
		   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally{
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
		
	}

	@Override
	public User finByName(String name) {
		// TODO Auto-generated method stub
		String sql = "select * from user where name=?";
		User user=null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
	  try {
		// 连接数据库
		ps = conn.prepareStatement(sql);
		// 设置参数
		ps.setString(1, name);
		// 执行语句
		rs = ps.executeQuery();
		  while (rs.next()) {
				 user = new User();
				int id1 = rs.getInt(1);
				String name1 = rs.getString(2);
				String password = rs.getString(3);
				String email = rs.getString(4);
				int status=rs.getInt(5);
				
				user.setId(id1);
				user.setName(name1);
				user.setPassword(password);
				user.setEmail(email);
				user.setStatus(status);
		   }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		return user;
	}
	
	
	
	
	
	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		
		String sql="select * from user where status=0";
		List list=new ArrayList();
		PreparedStatement  ps=null;
		ResultSet rs=null;
		User user=null;
		
		try {
			ps=conn.prepareStatement(sql);
			 rs=ps.executeQuery();
			while (rs.next()){
				 user=new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setStatus(rs.getInt(5));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return list;
	}

	public static void main(String[] args) {
		Connection conn=DBConnection.createConnextion();
		User user=new User();
//		user.setName("张三");
//		user.setPassword("123456");
//		user.setEmail("aaaa@gmail.com");
//		UserDao userDao=new UserDaoImpl(conn);
//		userDao.addUser(user);
		
//		user.setName("李四");
//		user.setPassword("123456");
//		user.setEmail("aaaa@gmail.com");
//		UserDao userDao=new UserDaoImpl(conn);
//		userDao.addUser(user);
//		user.setName("小明");
//		user.setPassword("1234");
//		user.setEmail("www.@123.com");
		UserDao userDao=new UserDaoImpl(conn);
//		userDao.addUser(user);
		
		//删除
//		userDao.deleteUser(3);
		
		//修改
//	
//		user.setName("小李");
//		user.setId(4);
//		userDao.updateUser(user);
//		
//		user.setName("小小鱼");
//		user.setId(5);
//		userDao.updateUser(user);
		
		//id查找
		user=userDao.finById(5);
		System.out.println(user);
	
		//name查找
		user=userDao.finByName("张三");
		System.out.println(user);
		
		
		List list =userDao.findAll();
		System.out.println(list.toString());
	}

	@Override
	public List findBlackList() {
		// TODO Auto-generated method stub
		String sql="select * from user where status=1";
		List list=new ArrayList();
		PreparedStatement  ps=null;
		ResultSet rs=null;
		User user=null;
		
		try {
			ps=conn.prepareStatement(sql);
			 rs=ps.executeQuery();
			while (rs.next()){
				 user=new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setStatus(rs.getInt(5));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public void updateStatus(User user) {
		String sql="update user set status=? where id=?";
		PreparedStatement ps =  null;
	
   		try {
   		  // 获取sql语句执行对象
			ps = conn.prepareStatement(sql);
			
			if(user.getStatus()==0){
				ps.setInt(1, 1);
			}else if(user.getStatus()==1){
				ps.setInt(1, 0);
			}
			ps.setInt(2, user.getId());

			// 执行语句
			 ps.execute();
	         
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	     }

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		String sql="select *from user where name='"+username+"' and password='"+password+"'";
		PreparedStatement ps=null;
		User user=null;
			try {                                     
				ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					user=new User();
					user.setId(rs.getInt("id"));
					user.setName(rs.getString("name"));
					user.setPassword(rs.getString("password"));
					user.setEmail(rs.getString("email"));
					user.setStatus(rs.getInt("status"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	finally{
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		return user;
	}
		
	}


