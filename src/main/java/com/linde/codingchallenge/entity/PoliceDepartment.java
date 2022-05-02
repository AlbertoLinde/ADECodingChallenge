package com.linde.codingchallenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "police_department")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class PoliceDepartment implements Serializable {

    private static final long serialVersionUID = 608632924154566107L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String departmentName;
//
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "id")
//    List<Police> polices;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "policeDepartment")
//    //@JoinColumn(name = "department_id")
//    private Set<Police> polices = new HashSet<>();

    @OneToMany(mappedBy = "policeDepartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Police> polices;

}
