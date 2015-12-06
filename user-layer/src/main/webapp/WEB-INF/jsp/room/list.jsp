<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Rooms">
<jsp:attribute name="body">

    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Number Of beds</th>
            <th>Price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${rooms}" var="room">
            <tr>
                <td>${room.id}</td>
                <td><c:out value="${room.name}"/></td>
                <td><c:out value="${room.numberOfBeds}"/></td>
                <td><c:out value="${room.Price}"/></td>
                <td>
                    <my:a href="/room/detail/${room.id}" class="btn btn-primary">View</my:a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>