package com.hui.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hui.dao.RecordDAO;
import com.hui.dao.UserDAO;
import com.hui.model.*;

/**
 * ���黹��
 * */
@WebServlet(name = "BookAction", urlPatterns = {"/BookAction"})
public class BookAction extends HttpServlet {

	/**
	 * Processes requests for both HTTP
	 * <code>GET</code> and
	 * <code>POST</code> methods.
	 * <p/>
	 * @param request  servlet request
	 * @param response servlet response
	 * <p/>
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("gbk");
		response.setContentType("text/html;charset=GBK");
		String action = request.getParameter("action");
		String ISBN = request.getParameter("ISBN");
		String userName = request.getParameter("username");
		String adminName = ((Admin) request.getSession().getAttribute("adminInfo")).getName();
		RecordDAO rd = new RecordDAO();
		
		if (action.equals("return")) {
			float fine= rd.returnBook(ISBN, userName); //����ʱ�����Ƿ�����ڽ��ɷ���
			if (fine == 0) {
				request.getRequestDispatcher("actionResult.jsp?result=�ɹ�&action=����").forward(request, response);
			}else if(fine > 0){
				
				request.getRequestDispatcher("fine.jsp?fine="+fine).forward(request, response);
			} else {
				request.getRequestDispatcher("actionResult.jsp?result=ʧ�ܣ����Ϻ����������&action=����").forward(request, response);
			}
		} else if (action.equals("borrow")) {
			if (rd.borrowBook(ISBN, userName, adminName) ) {
				request.getRequestDispatcher("actionResult.jsp?result=�ɹ�&action=����").forward(request, response);
			} else {
				request.getRequestDispatcher("actionResult.jsp?result=����ֻ֪�����飬�ֲ���ûŮ���ѣ�&action=����").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("actionResult.jsp?result=���Ϻ����������&action=������").forward(request, response);
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP
	 * <code>GET</code> method.
	 * <p/>
	 * @param request  servlet request
	 * @param response servlet response
	 * <p/>
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP
	 * <code>POST</code> method.
	 * <p/>
	 * @param request  servlet request
	 * @param response servlet response
	 * <p/>
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 * <p/>
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}
