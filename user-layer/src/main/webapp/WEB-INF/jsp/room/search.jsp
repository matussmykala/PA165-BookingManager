<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Search rooms">
<jsp:attribute name="body">
    <div class="row">  
        <div class="col-sm-4" style="border-right: solid 1px #c1c1c1;">
    <form:form method="get" action="${pageContext.request.contextPath}/room/free-rooms" cssClass="form-horizontal">
        

            <div style="margin-top:10px;"><label for="hotelid">Hotel ID</label>
                <select class="form-control" id="hotelid" name="hotelid">
                <c:forEach items="${hotels}" var="hotel">
                    <option value="<c:out value="${hotel.id}"/>"><c:out value="${hotel.name}"/></option>
                </c:forEach>
                </select>
            </div>
            <div style="margin-top:10px;"><label for="startDate">Start date</label><input class="form-control" name="startDate" type="date" id="startDate" value="<c:out value='${startDate}'/>"/></div>
            <div style="margin-top:10px;"><label for="endDate">End Date</label><input class="form-control" name="endDate" type="date" id="endDate" value="<c:out value='${endDate}'/>"/></div>            
            <div style="margin-top:10px;"><button class="btn btn-primary" type="submit" >Show free Rooms</button></div>
    </form:form>
        </div>
        <div class="col-sm-4">
    <form:form method="get" action="${pageContext.request.contextPath}/room/filter" cssClass="form-horizontal">


            <div style="margin-top:10px;"><label for="filterBy">Filter By</label>
                <select class="form-control" id="filterBy" name="filterType">
                    <option value="numberOfBeds">Number Of Beds</option>
                    <option value="price">Price</option>
                </select>
            </div>
            <div style="margin-top:10px;"><label for="valueOffilter">Value of filter</label><input class="form-control" name="filter" id="valueOffilter" value="<c:out value='${filter}'/>"/></div>            
            <div style="margin-top:10px;"><button class="btn btn-primary" type="submit" >Filter Rooms</button></div>
    </form:form>  
        </div>
    </div>
            

</jsp:attribute>
</my:pagetemplate>