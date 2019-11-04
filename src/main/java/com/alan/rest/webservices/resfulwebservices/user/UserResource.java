package com.alan.rest.webservices.resfulwebservices.user;

import com.alan.rest.webservices.resfulwebservices.helloword.HelloWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

@RestController
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public Resource<User> retrieveUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent())
            throw new UserNotFoundException("id-" + id);

        Resource<User> resource = new Resource<User>(user.get());
        ControllerLinkBuilder allUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        ControllerLinkBuilder getUser = linkTo(methodOn(this.getClass()).retrieveUser(user.get().getId()));
        resource.add(allUsers.withRel("all-users"));
        resource.add(getUser.withRel("get-user"));

        //WebMvcLinkBuilder allUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        //WebMvcLinkBuilder getUser = linkTo(methodOn(this.getClass()).retrieveUser(user.getId()));
        //WebMvcLinkBuilder deleteUser = linkTo(methodOn(this.getClass()).deleteUser(user.getId()));

        //model.add(allUsers.withRel("all-users"));
        //model.add(getUser.withRel("get-user"));
        //model.add(deleteUser.withRel("delete-user"));

        return resource;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User savedUser = new User();
        savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}