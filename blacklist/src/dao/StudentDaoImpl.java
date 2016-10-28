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
 * @author ��̽� 2016-09-01 ѧ��ʵ����
 */
public class StudentDaoImpl implements StudentDao {

	private Connection conn;// �������ݿ�

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
		//û�з���ɾ������
		String sql = "delete from student where claszId=?";
		PreparedStatement ps = null;
	    int count=0;
		try {
			// ���ݿ�ִ�ж���
			ps = conn.prepareStatement(sql);
			// ���ò���
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
			ps.execute();// ִ��

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
			// ���ݿ�ִ�ж���
			ps = conn.prepareStatement(sql);
			// ���ò���
			ps.setInt(1, id);
			// ��ȡ�����
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
		//ģ����ѯ
				String sql="select * from student where name like'%"+name+"%' " ;
				Connection conn1=null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				Student student=null;
				List<Student> studList=new ArrayList<Student>();
				try {
					
					// ���ݿ�ִ�ж���
					ps = conn.prepareStatement(sql);
					// ���ò���
//					ps.setString(1,name);
					// ��ȡ�����
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
			// ���ݿ�ִ�ж���
			ps = conn.prepareStatement(sql);
			// ���ò���
			ps.setString(1, code);
			// ��ȡ�����
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
		
		//��ȡSql���ִ�ж���
			try {
				
				//���ݿ�ִ�ж���
				Statement st=conn.createStatement();
				//��ȡ�������
				ResultSet rs=st.executeQuery(sql);
				
				//��ȡ����
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
			
			// ���ݿ�ִ�ж���
			ps = conn.prepareStatement(sql);
			// ���ò���
//			ps.setInt(1,ClsId);
			// ��ȡ�����
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
		 stud.setName("С·");
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
//		 stud1.setName("��½");
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
		// stud2.setName("����");
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

		// ��Ӷ��ѧ��
//		 List sList=new ArrayList<Student>();
//		 sList.add(stud1);
//		 sList.add(stud2);

		// ɾ��ѧ��
//		 studDao.delete(6);
		
		//ͨ���༶idɾ��ѧ��
		
//    System.out.println("ɾ����"+studDao.deleteByClsId(8)+"��ѧ��");
		
		//ͨ���༶nameɾ��ѧ��
//		System.out.println(studDao.deleteByClsName("һ�����"));
		
		
		
		
		// �޸�ѧ����Ϣ
		// stud.setId(1);
		// stud.setName("Ҷ��");
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

		// ѧ��id��ѧ��
//		stud = studDao.findById(8);
//		System.out.println(stud);
		
		//ͨ��name��ѧ��id��ģ����ѯ��
//		System.out.println(studDao.findByName("С"));
//
//		// ѧ��code��ѧ��
//		stud = studDao.findByCode("03");
//		System.out.println(stud);
		
		
		//��ѯ����ѧ��
//		List<Student> studList=studDao.findAll();
//		System.out.println(studList);
		
//		List<Student> studList=studDao.findAllByClsId(1);
//		System.out.println(studList);
		
	//ͨ��ѧ��id��༶
//	        System.out.println(studDao.findClsByStudId(4));
	

		//ͳ��ĳ���༶��Ů����
		
//		System.out.println( Arrays.toString(studDao.countBySex(1)));
	}

	
}
