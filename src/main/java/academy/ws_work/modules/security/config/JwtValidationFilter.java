package academy.ws_work.modules.security.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class JwtValidationFilter extends BasicAuthenticationFilter {



    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String atributo = request.getHeader("Authorization");
        if (atributo != null && atributo.startsWith("Bearer ")){
            chain.doFilter(request,response);
            return;
        }
            String token = atributo.split(" ")[1].trim();
            UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
    }
    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token){
        String user = JWT.require(Algorithm.HMAC512(JwtAuthenticationFilter.PASSWORD))
                .build()
                .verify(token)
                .getSubject();
        if (user == null){
            return null;
        }
        return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
    }
}