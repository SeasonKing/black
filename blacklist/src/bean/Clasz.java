package bean;

import java.util.List;

/**
 * @author 孙继杰2016-09-01
 * 创建班级实体类
 *
 */
public class Clasz {
 
	private int id;//主键
	
	private String name;//班级名
	
	private String t_name;//班主任名
	
	private List<Student> studtList;//班级的学生
	
	
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
