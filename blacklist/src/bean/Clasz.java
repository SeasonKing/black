package bean;

import java.util.List;

/**
 * @author ��̽�2016-09-01
 * �����༶ʵ����
 *
 */
public class Clasz {
 
	private int id;//����
	
	private String name;//�༶��
	
	private String t_name;//��������
	
	private List<Student> studtList;//�༶��ѧ��
	
	
	public Clasz(){
		
	}

	public Clasz(int id, String name, String t_name, List<Student> studtList) {
		super();
		this.id = id;
		this.name = name;
		this.t_name = t_name;
		this.studtList = studtList;
	}

	@Override
	public String toString() {
		return "Clasz [id=" + id + ", name=" + name + ", t_name=" + t_name
				+ ", studtList=" + studtList + "]";
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

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}

	public List<Student> getStudtList() {
		return studtList;
	}

	public void setStudtList(List<Student> studtList) {
		this.studtList = studtList;
	}
	
	
	

	
	
	
}
