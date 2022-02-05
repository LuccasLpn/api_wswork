package academy.ws_work.modules.security.config;

import academy.ws_work.exceptions.ValidationException;
import academy.ws_work.modules.security.domain.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public static final int EXPIRATION = 600_000;
    public static final String PASSWORD = "768b34fd-9a8b-4bb8-82a2-3f2bb679f0c1"; // COMO E APENAS DESENVOLVIMENTO COLOQUEI A SENHA AQUI, MAS EM PRODUÇÃO
                                                                                        // ELA NÃO PODE FICA AQUI

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            User user = new ObjectMapper()
                    .readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword(),
                    new ArrayList<>()
            ));
        } catch (IOException e) {
            throw new ValidationException("Failed Authentication User");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
                                            throws IOException, ServletException {
      User user = (User) authResult.getPrincipal();
      String token = JWT.create()
              .withSubject(user.getUsername())
              .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION))
              .sign(Algorithm.HMAC512(PASSWORD));
      response.getWriter().write(token);
      response.getWriter().flush();
    }
}
