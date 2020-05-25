//package com.venturegardengroup.foodvendorapplication.temp_trash;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import java.util.Set;
//
//@Entity
//@Table(name = "users")
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "user_id")
//    private int id;
//    @Column(name = "email")
//    private String email;
//    @Column(name = "password")
//    private String password;
//    @Column(name = "name")
//    private String name;
//    @Column(name = "last_name")
//    private String lastName;
//    @Column(name = "active")
//    private  int active;
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user"))
//    private Set<Role> roles;
//
//    public User() {
//    }
//
//    public User(User user) {
//        this.id = user.getId();
//        this.email = user.getEmail();
//        this.name = user.getName();
//        this.lastName = user.getLastName();
//        this.roles = user.getRoles();
//        this.password = user.getPassword();
//        this.active = user.getActive();
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public int getActive() {
//        return active;
//    }
//
//    public void setActive(int active) {
//        this.active = active;
//    }
//
//    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }
//}
