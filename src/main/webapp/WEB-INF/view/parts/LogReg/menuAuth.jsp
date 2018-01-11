<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<li class="nav-item dropdown list-unstyled">
    <a class="nav-link dropdown-toggle" href="" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${sessionScope.userAlias}</a>
    <div class="dropdown-menu" aria-labelledby="dropdown01">
      <a class="dropdown-item" href="${pageContext.request.contextPath}/profile">Profile</a>
      <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
</li>