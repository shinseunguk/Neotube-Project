<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<!-- CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
	$(function() {
		$("#b1").click(function() {
		    
		    user_id = $("#user_id").val()
			pw = $("#pw").val()
			$.ajax({
				url :"select1.do",
				data: {
					user_id : user_id,
					pw : pw
					
				},
				success :function(result){
					if (result == "1") {
						
						alert("�α��μ���");
						
						location.href='index.jsp'
					}else{
					  alert("���̵� ��й�ȣ Ȯ�����ּ���");
					
				
				}//else
				
			}//success

		})

	})
	})
</script>
</head>
<body>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
				<div class="card card-signin my-5">
					<div class="card-body">
						<h2 class="card-title text-center">�α���</h2>
						
							<div class="form-label-group">
								<input type="text" id="user_id" nmae="user_id"
									class="form-control" placeholder="Email address" required
									autofocus> <label for="inputEmail">���̵�</label>
							</div>
							<br>
							<div class="form-label-group">
								<input type="password" id="pw" name="pw" class="form-control"
									placeholder="Password" required> <label
									for="inputPassword">��й�ȣ</label>
							</div>




							<button id="b1"
							name="b1" class="btn btn-lg btn-primary btn-block text-uppercase"
								type="submit">�α���</button>
							<hr class="my-4">
							<form action="Member.jsp">
							<button class="btn btn-lg btn-google btn-block text-uppercase"
								type="submit" >
								<i class="fab fa-google mr-2"></i> ȸ������
							</button>
							</form>
							<form action="id.jsp">
							<button class="btn btn-lg btn-facebook btn-block text-uppercase"
								type="submit">
								<i class="fab fa-facebook-f mr-2"></i> IDã��
							</button>
							</form>
							<form action="pw.jsp">
							<button class="btn btn-lg btn-facebook btn-block text-uppercase"
								type="submit">
								<i class="fab fa-facebook-f mr-2"></i> PWã��
							</button>
							</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</body>
</html>