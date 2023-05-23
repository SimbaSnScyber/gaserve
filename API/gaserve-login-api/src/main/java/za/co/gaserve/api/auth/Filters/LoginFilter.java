package za.co.gaserve.api.auth.Filters;

import org.springframework.stereotype.Component;
import za.co.gaserve.api.auth.GoogleTokenVerifier;
import za.co.gaserve.api.auth.AppTokenProvider;
import za.co.gaserve.api.auth.HeaderMapRequestWrapper;
import za.co.gaserve.common.InvalidTokenException;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;

@Component
public class LoginFilter implements Filter {

  private GoogleTokenVerifier googleTokenVerifier;

  @Autowired
  public LoginFilter(GoogleTokenVerifier googleTokenVerifier) {
    this.googleTokenVerifier = googleTokenVerifier;
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                       FilterChain filterChain) throws IOException, ServletException {

    String idToken = ((HttpServletRequest) servletRequest).getHeader("X-ID-TOKEN");
    HttpServletResponse response = (HttpServletResponse) servletResponse;

    if (idToken != null) {
      final Payload payload;
      try {
        payload = googleTokenVerifier.verify(idToken);
        if (payload != null) {

          String username = payload.getSubject();

          // Get profile information from payload
          String email = payload.getEmail();
          String name = (String) payload.get("name");
          String pictureUrl = (String) payload.get("picture");
          String locale = (String) payload.get("locale");
          String familyName = (String) payload.get("family_name");
          String givenName = (String) payload.get("given_name");

          AppTokenProvider.addAuthentication(response, username);

          HttpServletRequest req = (HttpServletRequest)servletRequest;

          HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(req);

          requestWrapper.addHeader("oAuth","true");

          filterChain.doFilter(requestWrapper, response);
          return;
        }
      } catch (GeneralSecurityException | InvalidTokenException e) {
        // This is not a valid token,  will send HTTP 401 back
      }
    }
    ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED,"INVALID TOKEN");
  }

  @Override
  public void destroy() {
  }
}

