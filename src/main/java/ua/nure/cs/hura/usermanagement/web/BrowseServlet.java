package ua.nure.cs.hura.usermanagement.web;

import ua.nure.cs.hura.usermanagement.db.DaoFactory;
import ua.nure.cs.hura.usermanagement.db.DatabaseException;


import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BrowseServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		browse(req, resp);
	}

	private void browse(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
		// TODO Auto-generated method stub
		Collection users;
		try {
			users = DaoFactory.getInstance().getUserDao().findAll();
			req.getSession().setAttribute("users", users);
			req.getRequestDispatcher("/browse.jsp").forward(req,resp);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}
	}


}