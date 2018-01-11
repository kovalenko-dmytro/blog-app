<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
    <div class="col-sm-12">
    <div class="blog-post">
        <h4>Search</h4>
        <p>Search blog by context</p>
        <form class="form-inline" action="articles" method="GET">
            <div class="input-group">
            <input type="text" id="search" name="search" class="form-control" placeholder="Search for..." aria-label="Search for...">
            <span class="input-group-btn">
                <button class="btn btn-success" type="submit">Search</button>
            </span>
            </div>
        </form>
        </div>
    </div>
</div>
<div class="row">
    <h3 class="text-center text-primary mb-2">Most active bloggers</h3>
    <c:if test = "${not empty users}">
        <c:forEach var="user" items="${users}">
            <div class="col-sm-12">
                <div class="blog-post mt-2 mb-2">
                    <div class="card text-center border-success" >
                        <div class="card-header text-white bg-primary"><c:out value="${user.alias}"/></div>
                        <div class="text-center">
                            <img class="card-img-top rounded rounded-circle img-thumbnail" src="resources/images/users/${user.avatar}" style="width: 175px;" alt="${user.alias}">
                        </div>
                        <div class="card-body">
                            <h4 class="card-title">
                                <c:out value="${user.firstName}"/>
                                <c:out value="${user.lastName}"/>
                            </h4>
                            <span class="badge badge-success rounded float-left"><c:out value="${user.articleCount}"/> Blogs</span>
                            <a href="${pageContext.request.contextPath}/articles/byUser/${user.alias}" class="card-link rounded float-right">Read blogs...</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </c:if>
</div>
<div class="row">
    <div class="col-sm-12 pb-5">
        <div class="text-center mx-auto">
          <a href="${pageContext.request.contextPath}/users" class="btn btn-success text-center">Read all bloggers</a>
        </div>
    </div>
</div>