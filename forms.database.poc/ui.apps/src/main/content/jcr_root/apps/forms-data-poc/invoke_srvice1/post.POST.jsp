<%@include file="/libs/fd/af/components/guidesglobal.jsp" %>
<%@include file="/libs/foundation/global.jsp"%>
<%@page import="com.day.cq.wcm.foundation.forms.FormsHelper,com.forms.learn.core.database,
             org.apache.sling.api.resource.ResourceUtil,
             org.apache.sling.api.resource.ValueMap" %>
<%@taglib prefix="sling"
                uri="http://sling.apache.org/taglibs/sling/1.0" %>
<%@taglib prefix="cq"
                uri="http://www.day.com/taglibs/cq/1.0"
%>
<cq:defineObjects/>
<sling:defineObjects/>
<%


    String customer_ID = request.getParameter("customer_ID");
    String customer_Name = request.getParameter("customer_Name");
    String customer_Shipping_Address = request.getParameter("customer_Shipping_Address");
    String customer_State = request.getParameter("customer_State");
 String customer_ZIPCode = request.getParameter("customer_ZIPCode");
 String customer_Email = request.getParameter("customer_Email");


   /*  HandleForm hf = new HandleFormImp();
	hf.injestFormDataDB(customer_ID,customer_Name,customer_Shipping_Address,customer_Email,customer_State,customer_ZIPCode);
   com.forms.learn.core.database.HandleForm hf=sling.getService(com.forms.learn.core.database.HandleForm.class); */

    com.forms.learn.core.database.HandleForm hf = sling.getService(com.forms.learn.core.database.HandleForm.class);
    hf.injestFormDataDB(customer_ID,customer_Name,customer_Shipping_Address, customer_State,customer_ZIPCode,customer_Email);

%>

