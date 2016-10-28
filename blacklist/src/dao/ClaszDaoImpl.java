package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.DBConnection;
import bean.Clasz;
import bean.Student;
import bean.User;

/**
 * @author 孙继杰 2016-09-01
 *班级实现类
 */
public class ClaszDaoImpl implements ClaszDao {

	private Connection conn;//连接数据库
	
	
	public ClaszDaoImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public void add(Clasz cls) {
		// TODO Auto-generated method stub
		String sql="insert into Clasz(name,t_name) values(?,?)";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//插入班级信息
			ps=conn.prepareStatement(sql);
			ps.setString(1, cls.getName());
			ps.setString(2, cls.getT_name());
			ps.execute();
			
			//获取该班级 的id
			sql="select id from clasz where name=?";
			
			if(!ps.isClosed())
				ps.close();
				ps=conn.prepareStatement(sql);
				ps.setString(1, cls.getName());
				rs=ps.executeQuery();
				
				while(rs.next()){
					cls.setId(rs.getInt(1));
					break;
					
				}
				//插入学生信息
				List<Student> sList=cls.getStudtList();
			if(sList!=null && sList.size()>0){
				
				sql="insert into Student(code,name,sex,birth,claszId) values(?,?,?,?,?)";
				for(Student s:sList){
					if(!ps.isClosed())
						ps.close();
						ps=conn.prepareStatement(sql);
						ps.setString(1,s.getCode());
						ps.setString(2, s.getName());
						ps.setInt(3, s.getSex());
//			打日志			System.out.println("............s.getBirth()"+s.getBirth());
						ps.setDate(4, new java.sql.Date(s.getBirth().getTime()));
						ps.setInt(5, cls.getId());
						ps.execute();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(!ps.isClosed())
					ps.close();
				if(!rs.isClosed())
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	

	@Override
	public boolean delete(int claszId, boolean isDelStud) {
		// TODO Auto-generated method stub
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (isDelStud) {
			// 删除该班级的学生信息
			String sql = "delete from student where claszId=?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, claszId);
				ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} else {
			String sql = "select *from student where claszId=?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, claszId);
				rs = ps.executeQuery();
				if (rs != null && rs.next()) {
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
		String sql = "delete from clasz where id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, claszId);
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}


	

	@Override
	public void update(Clasz cls) {
		// TODO Auto-generated method stub
		String sql="update clasz set name=?,t_name=? where id=?";
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, cls.getName());
			ps.setString(2, cls.getT_name());
			ps.setInt(3, cls.getId());
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
	public List<Clasz> findAll() {
		// TODO Auto-generated method stub
		
		String sql="select * from clasz";
		
		List<Integer> idList=new ArrayList<Integer>();
		List<Clasz> clsList=new ArrayList<Clasz>();
		PreparedStatement ps=null;
		ResultSet rs =null;
		//获取Sql语句执行对象
			try {
				
				//数据库执行对象
				 ps=conn.prepareStatement(sql);
				//获取结果对象
				 rs=ps.executeQuery();
				
				//获取数据
				while(rs.next())
				{
					idList.add(rs.getInt(1));
					
				}
				
				for(Integer i:idList){
					Clasz cls=this.findById(i);
					clsList.add(cls);
				}
				
				 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return clsList;
	}

	@Override
	public Clasz findById(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from clasz where id=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Clasz clasz=null;
		try {
			// 数据库执行对象
			ps = conn.prepareStatement(sql);
			// 设置参数
			ps.setInt(1, id);
			// 获取结果集
		   rs=ps.executeQuery();
		   
		   while (rs.next()) {
			  
				int c_id=rs.getInt(1);
				String c_name=rs.getString(2);
			    String c_tname =rs.getString(3);
			    clasz = new Clasz( c_id,c_name,c_tname,null);
			    break;
				
		   }
		   rs.close();
		   ps.close();
		   sql="select * from Student where claszId=?";
		   ps=conn.prepareStatement(sql);
		   List<Student> sList=new ArrayList<Student>();
		   ps.setInt(1, clasz.getId());
		   rs=ps.executeQuery();
		   while(rs.next())
		   {
			   int s_id=rs.getInt(1);
			   String s_code=rs.getString(2);
			   String s_name=rs.getString(3);
			   int s_sex=rs.getInt(4);
			   Date s_birth=rs.getDate(5);
			   int s_clsId=rs.getInt(6);
			   
			   Student s=new Student ();
			   s.setId(s_id);
			   s.setCode(s_code);
			   s.setSex(s_sex);
			   s.setBirth(s_birth);
			   s.setName(s_name);
			   s.setClasz_id(s_clsId);
			    sList.add(s);
			   
		   }
		   clasz.setStudtList(sList);
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
		return clasz;
	}

	@Override
	public List<Clasz> findByName(String name) {
		// TODO Auto-generated method stub
		//模糊查询
		String sql="select * from clasz where name like'%"+name+"%' " ;
		Connection conn1=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Clasz clasz=null;
		List<Clasz> clsList=new ArrayList<Clasz>();
		try {
			
			// 数据库执行对象
			ps = conn.prepareStatement(sql);
			// 设置参数
//			ps.setString(1,name);
			// 获取结果集
			rs = ps.executeQuery();

			
			while(rs.next()){
				clasz=new Clasz();
				
				clasz.setId(rs.getInt(1));
				clasz.setName(rs.getString(2));
//				clasz.setT_name(rs.getString(3));
				
				clsList.add(clasz);
}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally {
				try {
					rs.close();
					ps.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		return clsList;
		
		
		
	}
	
	public static void main(String[] args) {
		Connection conn=DBConnection.createConnextion();
		ClaszDao claszDao=new ClaszDaoImpl(conn);
		Student s1=new Student();
		s1.setName("name1");
	    s1.setSex(0);
	    s1.setCode("20");
	    s1.setBirth(new Date());
	    
	    Student s2=new Student();
		s2.setName("name2");
	    s2.setSex(0);
	    s2.setCode("23");
	    s2.setBirth(new Date());
	    
	    Student s3=new Student();
		s3.setName("name3");
	    s3.setSex(0);
	    s3.setCode("24");
	    s3.setBirth(new Date());
	    
	    List<Student> sList=new ArrayList<Student>();
	    sList.add(s1);
	    sList.add(s2);
	    sList.add(s3);
	    
	    Clasz clasz=new Clasz();
		clasz.setName("六年一班");
		clasz.setT_name("许一");
		clasz.setStudtList(sList);
//		claszDao.add(clasz);
		
//		
//		boolean isOK=claszDao.delete(8, true);
//		System.out.println("delete isOK="+isOK);
		
		//查全部班级
		
//		System.out.println(claszDao.findAll());
				
		
		//通过班级id查学生
//		clasz=claszDao.findById(1);
//		System.out.println(clasz);
//		
//		clasz.setName("三年三班");
//		clasz.setT_name("许嵩");
//		ClaszDao claszDao=new ClaszDaoImpl(conn);
//		claszDao.add(clasz);
//		
//		clasz.setName("三年二班");
//		clasz.setT_name("许老");
//		ClaszDao claszDao=new ClaszDaoImpl(conn);
//		claszDao.add(clasz);
		
		
		//修改班级信息
//		clasz.setName("一年五班");
//		clasz.setT_name("许五");
//		clasz.setId(5);
//		claszDao.update(clasz);
		
		//查询全部班级
//		List<Clasz> clsList=claszDao.findAll();
//		System.out.println(clsList);
		
		//id查找
//		clasz=claszDao.findById(1);
//		System.out.println(clasz);
		
		//模糊查询班级
		
//		System.out.println(claszDao.findByName("二"));
		
	}

}
