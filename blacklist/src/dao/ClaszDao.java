package dao;

import java.util.List;

import bean.Clasz;

/**
 * @author ��̽� 2016-09-01
 *�༶���ݴ���ӿ�
 */
public interface ClaszDao {
	
	/**
	 * ��Ӱ༶
	 * ���°༶��ѧ�������У���ѧ����ѧ��Ҳ�������
	 * @param cls
	 */
	void add(Clasz cls);

	/**
	 * ͬ���༶��idɾ���༶
	 * @param claszId �༶id
	 * @param isDelStud �Ƿ���Թ���ɾ��ѧ�� true-����  false-������
	 * @return �Ƿ�ɾ���ɹ� true-ɾ���ɹ� 
	 */
	boolean delete (int claszId,boolean isDelStud);
	
	/**
	 * �޸İ༶��Ϣ
	 * @param cls 
	 */
	void update(Clasz cls); 
	
	/**
	 * ��ѯ���а༶
	 * @return
	 */
	List<Clasz> findAll();
	
	/**
	 * ͨ���༶id��ѯ�ð༶�������༶�� ��ѧ����
	 * @param id �༶��id
	 * @return 
	 */
	Clasz findById(int id);
	
	/**
	 * ͨ���༶name��ѯ�ð༶�������༶�� ��ѧ����֧��ģ����ѯ
	 * @param name
	 * @return
	 */
	List<Clasz> findByName(String name);
	
	
}
