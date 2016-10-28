package dao;

import java.util.List;

import bean.Clasz;

/**
 * @author 孙继杰 2016-09-01
 *班级数据处理接口
 */
public interface ClaszDao {
	
	/**
	 * 添加班级
	 * 在新班级的学生集合中，有学生则将学生也加入库中
	 * @param cls
	 */
	void add(Clasz cls);

	/**
	 * 同过班级的id删除班级
	 * @param claszId 班级id
	 * @param isDelStud 是否可以关联删除学生 true-可以  false-不可以
	 * @return 是否删除成功 true-删除成功 
	 */
	boolean delete (int claszId,boolean isDelStud);
	
	/**
	 * 修改班级信息
	 * @param cls 
	 */
	void update(Clasz cls); 
	
	/**
	 * 查询所有班级
	 * @return
	 */
	List<Clasz> findAll();
	
	/**
	 * 通过班级id查询该班级（包括班级下 的学生）
	 * @param id 班级的id
	 * @return 
	 */
	Clasz findById(int id);
	
	/**
	 * 通过班级name查询该班级（包括班级下 的学生）支持模糊查询
	 * @param name
	 * @return
	 */
	List<Clasz> findByName(String name);
	
	
}
