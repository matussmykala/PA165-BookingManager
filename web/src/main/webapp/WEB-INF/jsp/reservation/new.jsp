<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="title">
    <fmt:message key="reservation.new.caption"/>
</c:set>

<my:pagetemplate title="${title}">
<jsp:attribute name="body">

  <form:form method="get" action="${pageContext.request.contextPath}/reservation/new/${roomId}" cssClass="form-horizontal">
      <div style="margin-top:10px;"><label for="startDate"><f:message key="reservation.list.from"/></label><input class="form-control" name="startDate" type="date" id="startDate" value="<c:out value='${startDate}'/>"/></div>
      <div style="margin-top:10px;"><label for="endDate"><f:message key="reservation.list.to"/></label><input class="form-control" name="endDate" type="date" id="endDate" value="<c:out value='${endDate}'/>"/></div>
      <div style="margin-top:10px;"><button class="btn btn-primary" type="submit" ><f:message key="room.list.book"/></button></div>

  </form:form>

</jsp:attribute>
</my:pagetemplate>
