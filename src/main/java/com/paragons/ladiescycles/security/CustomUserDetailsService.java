package com.paragons.ladiescycles.security;

import com.paragons.ladiescycles.data.models.Lady;
import com.paragons.ladiescycles.data.models.Role;
import com.paragons.ladiescycles.data.repositories.LadyRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final LadyRepository ladyRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Lady lady = ladyRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Username not found"));
        return new User(lady.getUsername(), lady.getPassword(), mapRolesToAuthority(lady.getRole()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthority(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
