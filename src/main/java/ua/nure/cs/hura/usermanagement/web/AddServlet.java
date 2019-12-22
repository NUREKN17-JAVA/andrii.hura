package ua.nure.cs.hura.usermanagement.web;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.nure.cs.hura.usermanagement.db.DaoFactory;
import ua.nure.cs.hura.usermanagement.db.DatabaseException;
import ua.nure.cs.hura.usermanagement.domain.User;

public class AddServlet extends EditServlet{
	@Override
	protected void processUser(User user) throws DatabaseException {
		DaoFactory.getInstance().getUserDao().create(user);
	}


	@Override
	protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/add.jsp").forward(req,resp);
	}
}