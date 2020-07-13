package controller;

import static util.CommonUtil.*;

import java.io.Reader;
import java.io.Writer;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Message;
import service.BulletinBoardService;

@WebServlet("/BulletinBoard")
public class BulletinBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BulletinBoardService service;
	
	public BulletinBoardServlet() {
		service = new BulletinBoardService();
	}

	// �憓�
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		StringBuilder in = new StringBuilder();
		try (
			Reader reader = request.getReader();
			Writer writer = response.getWriter()
		) {
			request.setCharacterEncoding("UTF-8");
			int c;
			while ((c = reader.read()) != -1) {
				in.append((char) c);
			}
			Message message = GSON.fromJson(in.toString(), Message.class);
			boolean result = service.add(message);
			response.setContentType(JSON_WITH_UTF8);
			writer.write("{\"result\":" + result);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType(JSON_WITH_UTF8);
		try (Writer writer = response.getWriter()) {
			List<Message> list = service.findAll();
			writer.append(GSON.toJson(list));
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
