package com.chessapp.chess.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService){this.userService = userService;}

    @GetMapping("/{id}")
    public Optional<UserProfile> getUserProfileById(@PathVariable long id) { return userService.getUserProfileByID(id); }

    @GetMapping("/all")
    public ResponseEntity<List<UserProfile>> getUserProfiles(){ return new ResponseEntity<List<UserProfile>>(userService.getAllUserProfiles(),HttpStatus.OK); }
    @PostMapping
    public ResponseEntity<?> createNewUserProfile(@RequestBody Map<String,String> payload) throws Exception {
        String username = payload.get("username");
        String password = payload.get("password");
        String email = payload.get("email");

        if(username.isEmpty() || password.isEmpty() || email.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User properties cannot be null.");
        }

        return new ResponseEntity<UserProfile>(userService.createNewUserProfile(username,password,email),HttpStatus.CREATED);
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
