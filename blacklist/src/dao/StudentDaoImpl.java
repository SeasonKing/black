package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import util.DBConnection;
import bean.Clasz;
import bean.Student;
import bean.User;

/**
 * @author 孙继杰 2016-09-01 学生实现类
 */
public class StudentDaoImpl implements StudentDao {

	private Connection conn;// 连接数据库

	public StudentDaoImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public void add(Student stud) {
		// TODO Auto-generated method stub

		String sql = "insert into Student(name,code,sex,birth,claszId) values(?,?,?,?,?)";

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, stud.getName());
			ps.setString(2, stud.getCode());
			ps.setInt(3, stud.getSex());
			ps.setDate(4, new java.sql.Date(stud.getBirth().getTime()));
			ps.setInt(5, stud.getClasz_id());
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

	}

	@Override
	public void add(List<Student> sList) {
		// TODO Auto-generated method stub

		if (sList != null) {

			for (Student s : sList) {

				add(s);
			}
		}

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

		String sql2 = " delete from student where id=" + id;
		Statement st = null;

		try {
			st = conn.createStatement();
			st.execute(sql2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
	public int deleteByClsId(int claszId) {
		// TODO Auto-generated method stub
		//没有返回删除的数
		String sql = "delete from student where claszId=?";
		PreparedStatement ps = null;
	    int count=0;
		try {
			// 数据库执行对象
			ps = conn.prepareStatement(sql);
			// 设置参数
			ps.setInt(1,claszId);
			count=ps.executeUpdate();
			
		   
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
		return count;

	}

	@Override
	public int deleteByClsName(String claszname) {
		// TODO Auto-generated method stub
		String sql="select  id from clasz where name=?";
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, claszname);
			ResultSet rs=ps.executeQuery();
			Integer id=null;
			while(rs.next())
			{
				id=rs.getInt(1);
			}
			if(!ps.isClosed());
			ps.close();
			
			if(!rs.isClosed());
			rs.close();
			
			sql="delete from student where claszId=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			return ps.executeUpdate();
			
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
		return 0;
		
	}

	@Override
	public void update(Student student) {
		// TODO Auto-generated method stub

		String sql = "update student set name=?,code=?,sex=?,birth=?,claszId=? where id=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, student.getName());
			ps.setString(2, student.getCode());
			ps.setInt(3, student.getSex());
			ps.setDate(4, new java.sql.Date(student.getBirth().getTime()));
			ps.setInt(5, student.getClasz_id());
			ps.setInt(6, student.getId());
			ps.execute();// 执行

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

	}

	@Override
	public int updateClaszId(List<Student> studList, int claszId) {
		// TODO Auto-generated method stub
		if(studList!=null)
		{
			for(Student s:studList){
				s.setClasz_id(claszId);
				update(s);
			}
		}
		return studList.size();
	}

	@Override
	public Student findById(int id) {
		// TODO Auto-generated method stub

		String sql = "select * from student where id=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Student student = null;
		try {
			// 数据库执行对象
			ps = conn.prepareStatement(sql);
			// 设置参数
			ps.setInt(1, id);
			// 获取结果集
			rs = ps.executeQuery();

			while (rs.next()) {
				student = new Student();
				student.setId(rs.getInt(1));
				student.setName(rs.getString(2));
				student.setCode(rs.getString(3));
				student.setSex(rs.getInt(4));
				student.setBirth(rs.getDate(5));
				student.setClasz_id(rs.getInt(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			try {
				ps.close();
				//rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<Student> findByName(String name) {
		// TODO Auto-generated method stub
		//模糊查询
				String sql="select * from student where name like'%"+name+"%' " ;
				Connection conn1=null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				Student student=null;
				List<Student> studList=new ArrayList<Student>();
				try {
					
					// 数据库执行对象
					ps = conn.prepareStatement(sql);
					// 设置参数
//					ps.setString(1,name);
					// 获取结果集
					rs = ps.executeQuery();

					
					while(rs.next()){
						student=new Student();
						
						student.setId(rs.getInt(1));
						student.setName(rs.getString(2));
						student.setCode(rs.getString(3));
						student.setSex(rs.getInt(4));
						student.setBirth(rs.getDate(5));
						student.setClasz_id(rs.getInt(6));
						studList.add(student);
		}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 finally {
						try {
							//rs.close();
							ps.close();
							//conn.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				return studList;
	}

	@Override
	public Student findByCode(String code) {
		// TODO Auto-generated method stub
		String sql = "select * from student where code=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Student student = null;
		try {
			// 数据库执行对象
			ps = conn.prepareStatement(sql);
			// 设置参数
			ps.setString(1, code);
			// 获取结果集
			rs = ps.executeQuery();

			while (rs.next()) {
				student = new Student();
				student.setId(rs.getInt(1));
				student.setName(rs.getString(2));
				student.setCode(rs.getString(3));
				student.setSex(rs.getInt(4));
				student.setBirth(rs.getDate(5));
				student.setClasz_id(rs.getInt(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			try {
				ps.close();
				//rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		String sql="select * from student";
		List<Student> studList=new ArrayList<Student>();
		
		//获取Sql语句执行对象
			try {
				
				//数据库执行对象
				Statement st=conn.createStatement();
				//获取结果对象
				ResultSet rs=st.executeQuery(sql);
				
				//获取数据
				while(rs.next())
				{
					
					Student student=new Student();
					
					
					student.setId(rs.getInt(1));
					student.setName(rs.getString(2));
					student.setCode(rs.getString(3));
					student.setSex(rs.getInt(4));
					student.setBirth(rs.getDate(5));
					student.setClasz_id(rs.getInt(6));
					studList.add(student);
				}
				st.close();
				
				
				 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return studList;
	}

	@Override
	public List<Student> findAllByClsId(int ClsId) {
		// TODO Auto-generated method stub
		String sql = "select * from student where claszId="+ClsId;
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		Student student=null;
		List<Student> studList=new ArrayList<Student>();
		try {
			
			// 数据库执行对象
			ps = conn.prepareStatement(sql);
			// 设置参数
//			ps.setInt(1,ClsId);
			// 获取结果集
			rs = ps.executeQuery();

			
			while(rs.next()){
				student=new Student();
				
				student.setId(rs.getInt(1));
				student.setName(rs.getString(2));
				student.setCode(rs.getString(3));
				student.setSex(rs.getInt(4));
				student.setBirth(rs.getDate(5));
				student.setClasz_id(rs.getInt(6));
				studList.add(student);
}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally {
				try {
					//rs.close();
					ps.close();
				//	conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		return studList;
	}

	@Override
	public Clasz findClsByStudId(int studeId) {
		// TODO Auto-generated method stub
		String sql="select c.* from clasz c join  student s on c.id=s.claszId where s.id =? ";
		PreparedStatement ps=null;
		ResultSet rs=null;
		Clasz stu=new Clasz();
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, studeId);
			rs=ps.executeQuery();
			
			while(rs.next()){
				
				int c_id=rs.getInt(1);
				String c_name=rs.getString(2);
			    String c_tname =rs.getString(3);
			    Clasz  cls= new Clasz();
			    cls.setId(c_id);
			    cls.setName(c_name);
			    cls.setT_name(c_tname);
			    return cls;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;

	}
	
	@Override
	public int[] countBySex(int classId) {
		// TODO Auto-generated method stub
		String sql="select sex ,count(*) from Student where claszId=? group by sex" ;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int[] count =new int[2];
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, classId);
			rs=ps.executeQuery();
			while(rs.next())
			{
				int sex=rs.getInt(1);
				int num =rs.getInt(2);
				count[sex]=num;
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
		return count;
	}

	public static void main(String[] args) {
		Connection conn = DBConnection.createConnextion();
		Student stud = new Student();
//		 Student stud1=new Student();
//		 Student stud2=new Student();
//
		 try {
		 stud.setCode("11");
		 stud.setName("小路");
		 stud.setSex(1);
		
		 SimpleDateFormat sf=new SimpleDateFormat("yyyy-mm-dd");
		 String ddte="1993-8-30";
		 Date date=null;
		 date = sf.parse(ddte);
		 stud.setBirth(date);
		 stud.setClasz_id(7);
		 StudentDao studDao=new StudentDaoImpl(conn);
		 studDao.add(stud);
		 } catch (ParseException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
//
//		 try {
//		 stud1.setCode("12");
//		 stud1.setName("大陆");
//		 stud1.setSex(1);
//		 SimpleDateFormat sf=new SimpleDateFormat("yyyy-mm-dd");
//		 String ddte="1911-6-30";
//		 Date date=null;
//		 date = sf.parse(ddte);
//		 stud1.setBirth(date);
//		 stud1.setClasz_id(8);
//		 StudentDao studDao=new StudentDaoImpl(conn);
//		 studDao.add(stud1);
//		 } catch (ParseException e) {
//		 // TODO Auto-generated catch block
//		 e.printStackTrace();
//		 }
		//
		// try {
		// stud2.setCode("09");
		// stud2.setName("桃子");
		// stud2.setSex(0);
		// SimpleDateFormat sf=new SimpleDateFormat("yyyy-mm-dd");
		// String ddte="1993-8-30";
		// Date date=null;
		// date = sf.parse(ddte);
		// stud2.setBirth(date);
		// stud2.setClasz_id(2);
		// StudentDao studDao=new StudentDaoImpl(conn);
		// studDao.add(stud2);
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		StudentDao studDao = new StudentDaoImpl(conn);

		// 添加多个学生
//		 List sList=new ArrayList<Student>();
//		 sList.add(stud1);
//		 sList.add(stud2);

		// 删除学生
//		 studDao.delete(6);
		
		//通过班级id删除学生
		
//    System.out.println("删除了"+studDao.deleteByClsId(8)+"个学生");
		
		//通过班级name删除学生
//		System.out.println(studDao.deleteByClsName("一年五班"));
		
		
		
		
		// 修改学生信息
		// stud.setId(1);
		// stud.setName("叶子");
		// stud.setCode("21");
		// stud.setSex(0);
		// SimpleDateFormat sf=new SimpleDateFormat("yyyy-mm-dd");
		// String ddte="1993-8-30";
		// Date date=null;
		// try {
		// date = sf.parse(ddte);
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// stud.setBirth(date);
		// stud.setClasz_id(1);
		// studDao.update(stud);

		// 学生id查学生
//		stud = studDao.findById(8);
//		System.out.println(stud);
		
		//通过name查学生id（模糊查询）
//		System.out.println(studDao.findByName("小"));
//
//		// 学生code查学生
//		stud = studDao.findByCode("03");
//		System.out.println(stud);
		
		
		//查询所有学生
//		List<Student> studList=studDao.findAll();
//		System.out.println(studList);
		
//		List<Student> studList=studDao.findAllByClsId(1);
//		System.out.println(studList);
		
	//通过学生id查班级
//	        System.out.println(studDao.findClsByStudId(4));
	

		//统计某个班级男女人数
		
//		System.out.println( Arrays.toString(studDao.countBySex(1)));
	}

	
}
