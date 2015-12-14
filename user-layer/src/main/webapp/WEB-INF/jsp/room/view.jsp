
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Room overview">
<jsp:attribute name="body">


    <table class="table">

        <tbody>
            <tr>
                <td>ID</td>
                <td>${room.id}</td>
            </tr>
            <tr>
                <td>Name</td>
                <td><c:out value="${room.name}"/></td>
            </tr>
            <tr>
                <td>Hotel name</td>
                <td><c:out value="${room.hotel.name}"/></td>
            </tr>
            <tr>
                <td>Number of beds</td>
                <td><c:out value="${room.numberOfBeds}"/></td>
            </tr>
            <tr>
                <td>Price</td>
                <td><c:out value="${room.price}"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <div class="row"> 
                        <div class="col-xs-1">
                            <form action="${pageContext.request.contextPath}/room/edit/${room.id}">
                                <button class="btn btn-primary"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button>
                            </form>
                        </div>
                        <div class="col-xs-1">
                            <my:a href="/room/delete/${room.id}" class="btn btn-primary"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></my:a>
                        </div>
                        <div class="col-xs-1">
                            <my:a href="/room/list" class="btn btn-primary">Show all</my:a>
                        </div>
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
            

            
     



</jsp:attribute>
</my:pagetemplate>
