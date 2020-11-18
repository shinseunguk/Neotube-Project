<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
				<div class="card card-signin my-5">
					<div class="card-body">
						<h2 class="card-title text-center">ID찾기</h2>
						<div class="form-label-group">
							<br>
							<br>
							<h2>
								<label for="inputEmail">${vo.name}</label>
							</h2>
							님의
						</div>
						<br>
						<div class="form-label-group">
							<br>
							<br> <label for="inputEmail">아이디 : </label>
							<h2>${vo.user_id}</h2>
						</div>
						<br> <br> <br>
						<h1>
							<a href="logn.do"> 로그인</a>
						</h1>





					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>