package com.edix.restful.zaps.service;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edix.restful.zaps.Auth.AuthResponse;
import com.edix.restful.zaps.Auth.LoginRequest;
import com.edix.restful.zaps.Auth.RegisterRequest;
import com.edix.restful.zaps.Auth.Role;
import com.edix.restful.zaps.modelo.entities.Usuario;
import com.edix.restful.zaps.repository.UsuarioRepository;
import com.edix.restful.zaps.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=usuarioRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();

    }

    public AuthResponse register(RegisterRequest request) {
        Usuario user = Usuario.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode( request.getPassword()))
            .nombre(request.getNombre())
            .email(request.getEmail())
            .direccion(request.getDireccion())
            .telefono(request.getTelefono())
            .role(Role.USER)
            .build();

        usuarioRepository.save(user);

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
        
    }

}
