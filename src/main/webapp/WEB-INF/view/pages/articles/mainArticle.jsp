<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="row">
	<div class="col-sm-10 justify-content-sm-center blog-main">
	    <h2 class="blog-post-title">
	        <c:out value="${article.title}"/>
	    </h2>
	    <p class="blog-post-meta">
	        <c:out value="${article.date}"/> by
	        <a href="${pageContext.request.contextPath}/articles/byUser/${article.userAlias}">
	            <c:out value="${article.userAlias}"/>
	        </a>
	        <span class="badge badge-success rounded float-right">
	            <c:out value="${article.categoryName}"/>
	        </span>
	    </p>
	    ${article.text}
	</div>
</div>