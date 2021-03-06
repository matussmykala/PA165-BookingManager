<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="title">
    <fmt:message key="hotel.findYourRoom"/>
</c:set>

<my:pagetemplate title="${title}">
    <jsp:attribute name="body">

       <div class="row">
            <div class="col-sm-4">
            <form:form method="get" action="${pageContext.request.contextPath}/hotel/search"
                    cssClass="form-horizontal">

                <div style="margin-top:10px;">
                    <label for="goal"><f:message key="hotel.nameDestination"/></label>
                       <input class="form-control" name="goal" id="goal" placeholder="Search" type="text"/>
               </div>
               <div style="margin-top:10px;"><label for="startDate"><f:message key="hotel.startDate"/></label><input class="form-control" name="startDate" type="date" id="startDate" placeholder="yyyy-mm-dd" value="<c:out value='${startDate}'/>"/></div>
               <div style="margin-top:10px;"><label for="endDate"><f:message key="hotel.endDate"/></label><input class="form-control" name="endDate" type="date" id="endDate" placeholder="yyyy-mm-dd" value="<c:out value='${endDate}'/>"/></div>
               <div style="margin-top:10px;"><button class="btn btn-primary" type="submit" ><f:message key="hotel.find"/></button></div>

            </form:form>
        </div>
      </div>

    </jsp:attribute>
</my:pagetemplate>
