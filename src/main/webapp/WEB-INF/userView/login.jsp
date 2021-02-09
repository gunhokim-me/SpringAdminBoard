<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<%@ include file="/WEB-INF/common/lib.jsp" %>
<%@ include file="/WEB-INF/common/querylib.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/js-cookie@rc/dist/js.cookie.min.js"></script>
<script>
 function getCookieValue(cookieStr, cookieName){
	 var arr = document.cookie.split("; ");
	 for(var i = 0; i < arr.length; i++){
		 if(str[0] == cookieName){
			 return str[1];
		 }
	 }
	 return "";
 }
 

	function addCookie(cookieName, cookieValue, expires) {
		var dt = new Date(); 
		dt.setDate(dt.getDate() + parseInt(expires));
		document.cookie = cookieName + "=" + cookieValue + "; " + "path=/; expires=" + dt.toGMTString();
	}

	function deleteCookie(cookieName) {
		addCookie(cookieName, "", -1);
	}
	$(function() {
		$("#userid").val(Cookies.get("userid"))
		if (Cookies.get("rememberme") == "Y") {
			$("#rememberme").attr("checked", true);
		}

		$("#login").on("click", function() {
			if ($('#rememberme').is(":checked")) {
				Cookies.set("userid", $("#userid").val());
				Cookies.set("rememberme", "Y");

			} else {
				Cookies.remove("userid");
				Cookies.remove("rememberme");
			}
			$("#frm").submit();
		});
	});
</script>

<link href="../../css/login.css" rel="stylesheet">
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<b>관리자 로그인</b>
		</div>
		<!-- /.login-logo -->
		<div class="card">
			<div class="card-body login-card-body">
				<p class="login-box-msg">Sign in to start your session</p>

				<form action="${cp}/user/login" method="post">
					<div class="form-group has-feedback">
						<input type="text" class="form-control"  id ="userid" name="userid" placeholder="아이디를 입력하세요."> 
						<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
					</div>
					<div class="form-group has-feedback">
						<input type="password" class="form-control" name="pass" id="pass" value="sallyPass"	placeholder="패스워드를 입력하세요." >
						<span	class="glyphicon glyphicon-lock form-control-feedback"></span>
					</div>
					<div class="row">
						<div class="col-sm-8">
							<div class="checkbox icheck">
								<label> <input type="checkbox" name="rememberme" id="rememberme" value="rememberme"> Remember Me</label>
							</div>
						</div>
						<!-- /.col -->
						<div class="col-sm-4">
							<button type="submit" id="login" class="btn btn-primary btn-block btn-flat">로그인</button>
						</div>
						<!-- /.col -->
					</div>
				</form>

			</div>
			<!-- /.login-box-body -->
		</div>
	</div>
	<!-- /.login-box -->
</body>
</html>








