package com.geekster.instagram.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User {

    @Id//primary key
    @Column(name = "user_id") // to create column name
    @GeneratedValue(strategy = GenerationType.IDENTITY) // To auto generate primary key
    private int userId;

    @Column(name = "first_name", columnDefinition = "varchar(255) default 'John Snow'")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

}
