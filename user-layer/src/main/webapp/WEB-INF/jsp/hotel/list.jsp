<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<my:pagetemplate title="Hotel">
<jsp:attribute name="body">

    <c:if test="${authenticatedUser.admin == true}">
        <my:a href="/hotel/new" class="btn btn-primary">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            <f:message key="hotel.newHotel"/>
        </my:a>
    </c:if>
            
    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th><f:message key="hotel.name"/></th>
            <th><f:message key="hotel.destination"/></th>
            <th><f:message key="hotel.lastUpdate"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${hotels}" var="hotel">
            <tr>
                <td>${hotel.id}</td>
                <td><c:out value="${hotel.name}"/></td>
                <td><c:out value="${hotel.address}"/></td>
                <td><fmt:formatDate value="${hotel.lastUpdateDay}" pattern="yyyy-MM-dd"/></td>
                <td>
                    <my:a href="/hotel/view/${hotel.id}" class="btn btn-primary"><f:message key="hotel.view"/></my:a>
                </td>
                <c:if test="${authenticatedUser.admin == true}">
                    <td>
                        <my:a href="/hotel/edit/${hotel.id}" class="btn btn-primary"><f:message key="hotel.edit"/></my:a>
                    </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/hotel/delete/${hotel.id}">
                            <button type="submit" class="btn btn-primary"><f:message key="hotel.delete"/></button>
                        </form>
                    </td>
                </c:if>
                <td>
                    <my:a href="/room/free-rooms?hotelId=${hotel.id}&startDate=${startDate}&endDate=${endDate}" class="btn btn-primary"><f:message key="hotel.rooms"/></my:a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>