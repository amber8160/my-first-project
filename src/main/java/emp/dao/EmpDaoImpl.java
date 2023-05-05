package emp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import emp.vo.Emp;

//是一個實作類別的命名方式
public class EmpDaoImpl{
	public int insert(Emp emp) {
		String sql = "Insert into EMP values(?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///EXAMPLE", "root", "password");
				PreparedStatement psmt = conn.prepareStatement(sql);) {
			psmt.setInt(1, emp.getEmpno());
			psmt.setString(2, emp.getEname());
			psmt.setString(3, emp.getJob());
			psmt.setInt(4, emp.getMgr());
			psmt.setTimestamp(5, emp.getHiredate());
			psmt.setDouble(6, emp.getSal());
			psmt.setDouble(7, emp.getComm());
			psmt.setInt(8, emp.getDeptno());
			return psmt.executeUpdate();
		} catch (Exception e) {
		}
		return -1;
	}

	public int deleteByEmpno(Integer empno) {
		String sql = "Delete from EMP where empno = ?";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///EXAMPLE", "root", "password");
				PreparedStatement psmt = conn.prepareStatement(sql);) {
			psmt.setInt(1, empno);
			return psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int updateByEmpno(Emp emp) {
		String sql = "Update EMP set Ename = ?, Job = ?, Mgr = ?, Hiredate = ?, Sal = ?, Comm = ?, Deptno = ? where EMPNO = ?";
		try (Connection conn = DriverManager.getConnection("jdbc:mysql:///EXAMPLE", "root", "password");
				PreparedStatement psmt = conn.prepareStatement(sql);) {
			psmt.setString(1, emp.getEname());
			psmt.setString(2, emp.getJob());
			psmt.setInt(3, emp.getMgr());
			psmt.setTimestamp(4, emp.getHiredate());
			psmt.setDouble(5, emp.getSal());
			psmt.setDouble(6, emp.getComm());
			psmt.setInt(7, emp.getDeptno());
			psmt.setInt(8, emp.getEmpno());
			return psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public Emp selectByEmpno(Integer empno) {
		String sql = "Select * from EMP where empno = ?";
		try (Connection conn = DriverManager
				.getConnection("jdbc:mysql:///EXAMPLE", "root", "password");
				PreparedStatement psmt = conn.prepareStatement(sql);) {
			psmt.setInt(1, empno);
			try (ResultSet rs = psmt.executeQuery()) {
				if (rs.next()) {
					Emp emp = new Emp();
					emp.setEmpno(rs.getInt(1));
					emp.setEname(rs.getString(2));
					emp.setJob(rs.getString(3));
					emp.setMgr(rs.getInt(4));
					emp.setHiredate(rs.getTimestamp(5));
					emp.setSal(rs.getDouble(6));
					emp.setComm(rs.getDouble(7));
					emp.setDeptno(rs.getInt(8));
					return emp;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Emp> selectAll() {
		String sql = "Select * from EMP";
//		Class.forName("com.mysql.cj.jdbc.Driver");
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EXAMPLE?serverTimezone=Asia/Taipei", "root", "password");
				PreparedStatement psmt = conn.prepareStatement(sql);
				ResultSet rs = psmt.executeQuery()) {
			List<Emp> list = new ArrayList<>();

			while (rs.next()) {
				Emp emp = new Emp();
				emp.setEmpno(rs.getInt(1));
				emp.setEname(rs.getString(2));
				emp.setJob(rs.getString(3));
				emp.setMgr(rs.getInt(4));
				emp.setHiredate(rs.getTimestamp(5));
				emp.setSal(rs.getDouble(6));
				emp.setComm(rs.getDouble(7));
				emp.setDeptno(rs.getInt(8));
				list.add(emp);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		EmpDaoImpl empDaoImpl = new EmpDaoImpl();
		for (Emp emp : empDaoImpl.selectAll()) {
			System.out.println(emp.getEname());
		}
	}

}
