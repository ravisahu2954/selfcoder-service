/*
 * package com.selfcoder.selfcoder.config;
 * 
 * import java.util.Optional;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.stereotype.Component;
 * 
 * import com.selfcoder.selfcoder.entity.User; import
 * com.selfcoder.selfcoder.repository.UserRepository;
 * 
 * @Component public class UserInfoServiceImpl implements UserDetailsService {
 * 
 * @Autowired private UserRepository userRepository;
 * 
 * 
 * 
 * @Override public UserDetails loadUserByUsername(String username) throws
 * UsernameNotFoundException {
 * 
 * Optional<User> user = userRepository.findByUserName(username); return
 * user.map(UserDetail::new).get();
 * 
 * }
 * 
 * }
 */