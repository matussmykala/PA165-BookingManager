<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<my:pagetemplate title="Hotel">
<jsp:attribute name="body">

    <my:a href="/hotel/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        New hotel
    </my:a>

    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>address</th>
            <th>description</th>
            <th>last update</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${hotels}" var="hotel">
            <tr>
                <td>${hotel.id}</td>
                
                <td><c:out value="${hotel.name}"/></td>
                <td><c:out value="${hotel.address}"/></td>
                <td><c:out value="${hotel.description}"/></td>
                <td><fmt:formatDate value="${hotel.lastUpdateDay}" pattern="yyyy-MM-dd"/></td>
                <td>
                    <my:a href="/hotel/view/${hotel.id}" class="btn btn-primary">View</my:a>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/hotel/delete/${hotel.id}">
                        <button type="submit" class="btn btn-primary">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</jsp:attribute>
</my:pagetemplate>
