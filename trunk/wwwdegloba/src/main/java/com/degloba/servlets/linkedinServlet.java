package com.degloba.servlets;

import java.io.IOException;
import java.util.EnumSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/////////////////import com.cs.liveebiz.server.common.ui.ImportLinkedInProfile;
/////////////////import com.cs.liveebiz.server.common.util.UserContextHolder;
/*import com.benfante.jslideshare.SlideShareAPI;
import com.benfante.jslideshare.SlideShareAPIFactory;
import com.benfante.jslideshare.messages.Slideshow;
import com.benfante.jslideshare.messages.User;*/
import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClientFactory;
import com.google.code.linkedinapi.client.enumeration.ProfileField;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthService;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthServiceFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;
import com.google.code.linkedinapi.schema.Education;
import com.google.code.linkedinapi.schema.Person;
import com.google.code.linkedinapi.schema.Position;

@SuppressWarnings("serial")
public class linkedinServlet extends HttpServlet {

private static final String AUTH_TOKEN_PARAMETER = "oauth_token";
private static final String AUTH_TOKEN_VERIFIER_PARAMETER = "oauth_verifier";
/**
* Consumer Key you can get this from linked in by adding your application at (https://www.linkedin.com/secure/developer)
*/
private static final String CONSUMER_KEY_OPTION = "jgg3OFl1DdoeepiohoiObr9coiZgE-q4_jeKtH-btsmPXQK1XbFFocJxcLwHf-XF";  //"consumerKey";

/**
* Consumer Secret you can get this from linked in by adding your application
*/
private static final String CONSUMER_SECRET_OPTION = "paOcIRGQ1RbJW5jBqAKKoBmqxWASPkE15X-pnfajNYqhoJLAYlWDNYmbf5S5s6pj";  //"consumerSecret";

public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 

	/*
	SlideShareAPI ssapi =  SlideShareAPIFactory.getSlideShareAPI("FfZhXNRX", "TAqlQKeN"  );   // Your API key + Your shared secret
	String embedCode="";

	User user = ssapi.getSlideshowByUser("degloba"); // getSlideshow("soa-sca-esb"); //142806
	List<Slideshow> list = user.getSlideshows();
	
	
    for (Slideshow slideshow : list){
        if (slideshow.getTitle().compareTo("Soa sca esb") == 0 )
        	embedCode = slideshow.getEmbedCode();
      }
	
	System.out.println("Codi embed " + embedCode);
	*/
	
	try {
	
		String oauthToken = req.getParameter(AUTH_TOKEN_PARAMETER);  // associat a l'usuari linkedin que s'ha loginat
		String oauthVerifier = req.getParameter(AUTH_TOKEN_VERIFIER_PARAMETER);  // associat a l'usuari linkedin que s'ha loginat
		
		final String consumerKeyValue = CONSUMER_KEY_OPTION;
		final String consumerSecretValue = CONSUMER_SECRET_OPTION;
		
		HttpSession session = req.getSession();
		
		final LinkedInOAuthService oauthService = LinkedInOAuthServiceFactory.getInstance().createLinkedInOAuthService(consumerKeyValue, consumerSecretValue);
		
		if (oauthToken == null && oauthVerifier == null ) {
		
			// REQUEST TOKEN
			LinkedInRequestToken requestToken = oauthService.getOAuthRequestToken("http://localhost:8080/degloba/servlets/linkedinServlet");  //calbackUrl
			
			
			System.out.println("Fetching request token from LinkedIn..."
			+ requestToken);
			session.setAttribute("requestToken", requestToken);
			
			String authUrl = requestToken.getAuthorizationUrl();
			resp.sendRedirect(authUrl);
		} else {
			// REQUEST TOKEN
			String sVerifier = oauthVerifier; // es el codiPIN que retornaria si possesim la authUrl en el browser
			
			LinkedInRequestToken requestToken = (LinkedInRequestToken) session.getAttribute("requestToken");
			
			// ACCESTOKEN
			LinkedInAccessToken accessToken = oauthService.getOAuthAccessToken(requestToken,sVerifier);
			
			System.out.println("Access token: " + accessToken.getToken());
			System.out.println("Token secret: " + accessToken.getTokenSecret());
			
			final LinkedInApiClientFactory factory = LinkedInApiClientFactory.newInstance(consumerKeyValue, consumerSecretValue);
			
			final LinkedInApiClient client = factory.createLinkedInApiClient(accessToken);  // usuari que ha donat permis a l'aplicació degloba (ex: joan robledo)
			
			//OPERACIONS API
			System.out.println("Fetching profile for current user.");
			
			Person profile = client.getProfileForCurrentUser(EnumSet.of(ProfileField.ID));
			Person profile2 = client.getProfileById(profile.getId());
			
			
			
			
			// en aquest punt caldria afegir la relació de l'usuari que ha donat permís (ex: joan robledo) 
			// amb l'usuari degloba
			
			
			
			
			
			
			
			printResult(profile2);
			
			
			//resp.sendRedirect("page to which you want to redirect after processing the result");
}
}
catch (Throwable ex) {
	// TODO
	//resp.setContentType("text/plain");
	ex.printStackTrace();
	}
}


/**
* Print the result of API call.
*/
private void printResult(Person profile) {

System.out.println("ImagePath" + profile.getAssociations());
System.out.println("Industry:" + profile.getIndustry());

///////////////////////ImportLinkedInProfile importProfile = new ImportLinkedInProfile();

//Here in this bean you can add your functionality 
//////////////////////importProfile.processImportedResult(profile); 


for (Position position:profile.getPositions().getPositionList()) {

	System.out.println("position:" + position.getTitle());
	System.out.println("comp:" + position.getCompany().getName());
	
	if(position.getEndDate() != null)
		System.out.println("EndDate:" + position.getEndDate().getMonth() + "" +position.getEndDate().getYear());
	
	if(position.getStartDate() != null)
		System.out.println("StartDate:" + position.getStartDate().getMonth() + "" +position.getStartDate().getYear());
	
	System.out.println("Summary:" + position.getSummary());
}


for (Education education:profile.getEducations().getEducationList()) {

	System.out.println("degree:" + education.getDegree());
	System.out.println("school name:" + education.getSchoolName());
	
	if(education.getEndDate() != null)
		System.out.println("EndDate:" + education.getEndDate().getMonth() + "" +education.getEndDate().getYear());
	
	if(education.getStartDate() != null)
		System.out.println("StartDate:" + education.getStartDate().getMonth() + "" +education.getStartDate().getYear());
	}
	}
} 

