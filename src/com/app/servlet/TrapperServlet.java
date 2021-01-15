package com.app.servlet;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import om.app.handler.CountriesHandler;
import om.app.handler.Handler;
import om.app.handler.LangugesHandler;
/*to make any class as servlet class extends
 from HttpServlet predefined class*/
public class TrapperServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, 
			HttpServletResponse res) 
					throws ServletException, IOException {
	   String path=null;
	   Handler handler=null;
	   String lvn=null; //logical view name(jsp page name)
	   String target=null;
	   RequestDispatcher rd=null;
       try {
	   //Action Management
	   path=req.getServletPath();//gives lang.do or countries.do
	   if(path.equalsIgnoreCase("/lang.do")) {
		   handler= new LangugesHandler();
	   }
	   else if(path.equalsIgnoreCase("/countries.do")) {
		   handler=new CountriesHandler();
	   }
	   else {
		   throw new IllegalArgumentException("Invalid request url");
	   }
	   //invoke handler method
	   lvn=handler.handle(req, res);
	}
    catch(Exception e) {
   		e.printStackTrace();
   	}
     //perform view management(mapping lvn to physical jsp's)
       try {
       if(lvn.equalsIgnoreCase("disp_lang")) {
    	   target="show_languages.jsp";
       }
       else if(lvn.equalsIgnoreCase("disp_countires")) {
    	   target="show_countries.jsp";
       }
       else
    	   throw new IllegalArgumentException("Invalid lvn");
       //forward to Target page or result page
       rd=req.getRequestDispatcher(target);
       rd.forward(req, res);
	}
       catch (Exception e) {
	    e.printStackTrace();
	}
	}
   
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   doGet(req, resp);
	}
}
