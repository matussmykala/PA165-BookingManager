<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Room ${room.id} detail">
<jsp:attribute name="body">


        <table class="table">
            <caption>Room</caption>
            <thead>
            <tr>
                <th>id</th>
                <th>Name</th>
                <th>Number Of Beds</th>
                <th>Price</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${room.id}</td>
                <td><c:out value="${room.name}"/></td>
                <td><c:out value="${room.numberOfBeds}"/></td>
                <td><c:out value="${room.price}"/></td>
            </tr>
            </tbody>
        </table>

</jsp:attribute>
</my:pagetemplate>