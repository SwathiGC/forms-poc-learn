package com.forms.learn.core.database;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.engine.EngineConstants;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import java.util.Properties;
 
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
 
//Add the DataSourcePool package
import com.day.commons.datasource.poolservice.DataSourcePool; 
  
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.sql.SQLException;
import javax.sql.DataSource;
 
@Component
public class HandleFormImp implements HandleForm{
     
    /** Default log. */
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
     
    @Reference
    private DataSourcePool source;
  
    //Inject the Form Data into a database! 
    @Override
    public void injestFormDataDB(String customer_ID, String customer_Name, String customer_Shipping_Address, String customer_State, String customer_ZIPCode, String customer_Email)
{
         
        //Simply write out the values that are posted from the AEM form to the AEM log file
        log.info("DB Data posted from an AEM adaptive form - customer_ID: "+customer_ID +" customer_Name: "+customer_Name +" customer_Shipping_Address: "+customer_Shipping_Address +" customer_State "+customer_State) ;
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()); 
        System.out.println("Executed time is :"+timeStamp);
        Connection connection = null;
         
        int rowCount= 0; 
        try {
                          
              // Create a Connection object
        	connection =  getConnection();
             
               ResultSet rs = null;
               Statement s = connection.createStatement();
               Statement scount = connection.createStatement();
                   
               //Use prepared statements to protected against SQL injection attacks
               PreparedStatement pstmt = null;
               PreparedStatement ps = null; 
                             
               int pk = Integer.parseInt(customer_ID);    
               int intZIP =Integer.parseInt(customer_ZIPCode);    
                 
               log.info("****** THe PK IS is "+pk); 
                 
               String insert = "INSERT INTO detailsinfo(id,name,shipping,state,zipcode,email) VALUES(?, ?,?,?,?,?);";
               ps = connection.prepareStatement(insert);
                 
               ps.setInt(1,pk); 
               ps.setString(2, customer_Name);
               ps.setString(3, customer_Shipping_Address);
               ps.setString(4, customer_State);
               ps.setInt(5, intZIP);
               ps.setString(6, customer_Email);
              
               ps.execute();
               
               connection.commit();
                
        }
        catch (Exception e) {
          e.printStackTrace();
         }
        finally {
          try
          {
        	  connection.close();
          }
            
            catch (SQLException e) {
              e.printStackTrace();
            }
    }
        
  }
 
  //Returns a connection using the configured DataSourcePool 
    private Connection getConnection()
    {
             DataSource dataSource = null;
             Connection con = null;
             try
             {
                 //Inject the DataSourcePool right here! 
                 dataSource = (DataSource) source.getDataSource("dataSelection");
                 con = dataSource.getConnection();
                 return con;
                   
               }
             catch (Exception e)
             {
                 e.printStackTrace(); 
             }
                 return null; 
    }
           
  }
