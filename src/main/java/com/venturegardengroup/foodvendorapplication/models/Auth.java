package com.venturegardengroup.foodvendorapplication.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "auth")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Auth extends AbstractPersistable<Long> {

    @NotEmpty
    @Email
    @Column(name = "email", unique = true)
    private String email;
    @NotEmpty
    @Column(name = "password")
    private String password;
    @ManyToOne
    private Role roleId;

}
