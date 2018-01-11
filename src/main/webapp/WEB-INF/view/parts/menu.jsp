<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<nav class="navbar navbar-expand-md navbar-light text-dark bg-primary rounded">
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <c:if test="${not empty applicationScope.menu}">
            <ul class="navbar-nav mr-auto">
            <c:forEach var="item" items="${applicationScope.menu}">
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
                                <a class="nav-link" href="${pageContext.request.contextPath}${item.path}">
                                    <c:out value="${item.name}"/>
                                </a>
                            </li>
                        </c:otherwise>
                    </c:choose>
            </c:forEach>
            </ul>
        </c:if>
        <c:choose>
            <c:when test = "${sessionScope.userAlias != null}">
                <jsp:include page="/WEB-INF/view/parts/LogReg/menuAuth.jsp" />
            </c:when>
            <c:otherwise>
                <jsp:include page="/WEB-INF/view/parts/LogReg/menuNotAuth.jsp" />
            </c:otherwise>
        </c:choose>
	</div>
</nav>

