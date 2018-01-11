<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form action="${pageContext.request.contextPath}/profile/user/update" method="POST">
    <div class="form-group row justify-content-center">
            <label for="inputFirstName" class="col-sm-4 col-form-label">First Name</label>
            <div class="col-sm-4">
              <input type="text" id="inputFirstName" name="inputFirstName" value="${user.firstName}" class="form-control" placeholder="First Name" required autofocus>
            </div>
        </div>
        <div class="form-group row justify-content-center">
            <label for="inputLastName" class="col-sm-4 col-form-label">Last Name</label>
            <div class="col-sm-4">
                <input type="text" id="inputLastName" name="inputLastName" value="${user.lastName}" class="form-control" placeholder="Last Name" required autofocus>
            </div>
        </div>
        <div class="form-group row justify-content-center">
            <label for="inputUserAlias" class="col-sm-4 col-form-label">Your Alias</label>
            <div class="col-sm-4">
                <input type="text" id="inputUserAlias" name="inputUserAlias" value="${user.alias}" class="form-control" placeholder="Your Alias" required autofocus>
            </div>
        </div>
        <div class="form-group row justify-content-center">
                <label for="inputAvatar" class="col-sm-4 col-form-label">Your Avatar</label>
                <div class="col-sm-4">
                    <input type="file" id="inputAvatar" name="inputAvatar" value="${user.avatar}" class="form-control-file">
                </div>
            </div>
        <div class="form-group row justify-content-center">
            <div class="col-sm-4">
                <button type="submit" class="btn btn-success">Update</button>
            </div>
        </div>
</form>