package com.chessapp.chess.controller;

import com.chessapp.chess.dto.CreateUserProfileRequest;
import com.chessapp.chess.model.UserProfile;
import com.chessapp.chess.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user-profile")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService){this.userService = userService;}

    @GetMapping("/{id}")
    public Optional<UserProfile> getUserProfileById(@PathVariable long id) { return userService.getUserProfileByID(id); }

    @GetMapping("/all")
    public ResponseEntity<List<UserProfile>> getUserProfiles(){ return new ResponseEntity<List<UserProfile>>(userService.getAllUserProfiles(),HttpStatus.OK); }
    @PostMapping
    public ResponseEntity<?> createNewUserProfile(@RequestBody CreateUserProfileRequest request) throws Exception {
        String firstname = request.getFirstname();
        String lastname = request.getLastname();
        String username = request.getUsername();
        String password = request.getPassword();
        String email = request.getEmail();
        return new ResponseEntity<UserProfile>(userService.createNewUserProfile(firstname,lastname,username,password,email),HttpStatus.CREATED);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserProfile(@PathVariable Long id){
        try{
            userService.deleteUserProfile(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(String.format("Error occurred when deleting user with id:%d", id));
        }
    }
}
