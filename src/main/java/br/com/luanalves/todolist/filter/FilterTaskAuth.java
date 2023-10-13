package br.com.luanalves.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.luanalves.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRespository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // pegar a autenticação (usuario e senha)
        var authorization = request.getHeader("Authorization");

        var authEncoded = authorization.substring("Basic".length()).trim();

        byte[] authDecode = Base64.getDecoder().decode(authEncoded);

        var authString = new String(authDecode);
        System.out.println(authString);

        String[] credentials = authString.split(":");

        String username = credentials[0];
        String password = credentials[1];

        // this.userRespository.findByPassword(password);

        // valdiar usúario
        var user = this.userRespository.findByUsername(username);

        if (user == null) {
            response.sendError(401, "Usúario sem autorização");
        } else {
            // validar senha
            var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
            if(passwordVerify.verified) {
                filterChain.doFilter(request, response);
            }else {
                response.sendError(401, "senha inválida")
            }
        }

        // segue viagem

        filterChain.doFilter(request, response);
    }

}
