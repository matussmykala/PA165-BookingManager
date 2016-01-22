<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="title">
    <fmt:message key="hotel.editHotel"/>
</c:set>

<my:pagetemplate title="${title}">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/hotel/update/${hotel.id}"
               modelAttribute="hotel" cssClass="form-horizontal">
        
        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label"><f:message key="hotel.name"/></form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>
            
        <div class="form-group ${address_error?'has-error':''}">
            <form:label path="address" cssClass="col-sm-2 control-label"><f:message key="hotel.destination"/></form:label>
            <div class="col-sm-10">
                <form:input path="address" cssClass="form-control"/>
               <form:errors path="address" cssClass="help-block"/>
            </div>
        </div>
        
        <div class="form-group ${description_error?'has-error':''}">
            <form:label path="description" cssClass="col-sm-2 control-label"><f:message key="hotel.description"/></form:label>
            <div class="col-sm-10">
                <form:textarea cols="80" rows="20" path="description" cssClass="form-control"/>
                <form:errors path="description" cssClass="help-block"/>
            </div>
        </div>
       
        <button class="btn btn-primary" type="submit"><f:message key="hotel.confirm"/></button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>
