<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="title">
    <fmt:message key="login.title"/>
</c:set>
<my:pagetemplate title="${title}">

<jsp:attribute name="body">
    <div style="width:100%">
        <div style="min-width: 200px;max-width:300px;">
            <form:form method="get" action="${pageContext.request.contextPath}/room/list" cssClass="form-horizontal">
                <div style="margin-top:10px;"><label for="username"><f:message key="login.username"/></label><input class="form-control" name="username" id="username" value="<c:out value='${username}'/>"/></div>
                <div style="margin-top:10px;"><label for="password"><f:message key="login.password"/></label><input class="form-control" type="password" name="password" id="password" value="<c:out value='${password}'/>"/></div>
                <div style="margin-top:10px;"><button class="btn btn-primary" type="submit" ><f:message key="login.button"/></button></div>
            </form:form>
        </div>
    </div>

</jsp:attribute>
</my:pagetemplate>
