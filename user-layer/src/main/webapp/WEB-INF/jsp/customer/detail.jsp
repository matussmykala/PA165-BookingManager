<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Customer ${customer.name} ${customer.surname} detail">
<jsp:attribute name="body">


        <table class="table">
            <caption>Customer</caption>
            <thead>
            <tr>
                <th>id</th>
                <th>given name</th>
                <th>surname</th>
                <th>email</th>
                <th>phone</th>
                <th>address</th>
                <th>username</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${customer.id}</td>
                <td><c:out value="${customer.givenName}"/></td>
                <td><c:out value="${customer.surname}"/></td>
                <td><c:out value="${customer.email}"/></td>
                <td><c:out value="${customer.phone}"/></td>
                <td><c:out value="${customer.address}"/></td>
                <td><c:out value="${customer.username}"/></td>
            </tr>
            </tbody>
        </table>

    <table class="table">
        <caption>Reservations</caption>
        <thead>
        <tr>
            <th>id</th>
            <th>start date</th>
            <th>end date</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${reservations}" var="reservation">
            <tr>
                <td>${reservation.id}</td>
                <td><c:out value="${reservation.startOfReservation}"/>x</td>
                <td><c:out value="${reservation.endOfReservation}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>