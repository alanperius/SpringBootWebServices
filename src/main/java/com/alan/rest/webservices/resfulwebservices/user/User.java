package com.alan.rest.webservices.resfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel(description="All details about the user.")
@Entity
@Table(name = "application_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(min = 2, message = "Name should have at least 2 characters")
    @ApiModelProperty(notes="Name should have at least 2 characters")
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Past
    @ApiModelProperty(notes="Birth date should be in the past")
    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "role")
    private String role;

    //@OneToMany(mappedBy="user")
    //private List<Post> posts;

}