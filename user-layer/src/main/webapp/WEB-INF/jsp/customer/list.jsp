<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Customers">
<jsp:attribute name="body">

    <table class="table">
        <caption>Customers</caption>
        <thead>
        <tr>
            <th>id</th>
            <th>given name</th>
            <th>surname</th>
            <th>email</th>
            <th>username</th>
            <th>admin role</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${customers}" var="customer">
            <tr>
                <td>${customer.id}</td>
                <td><c:out value="${customer.name}"/></td>
                <td><c:out value="${customer.surname}"/></td>
                <td><c:out value="${customer.email}"/></td>
                <td><c:out value="${customer.username}"/></td>
                    <%--<td><input type="checkbox" name="isAdmin" <% if () { %> checked <% } %> /></td>--%>
                    <%--<td><form:checkbox path="${customer.admin}"/></td>--%>
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
                <td>
                    <my:a href="/customer/view/${customer.id}" class="btn btn-primary"><span
                            class="glyphicon glyphicon-eye-open" style="height:20px;" aria-hidden="true"></span></my:a>
                </td>
                <td>
                    <my:a href="/customer/edit/${customer.id}" class="btn btn-primary"><span
                            class="glyphicon glyphicon-pencil" style="height:20px;" aria-hidden="true"></span></my:a>
                </td>
                <td>
                    <my:a href="/customer/delete/${customer.id}" class="btn btn-primary"><span
                            class="glyphicon glyphicon-trash" aria-hidden="true"></span></my:a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>