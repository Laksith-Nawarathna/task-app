package lk.ijse.dep9.app.api.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.dep9.app.dto.UserDTO;
import lk.ijse.dep9.app.service.custom.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;


@Component
public class SecurityFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (req.getServletPath().matches("/api/v1/users/?") && req.getMethod().equals("POST")){
            chain.doFilter(req, res);
        } else if (req.getServletPath().matches("/api/v1/auth/.+")) {
            chain.doFilter(req, res);
        }else{

            String authorization = req.getHeader("Authorization");
            if (authorization != null && authorization.startsWith("Basic")){
                String token = authorization.substring("Basic".length() + 1);
                String credentials = new String(Base64.getDecoder().decode(token));
                String username = credentials.split(":")[0];
                String password = credentials.split(":")[1];
                System.out.println(username +" : "+ password);
            }

            HashMap<String, Object> errorAttributes = new LinkedHashMap<>();
            errorAttributes.put("status", HttpStatus.UNAUTHORIZED.value());
            errorAttributes.put("error", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            errorAttributes.put("message", "Invalid Login Credentials");
            errorAttributes.put("timestamp", new Date().toString());

            ObjectMapper objectMapper = new ObjectMapper();
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.setContentType("application/json");
            objectMapper.writeValue(res.getWriter(), errorAttributes);
        }
    }
}
