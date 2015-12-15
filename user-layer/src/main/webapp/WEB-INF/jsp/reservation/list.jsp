<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Reservations">
<jsp:attribute name="body">

    <my:a href="/reservation/new" class="btn btn-primary">New</my:a>

    <table class="table">
      <thead>
      <tr>
        <th>Id</th>
        <th>Customer</th>
        <th>Room</th>
        <th>From</th>
        <th>To</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${reservations}" var="reservation">
        <tr>
          <td>${reservation.id}</td>
          <td><c:out value="${reservation.customer}"/></td>
          <td><c:out value="${reservation.room}"/></td>
          <td><c:out value="${reservation.startOfReservation}"/></td>
          <td><c:out value="${reservation.endOfReservation}"/></td>
          <td>
            <my:a href="/reservation/view/${reservation.id}" class="btn btn-primary">View</my:a>
          </td>
          <td>
            <form method="post" action="${pageContext.request.contextPath}/reservation/delete/${reservation.id}">
              <button type="submit" class="btn btn-primary">Delete</button>
            </form>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>
