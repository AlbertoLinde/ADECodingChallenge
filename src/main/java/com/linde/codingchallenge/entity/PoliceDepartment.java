package com.linde.codingchallenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "policeDepartment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Police> polices;

}
