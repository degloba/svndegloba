package com.degloba.webapp.endpoints.cloud.google;

import java.util.ArrayList;
import java.util.List;

import com.degloba.webapp.endpoints.Email;
import com.degloba.webapp.endpoints.Message;
import com.degloba.webapp.endpoints.Todo;
import com.google.api.server.spi.auth.EspAuthenticator;
import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiIssuer;
import com.google.api.server.spi.config.ApiIssuerAudience;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.UnauthorizedException;

/**
 * The Echo API which Endpoints will be exposing.
 */
@Api(
    name = "endpoint",
    version = "v1",
    namespace =
    @ApiNamespace(
        ownerDomain = "endpoints.degloba.com",
        ownerName = "endpoints.degloba.com",
        packagePath = ""
    ),
    // [START_EXCLUDE]
    issuers = {
        @ApiIssuer(
            name = "firebase",
            issuer = "https://securetoken.google.com/wwwdegloba-1350",
            jwksUri =
                "https://www.googleapis.com/service_accounts/v1/metadata/x509/securetoken@system"
                    + ".gserviceaccount.com"
        )
    }
)

public class Endpoint {

  /**
   * Echo del missatge rebut. Si n és un enter enter no negatiu, es copia aquest missatge
   * moltes vegades en el missatge retornat.
   *
   * <p> Tingueu en compte que el nom s’especifica i sobreescriurà el nom per defecte de "{nom de classe}.{nom del mètode}". 
   * Per exemple, el valor per defecte és "Endpoint.echo".
   *
   * <p> Tingueu en compte que el mètode http no està especificat. Això passarà per defecte a un mètode HTTP raonable
   * depenent del nom del mètode de l'API. En aquest cas, el mètode HTTP serà per defecte POST.
   */
  // [START endpoint_method]
  @ApiMethod(name = "echo")
  public Message echo(Message message, @Named("n") @Nullable Integer n) {
    return doEcho(message, n);
  }
  // [END echo_method]

  /**
   * Echo del missatge rebut. Si n és un enter enter no negatiu, es copia aquest missatge
   * moltes vegades en el missatge retornat.
   *
   * <p> Tingueu en compte que el nom s’especifica i sobreescriurà el nom per defecte de "{nom de classe}.{nom del mètode}". 
   * Per exemple, el valor per defecte és "Endpoint.echo".
   *
   * <p> Tingueu en compte que httpMethod no està especificat. Això passarà per defecte a un mètode HTTP raonable
   * depenent del nom del mètode de l'API. En aquest cas, el mètode HTTP serà per defecte POST.
   */
  // [START echo_path]
  @ApiMethod(name = "echo_path_parameter", path = "endpoint/{n}")
  public Message echoPathParameter(Message message, @Named("n") int n) {
    return doEcho(message, n);
  }
  // [END echo_path]

  /**
   * Echoes the received message back. If n is a non-negative integer, the message is copied that
   * many times in the returned message.
   *
   * <p>Note that name is specified and will override the default name of "{class name}.{method
   * name}". For example, the default is "echo.echo".
   *
   * <p>Note that httpMethod is not specified. This will default to a reasonable HTTP method
   * depending on the API method name. In this case, the HTTP method will default to POST.
   */
  // [START echo_api_key]
  @ApiMethod(name = "echo_api_key", path = "endpoint_api_key", apiKeyRequired = AnnotationBoolean.TRUE)
  public Message echoApiKey(Message message, @Named("n") @Nullable Integer n) {
    return doEcho(message, n);
  }
  // [END echo_api_key]

  private Message doEcho(Message message, Integer n) {
    if (n != null && n >= 0) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < n; i++) {
        if (i > 0) {
          sb.append(" ");
        }
        sb.append(message.getMessage() + "adeu");
      }
      message.setMessage(sb.toString() + "hola");
    }
    return message;
  }

  /**
   * Obté el correu electrònic de l’usuari autenticat. Si l’usuari no està autenticat, retornarà un HTTP
   * 401.
   *
   * <p> Tingueu en compte que el nom no està especificat. Això passarà per defecte a "{nom de classe}.{Nom de mètode}". Per
   * Per exemple, el valor per defecte és "Endpoint.getUserEmail".
   *
   * <p> Tingueu en compte que el httpMethod no és necessari aquí. Sense el httpMethod, es GET dueu per defecte
   * al nom del mètode de l'API. httpMethod  s’afegeix aquí per a propòsits d’exemple.
   */
  // [START google_id_token_auth]
  @ApiMethod(
      httpMethod = ApiMethod.HttpMethod.GET,
      authenticators = {EspAuthenticator.class},
      audiences = {"YOUR_OAUTH_CLIENT_ID"},
      clientIds = {"YOUR_OAUTH_CLIENT_ID"}
  )
  public Email getUserEmail(User user) throws UnauthorizedException {
    if (user == null) {
      throw new UnauthorizedException("Invalid credentials");
    }

    Email response = new Email();
    response.setEmail(user.getEmail());
    return response;
  }
  // [END google_id_token_auth]

  /**
   * Gets the authenticated user's email. If the user is not authenticated, this will return an HTTP
   * 401.
   *
   * <p>Note that name is not specified. This will default to "{class name}.{method name}". For
   * example, the default is "echo.getUserEmail".
   *
   * <p>Note that httpMethod is not required here. Without httpMethod, this will default to GET due
   * to the API method name. httpMethod is added here for example purposes.
   */
  // [START firebase_auth]
  @ApiMethod(
      path = "firebase_user",
      httpMethod = ApiMethod.HttpMethod.GET,
      authenticators = {EspAuthenticator.class},
      issuerAudiences = {
          @ApiIssuerAudience(
              name = "firebase",
              audiences = {"YOUR-PROJECT-ID"}
          )
      }
  )
  public Email getUserEmailFirebase(User user) throws UnauthorizedException {
    if (user == null) {
      throw new UnauthorizedException("Invalid credentials");
    }

    Email response = new Email();
    response.setEmail(user.getEmail());
    return response;
  }
  // [END firebase_auth]
  
  
  @ApiMethod(
	      httpMethod = ApiMethod.HttpMethod.GET,
	      authenticators = {EspAuthenticator.class}
	  )
	  public List<Todo> getTodo() {
	  List<Todo> llista = new ArrayList<>(); 
	  
	    Todo todo = new Todo();
	    todo.setId("12");
	    todo.setTitle("es el titol que ve de degloba produccio GCP");
	    
	    llista.add(todo);
	    return llista;
	  }
}
