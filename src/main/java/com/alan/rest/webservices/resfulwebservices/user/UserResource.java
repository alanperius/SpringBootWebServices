package com.alan.rest.webservices.resfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null)
            throw new UserNotFoundException("id-" + id);


        Resource<User> resource = new Resource<User>(user);
        ControllerLinkBuilder allUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        ControllerLinkBuilder getUser = linkTo(methodOn(this.getClass()).retrieveUser(user.getId()));
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
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);

        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }

    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}