<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<%@ include file="/WEB-INF/common/lib.jsp"%>
<%@ include file="/WEB-INF/common/querylib.jsp" %>
<title>회원 리스트</title>

<script>
	$(function(){
		//회원을 클릭 했을 경우(상세페이지 이동)
		$(".user").on("click",function(){
			var userid = $(this).data("userid");
			$("#userid").val(userid);
			$("#frm").attr("action", "/user/userDetail");
			$("#frm").submit();
		});
		
		//페이지에 출력될 갯수 바꿀경우
		$("#perPageNum").change(function(){
			var page = $(this).val();
			$("#pageSize").val(page);
			$("#frm").attr("action", "/user/pagingUser");
			$("#frm").submit();
		});
		$("#perPageNum").val("${pagesize}")
		
		//검색 타입 바꿀경우
		$("#searchType").change(function(){
			type = $(this).val();
		});
		
		//검색을 눌렀을 때
		$("#searchBtn").on("click", function(){
			var keyword = $("#word").val();
			var page = $("#perPageNum").val();
			type = $("#searchType").val();
			$("#pageSize").val(page);
			$("#search").val(keyword);
			$("#type").val(type);
			$("#frm").attr("action", "/user/pagingUser");
			$("#frm").submit();
		});
		$("#searchType").val("${type}")
		$("#word").val("${keyword}")
	});
</script>

</head>
<body class="hold-transition sidebar-mini">
	<div class="wrapper">

<%@ include file="/WEB-INF/common/sidebar.jsp" %>
	<form id="frm">
		<input type="hidden" id="userid" name="userid" value=""/>
		<input type="hidden" id="pageSize" name="pageSize" value=""/>
		<input type="hidden" id="type" name="type" value=""/>
		<input type="hidden" id="search" name="search" value=""/>
	</form>
		<div id="if_list_div" style="position: relative; padding: 0; overflow: hidden; height: 750px;">
			<div class="content-wrapper" style="min-height: 584px;">
				<!-- Content Header (Page header) -->
				<section class="content-header">
					<div class="container-fluid">
						<div class="row md-2">
							<div class="col-sm-6">
								<h1>회원리스트</h1>
							</div>
							<div class="col-sm-6">
								<ol class="breadcrumb float-sm-right">
									<li class="breadcrumb-item">회원리스트</li>
									<li class="breadcrumb-item">목록</li>
								</ol>
							</div>
						</div>
					</div>
				</section>
				<!-- Main content -->
				<section class="content">
					<div class="card">
						<div class="card-header with-border">
							<button type="button" class="btn btn-primary" onclick="location.href='/user/registUser';">회원등록</button>
							<div id="keyword" class="card-tools" style="width: 550px;">
								<div class="input-group row">
									<!-- sort num -->
									<select class="form-control col-md-3" name="perPageNum" id="perPageNum">
										<option value="" selected="selected">정렬개수</option>
										<option value="3">3개씩</option>
										<option value="5">5개씩</option>
										<option value="7">7개씩</option>
									</select>
									<!-- search bar -->
									<select class="form-control col-md-3" name="searchType" id="searchType">
										<option value="">검색구분</option>
										<option value="i">아이디</option>
										<option value="n">이름</option>
										<option value="a">별명</option>
									</select> <input class="form-control" type="text" name="keyword" id="word" placeholder="검색어를 입력하세요." value=""> <span class="input-group-append">
										<button class="btn btn-primary" type="button" id="searchBtn" data-card-widget="search">
											<i class="fa fa-fw fa-search"></i>
										</button>
									</span>
									<!-- end : search bar -->
								</div>
							</div>
						</div>
						<div class="card-body" style="text-align: center;">
							<div class="row">
								<div class="col-sm-12">
									<table class="table table-bordered">
										<tbody>
											<tr>
												<th>아이디</th>
												<th>이름</th>
												<th>별명</th>
												<th>도로주소</th>
												<th>상세주소</th>
												<th>등록날짜</th>
												<!-- yyyy-MM-dd  -->
											</tr>
											<c:forEach items="${userList }" var="users">
												<tr class="user" data-userid="${users.userid }">
													<td>${users.userid }</td>
													<td>${users.usernm }</td>
													<td>${users.alias }</td>
													<td>${users.addr1 } </td>
													<td>${users.addr2 } </td>
													<td><fmt:formatDate value="${users.reg_dt }" pattern="yyyy-MM-dd"/></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<!-- col-sm-12 -->
							</div>
							<!-- row -->
						</div>
						<!-- card-body -->
						<div class="card-footer">
							<nav aria-label="member list Navigation">
								<ul class="pagination justify-content-center m-0">
									<li class="page-item"><a class="page-link" href="/user/pagingUser?page=1&pageSize=${pageVo.getPageSize()}"><i class="fas fa-angle-double-left"></i></a></li>
									
									<c:choose>
										<c:when test="${pageVo.getPage()-1  <= 1}">
											<li class="page-item"><a class="page-link" href="/user/pagingUser?page=1&pageSize=${pageVo.getPageSize()}"><i class="fas fa-angle-left"></i></a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link" href="/user/pagingUser?page=${pageVo.getPage()-1}&pageSize=${pageVo.getPageSize()}"><i class="fas fa-angle-left"></i></a></li>
										</c:otherwise>
									</c:choose>
									
									<c:forEach begin="1" end="${pagination }" var="i">
										<c:choose>
											<c:when test="${pageVo.getPage() == i}">
												<li class="page-item active"><a class="page-link" href="#">${i }</a></li>
											</c:when>
											<c:otherwise>
												<li><a class="page-link" href="/user/pagingUser?page=${i }&pageSize=${pageVo.getPageSize()}"><span>${i }</span></a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									
									<c:choose>
										<c:when test="${pageVo.getPage() ==  pagination }">
											<li class="page-item"><a class="page-link" href="/user/pagingUser?page=${pagination }&pageSize=${pageVo.getPageSize()}"><i class="fas fa-angle-right"></i></a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link" href="/user/pagingUser?page=${pageVo.getPage()+1 }&pageSize=${pageVo.getPageSize()}"><i class="fas fa-angle-right"></i></a></li>
										</c:otherwise>
									</c:choose>
									<li class="page-item"><a class="page-link" href="/user/pagingUser?page=${pagination }&pageSize=${pageVo.getPageSize()}"><i class="fas fa-angle-double-right"></i></a></li>
								</ul>
							</nav>
						</div>
						<!-- card-footer -->
					</div>
					<!-- card  -->
				</section>
			</div>
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
	<%@ include file="/WEB-INF/common/querylib.jsp" %>
</body>
</html>







