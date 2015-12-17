<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<my:pagetemplate title="Hotel Administration">
<jsp:attribute name="body">

    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th><f:message key="hotel.name"/></th>
            <th><f:message key="hotel.destination"/></th>
            <th><f:message key="hotel.description"/></th>
            <th><f:message key="hotel.lastUpdate"/></th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>${hotel.id}</td>
                <td><c:out value="${hotel.name}"/></td>
                <td><c:out value="${hotel.address}"/></td>
                <td><c:out value="${hotel.description}"/></td>
                <td><fmt:formatDate value="${hotel.lastUpdateDay}" pattern="yyyy-MM-dd"/></td>
                <c:if test="${authenticatedUser.admin == true}">
                    <td>
                        <form action="${pageContext.request.contextPath}/hotel/edit/${hotel.id}">
                            <button class="btn btn-primary"><f:message key="hotel.edit"/></button>
                        </form>
                   </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/hotel/delete/${hotel.id}">
                            <button type="submit" class="btn btn-primary"><f:message key="hotel.delete"/></button>
                        </form>
                    </td>
                </c:if>
           </tr>
        </tbody>
    </table>
         
     <form method="get" action="${pageContext.request.contextPath}/hotel/list/">
         <p align="left">
            <button type="submit" class="btn btn-primary"><f:message key="hotel.show"/></button>
         </p>
     </form>

</jsp:attribute>
</my:pagetemplate>
