package com.edix.restful.zaps.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edix.restful.zaps.modelo.entities.Usuario;
import com.edix.restful.zaps.repository.UsuarioRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public MyUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        String roleString = usuario.getRole().toString();
        
        return org.springframework.security.core.userdetails.User
            .withUsername(usuario.getUsername())
            .password(usuario.getPassword())
            .roles(roleString)
            .build();
    }
}
