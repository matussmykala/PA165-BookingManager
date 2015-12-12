
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Hotel Administration">
<jsp:attribute name="body">


    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>hotel name</th>
            <th>address</th>
            <th>destination</th>
            <th>last update</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>${room.id}</td>
                <td><c:out value="${room.name}"/></td>
                <td><c:out value="${room.numberOfBeds}"/></td>
                <td><c:out value="${room.price}"/></td>
                <td>
                     <form action="${pageContext.request.contextPath}/room/edit/${room.id}">
                        <button class="btn btn-primary">Edit</button>
                    </form>
                </td>
                <td>
                    <my:a href="/room/delete/${room.id}" class="btn btn-primary">Delete</my:a>
                </td>
            </tr>
        </tbody>
    </table>
            

            
     <form method="get" action="${pageContext.request.contextPath}/room/list/">
         <p align="left">
            <button type="submit" class="btn btn-primary">Show all</button>
         </p>
     </form>



</jsp:attribute>
</my:pagetemplate>
