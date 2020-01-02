package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeansJsp;
import dao.DaoUser;

@WebServlet("/saveUser")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUser daoUser = new DaoUser();

	public UserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			if (acao.equalsIgnoreCase("delete")) {
				daoUser.delete(user);
				RequestDispatcher view = request.getRequestDispatcher("/userRegistration.jsp");
				request.setAttribute("usuarios", daoUser.listar());
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("edit")) {

				BeansJsp beansJsp = daoUser.consultar(user);

				RequestDispatcher view = request.getRequestDispatcher("/userRegistration.jsp");
				request.setAttribute("user", beansJsp);
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher view = request.getRequestDispatcher("/userRegistration.jsp");
				request.setAttribute("usuarios", daoUser.listar());
				view.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/userRegistration.jsp");
				request.setAttribute("usuarios", daoUser.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			String name = request.getParameter("name");

			BeansJsp user = new BeansJsp();
			user.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			user.setLogin(login);
			user.setPassword(password);
			user.setName(name);

			try {
				
				if (id == null || id.isEmpty() && !daoUser.validarLogin(login)) {
					request.setAttribute("msg", "User already exist in system");
				}
				
				if (id == null || id.isEmpty() && daoUser.validarLogin(login)) {
					
					daoUser.save(user);
					
				} else if(id == null && id.isEmpty()){
					daoUser.update(user);
				}

				RequestDispatcher view = request.getRequestDispatcher("/userRegistration.jsp");
				request.setAttribute("usuarios", daoUser.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}