package ru.otus.users.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.HandlerMapping;
import ru.otus.users.exeption.ApplicationException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import static ru.otus.users.constant.ErrorCodeConstant.ERROR_CODE;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String authHeader = request.getHeader("authorization");

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
        } else {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new ApplicationException(ERROR_CODE, "An exception occurred");
            }
        }
        final Optional<String> userId = getPathVariableUserId(request);

        final String token = authHeader.substring(7);
        Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
        if (userId.isPresent() && !claims.getSubject().equals(userId.get())) {
            throw new ApplicationException(ERROR_CODE, "Incorrect authorization");
        }
        request.setAttribute("claims", claims);
        request.setAttribute("blog", servletRequest.getParameter("id"));
        servletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        filterChain.doFilter(request, response);
    }

    private static Optional<String> getPathVariableUserId(HttpServletRequest request) {
        Optional<String> userId = Optional.empty();
        String pathInfo = request.getServletPath();
        if (pathInfo != null) {
            String[] parts = pathInfo.split("/");
            int indexOfName = Arrays.asList(parts).indexOf("user");
            if (indexOfName != -1) {
                userId = Optional.of(parts[indexOfName + 1]);
            }
        }
        return userId;
    }
}
