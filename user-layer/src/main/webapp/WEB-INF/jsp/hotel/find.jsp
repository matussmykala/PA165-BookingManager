<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Find your hotel">
    <jsp:attribute name="body">
        

        
         <form:form method="get" action="${pageContext.request.contextPath}/hotel/search/${filter}"
                    cssClass="form-horizontal">
             
                   
                    <form name="form">
                        <select name="filter">
                        <option selected>Filter
                        <option value=1>Name</option>
                        <option value=2>Address</option>
                    </select>   
                  
                   <div class="col-sm-10">
                        <input class="form-control" name="goal" value="<c:out value='${filter}'/>" placeholder="Search" type="text"/>
                   </div>
                   <button class="btn btn-primary" type="submit">Confirm</button>
     
         </form:form>

    
    
    
    </jsp:attribute>
</my:pagetemplate>