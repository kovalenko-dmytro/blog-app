<jsp:include page="/WEB-INF/view/layout.jsp">
	<jsp:param name="title" value="${user.alias}"/>
	<jsp:param name="content" value="/users/contentProfile"/>
</jsp:include>