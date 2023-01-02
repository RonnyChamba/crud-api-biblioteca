package com.ideas.org.crud.service;

import com.ideas.org.crud.dto.UserDTO;
import com.ideas.org.crud.dto.UserResponse;
import com.ideas.org.crud.entities.Rol;
import com.ideas.org.crud.entities.User;
import com.ideas.org.crud.enums.RolEnun;
import com.ideas.org.crud.exeption.DuplicatedResourceException;
import com.ideas.org.crud.exeption.NotDataAccessException;
import com.ideas.org.crud.exeption.NotFoundException;
import com.ideas.org.crud.mapper.Mapper;
import com.ideas.org.crud.repository.RolRepository;
import com.ideas.org.crud.repository.UserRepository;
import com.ideas.org.crud.security.dto.AuthUser;
import com.ideas.org.crud.security.dto.JwtDTO;
import com.ideas.org.crud.security.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    private RolRepository rolRepository;
    private UserRepository userRepository;
    private Mapper mapper;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public UserResponse save(UserDTO userDTO) {

        try {
            if (userRepository.existsByUsernameIgnoreCase(userDTO.getUsername())) {
                throw new DuplicatedResourceException(String.format("Usuario %s ya existe", userDTO.getUsername()));
            }

            User user = mapper.mapperToEntity(userDTO);

            String password = passwordEncoder.encode(user.getPassword());
            user.setPassword(password);


            Set<Rol> roles = new HashSet<>();
            roles.add(rolRepository.findByRolEnun(RolEnun.ROLE_USER).orElseThrow(
                    () -> new NotFoundException("No existe el rol seleccionado")
            ));

            if (userDTO.getRoles().equalsIgnoreCase(RolEnun.ROLE_ADMIN.name())) {

                roles.add(rolRepository.findByRolEnun(RolEnun.ROLE_ADMIN).orElseThrow(
                        () -> new NotFoundException("No existe el rol selecccionado")
                ));
            }
            user.setRoles(roles);

            return mapper.mapperToDTO(userRepository.save(user));

        } catch (DataAccessException e) {

            LOGGER.error("Error to save User", e);

            throw new NotDataAccessException("Ocurrio un error al ejecutar la operación");
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> finAll() {


        try {

            List<User> userList = userRepository.findAll();
            return mapper.mapperToDTO(userList);


        } catch (DataAccessException e) {

            LOGGER.error("Error to list User", e);

            throw new NotDataAccessException("Ocurrio un error al ejecutar la operación");
        }
    }

    @Override
    public JwtDTO login(AuthUser authUser) {

        /**
         * Aqui si estoy autenticacion
         */
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authUser.getUsername(), authUser.getPassword())
        );

        // Actualizar el contexto de autenticacion
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.generateToken((UserDetails) authentication.getPrincipal());

        JwtDTO jwtDTO = new JwtDTO();
        jwtDTO.setToken(token);

        return jwtDTO;
    }

    @Override
    @Transactional
    public void delete(Integer ide) {

        try {

            if (userRepository.existsById(ide)) {
                userRepository.deleteById(ide);
            }

        } catch (DataAccessException e) {

            LOGGER.error("Error al eliminar usuario", e);
            throw new NotDataAccessException("Ocurrio un eror al ejecutar operacion");
        }

    }
}
