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
	    padding-top: 50px;
	    padding-bottom: 20px;
	  }
	</style>
  </head>
  <body>
		<c:import url="header.jsp" />
    <div class="jumbotron">
      <div class="container">
        <h1>Hello, world!</h1>
        <p>Welcome to microblog</p>
        <c:if test="${ empty user }">
        <p>
        	<a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/login" role="button">Login &raquo;</a>
        	or
        	<a class="btn btn-success btn-lg" href="${pageContext.request.contextPath}/register" role="button">Register &raquo;</a>
        </p>
        </c:if>
        <c:if test="${ !empty user and me }">
        	<form class="form-inline" id="addPost" role="form" method="post" action="${pageContext.request.contextPath}/p/add">
        		<div class="form-group">
        			<input type="text" class="form-control" style="width: 300px;"
        				id="microBlogText" name="body" 
        				placeholder="说点什么吧"
        				maxlength="140">
        		</div>
        		<button type="submit" class="btn btn-default" id="sendBodyButton" disabled>发表</button>
        	</form>
        </c:if>
      </div>
    </div>
    
    <div class="container">
    	<div class="row">
      <c:forEach var="item" items="${posts }" varStatus="i">
      	<div class="col-md-4">
      		<h2><a href="${pageContext.request.contextPath}/u/${ item.user.id }">${ item.user.username }</a> 说 </h2>
      		<p style="word-break: break-all; word-wrap:break-word;">${ item.body }</p>
      		<p class="label label-info">${ item.createDate }</p>
      		<br />
      		<br />
      		<c:if test="${ !empty user and me }">
      		  <form class="form" method="post" action="${pageContext.request.contextPath}/p/delete/${item.id }">
      		  	<button type="submit" class="btn btn-danger">删除</button>
      		  </form>
      		</c:if>
      	</div>
      </c:forEach> 
      </div>
      <hr>

      <footer>
        <p>&copy; Company 2016</p>
      </footer>
    </div>
	<script src="http://cdn.bootcss.com/jquery/2.2.1/jquery.js"></script>
	<script src="http://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#microBlogText").keyup(function(){
				$("#sendBodyButton").prop("disabled", $(this).val().length == 0);
			});
			$("#addPost").submit(function(){
				return $("#microBlogText").val().length > 0;
			});
		});
	</script>
  </body>
</html>