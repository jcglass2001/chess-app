package com.chessapp.chess.controller;

import com.chessapp.chess.dto.CreateUserProfileRequest;
import com.chessapp.chess.dto.UpdateUserProfileRequest;
import com.chessapp.chess.model.user.UserProfile;
import com.chessapp.chess.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService){this.userService = userService;}

    @GetMapping("/get-{id}")
    public Optional<UserProfile> getUserProfileById(@PathVariable long id) { return userService.getUserProfileByID(id); }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<UserProfile>> getUserProfiles(){ return new ResponseEntity<List<UserProfile>>(userService.getAllUserProfiles(),HttpStatus.OK); }
    @PostMapping("/create")
    public ResponseEntity<?> createNewUserProfile(@RequestBody @Valid CreateUserProfileRequest request) throws Exception {

        return new ResponseEntity<UserProfile>(userService.createNewUserProfile(request),HttpStatus.CREATED);
    }
    @PostMapping("/update-{id}")
    public ResponseEntity<UserProfile> updateUserProfile(@PathVariable long id,
                                              @RequestBody UpdateUserProfileRequest request
    ) {
        return new ResponseEntity<UserProfile>(userService.updateUserProfile(id, request), HttpStatus.OK) ;
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
