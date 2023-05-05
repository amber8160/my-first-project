package emp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import emp.dao.EmpDaoImpl;
import emp.vo.Emp;

@WebServlet("/emp/getAll")
public class EmpGetAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EmpDaoImpl dao = new EmpDaoImpl();
		List<Emp> list = dao.selectAll();
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		resp.getWriter().write(jsonStr);
	}
}
