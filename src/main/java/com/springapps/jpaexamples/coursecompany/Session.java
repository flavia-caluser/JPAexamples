package com.springapps.jpaexamples.coursecompany;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Session {


    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "session", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    List<Attendance> attendaces;
}
