package com.geekster.instagram.model;


import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "tbl_post")
public class Post {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "updated_date")
    private Timestamp updatedDate;

    @Column(name = "post_data")
    private String postData;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

}
