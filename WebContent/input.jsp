<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/SampleStyle.css">
<title>心情留言板</title>
</head>
<body>
	<div class="header">
		<img src="https://fakeimg.pl/100x60/" alt="">
		<h2 class="title">心情留言板</h2>
	</div>
	<form action="MessageServlet" method="post">
		<div class="main" id="input-view">
			<div class="board">
				<div>
					<div class="mt10">
						<label for="name">名稱：</label> <input type="text" id="name"
							name="name">
					</div>
					<div class="mt10">
						<label for="content">留言：</label>
						<textarea name="content" id="content" cols="30" rows="10"></textarea>
					</div>
					<div class="btn-group">
						<input type="submit" value="留言" onclick="return checkEmpty()"></input>
						<input type="button" name="cancel" value="取消"
							onclick="window.location.href='./index.jsp'" />

					</div>
				</div>
			</div>
		</div>
	</form>
	<script>
		function checkEmpty() {
			let v = document.getElementById("name").value
			if (v === "") {
				alert("名稱： 不能空");
				return false;
			}
			v = document.getElementById("content").value
			if (v === "") {
				alert("留言： 不能空");
				return false;
			}
			return true;
		}
		function cancel() {
			window.location = "./index.jsp"
		}
	</script>
</body>
</html>