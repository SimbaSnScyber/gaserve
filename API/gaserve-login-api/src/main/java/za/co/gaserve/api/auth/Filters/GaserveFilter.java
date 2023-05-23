package za.co.gaserve.api.auth.Filters;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static za.co.gaserve.api.auth.AppTokenProvider.addAuthentication;
import static za.co.gaserve.api.auth.AppTokenProvider.getUserFromToken;


@Component
public class GaserveFilter implements Filter {

   @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

   @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Optional<String> userFromToken = getUserFromToken(request);

        if (!userFromToken.isPresent()) {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
            return;
        }
        request.setAttribute("userId", userFromToken.get());
        addAuthentication(response, userFromToken.get());
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
