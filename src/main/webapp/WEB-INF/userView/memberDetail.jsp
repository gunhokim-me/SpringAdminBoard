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
<%@ include file="/WEB-INF/common/lib.jsp"%>
<%@ include file="/WEB-INF/common/querylib.jsp" %>
<title>회원 정보</title>

<!-- Font Awesome Icons -->
<link rel="stylesheet" href="./resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="./resources/bootstrap/dist/css/adminlte.min.css">

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
$(function(){
	//수정 버튼
	$("#modifyBtn").on("click",function(){
		$("#frm").attr("method" , "POST");
		$("#frm").attr("action" , "/user/userModify" );
		$("#frm").submit();
	});
	
	//삭제 버튼
	$("#deleteBtn").on("click",function(){
		$("#frm").attr("action" , "/user/deleteUser");
		$("#frm").submit();
	});
	
	//취소버튼
	$("#cancelBtn").on("click",function(){
		$("#frm").attr("action" , "/user/pagingUser");
		$("#frm").submit();
	});
	
});
</script>

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
							<b>회원 정보</b>
						</div>
						<!-- form start -->
						<div class="card">
							<div class="register-card-body">
								<form role="form" id="frm" class="form-horizontal" >
									<input type="hidden" name="userid" value="${vo.userid }">
									<div class="input-group mb-3">
										<div class="mailbox-attachments clearfix" style="text-align: center; width:100%;">
											<div class="mailbox-attachment-icon has-img" id="pictureView" style="border: 1px solid green; height: 200px; width: 140px; margin: 0 auto;">
												<img src="${cp }/user/profile?userid=${vo.userid}" id="pictureViewImg" style="width:100%; height:100%;"/>
											</div>
											<!-- <div class="mailbox-attachment-info">
												<div class="input-group input-group-sm">
													<input id="picture" class="form-control" type="file" name="picture" accept=".gif, .jpg, .png" style="height:37px;"/>
												</div>
											</div> -->
										</div>
										<br />
									</div>
									
									<div class="form-group row">
										<label for="id" class="col-sm-3" style="font-size: 0.9em;">
											<span style="color: red; font-weight: bold;">*</span>아이디
										</label>
										<div class="col-sm-9 input-group-sm">
											<span class="input-group-append-sm">${vo.userid }</span>
										</div>
									</div>
									
									<div class="form-group row">
										<label for="pwd" class="col-sm-3" style="font-size: 0.9em;">
											<span style="color: red; font-weight: bold;">*</span>패스워드</label>
										<div class="col-sm-9 input-group-sm">
											<span class="input-group-append-sm">${vo.pass }</span>
										</div>
									</div>
									
									<div class="form-group row">
										<label for="name" class="col-sm-3" style="font-size: 0.9em;">
											<span style="color: red; font-weight: bold;">*</span>이 름
										</label>
										<div class="col-sm-9 input-group-sm">
											<span class="input-group-append-sm">${vo.usernm }</span>
										</div>

									</div>
									<div class="form-group row">
										<label for="alias" class="col-sm-3" style="font-size: 0.9em;">별명</label>
										<div class="col-sm-9 input-group-sm">
											<span class="input-group-append-sm">${vo.alias }</span>
										</div>
									</div>
									<div class="form-group row">
										<label for="addr1" class="col-sm-3 control-label">주소</label>
										<div class="col-sm-3 input-group-sm">
											<span class="input-group-append-sm">${vo.addr1 }</span>
										</div>
										<div class="col-sm-3 input-group-sm">
											<span class="input-group-append-sm">${vo.addr2 }</span>	
										</div>
										
										<div class="col-sm-2 input-group-sm">
											<span class="input-group-append-sm">${vo.zipcode }</span>
										</div>
										<!-- <div class="col-sm-1 input-group-sm">
											<span class="input-group-append-sm">
												<button type="button" class="btn btn-info btn-sm btn-append">주소검색</button>
											</span>
										</div> -->
									</div>
									
									<div class="card-footer">
										<div class="row">
											<div class="col-sm-6">
											<div class="container">
											  <button type="button" class="btn btn-info"   data-toggle="modal" data-target="#myModal">삭제</button>
											  <!-- Modal -->
											  <div class="modal fade" id="myModal" role="dialog">
											    <div class="modal-dialog">
											    
											      <!-- Modal content-->
											      <div class="modal-content">
											        <div class="modal-header">
											          <button type="button" class="close" data-dismiss="modal">&times;</button>
											          <h4 class="modal-title">경고</h4>
											        </div>
											        <div class="modal-body">
											          <p>정말로 삭제 하시겠습니까?</p>
											        </div>
											        <div class="modal-footer">
											          <button type="button" class="btn btn-default"   id="deleteBtn" data-dismiss="modal">삭제</button>
											          <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
											        </div>
											      </div>
											      
											    </div>
											  </div>
												<button type="button"  id="modifyBtn" class="btn btn-warning">수정</button>
											</div>
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







