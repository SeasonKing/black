package service;

import bean.User;



/**
 * ��¼ע�����ӿ�
 * @author ��̽� 2016-08-25
 *
 */
public interface LoginService {

	/**
	 * ע���û�
	 * @param user ע����û���Ϣ
	 * @return true-ע��ɹ�  false-ע��ʧ��
	 */
	int register(User user);
	
	/**
	 * �û���¼
	 * @param user ��¼���û���Ϣ
	 * @return 0-��¼�ɹ�  1-���û�������  2-���û����벻��ȷ
	 */
	int login(User user);
	/**
	 * �û���¼
	 * @param username��Ҫ�����û��� ������
	 * @param password
	 * @return ��¼�ɹ����ص�ǰ��¼�û�����
	 */
	
	User login(String username,String password);
	
	
	
}
