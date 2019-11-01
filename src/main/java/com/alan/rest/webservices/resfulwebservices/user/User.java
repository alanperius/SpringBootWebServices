package com.alan.rest.webservices.resfulwebservices.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.Size;
import java.util.Date;

//@ApiModel(description="All details about the user.")
@Entity
@Data
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2, message = "Name should have atleast 2 characters")
    //@ApiModelProperty(notes="Name should have atleast 2 characters")
    private String name;

    //@Past
    //@ApiModelProperty(notes="Birth date should be in the past")
    private Date birthDate;

    //@OneToMany(mappedBy="user")
    //private List<Post> posts;

}