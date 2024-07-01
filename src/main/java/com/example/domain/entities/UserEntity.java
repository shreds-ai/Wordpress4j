package com.example.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "wp_users", schema = "wordpress")
public class UserEntity {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 60)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "user_login", nullable = false, length = 60)
    private String userLogin;

    @Size(max = 255)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "user_pass", nullable = false)
    private String userPass;

    @Size(max = 50)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "user_nicename", nullable = false, length = 50)
    private String userNicename;

    @Size(max = 100)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "user_email", nullable = false, length = 100)
    private String userEmail;

    @Size(max = 100)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "user_url", nullable = false, length = 100)
    private String userUrl;

    @NotNull
    @ColumnDefault("'0000-00-00 00:00:00'")
    @Column(name = "user_registered", nullable = false)
    private Instant userRegistered;

    @Size(max = 255)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "user_activation_key", nullable = false)
    private String userActivationKey;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "user_status", nullable = false)
    private Integer userStatus;

    @Size(max = 250)
    @NotNull
    @ColumnDefault("''")
    @Column(name = "display_name", nullable = false, length = 250)
    private String displayName;


    @OneToMany(mappedBy = "user")
    private List<UsermetaEntity> userMeta;

    @OneToMany(mappedBy = "postAuthor")
    private List<PostEntity> posts;

    @OneToMany(mappedBy = "user")
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "owner")
    private List<LinkEntity> links;



}