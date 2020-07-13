package controller;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Message;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MessageServlet() {
		super();
		// TODO Auto-gesnerated constructor stub
	}

	private void writeToFile(HttpServletRequest request) {
		ServletContext application = request.getServletContext();
		List<Message> list;
		list = (List<Message>) application.getAttribute("list");
		String filename = "C:\\Users\\A\\Desktop\\javaweb\\bulletin-board\\bulletin-board\\record.txt";
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
			for (Message model : list) {
				bw.write(model.getName() + "\n");
				bw.write(model.getCreateTime() + "\n");
				bw.write(model.getContent() + "\n");

			}
			bw.flush();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private Message formDataToModel(HttpServletRequest request) {
		String createTime = new Date().toLocaleString();
		String name = request.getParameter("name");
		String content = request.getParameter("content");

		Message model = new Message(name, createTime, content);
		return model;
	}

	private void processAppData(HttpServletRequest request, Message model) {
		ServletContext application = request.getServletContext();
		List<Message> list;
		if (application.getAttribute("list") == null) {
			list = (List<Message>) Collections.synchronizedList(new ArrayList());
			application.setAttribute("list", list);
		} else {
			list = (List<Message>) application.getAttribute("list");
		}
		list.add(model);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		Message model = formDataToModel(request);
		processAppData(request, model);
		writeToFile(request);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
