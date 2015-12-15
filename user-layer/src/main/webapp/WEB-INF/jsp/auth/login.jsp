
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Login">
<jsp:attribute name="body">

    <form:form method="get" action="${pageContext.request.contextPath}/room/list" cssClass="form-horizontal">
            <div style="margin-top:10px;"><label for="username">Username</label><input class="form-control" name="username" id="username" value="<c:out value='${username}'/>"/></div>
            <div style="margin-top:10px;"><label for="password">Password</label><input class="form-control" name="password" id="password" value="<c:out value='${password}'/>"/></div>            
            <div style="margin-top:10px;"><button class="btn btn-primary" type="submit" >Login</button></div>
    </form:form>

</jsp:attribute>
</my:pagetemplate>
