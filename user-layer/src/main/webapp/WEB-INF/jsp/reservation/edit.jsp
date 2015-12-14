<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Edit reservation">
<jsp:attribute name="body">

    <form:form method="get" action="${pageContext.request.contextPath}/reservation/update/${reservation.id}"
               modelAttribute="reservation" cssClass="form-horizontal">

        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="customer" cssClass="col-sm-2 control-label">Customer</form:label>
            <div class="col-sm-10">
                <form:input path="customer" cssClass="form-control" readonly="true"/>
                <form:errors path="customer" cssClass="help-block"/>
                <!--<c:out value="${reservation.customer.name} ${reservation.customer.surname}"/>
                -->
            </div>
        </div>

        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="room" cssClass="col-sm-2 control-label">Room</form:label>
            <div class="col-sm-10">
                <form:input path="room" cssClass="form-control" readonly="true" />
                <form:errors path="room" cssClass="help-block"/>
                <!--<c:out value="${reservation.room.name}"/>-->
            </div>
        </div>
        <!--
        <div style="margin-top:10px;">
            <label for="startDate">Start date</label>
            <form:input path="startOfReservation" class="form-control" name="startDate" type="date" id="startDate" value="<c:out value='${reservation.startOfReservation}'/>"/>
            <form:errors path="startOfReservation" cssClass="help-block"/>
        </div>

        <div style="margin-top:10px;">
            <label for="endDate">End date</label>
            <form:input path="endOfReservation" class="form-control" name="endDate" type="date" id="endDate" value="<c:out value='${reservation.endOfReservation}'/>"/>
            <form:errors path="endOfReservation" cssClass="help-block"/>
        </div>
        -->
        <div style="margin-top:10px;"><label for="startDate">Start date</label><input class="form-control" name="startDate" type="date" id="startDate" value="<c:out value='${startDate}'/>"/></div>
        <div style="margin-top:10px;"><label for="endDate">End Date</label><input class="form-control" name="endDate" type="date" id="endDate" value="<c:out value='${endDate}'/>"/></div>


        <button class="btn btn-primary" type="submit">Confirm</button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>
