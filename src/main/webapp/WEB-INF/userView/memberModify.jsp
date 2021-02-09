<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<%@ include file="/WEB-INF/common/lib.jsp"%>
<%@ include file="/WEB-INF/common/querylib.jsp" %>
<script>
$(function() {
	//주소 찾기 api
	$("#searchAddr").on("click", function() {
		new daum.Postcode({
			oncomplete : function(data) {
				$("#addr1").val(data.address);
				$("#addr2").focus();
				$("#zipcode").val(data.zonecode);
			}
		}).open();
	});
	
	//필수항목이 비워져 있을 경우
	status = 0
	$("#searchAddr").on("click", function(){
		new daum.Postcode({
	        oncomplete: function(data) {
				$("#addr1").val(data.address);
				$("#addr2").focus();
				$("#zipcode").val(data.zonecode);
	        }
	    }).open();
	});
	$("#id").keydown(function(){
		if($("#id").val() == ""){
			status = 1;
		}
	})
	
	$("#pass").keydown(function(){
		if($("#pass").val() == ""){
			status = 1;
		}
	})
	
	$("#name").keydown(function(){
		if($("#name").val() == ""){
			status = 1;
		}
	})
	
	//수정 버튼 클릭시 작동
	$("#modifyBtn").on("click",function(){
		if($("#id").val() !="" && $("#pass").val()!="" && $("#name").val()!="")	status = 0;
		if(status ==1){
			alert("필수항목을 입력해 주세요.")
		}else{
			$("#frm").attr("method", "POST")
			$("#frm").attr("action", "/user/modifySubmit");
			$("#frm").submit();
		}
	});
	
	//취소버튼
	$("#cancelBtn").on("click",function(){
		$("#frm").attr("action" , "/user/userDetail?userid=${S_USER.userid}");
		$("#frm").submit();
	});
});

	
</script>

<title>회원 등록</title>

</head>
<body class="hold-transition sidebar-mini">
	<div class="wrapper">

<%@ include file="/WEB-INF/common/sidebar.jsp" %>


		<div id="if_list_div" style="position: relative; padding: 0; overflow: hidden;">
			<!-- Content Wrapper. Contains page content -->
			<div class="content-wrapper">

				<!-- Main content -->
				<section class="content register-page" style="height:100%;">
					<div class="container-fluid">
						<div class="login-logo">
							<b>회원 수정</b>
						</div>
						<!-- form start -->
						<div class="card">
							<div class="register-card-body">
								<form role="form" id="frm" class="form-horizontal" enctype="multipart/form-data">
									<div class="input-group mb-3">
										<div class="mailbox-attachments clearfix" style="text-align: center; width:100%;">
											<div class="mailbox-attachment-icon has-img" id="pictureView" style="border: 1px solid green; height: 200px; width: 140px; margin: 0 auto;">
												<img src="/user/profile?userid=${vo.userid}" name="userfile" id="pictureViewImg" style="width:100%; height:100%;"/>
											</div>
											<div class="mailbox-attachment-info">
												<div class="input-group input-group-sm">
													<input id="picture" class="form-control"
														   type="file" name="picture" accept=".gif, .jpg, .png" style="height:37px;"/>
												</div>
											</div>
										</div>
										<br/>
									</div>
									
									<div class="form-group row">
										<label for="id" class="col-sm-3" style="font-size: 0.9em;">
											<span style="color: red; font-weight: bold;">*</span>아이디
										</label>
										<div class="col-sm-9 input-group-sm">
											<span class="input-group-append-sm">${vo.userid }</span>
											<input name="userid" type="hidden" class="form-control" id="id" placeholder="회원 id" value="${vo.userid }">
										</div>
									</div>
									
									<div class="form-group row">
										<label for="pwd" class="col-sm-3" style="font-size: 0.9em;">
											<span style="color: red; font-weight: bold;">*</span>패스워드</label>
										<div class="col-sm-9 input-group-sm">
											<input class="form-control" name="pass" type="password" class="form-control" id="pass" placeholder="비밀번호" value="${vo.pass }" />
										</div>
									</div>
									
									<div class="form-group row">
										<label for="name" class="col-sm-3" style="font-size: 0.9em;">
											<span style="color: red; font-weight: bold;">*</span>이 름
										</label>
										<div class="col-sm-9 input-group-sm">
											<input class="form-control" name="usernm" type="text" id="name" placeholder="이름" value="${vo.usernm }" />
										</div>

									</div>
									<div class="form-group row">
										<label for="alias" class="col-sm-3" style="font-size: 0.9em;">별명</label>
										<div class="col-sm-9 input-group-sm">
											<input class="form-control" name="alias" type="text" id="alias" placeholder="별명" value =${vo.alias }>
										</div>
									</div>
									<div class="form-group row">
										<label for="addr1" class="col-sm-3 control-label">주소</label>
										<div class="col-sm-3 input-group-sm">
											<input name="addr1" type="text" class="form-control" id="addr1" placeholder="주소" readonly value="${vo.addr1 }">
										</div>
										<div class="col-sm-3 input-group-sm">
											<input name="addr2" type="text" class="form-control" id="addr2" placeholder="상세주소" value="${vo.addr2 }">	
										</div>
										
										<div class="col-sm-2 input-group-sm">
											<input name="zipcode" type="text" class="form-control" id="zipcode" placeholder="우편번호" readonly value="${vo.zipcode }">
										</div>
										<div class="col-sm-1 input-group-sm">
											<span class="input-group-append-sm">
												<button type="button" id="searchAddr" class="btn btn-info btn-sm btn-append">주소검색</button>
											</span>
										</div>
									</div>
									
									<div class="card-footer">
										<div class="row">
											<div class="col-sm-6">
												<button type="button" id="modifyBtn" class="btn btn-info">수정</button>
											</div>

											<div class="col-sm-6">
												<button type="button" id="cancelBtn" class="btn btn-default float-right">&nbsp;&nbsp;&nbsp;취 &nbsp;&nbsp;소&nbsp;&nbsp;&nbsp;</button>
											</div>

										</div>
									</div>
								</form>
							</div>
							<!-- register-card-body -->
						</div>
					</div>
				</section>
				<!-- /.content -->
			</div>
			<!-- /.content-wrapper -->
		</div>
	</div>

	<!-- Main Footer -->
	<footer class="main-footer">
		<!-- To the right -->
		<div class="float-right d-none d-sm-inline">Anything you want</div>
		<!-- Default to the left -->
		<strong>Copyright &copy; 2014-2019 <a href="https://adminlte.io">AdminLTE.io</a>.
		</strong> All rights reserved.
	</footer>
	</div>
	<!-- ./wrapper -->


	<script>
		$(document).ready(function(){
			// picture input의 파일 변경시 이벤트 
			$("#picture").change(function(){
			   readURL(this);
			});
		});
		
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
			  
				reader.onload = function (e) {
					$('#pictureViewImg').attr('src', e.target.result);  
				}
				reader.readAsDataURL(input.files[0]);
			}
		}
			 
		
	</script>
</body>
</html>







