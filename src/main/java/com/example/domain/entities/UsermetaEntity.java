package com.example.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "wp_usermeta", schema = "wordpress")
public class UsermetaEntity {
    @Id
    @Column(name = "umeta_id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "meta_key")
    private String metaKey;

    @Lob
    @Column(name = "meta_value")
    private String metaValue;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}