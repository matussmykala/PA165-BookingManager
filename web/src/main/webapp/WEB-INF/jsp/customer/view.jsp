<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="title">
    <fmt:message key="customer.customer"/>
</c:set>

<my:pagetemplate title="${title} ${customer.name} ${customer.surname}">
<jsp:attribute name="body">


        <table class="table">
            <caption><fmt:message key="customer.caption"/></caption>
            <thead>
            <tr>
                <th>id</th>
                <th><fmt:message key="customer.name"/></th>
                <th><fmt:message key="customer.surname"/></th>
                <th><fmt:message key="customer.email"/></th>
                <th><fmt:message key="customer.username"/></th>
                <th><fmt:message key="customer.admin"/></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${customer.id}</td>
                <td><c:out value="${customer.name}"/></td>
                <td><c:out value="${customer.surname}"/></td>
                <td><c:out value="${customer.email}"/></td>
                <td><c:out value="${customer.username}"/></td>
                <td>
                    <c:choose>
                        <c:when test="${customer.admin==true}">
                            <input type="checkbox" name="admin" disabled checked/>
                        </c:when>
                        <c:otherwise>
                            <input type="checkbox" name="admin" disabled/>
                        </c:otherwise>
                    </c:choose>
                </td>

                <c:if test="${authenticatedUser.admin}">
                    <td>
                        <my:a href="/customer/edit/${customer.id}" class="btn btn-primary"><span
                                class="glyphicon glyphicon-pencil" style="height:20px;"
                                aria-hidden="true"></span></my:a>
                    </td>
                    <td>
                        <my:a href="/customer/delete/${customer.id}" class="btn btn-primary"><span
                                class="glyphicon glyphicon-trash" aria-hidden="true"></span></my:a>
                    </td>
                </c:if>
            </tr>
            </tbody>
        </table>

    <table class="table">
        <caption><fmt:message key="reservation.caption"/></caption>
        <thead>
        <tr>
            <th>id</th>
            <th><fmt:message key="reservation.list.from"/></th>
            <th><fmt:message key="reservation.list.to"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${reservations}" var="reservation">
            <tr>
                <td>${reservation.id}</td>
                <td><c:out value="${reservation.startOfReservation}"/>x</td>
                <td><c:out value="${reservation.endOfReservation}"/></td>
                <td>
                    <my:a href="/reservation/view/${reservation.id}" class="btn btn-primary"><span
                            class="glyphicon glyphicon-eye-open" style="height:20px;" aria-hidden="true"></span></my:a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <form method="get" action="${pageContext.request.contextPath}/customer/list/">
        <p align="left">
            <button type="submit" class="btn btn-primary"><fmt:message key="button.back"/></button>
        </p>
    </form>

</jsp:attribute>
</my:pagetemplate>
