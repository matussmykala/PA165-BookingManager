<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
<jsp:attribute name="body">

    <div class="jumbotron">
        <h2>Welcome to PA165 project Booking Manager !</h2>
        <h4>Find your best accommodation!</h4>
    </div>
 
 <form:form method="post" action="${pageContext.request.contextPath}/hotel/find"
               modelAttribute="hotelCreate" cssClass="form-horizontal">   
     
    <form class="form-horizontal">
        <fieldset><legend>Find the Best Deals</legend>
            <div class="form-group"><label class="col-lg-2 control-label" for="destination">Destination</label>
                <div class="col-lg-10"><input class="form-control" id="destination" placeholder="city" type="text" /></div>
            </div>
            <div class="form-group"><label class="col-lg-2 control-label" for="dateFrom">Check-in Date </label>
                <div class="col-lg-10"><input class="form-control" id="dateFrom" placeholder="DD.MM.RRRR" type="text" /></div>
            </div>
            <div class="form-group"><label class="col-lg-2 control-label" for="dateTo">Check-out Date </label>
                <div class="col-lg-10"><input class="form-control" id="dateTo" placeholder="DD.MM.RRRR" type="text" /></div>
            </div>
            <div class="checkbox"><label><input type="checkbox" /> I am not ready to enter dates </label></div>
            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-2"><button class="btn btn-default" type="reset">Clear</button>
                    <button class="btn btn-primary" type="submit">Submit</button></div>
            </div>
        </fieldset>
    </form>
 </form:form>
    


   

</jsp:attribute>
</my:pagetemplate>