
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Hotel Administration">
<jsp:attribute name="body">

    <form method="post" action="${pageContext.request.contextPath}/hotel/delete/${hotel.id}">
        <button type="submit" class="btn btn-primary">Delete</button>
    </form>


    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>added</th>
            <th>hotel name</th>
            <th>description</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>${hotel.id}</td>
                <td><fmt:formatDate value="${hotel.lastUpdateDay}" pattern="yyyy-MM-dd"/></td>
                <td><c:out value="${hotel.description}"/></td>
                <td><c:out value="${hotel.description}"/></td>
            </tr>
        </tbody>
    </table>


</jsp:attribute>
</my:pagetemplate>
