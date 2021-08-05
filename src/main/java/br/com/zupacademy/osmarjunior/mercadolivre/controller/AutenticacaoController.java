package br.com.zupacademy.osmarjunior.mercadolivre.controller;

import br.com.zupacademy.osmarjunior.mercadolivre.config.service.TokenService;
import br.com.zupacademy.osmarjunior.mercadolivre.controller.dto.TokenDto;
import br.com.zupacademy.osmarjunior.mercadolivre.controller.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm loginForm){
        UsernamePasswordAuthenticationToken loginData = loginForm.toUsernamePasswordAuthenticationToken();

        try {
            Authentication authentication = authManager.authenticate(loginData);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException exception){

            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
