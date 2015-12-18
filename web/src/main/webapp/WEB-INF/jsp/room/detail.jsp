<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:pagetemplate title="Room ${room.id} detail">
<jsp:attribute name="body">

        <table class="table">
            <caption><f:message key="room.list.room"/></caption>
            <thead>
            <tr>
                <th>ID</th>
                <th><f:message key="room.list.name"/></th>
                <th><f:message key="room.list.hotel"/></th>
                <th><f:message key="room.list.numberOfBeds"/></th>
                <th><f:message key="room.list.price"/></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${room.id}</td>
                <td><c:out value="${room.name}"/></td>
                <td><c:out value="${room.hotel.name}"/></td>
                <td><c:out value="${room.numberOfBeds}"/></td>
                <td><c:out value="${room.price}"/></td>
            </tr>
            </tbody>
        </table>

</jsp:attribute>
</my:pagetemplate>
