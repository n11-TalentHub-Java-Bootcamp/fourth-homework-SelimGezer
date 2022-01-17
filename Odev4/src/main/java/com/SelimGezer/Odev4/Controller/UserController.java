package com.SelimGezer.Odev4.Controller;

import com.SelimGezer.Odev4.Dtos.UserRequestDto;
import com.SelimGezer.Odev4.Dtos.UserResponceDto;
import com.SelimGezer.Odev4.Services.UserEntityService;
import com.SelimGezer.Odev4.Util.ResponceBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserEntityService userEntityService;

    public UserController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @GetMapping()
    public List<UserResponceDto> getAllUsers(){
        return userEntityService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponceDto getUserById(@PathVariable("id") Long id){
        return userEntityService.getUserById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponceDto addUser(@RequestBody UserRequestDto userRequestDto){
        return userEntityService.addUser(userRequestDto);
    }

    @PutMapping("/{id}")
    public UserResponceDto updateUserById(@PathVariable("id") Long id,@RequestBody UserRequestDto userRequestDto){
        return userEntityService.updateUserById(id,userRequestDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity deleteUserById(@PathVariable("id") Long id)
    {
        return userEntityService.deleteUserById(id);
    }

}
