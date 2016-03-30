<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${pageContext.request.contextPath}/index">MicroBlog</a>
    </div>
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav navbar-right">
    	<c:choose>
    		<c:when test="${! empty user }">
    			<li><a href="${pageContext.request.contextPath}/u/${ user.id }">me</a></li>
        	<li><a href="${pageContext.request.contextPath}/logout">logout</a></li>
    		</c:when>
    		<c:otherwise>
    			<li><a href="${pageContext.request.contextPath}/login">login</a></li>
        	<li><a href="${pageContext.request.contextPath}/register">register</a></li>
    		</c:otherwise>
    	</c:choose>
     	</ul>
    </div><!--/.navbar-collapse -->
  </div>
</nav>