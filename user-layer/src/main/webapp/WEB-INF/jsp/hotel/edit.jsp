<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Edit hotel">
<jsp:attribute name="body">

     <form:form method="post" action="${pageContext.request.contextPath}/hotel/update/${hotel.id}"
               modelAttribute="hotelUpdate" cssClass="form-horizontal">
          
              
         
         <div class="form-group">
             <label for="name" class="col-lg-2 control-label">Name</label>
                <div class="col-sm-10">
                <input class="form-control" id="name" placeholder="${hotel.name}" type="text">
             </div>
         </div>
                    
       <div class="form-group">
           <label for="address" class="col-lg-2 control-label">Address</label>
           <div class="col-sm-10">
           <input class="form-control" id="address" placeholder="${hotel.address}" type="text">
            </div>
        </div>
                
          
             
         <div class="form-group">
            <label for="description" class="col-lg-2 control-label">Description</label>
            <div class="col-sm-10">
                <input class="form-control" id="description" placeholder="${hotel.description}" type="text">
                 
        
      </div>
    </div>
               


        <button class="btn btn-primary" type="submit">Update hotel</button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>
