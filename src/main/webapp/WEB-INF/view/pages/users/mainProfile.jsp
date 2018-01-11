<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="row">
    <div class="col-sm-12">
        <c:if test = "${not empty user}">
            <p class="text-center">
              <a href="${pageContext.request.contextPath}/profile/create-article" class="btn btn-success text-center">Create blog</a>
            </p>
            <c:if test = "${not empty user.articles}">
                <table class="table table-hover table-responsive">
                  <thead>
                    <tr>
                      <th scope="col">#</th>
                      <th scope="col">Title</th>
                      <th scope="col">Category</th>
                      <th scope="col">Date</th>
                      <th scope="col"></th>
                      <th scope="col"></th>
                    </tr>
                  </thead>
                  <tbody>
                        <c:forEach var="article" items="${user.articles}" varStatus="count">
                            <tr>
                                <td>${count.index}</td>
                                <td>${article.title}</td>
                                <td>${article.categoryName}</td>
                                <td>${article.date}</td>
                                <td><a href="${pageContext.request.contextPath}/profile/update-article?articleId=${article.id}" class="btn btn-success text-center">Update blog</a></td>
                                <td><a href="${pageContext.request.contextPath}/profile/delete-article?articleId=${article.id}" class="btn btn-danger text-center">Delete blog</a></td>
                             </tr>
                        </c:forEach>
                  </tbody>
                </table>
            </c:if>
        </c:if>
    </div>
</div>