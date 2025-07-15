package com.example.my_otus_app.controller;

import com.example.my_otus_app.service.AuthService;
import com.example.my_otus_app.service.UserService;
import com.example.my_otus_app.model.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor

public class OtusContorller {
    private final AuthService authService;
    private final UserService userService;

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/user/register",
            produces = { "application/json" },
            consumes = { "application/json" }
    )

    public ResponseEntity<?> register(@Valid @RequestBody(required = false) Optional<RegisterRequest> registerRequest)
    {
        if (registerRequest.isPresent()) {
            RegisterRequest req = registerRequest.get();

            // Call the UserService to save the data
            UUID userId = userService.register(
                    req.getFirstName(),
                    req.getSecondName(),
                    req.getBirthdate() != null ? req.getBirthdate().toString() : null, // Convert LocalDate to String
                    req.getBiography(),
                    req.getCity(),
                    req.getPassword()
            );

            // Construct and return the response
            Register200Response response = new Register200Response();
            response.setUserId(userId.toString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            String errorMessage="Invalid data";
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(errorMessage);
            errorResponse.setCode(400);
            System.err.println(errorMessage);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/user/get/{id}",
            produces = { "application/json" }
    )

    public ResponseEntity<?> getUser(@PathVariable("id") String id, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader)
    {
        String token = extractTokenFromHeader(authorizationHeader);

        if (token == null) {
            String errorMessage="Missing or invalid Authorization header";
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(errorMessage);
            errorResponse.setCode(401);
            System.err.println(errorMessage);
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        }

        Optional<String> jwtUserId = authService.validateToken(token);

        if (jwtUserId.isEmpty()) {
            String errorMessage="Invalid or expired token";
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(errorMessage);
            errorResponse.setCode(401);
            System.err.println(errorMessage);
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        }

        UUID jwtUUID;
        try {
            jwtUUID = UUID.fromString(jwtUserId.get());
        } catch (IllegalArgumentException e) {
            String errorMessage="Invalid user ID format in token";
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(errorMessage);
            errorResponse.setCode(500);
            System.err.println(errorMessage);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        try {
            UUID uuidId = UUID.fromString(id);
            if (!jwtUUID.equals(uuidId)) {
                String errorMessage="Unauthorized: Token does not match requested user";
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setMessage(errorMessage);
                errorResponse.setCode(403);
                System.err.println(errorMessage);
                return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
            }

            Optional<User> user = userService.getUser(uuidId);

            // Construct and return the response
            if (user.isEmpty()) {
                String errorMessage="User not found: " + id;
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setMessage(errorMessage);
                errorResponse.setCode(404);
                System.err.println(errorMessage);
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }
            else {
                User response = user.get();
                return new ResponseEntity<>(response, HttpStatus.OK);

            }
        } catch (IllegalArgumentException e) {
            String errorMessage="Invalid ID format (not a UUID): " + id;
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(errorMessage);
            errorResponse.setCode(400);
            System.err.println(errorMessage);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }


    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/login",
            produces = { "application/json" },
            consumes = { "application/json" }
    )

    public ResponseEntity<?> login(@Valid @RequestBody(required = false) Optional<LoginRequest> loginRequest)
    {
        if (loginRequest.isEmpty() || loginRequest.get().getId() == null || loginRequest.get().getPassword() == null) {
            String errorMessage="User not found: " + loginRequest.get().getId();
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(errorMessage);
            errorResponse.setCode(404);
            System.err.println(errorMessage);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        try {
            UUID uuidId = UUID.fromString(loginRequest.get().getId());
            // Call the AuthService to auth user
            Optional<String> token = authService.login(
                    uuidId,
                    loginRequest.get().getPassword()
            );
            // Construct and return the response
            if (token.isEmpty()) {
                String errorMessage="Invalid password";
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setMessage(errorMessage);
                errorResponse.setCode(401);
                System.err.println(errorMessage);
                return new ResponseEntity<>(errorResponse,HttpStatus.UNAUTHORIZED);
            }
            else {
                Login200Response response= new Login200Response();
                response.setToken(token.orElse(null));
                return new ResponseEntity<>(response, HttpStatus.OK);

            }
        } catch (IllegalArgumentException e) {
            String errorMessage="Invalid ID format (not a UUID): " + loginRequest.get().getId();
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(errorMessage);
            errorResponse.setCode(400);
            System.err.println(errorMessage);
            return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
        }


    }

    private String extractTokenFromHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // Skip "Bearer "
        }
        return null;
    }


}
