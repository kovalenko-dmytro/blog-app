<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test = "${not empty users}">
    <div class="row">
        <div class="card-group clearfix">
            <c:forEach var="user" items="${users}" varStatus="count">
                <c:if test="${not count.first and count.index % 3 == 0}">
                        </div>
                    </div>
                    <div class="row">
                        <div class="card-group clearfix">
                </c:if>
                <div class="col-sm-4">
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
                <c:if test="${count.index  == 9}">
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>
</c:if>
<div class="row">
    <div class="col-sm-12">
        <div class="blog-pagination text-center">
            <c:if test="${currentPage != 1}">
                <a class="btn btn-outline-primary" href="?page=${currentPage - 1}">Previous</a>
            </c:if>
            <c:forEach var="i" begin="1" end="${pageQuantity}" >
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        ${i}
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn-outline-primary" href="?page=${i}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage lt pageQuantity}">
               <a class="btn btn-outline-primary" href="?page=${currentPage + 1}">Next</a>
            </c:if>
        </div>
    </div>
</div>