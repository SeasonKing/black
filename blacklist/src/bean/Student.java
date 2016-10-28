package bean;

import java.util.Date;

/**
 * @author ��̽� 2016-09-01
 * ����ѧ��ʵ����
 */
public class Student {

	private int id;//����
	
	private String name;//ѧ������
	
	private int sex;//�Ա� 0-Ů  1-��
	
	private String code; //ѧ������
	
	private Date birth;//��������
	
	private int clasz_id;//�༶id
	
	
	public Student()
	{
		
	}

	public Student(int id, String name, int sex, String code, Date birth,
			int clasz_id) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.code = code;
		this.birth = birth;
		this.clasz_id = clasz_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public int getClasz_id() {
		return clasz_id;
	}

	public void setClasz_id(int clasz_id) {
		this.clasz_id = clasz_id;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", sex=" + sex
				+ ", code=" + code + ", birth=" + birth + ", clasz_id="
				+ clasz_id + "]";
	}
	
	
}
