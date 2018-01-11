<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="row">
	<div class="col-sm-12 blog-main">
	<c:if test="${not empty errorMessage}">
        <p class="text-danger">${errorMessage}</p>
    </c:if>
    <c:if test="${not empty sendMessage}">
        <p class="text-success">${sendMessage}</p>
    </c:if>
        <form action="${pageContext.request.contextPath}/contacts" method="POST">
            <div class="form-group row justify-content-center">
                <label for="inputEmail" class="col-sm-4 col-form-label">Email address</label>
                <div class="col-sm-8">
                    <input type="email" id="inputEmail" name="inputEmail" class="form-control" placeholder="Email address" required autofocus>
                </div>
            </div>
            <div class="form-group row justify-content-center">
                <label for="inputSubject" class="col-sm-4 col-form-label">Message Subject</label>
                <div class="col-sm-8">
                  <input type="text" id="inputSubject" name="inputSubject" class="form-control" placeholder="Subject" required autofocus>
                </div>
            </div>
            <div class="form-group row justify-content-center">
                <label for="inputText" class="col-sm-4 col-form-label">Your message</label>
                <div class="col-sm-8">
                    <textarea class="form-control" id="inputText" name="inputText" rows="7"></textarea>
                </div>
            </div>
            <div class="form-group row justify-content-center">
                <label for="inputLogin" class="col-sm-4 col-form-label">Login</label>
                <div class="col-sm-8">
                    <input type="text" id="inputLogin" name="inputLogin" class="form-control" placeholder="Login" required autofocus>
                </div>
            </div>
            <div class="form-group row justify-content-center">
                <label for="inputPassword" class="col-sm-4 col-form-label">Password</label>
                <div class="col-sm-8">
                    <input type="password" id="inputPassword" name="inputPassword" class="form-control" placeholder="Password" required autofocus>
                </div>
            </div>
            <div class="form-group row justify-content-center">
                <div class="col-sm-4">
                    <button type="submit" class="btn btn-success">Submit</button>
                </div>
            </div>
        </form>
    </div>
</div>