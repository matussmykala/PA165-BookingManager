<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="title">
    <fmt:message key="room.title.edit"/>
</c:set>
<my:pagetemplate title="${title}">

<jsp:attribute name="body">

    <form:form method="get" action="${pageContext.request.contextPath}/room/update/${room.id}"
               modelAttribute="room" cssClass="form-horizontal">

        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label"><f:message key="room.list.name"/></form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group ${hotel_error?'has-error':''}">
            <form:label path="hotel" cssClass="col-sm-2 control-label"><f:message key="room.list.hotel"/></form:label>
            <div class="col-sm-10">
            <select class="form-control" id="hotelid" name="hotelId">
            <c:forEach items="${hotels}" var="hotel">
                <c:choose>
                <c:when test="${hotel.id == room.hotel.id}">
                    <option selected value="<c:out value="${hotel.id}"/>"><c:out value="${hotel.name}"/></option>
                </c:when>
                <c:otherwise>
                    <option value="<c:out value="${hotel.id}"/>"><c:out value="${hotel.name}"/></option>
                </c:otherwise>
                </c:choose>
            </c:forEach>
            </select>
            </div>
        </div>

        <div class="form-group ${numberOfBeds_error?'has-error':''}">
            <form:label path="numberOfBeds" cssClass="col-sm-2 control-label"><f:message key="room.list.numberOfBeds"/></form:label>
            <div class="col-sm-10">
                <form:input path="numberOfBeds" type="number" style="width:75px;" min="1" title="Min:1" cssClass="form-control"/>
               <form:errors path="numberOfBeds" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group ${price_error?'has-error':''}">
            <form:label path="price" cssClass="col-sm-2 control-label"><f:message key="room.list.price"/></form:label>
            <div class="col-sm-10">
                <form:input path="price" style="width:75px;" pattern="^\d+\.?\d{1,2}$" title="Format: '12.34'" cssClass="form-control"/>
                <form:errors path="price" cssClass="help-block"/>
            </div>
        </div>

        <button class="btn btn-primary" type="submit"><f:message key="room.edit"/></button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>
