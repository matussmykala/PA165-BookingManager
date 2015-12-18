<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Edit customer">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/customer/update/${customer.id}"
               modelAttribute="customer" cssClass="form-horizontal">

        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label"><fmt:message key="customer.name"/></form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group ${surname_error?'has-error':''}">
            <form:label path="surname" cssClass="col-sm-2 control-label"><fmt:message
                    key="customer.surname"/></form:label>
            <div class="col-sm-10">
                <div class="col-sm-10">
                    <form:input path="surname" cssClass="form-control"/>
                    <form:errors path="surname" cssClass="help-block"/>
                </div>
            </div>
        </div>

        <div class="form-group ${email_error?'has-error':''}">
            <form:label path="email" cssClass="col-sm-2 control-label"><fmt:message key="customer.email"/></form:label>
            <div class="col-sm-10">
                <form:input path="email" cssClass="form-control"/>
                <form:errors path="email" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group ${username_error?'has-error':''}">
            <form:label path="username" cssClass="col-sm-2 control-label"><fmt:message
                    key="customer.username"/></form:label>
            <div class="col-sm-10">
                <form:input path="username" cssClass="form-control"/>
                <form:errors path="username" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group ${admin_error?'has-error':''}">
            <form:label path="admin" cssClass="col-sm-2 control-label"><fmt:message key="customer.admin"/></form:label>
            <div class="col-sm-4">
                <form:checkbox path="admin" cssClass="form-control"/>
                <form:errors path="admin" cssClass="help-block"/>
            </div>
        </div>

        <button class="btn btn-primary" type="submit"><fmt:message key="button.confirm"/></button>
    </form:form>

    <form method="get" action="${pageContext.request.contextPath}/customer/list/">
        <p align="left">
            <button type="submit" class="btn btn-default"><fmt:message key="button.back"/></button>
        </p>
    </form>

</jsp:attribute>
</my:pagetemplate>