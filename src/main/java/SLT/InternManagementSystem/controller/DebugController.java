package SLT.InternManagementSystem.controller;

import SLT.InternManagementSystem.entity.Role;
import SLT.InternManagementSystem.entity.User;
import SLT.InternManagementSystem.repository.UserRepository;
import SLT.InternManagementSystem.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/debug")
@CrossOrigin(origins = "*")
public class DebugController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    // Get all users and their roles (for debugging)
    @GetMapping("/users")
    public ResponseEntity<List<Map<String, Object>>> getAllUsers() {
        List<User> users = userRepository.findAll();

        List<Map<String, Object>> userInfo = users.stream().map(user -> {
            Map<String, Object> info = new HashMap<>();
            info.put("id", user.getId());
            info.put("email", user.getEmail());
            info.put("role", user.getRole().name());
            info.put("emailVerified", user.isEmailVerified());
            info.put("authProvider", user.getAuthProvider().name());
            return info;
        }).toList();

        return ResponseEntity.ok(userInfo);
    }

    // Update user role
    @PutMapping("/users/{userId}/role")
    public ResponseEntity<Map<String, String>> updateUserRole(
            @PathVariable int userId,
            @RequestBody Map<String, String> request) {

        String roleName = request.get("role");

        try {
            Role role = Role.valueOf(roleName);

            Optional<User> userOpt = userRepository.findById(userId);
            if (userOpt.isEmpty()) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "User not found");
                return ResponseEntity.notFound().build();
            }

            User user = userOpt.get();
            user.setRole(role);
            userRepository.save(user);

            Map<String, String> response = new HashMap<>();
            response.put("message", "User role updated successfully");
            response.put("email", user.getEmail());
            response.put("newRole", role.name());

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Invalid role. Must be one of: Intern, Supervisor, Admin");
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Debug JWT token
    @PostMapping("/token/debug")
    public ResponseEntity<Map<String, Object>> debugToken(@RequestBody Map<String, String> request) {
        String token = request.get("token");

        if (token == null || token.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Token is required");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            String email = jwtService.extractEmail(token);
            String role = jwtService.extractClaim(token, claims -> claims.get("role", String.class));
            Integer userId = jwtService.extractClaim(token, claims -> claims.get("user_id", Integer.class));
            var allClaims = jwtService.extractAllClaims(token);

            Map<String, Object> response = new HashMap<>();
            response.put("email", email);
            response.put("role", role);
            response.put("userId", userId);
            response.put("allClaims", allClaims);
            response.put("isValid", !jwtService.isTokenExpired(token));

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Invalid token");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Find user by email
    @GetMapping("/users/email/{email}")
    public ResponseEntity<Map<String, Object>> getUserByEmail(@PathVariable String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "User not found");
            return ResponseEntity.notFound().build();
        }

        User user = userOpt.get();
        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("email", user.getEmail());
        response.put("role", user.getRole().name());
        response.put("emailVerified", user.isEmailVerified());
        response.put("authProvider", user.getAuthProvider().name());

        return ResponseEntity.ok(response);
    }
}
