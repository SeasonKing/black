package dao;

import java.util.List;

import bean.Clasz;
import bean.Student;

/**
 * @author ��̽� 2016-09-01
 *ѧ�����ݴ���ӿ� dao
 */

public interface StudentDao {

	/**
	 * ���һ��ѧ��
	 * @param stud
	 */
	public void add(Student stud);
	
	/**
	 * ��Ӷ��ѧ��
	 * @param sList
	 */
	public void add(List<Student> sList);
	
	/**
	 * ɾ��һ��ѧ��
	 * @param id
	 */
	public void delete(int id);
	
	/**
	 * ͨ���༶idɾ��ѧ��
	 * @param claszId �༶ ��id
	 * @return ɾ����ѧ������
	 */
	public int deleteByClsId(int claszId);
	
	/**
	 * ͨ���༶nameɾ��ѧ��
	 * @param claszname �༶����
	 * @return ɾ����ѧ������
	 */
	public int deleteByClsName(String claszname);
	
	/**
	 * �޸�ѧ����Ϣ
	 * @param student �޸ĵ�ѧ��
	 */
	public void update(Student student);
	
	/**
	 * �޸�һ��ѧ������ �İ༶
	 * @param studListѧ������
	 * @param claszId �µİ༶id
	 * @return ���ĵ�ѧ������
	 */
	public int updateClaszId(List<Student> studList,int claszId );
	
	/**
	 * ͨ��id����ѧ��
	 * @param id ѧ��id
	 * @return ѧ������
	 */
	public Student findById(int id);
	
	/**
	 * ͨ��name��ѧ��id��֧��ģ����ѯ��
	 * @param name ѧ��������
	 * @return ѧ������
	 */
	public List<Student> findByName(String name);
	/**
	 * ͨ��code��ѧ��
	 * @param code
	 * @return
	 */
	public Student findByCode(String code);
	
	
	/**
	 * ��ѯ����ѧ����Ϣ
	 * @return
	 */
	public List<Student> findAll();
	
	/**
	 * ͨ���༶id�������ѧ����Ϣ
	 * @param ClsId �༶id
	 * @return �鵽��ѧ������
	 */ 
	public List<Student> findAllByClsId(int ClsId);
	
	/**
	 * ��ѯĳ��ѧ�����ڵİ༶
	 * @param studeId ѧ��id
	 * @return �༶
	 */
	public Clasz findClsByStudId(int studeId);
	
	/**
	 * ͳ��ĳ���༶����Ů����
	 * @param classId
	 * @return �±� 0-Ů  �±� 1-��
	 */
	int[] countBySex(int classId);
	
}
