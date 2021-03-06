<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="title">
  <fmt:message key="reservation.caption"/>
</c:set>

<my:pagetemplate title="${title}">

  <jsp:attribute name="body">

    <table class="table">
      <thead>
      <tr>
        <th><f:message key="reservation.list.id"/></th>
        <th><f:message key="reservation.list.customer"/></th>
        <th><f:message key="reservation.list.room"/></th>
        <th><f:message key="reservation.list.from"/></th>
        <th><f:message key="reservation.list.to"/></th>
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
