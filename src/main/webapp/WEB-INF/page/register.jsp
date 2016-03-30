<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Index</title>
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.css" />
	<style type="text/css">
	  body {
	    padding-top: 80px;
	    padding-bottom: 20px;
	  }
	</style>
  </head>
  <body>
		<c:import url="header.jsp" />
    <div class="container">
      <div class="row">
      <div class="col-md-6 col-md-offset-2">
      <form class="form-horizontal" role="form" method="post">
        <c:if test="${! empty message }">
        <div class="alert alert-warning" role="alert" id="alert1">${message }</div>
        </c:if>
        <div class="form-group">
			    <label for="username" class="col-sm-2 control-label">用户名</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="username" name="username" placeholder="用户名" required>
			    </div>
			  </div>
			  <div class="alert alert-warning" role="alert" id="alert2" style="display:none"></div>
			  <div class="form-group">
			    <label for="password" class="col-sm-2 control-label">密码</label>
			    <div class="col-sm-10">
			      <input type="password" class="form-control" id="password" name="password" placeholder="密码" required>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="repeatPassword" class="col-sm-2 control-label">重复密码</label>
			    <div class="col-sm-10">
			      <input type="password" class="form-control" id="repeatPassword" name="repeatPassword" placeholder="再次输入密码" required>
			    </div>
			  </div>
			  <div class="alert alert-warning" role="alert" id="alert3" style="display:none"></div>
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <button type="submit" class="btn btn-success" id="registerButton" disabled>注册</button>
			    </div>
			  </div>
      </form>
			</div>
			</div>
    </div>
	<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.js"></script>
	<script>
		$(function(){
			$("#username").change(function() {
				$.get("${pageContext.request.contextPath}/checkUsername", { username: $("#username").val() }, function(res) {
					if (res > 0){
						$("#alert2").show().html("用户已存在");
						$("#registerButton").prop("disabled", true);
					} else {
						$("#alert2").hide();
						$("#registerButton").prop("disabled", false);
					}
				});
			});
			$("#password, #repeatPassword").keyup(function(){
				if ($("#password").val() != $("#repeatPassword").val()) {
					$("#alert3").show().html("密码输入不匹配");
					$("#registerButton").prop("disabled", true);
				} else {
					$("#alert2").hide();
					$("#registerButton").prop("disabled", false);
				}
			});
			$("form").submit(function(){
				if ($("#password").val() != $("#repeatPassword").val()) {
					$("#alert3").show().html("密码输入不匹配");
					return false;
				}
				return true;
			});
		});
	</script>
  </body>
</html>