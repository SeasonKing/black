package dao;

import java.util.List;

import bean.Clasz;
import bean.Student;

/**
 * @author 孙继杰 2016-09-01
 *学生数据处理接口 dao
 */

public interface StudentDao {

	/**
	 * 添加一个学生
	 * @param stud
	 */
	public void add(Student stud);
	
	/**
	 * 添加多个学生
	 * @param sList
	 */
	public void add(List<Student> sList);
	
	/**
	 * 删除一个学生
	 * @param id
	 */
	public void delete(int id);
	
	/**
	 * 通过班级id删除学生
	 * @param claszId 班级 的id
	 * @return 删除的学生数量
	 */
	public int deleteByClsId(int claszId);
	
	/**
	 * 通过班级name删除学生
	 * @param claszname 班级名称
	 * @return 删除的学生数量
	 */
	public int deleteByClsName(String claszname);
	
	/**
	 * 修改学生信息
	 * @param student 修改的学生
	 */
	public void update(Student student);
	
	/**
	 * 修改一批学生所在 的班级
	 * @param studList学生集合
	 * @param claszId 新的班级id
	 * @return 更改的学生人数
	 */
	public int updateClaszId(List<Student> studList,int claszId );
	
	/**
	 * 通过id查找学生
	 * @param id 学生id
	 * @return 学生对象
	 */
	public Student findById(int id);
	
	/**
	 * 通过name查学生id（支持模糊查询）
	 * @param name 学生的名字
	 * @return 学生集合
	 */
	public List<Student> findByName(String name);
	/**
	 * 通过code查学生
	 * @param code
	 * @return
	 */
	public Student findByCode(String code);
	
	
	/**
	 * 查询所有学生信息
	 * @return
	 */
	public List<Student> findAll();
	
	/**
	 * 通过班级id查出所有学生信息
	 * @param ClsId 班级id
	 * @return 查到的学生集合
	 */ 
	public List<Student> findAllByClsId(int ClsId);
	
	/**
	 * 查询某个学生所在的班级
	 * @param studeId 学生id
	 * @return 班级
	 */
	public Clasz findClsByStudId(int studeId);
	
	/**
	 * 统计某个班级的男女人数
	 * @param classId
	 * @return 下标 0-女  下标 1-男
	 */
	int[] countBySex(int classId);
	
}
