package com.venturegardengroup.foodvendorapplication.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "auth")
public class Auth extends AbstractPersistable<Long> {

    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    @ManyToOne
    private Role roleId;

}
