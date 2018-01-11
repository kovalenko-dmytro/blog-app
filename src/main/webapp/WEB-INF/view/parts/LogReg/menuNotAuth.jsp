<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/login" method="POST">
  <input class="form-control mr-1" type="text" name="loginEmail" placeholder="Email" aria-label="Login">
  <input class="form-control" type="password" name="loginPassword" placeholder="Password" aria-label="Login">
  <button type="submit" class="btn btn-success mx-2 px-2">Login</button>
</form>
<a href="register" type="button" class="btn btn-success mx-2 px-2">Register</a>



