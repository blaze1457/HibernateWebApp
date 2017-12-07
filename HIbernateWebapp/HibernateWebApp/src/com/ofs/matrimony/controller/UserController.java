package com.ofs.matrimony.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.codehaus.jettison.json.JSONObject;

import com.ofs.matrimony.model.User;
import com.ofs.matrimony.service.UserService;
import com.ofs.matrimony.service.UserServiceImpl;

@Path("/user")
public class UserController {
	
	UserService userService = new UserServiceImpl();

	@Path("/auth")
	@GET
	@Produces("application/json")
	public String authUserByEmail(
			@QueryParam("user") User user_details
					
			) throws Exception
	{
	
		User user = userService.findById(user_details.getId());
		
		String response="";
		JSONObject jsonObject = new JSONObject();
		
		String password = user.getUserPassword();
		String emailid = user.getEmailid();
		
		if(emailid.equalsIgnoreCase(user.getEmailid()) && password.equalsIgnoreCase(user.getId()))		{
		
		jsonObject.put("Status", "Success");
		jsonObject.put("user_Id", user.getId());
		jsonObject.put("emailid", user.getEmailid());
		
		response = jsonObject.toString();
	
		//}
		/*else{
			
			jsonObject.put("Status","Failure");			
			response = jsonObject.toString();
			
		}*/
		}
		return response;
		
	}
	@Path("/add")
	@POST
	@Consumes("text/html")
	@Produces("text/html")
	public String addUser(User user)
	{	
	return userService.addUser(user);
	}
	
	
	
}