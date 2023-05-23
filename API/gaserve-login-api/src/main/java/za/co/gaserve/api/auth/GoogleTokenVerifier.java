package za.co.gaserve.api.auth;

import za.co.gaserve.common.InvalidTokenException;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

@Component
public class GoogleTokenVerifier {

  private static final HttpTransport transport = new NetHttpTransport();
  private static final JsonFactory jsonFactory = new JacksonFactory();
  private static final String CLIENT_ID_IOS = "751307338915-nm4lhpijbkgrnkabcjhj500f4v8pksio.apps.googleusercontent.com";
  private static final String CLIENT_ANDROID = "360562068743-e05pqs24u2da217fl32sfceicpd6j9tj.apps.googleusercontent.com";

  private static final String WEBAPP = "778231454716-949839k82t8k1sof3p9hfmpp84lh824q.apps.googleusercontent.com";


  public Payload verify(String idTokenString)
      throws GeneralSecurityException, IOException, InvalidTokenException {
    return GoogleTokenVerifier.verifyToken(idTokenString);
  }


  private static Payload verifyToken(String idTokenString)
      throws GeneralSecurityException, IOException, InvalidTokenException {
    final GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.
        Builder(transport, jsonFactory)
        .setIssuers(Arrays.asList("https://accounts.google.com", "accounts.google.com"))
        .setAudience(Arrays.asList(CLIENT_ANDROID,CLIENT_ID_IOS,WEBAPP))
        .build();

    GoogleIdToken idToken = null;
    try {
       idToken = verifier.verify(idTokenString);
    } catch (IllegalArgumentException e){
      // means token was not valid and idToken
      // will be null
      throw new InvalidTokenException("idToken is invalid");
    }

    if (idToken == null) {
      System.out.println("idToken is invalid");
      throw new InvalidTokenException("idToken is invalid");
    }

    return idToken.getPayload();
  }
}
