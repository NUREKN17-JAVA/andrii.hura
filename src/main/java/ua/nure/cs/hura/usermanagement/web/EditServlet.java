package ua.nure.cs.hura.usermanagement.web;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.nure.cs.hura.usermanagement.db.DaoFactory;
import ua.nure.cs.hura.usermanagement.db.DatabaseException;
import ua.nure.cs.hura.usermanagement.domain.User;


public class EditServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("okButton")!=null) {
			doOk(req,resp);
		} else if(req.getParameter("cancelButton")!=null) {
			doCancel(req,resp);
		} else {
			showPage();
		}
	}

	private void showPage() {
		// TODO Auto-generated method stub

	}

	private void doCancel(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub

	}

	private void doOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = getUser(req);
		try {
			processUser(user);
		} catch (DatabaseException e) {
			e.printStackTrace();
			new ServletException(e);
		}
		req.getRequestDispatcher("/browse").forward(req,resp);
	}

	private void processUser(User user) throws DatabaseException {
		DaoFactory.getInstance().getUserDao().update(user);
		}

	private User getUser(HttpServletRequest req) {
		User user = new User();
		String idStr = req.getParameter("id");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String dateStr = req.getParameter("date");
		if(idStr != null) {
			user.setId(new Long(idStr));
		}
		user.setFirstName(firstName);
		user.setLastName(lastName);
		try {
			user.setDateOfBirth(DateFormat.getDateInstance().parse(dateStr));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return user;
	}
}
