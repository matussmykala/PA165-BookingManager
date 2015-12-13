<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Rooms">
<jsp:attribute name="body">
    <my:a href="/room/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        New room
    </my:a>
     
       <div class="row">  
        <div class="col-sm-4">    
            
    <form:form method="get" action="${pageContext.request.contextPath}/room/filter" 
                modelAttribute="rooms" cssClass="form-horizontal">


            <div style="margin-top:10px;"><label for="filterBy">Filter By</label>
                <select class="form-control" id="filterBy" name="filterType">
                    <option value="numberOfBeds">Number Of Beds</option>
                    <option value="price">Price</option>
                </select>
                <div style="margin-top:10px;"><label for="valueOffilter">Value of filter</label><input class="form-control" name="filter" id="valueOffilter" value="<c:out value='${filter}'/>"/></div>            
            <div style="margin-top:10px;"><button class="btn btn-primary" type="submit" >Filter Rooms</button></div>
    </form:form>  
        </div>
       </div>
    
                   
    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Number Of beds</th>
            <th>Price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${rooms}" var="room">
            <tr>
                <td style="vertical-align: middle;">${room.id}</td>
                <td style="vertical-align: middle;"><c:out value="${room.name}"/></td>
                <td style="vertical-align: middle;"><c:out value="${room.numberOfBeds}"/></td>
                <td style="vertical-align: middle;"><c:out value="${room.price}"/></td>
                
                <td style="width:45px;">
                    <my:a href="/room/view/${room.id}" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open" style="height:20px;" aria-hidden="true"></span></my:a>
                </td>
                <td style="width:45px;">
                    <my:a href="/room/edit/${room.id}" class="btn btn-primary"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></my:a>
                </td>
                <td style="width:45px;">
                    <my:a href="/room/delete/${room.id}" class="btn btn-primary"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></my:a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>