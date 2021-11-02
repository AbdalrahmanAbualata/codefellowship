package com.example.codefellowship.Models;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class ApplicationUser implements UserDetails{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        @Column(unique = true)
        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private String dateOfBirth;
        private String bio;
         @OneToMany(mappedBy = "owner",cascade=CascadeType.ALL)
         private List<Post> posts;
    @ManyToMany
    @JoinTable(
            name="posters_and_followers",
            joinColumns = { @JoinColumn(name="follower") },
            inverseJoinColumns = { @JoinColumn(name = "poster")}
    )
    Set<ApplicationUser> usersIFollow;

    @ManyToMany(mappedBy = "usersIFollow")
    Set<ApplicationUser> usersFollowingMe;

    public void followUser(ApplicationUser followedUser){
        usersIFollow.add(followedUser);
    }


    public ApplicationUser(){
    }

    public ApplicationUser(String username, String password, String firstName, String lastName, String dateOfBirth, String bio) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Set<ApplicationUser> getUsersIFollow() {
        return usersIFollow;
    }

    public void setUsersIFollow(Set<ApplicationUser> usersIFollow) {
        this.usersIFollow = usersIFollow;
    }

    public Set<ApplicationUser> getUsersFollowingMe() {
        return usersFollowingMe;
    }

    public void setUsersFollowingMe(Set<ApplicationUser> usersFollowingMe) {
        this.usersFollowingMe = usersFollowingMe;
    }
}
