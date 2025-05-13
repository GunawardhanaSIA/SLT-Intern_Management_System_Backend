package SLT.InternManagementSystem.service;

import SLT.InternManagementSystem.dto.UserDto;
import SLT.InternManagementSystem.entity.*;
import SLT.InternManagementSystem.mapper.UserMapper;
import SLT.InternManagementSystem.repository.UserRepository;
import SLT.InternManagementSystem.repository.VerificationRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationService {
    @Autowired
    private JavaMailSender mailSender;

    private final UserRepository userRepository;
    private final VerificationRepository verificationRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    private final String CLIENT_ID = "96778155456-3ji09r49k7im0kid1end8k161ho4ic9o.apps.googleusercontent.com";

    public AuthenticationService(UserRepository userRepository, VerificationRepository verificationRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.verificationRepository = verificationRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

//    public AuthenticationResponse register(UserDto request) {
//        Optional<User> existingUser = userRepository.findByUsername(request.getUsername());
//
//        if (existingUser.isPresent()) {
//            String token = jwtService.generateToken(existingUser.get());
//            return new AuthenticationResponse(token);
//        }
//
//        User user = new User();
//        user.setUsername(request.getUsername());
////        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setRole(request.getRole());
////        user.setState(1);
//
//        user = userRepository.save(user);
//
//        String token = jwtService.generateToken(user);
//        return new AuthenticationResponse(token);
//    }


    public AuthenticationResponse ManualRegister(UserDto request) throws MessagingException {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already in use.");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setAuthProvider(AuthProvider.Local);
        user.setEmailVerified(false);

        user = userRepository.save(user);

        String verificationTokenStr = UUID.randomUUID().toString();

        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(verificationTokenStr);
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(LocalDateTime.now().plusHours(24));

        verificationRepository.save(verificationToken);

        SendVerificationEmail(user.getEmail(), verificationTokenStr);

        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }



    public void SendVerificationEmail(String email, String token) throws MessagingException {
        String verificationUrl = "http://localhost:8080/verify?token=" + token;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject("Complete Registration!");
        helper.setText(
                "<html>" +
                        "<body>" +
                        "<p>To verify your account, please click the button below:</p>" +
                        "<a href=\"" + verificationUrl + "\" style=\"display: inline-block; padding: 10px 20px; font-size: 16px; color: white; background-color: #28a745; text-decoration: none; border-radius: 5px;\">Verify Account</a>" +
                        "</body>" +
                        "</html>",
                true
        );

        mailSender.send(message);
    }



    public void VerifyToken(String token, HttpServletResponse response) throws IOException {
        Optional<VerificationToken> optionalToken = verificationRepository.findByToken(token);

        if (optionalToken.isEmpty()) {
            response.sendRedirect("http://localhost:5173/authenticate/invalid-token");
            return;
        }

        VerificationToken verificationToken = optionalToken.get();

        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            response.sendRedirect("http://localhost:5173/authenticate/token-expired");
            return;
        }

        User user = verificationToken.getUser();
        user.setEmailVerified(true);
        userRepository.save(user);

        response.sendRedirect("http://localhost:5173/authenticate/signup-successful");
    }



    public AuthenticationResponse GoogleRegister(String tokenString) {
        GoogleIdToken.Payload payload = verifyGoogleToken(tokenString);
        if (payload == null) {
            return null;
        }

        User user = processUser(payload);

        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }



    public GoogleIdToken.Payload verifyGoogleToken(String tokenString) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(), JacksonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();

            GoogleIdToken idToken = verifier.verify(tokenString);
            return idToken != null ? idToken.getPayload() : null;
        } catch (Exception e) {
            return null;
        }
    }



    public User processUser(GoogleIdToken.Payload payload) {
        String email = payload.getEmail();

        Optional<User> optionalUser = userRepository.findByEmail(email);
        User user = optionalUser.orElseGet(User::new);

        user.setEmail(email);
        user.setEmailVerified(true);
        user.setAuthProvider(AuthProvider.Google);

        if (!optionalUser.isPresent()) {
            user.setRole(Role.Intern);
        }

        return userRepository.save(user);
    }



    public AuthenticationResponse authenticate(UserDto userDto) {
        System.out.println("Inside authenticate service");
        System.out.println("Password: " + userDto.getPassword());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getEmail(),
                        userDto.getPassword()
                )
        );

        User existingUser = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid email or password"));

        System.out.println("Password in db: " + existingUser.getPassword());

        if(!existingUser.isEmailVerified()) {
            throw new RuntimeException("Please verify your email before logging in.");
        }

        String token = jwtService.generateToken(existingUser);
        return new AuthenticationResponse(token);
    }

}
