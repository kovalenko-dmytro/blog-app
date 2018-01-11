<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<ul class="dropdown-menu" aria-labelledby="navbarDropdown${dropDownId}">
    <c:forEach var="item" items="${subItem}">
        <c:set var="subItem" value="${item.subItems}" scope="application"/>
        <c:choose>
            <c:when test="${ fn:length(item.subItems) > 0 }">
            <c:set var = "name" value = "${item.name}"/>
            <c:if test = "${fn:contains(name, ' ')}">
                 <c:set var = "name" value = "${fn:replace(name, ' ', '')}" />
            </c:if>
            <c:set var="dropDownId" value="${name}" scope="application"/>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown${name}" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <c:out value="${item.name}"/>
                    </a>
                    <jsp:include page="menuItems.jsp"/>
                </li>
            </c:when>
            <c:otherwise>
            <li class="nav-item">
                <c:set var = "path" value = "${item.path}"/>
                <c:set var = "subpath" value = "${fn:substring(path, 1, 4)}" />
                <c:choose>
                    <c:when test = "${subpath eq 'cat'}">
                        <c:set var="cat" value="${fn:split(path, '/')}" />
                        <c:set var="length" value="${fn:length(cat)}" />
                        <a class="nav-link" href="${pageContext.request.contextPath}/articles/byCategory/${cat[length-1]}">
                            <c:out value="${item.name}"/>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a class="nav-link" href="${pageContext.request.contextPath}${item.path}">
                            <c:out value="${item.name}"/>
                        </a>
                    </c:otherwise>
                </c:choose>
            </li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</ul>