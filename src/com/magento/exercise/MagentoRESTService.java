package com.magento.exercise;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Session;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.magento.domain.MagentoOrder;
import com.magento.domain.ResponseMagento;
import com.magento.util.EmailUtil;

@Path("/")
public class MagentoRESTService {
	@POST
	@Path("/MagentoService")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseMagento magentoREST(InputStream incomingData) {
		StringBuilder magentoBuilder = new StringBuilder();
		ObjectMapper mapper = new ObjectMapper();
		ResponseMagento responseMagento = new ResponseMagento();
		
	    String smtpHostServer = "smtp.example.com";
	    String emailID = "email_me@example.com";
	    Properties props = System.getProperties();
	    String body = "This is the orders details:";
	    
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				magentoBuilder.append(line);
			}
			MagentoOrder magentoListOrder = mapper.readValue(magentoBuilder.toString(), MagentoOrder.class);
			
			if (!magentoListOrder.validationLineNumbers()) {				
				responseMagento.setCodeResponse("KO - Error 1");
				responseMagento.setMessage("The line numbers of the order items must be sequential (1,2,3...) starting at 1");
			}else if (!magentoListOrder.validationIsUnicStoreOrder()) {
				responseMagento.setCodeResponse("KO - Error 2");
				responseMagento.setMessage("It's not possible to have two orders with the same id in the same store.");
			}
			
			// Bonus 
			props.put("mail.smtp.host", smtpHostServer);
		    Session session = Session.getInstance(props, null);
		    body = "This is the orders details: <br>" + magentoListOrder.detailOrders(); 
		    
		    EmailUtil.sendEmail(session, emailID,"New Order for Magento", body);
						
		} catch (Exception e) {
			System.out.println("Error Parsing: - " + e.getMessage());
			responseMagento.setCodeResponse("KO - Error -1");
			responseMagento.setMessage(" Exception: " + e.getMessage());
			return responseMagento;
		}
 
		return responseMagento;
	}
 
	@GET
	@Path("/verify")
	@Produces(MediaType.TEXT_PLAIN)
	public Response verifyRESTService(InputStream incomingData) {
		String result = "magento REST Service Successfully started..";
 
		return Response.status(200).entity(result).build();
	}
 
}
