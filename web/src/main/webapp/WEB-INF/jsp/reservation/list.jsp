<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Reservations">
<jsp:attribute name="body">

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
          <td><c:out value="${reservation.customer.name} ${reservation.customer.surname}"/></td>
          <td><c:out value="${reservation.room.name}"/></td>
          <td><c:out value="${reservation.startOfReservation}"/></td>
          <td><c:out value="${reservation.endOfReservation}"/></td>
          <td>
            <my:a href="/reservation/view/${reservation.id}" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open" style="height:20px;" aria-hidden="true"></span></my:a>
          </td>
          <c:if test="${authenticatedUser.admin == true}">
            <td style="width:45px;">
              <my:a href="/reservation/edit/${reservation.id}" class="btn btn-primary"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></my:a>
            </td>
            <td>
                <my:a href="/reservation/delete/${reservation.id}" class="btn btn-primary"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></my:a>
            </td>
          </c:if>
        </tr>
      </c:forEach>
      </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>
