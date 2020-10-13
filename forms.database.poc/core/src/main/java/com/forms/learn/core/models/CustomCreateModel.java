package com.forms.learn.core.models;
import javax.inject.Inject;
import org.apache.sling.api.SlingHttpServletRequest;
import javax.annotation.PostConstruct;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
//SlingHttpServletRequest.class,
@Model(adaptables = {Resource.class},defaultInjectionStrategy= DefaultInjectionStrategy.OPTIONAL)
public class CustomCreateModel {



        @Inject
        private String firstName;
     
        private String message;

        
        @Inject 
        private String lastName;
        
        @Inject 
        private String contactNo;
   
     @PostConstruct
    protected void init() {
         
        message = "Hello World\n";
               
//        if (request != null) {
//            this.message += "Request Path: "+request.getRequestPathInfo().getResourcePath()+"\n";
//        }
// 
        message += "First Name: "+ firstName +" \n";
        message += "Last Name: "+ lastName + "\n";
         
        //logger.info("inside post construct");
    }

         public String getFirstName() {
            return firstName;
        }

       
        public String getLastName() {
            return lastName;
       }

      public String getMessage() {
        return message;
    }
      public String getContactNo() {
            return contactNo;
       }
}