package com.rafaelchavesdias.receitas.service;

import com.rafaelchavesdias.receitas.model.Usuario;
import com.rafaelchavesdias.receitas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario!=null){
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(usuario.getRole());
            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(authority);
            User user = new User(usuario.getUsername(),usuario.getPassword(),authorities);
            return user;
        }
        return null;
    }

    /*@Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = Optional.ofNullable(usuarioRepository.findByUsername(username)).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<GrantedAuthority> authorityAdmin = AuthorityUtils.createAuthorityList("ROLE_USER","ROLE_ADMIN");
        List<GrantedAuthority> authorityUser = AuthorityUtils.createAuthorityList("ROLE_USER");
        return new User(user.getUsername(),user.getPassword(),user.getAdmin() ? authorityAdmin : authorityUser);
    }*/


}
