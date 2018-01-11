<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="row">
    <div class="col-sm-12">
        <div class="blog-post">
            <ul class="list-unstyled">
                <c:if test = "${not empty articles}">
                     <c:forEach var="article" items="${articles}">
                            <li class="media mt-2 mb-3">
                                <img class="mr-3" src="${pageContext.request.contextPath}/resources/images/${article.image}" alt="image">
                                <div class="media-body font-weight-normal">
                                    <h4>${article.title}</h4>
                                    <p class="blog-post-meta">${article.date} by
                                        <a href="${pageContext.request.contextPath}/articles/byUser/${article.userAlias}">${article.userAlias}</a>
                                    </p>
                                    <c:set var = "text" value = "${article.text}"/>
                                    <c:set var = "subtext" value = "${fn:substring(text, 0, 120)}" />
                                    ${subtext}<a href="${pageContext.request.contextPath}/articles/byCategory/${article.categoryName}/${article.id}">...</a>
                                    <div class="clearfix">
                                        <a href="${pageContext.request.contextPath}/articles/byCategory/${article.categoryName}/${article.id}" class="btn btn-success float-right">Read more</a>
                                    </div>
                                </div>
                            </li>
                     </c:forEach>
                </c:if>
            </ul>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-12">
        <div class="blog-pagination text-center">
            <c:choose>
                <c:when test="${not empty search}">
                    <c:if test="${currentPage != 1}">
                        <a class="btn btn-outline-primary" href="${search}page=${currentPage - 1}">Previous</a>
                    </c:if>
                    <c:forEach var="i" begin="1" end="${pageQuantity}" >
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                ${i}
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-outline-primary" href="${search}&page=${i}">${i}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${currentPage lt pageQuantity}">
                       <a class="btn btn-outline-primary" href="${search}&page=${currentPage + 1}">Next</a>
                    </c:if>
                </c:when>
                <c:otherwise>
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
                </c:otherwise>
            </c:choose>

        </div>
    </div>
</div>