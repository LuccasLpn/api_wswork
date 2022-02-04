package academy.ws_work.modules.security.controller;

import academy.ws_work.modules.security.domain.User;
import academy.ws_work.modules.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;



    @PostMapping(path = "/api/user/save")
    public ResponseEntity<User> save(@RequestBody @Valid User user){
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

}
