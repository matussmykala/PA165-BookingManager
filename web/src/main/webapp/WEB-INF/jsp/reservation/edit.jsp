<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Edit reservation">
<jsp:attribute name="body">

    <form:form method="get" action="${pageContext.request.contextPath}/reservation/update/${reservation.id}"
               modelAttribute="reservation" cssClass="form-horizontal">

        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="customer" cssClass="col-sm-2 control-label"><f:message key="reservation.list.customer"/></form:label>
            <div class="col-sm-10">
                <form:input path="customer.name" cssClass="form-control" readonly="true"/>
                <form:errors path="customer.name" cssClass="help-block"/>
                <form:input path="customer.surname" cssClass="form-control" readonly="true"/>
                <form:errors path="customer.surname" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="room" cssClass="col-sm-2 control-label"><f:message key="reservation.list.room"/></form:label>
            <div class="col-sm-10">
                <form:input path="room.hotel.name" cssClass="form-control" readonly="true" />
                <form:errors path="room.hotel.name" cssClass="help-block"/>
                <form:input path="room.name" cssClass="form-control" readonly="true" />
                <form:errors path="room.name" cssClass="help-block"/>
                <form:input path="room.price" cssClass="form-control" readonly="true" />
                <form:errors path="room.price" cssClass="help-block"/>
            </div>
        </div>
        <div style="margin-top:10px;">
            <label for="startDate"><f:message key="reservation.list.from"/></label>
            <input class="form-control" name="startDate" type="date" id="startDate" value="<c:out value='${startDate}'/>"/>
        </div>
        <div style="margin-top:10px;">
            <label for="endDate"><f:message key="reservation.list.to"/></label>
            <input class="form-control" name="endDate" type="date" id="endDate" value="<c:out value='${endDate}'/>"/>
        </div>

        <button class="btn btn-primary" type="submit"><f:message key="room.edit"/></button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>
