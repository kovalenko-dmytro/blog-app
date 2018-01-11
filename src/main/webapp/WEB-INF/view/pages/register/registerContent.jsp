<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<div class="col-sm-8 justify-content-sm-center blog-main">
	    <c:if test = "${not empty errorMessage}">
	        <p class="text-danger">${errorMessage}</p>
	        <a href="${pageContext.request.contextPath}" class="text-primary">Back to Home</a> OR:
	    </c:if>
		<form action="${pageContext.request.contextPath}/register" method="POST">
            <div class="form-group row justify-content-center">
                <label for="inputFirstName" class="col-sm-4 col-form-label">First Name</label>
                <div class="col-sm-4">
                  <input type="text" id="inputFirstName" name="inputFirstName" class="form-control" placeholder="First Name" required autofocus>
                </div>
            </div>
            <div class="form-group row justify-content-center">
                <label for="inputLastName" class="col-sm-4 col-form-label">Last Name</label>
                <div class="col-sm-4">
                    <input type="text" id="inputLastName" name="inputLastName" class="form-control" placeholder="Last Name" required autofocus>
                </div>
            </div>
            <div class="form-group row justify-content-center">
                <label for="inputUserAlias" class="col-sm-4 col-form-label">Your Alias</label>
                <div class="col-sm-4">
                    <input type="text" id="inputUserAlias" name="inputUserAlias" class="form-control" placeholder="Your Alias" required autofocus>
                </div>
            </div>
            <div class="form-group row justify-content-center">
                <label for="inputEmail" class="col-sm-4 col-form-label">Email address</label>
                <div class="col-sm-4">
                    <input type="email" id="inputEmail" name="inputEmail" class="form-control" placeholder="Email address" required autofocus>
                </div>
            </div>
            <div class="form-group row justify-content-center">
                <label for="inputPassword" class="col-sm-4 col-form-label">Password</label>
                <div class="col-sm-4">
                    <input type="password" id="inputPassword" name="inputPassword" class="form-control" placeholder="Password" required>
                </div>
            </div>
            <div class="form-group row justify-content-center">
                <div class="col-sm-4">
                    <button type="submit" class="btn btn-success">Register</button>
                </div>
            </div>
        </form>
	</div>
</div>

