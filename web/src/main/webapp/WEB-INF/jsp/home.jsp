<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:pagetemplate>
<jsp:attribute name="body">

    <div class="jumbotron">
        <h2><f:message key="home.h2"/></h2>
        <h3><f:message key="home.h3"/></h3>
        <br>
        <a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/hotel/find"
              role="button"><f:message key="home.button"/></a>
    </div>

</jsp:attribute>
</my:pagetemplate>
