<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<my:pagetemplate title="Rooms">
<jsp:attribute name="body">
    <c:if test="${authenticatedUser.admin == true}">
        <my:a href="/room/new" class="btn btn-primary">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            <f:message key="room.list.newRoom"/>
        </my:a>
    </c:if>

    <c:if test="${not fn:containsIgnoreCase(pageContext.request.getQueryString(),'goal')}">
        <div class="form-horizontal" style="margin-top:20px;">
           <form:form method="get" action="${pageContext.request.contextPath}/room/filter" modelAttribute="rooms" cssClass="form-horizontal">
                <div class="form-group">
                    <label for="filterBy" style="width:90px;text-align:left" class="col-md-2 control-label"><f:message key="room.list.filterBy"/></label>
                    <div class="col-md-2">
                        <select class="form-control" id="filterBy" name="filterType">
                            <option value="numberOfBeds"><f:message key="room.list.numberOfBeds"/></option>
                            <option value="price"><f:message key="room.list.price"/></option>
                            <option value="hotelName"><f:message key="room.list.hotel"/></option>
                        </select>
                    </div>
                    <label for="valueOffilter" style="width:130px;text-align:left" class="col-md-2 control-label"><f:message key="room.list.valueOfFilter"/></label>
                    <div class="col-md-2">
                        <input class="form-control" name="filter" id="valueOffilter" value="<c:out value='${filter}'/>"/>
                    </div>
                    <div class="col-md-2">
                        <button class="btn btn-primary" type="submit" ><f:message key="room.list.filterRooms"/></button>
                    </div>
                </div>
            </form:form>
      </div>
    </c:if>

    <table class="table">
        <thead>
        <tr>
            <th><f:message key="room.list.id"/></th>
            <th><f:message key="room.list.name"/></th>
            <th><f:message key="room.list.hotel"/></th>
            <th><f:message key="room.list.numberOfBeds"/></th>
            <th><f:message key="room.list.price"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${rooms}" var="room">
            <tr>
                <td style="vertical-align: middle;">#${room.id}</td>
                <td style="vertical-align: middle;"><c:out value="${room.name}"/></td>
                <td style="vertical-align: middle;"><c:out value="${room.hotel.name}"/></td>
                <td style="vertical-align: middle;"><c:out value="${room.numberOfBeds}"/></td>
                <td style="vertical-align: middle;"><c:out value="${room.price}"/> €</span></td>

                <td style="width:45px;">
                    <my:a href="/room/view/${room.id}" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open" style="height:20px;" aria-hidden="true"></span></my:a>
                </td>

                <c:if test="${authenticatedUser.admin == true}">
                    <td style="width:45px;">
                        <my:a href="/room/edit/${room.id}" class="btn btn-primary"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></my:a>
                    </td>

                    <td style="width:45px;">
                        <my:a href="/room/delete/${room.id}" class="btn btn-primary"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></my:a>
                    </td>
                </c:if>

                
                
                <td>
                <c:choose>    
                    <c:when test="${startDate != null}">
                        <my:a href="/reservation/new/${room.id}?startDate=${startDate}&endDate=${endDate}" class="btn btn-primary"><f:message key="room.list.book"/></my:a>
                    </c:when>
                    <c:otherwise>
                        <my:a href="/reservation/pickdate/${room.id}" class="btn btn-primary"><f:message key="room.list.book"/></my:a>
                    </c:otherwise>    
                </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>
