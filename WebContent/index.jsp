<%@page import="model.Message"%>
<%@page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>心情留言板</title>
<link rel="stylesheet" href="./css/SampleStyle.css">
</head>
<body>
	<%
		try (BufferedReader br = new BufferedReader(
				new FileReader("C:\\Users\\A\\Desktop\\javaweb\\bulletin-board\\bulletin-board\\record.txt"))) {
			String name;
			List<Message> list;
			list = (List<Message>) Collections.synchronizedList(new ArrayList());
			application.setAttribute("list", list);

			while ((name = br.readLine()) != null) {
				String createTime = br.readLine();

				String content = br.readLine();

				Message model = new Message(name, createTime, content);
				list.add(model);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	%>
	<div class="header">
		<img src="https://fakeimg.pl/100x60/" alt="">
		<h2 class="title">心情留言板</h2>
	</div>
	<div>
		<div >

			<c:if test="${empty  list}">
				<h2 class="pane">??..都還沒有人來留言</h2>
			</c:if>
			<c:if test="${ not empty list}">
				<div class="scroll_height">
					<c:forEach items="${list}" var="item">
						<div class="pane">
							<p class="say">${item.name}於${item.createTime}說：</p>
							<p class="msg_row">${item.content}</p>
						</div>

						<div class="pane">
							<div class="border_row"></div>
							<div class="border_row"></div>
						</div>
					</c:forEach>
				</div>
			</c:if>
		</div>

		<button onclick="window.location.href='./input.jsp'" class="redirect">留言</button>
	</div>

	<!-- 	<script src="./js/main.js"></script>  -->
</body>
</html>