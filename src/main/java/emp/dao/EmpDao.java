package emp.dao;

import java.util.List;

import emp.vo.Emp;

public interface EmpDao {
	int insert(Emp emp);

	int deleteByEmpno(Integer empno);

	int updataByEmpno(Emp empno);

	Emp selectByEmpno(Integer empno);

	List<Emp> selectAll() ;
}
