<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
    <div class="col-sm-12">
        <c:if test = "${not empty user}">
            <div class="blog-post mt-2 mb-2">
                <div class="card-header text-white bg-primary">
                    Author alias: <c:out value="${user.alias}"/>
                </div>
                <div class="text-center">
                    <img class="card-img-top rounded rounded-circle img-thumbnail" src="resources/images/users/${user.avatar}" style="width: 175px;" alt="${user.alias}">
                </div>
                <p>
                    First name: <c:out value="${user.firstName}"/>
                </p>
                <p>
                    Last name: <c:out value="${user.lastName}"/>
                </p>
                <p>
                    Email: <c:out value="${user.email}"/>
                </p>
                <p>
                    <span class="badge badge-success rounded float-left">
                        <c:out value="${user.articleCount}"/> Blogs
                    </span>
                </p>
                <p class="text-center">
                  <a href="${pageContext.request.contextPath}/profile/user/update" class="btn btn-success text-center">Update Profile</a>
                </p>
            </div>
        </c:if>
    </div>
</div>