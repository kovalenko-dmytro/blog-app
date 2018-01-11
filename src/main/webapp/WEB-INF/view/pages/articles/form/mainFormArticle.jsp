<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form action="${uri}" method="POST">
    <div class="form-group row justify-content-center">
        <label for="inputTitle" class="col-sm-2 col-form-label">Title</label>
        <div class="col-sm-8">
          <input type="text" id="inputTitle" name="inputTitle" value="${article.title}" class="form-control" required autofocus>
        </div>
    </div>
    <div class="form-group row justify-content-center">
        <label for="inputText" class="col-sm-2 col-form-label">Text</label>
        <div class="col-sm-8">
            <textarea class="form-control" id="inputText" name="inputText" value="${article.text}" rows="7" required autofocus>${article.text}</textarea>
        </div>
    </div>
    <div class="form-group row justify-content-center">
        <label for="inputCategory" class="col-sm-2 col-form-label">Category</label>
        <div class="col-sm-8">
            <select class="form-control" id="inputCategory" name="inputCategory" required>
              <c:if test="${empty article}">
                 <option>Choose...</option>
              </c:if>
              <c:forEach var="category" items="${categories}">
                  <option value="${category.id}" ${category.id == article.id ? "selected" : ""}>${category.name}</option>
              </c:forEach>
            </select>
        </div>
    </div>

    <input type="text" id="inputUserId" name="inputUserId" value="${user.id}" hidden>
    <input type="text" id="inputArticleId" name="inputArticleId" value="${article.id}" hidden>

    <div class="form-group row justify-content-center">
        <div class="col-sm-4">
            <button type="submit" class="btn btn-success">Create</button>
        </div>
    </div>
</form>