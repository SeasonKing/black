package dao;

import java.util.List;

import bean.User;


public interface UserDao {

	/**
	 * ����û�
	 * @param user
	 */
	void addUser(User user);
	/**
	 * ɾ���û�
	 * @param id
	 */
	void deleteUser(int id);
	/**
	 * ������Ϣ
	 * @param user
	 */
	void updateUser(User user);
	/**
	 * �����û�
	 * @param id �õ�id
	 * @return ���û�����
	 */
	User finById(int id);
	/**
	 * ͨ���û����Ʋ����û�
	 * @param name �û�����
	 * @return
	 */
	User finByName(String name);
	
	/**
	 * ����ȫ��
	 * @return
	 */
	List findAll();
	
	/**
	 * �鿴�������û�
	 * @return ����list����
	 */
	List findBlackList();

	/**
	 * �޸���Ա״̬
	 * @param user
	 */
	void updateStatus(User user);
	
	/**
	 * �����û���������
	 * @param username
	 * @param password
	 * @return ���ص�ǰ��¼�û�
	 */
	User login(String username ,String password);
	
	
}
 