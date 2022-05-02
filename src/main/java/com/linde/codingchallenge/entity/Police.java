package com.linde.codingchallenge.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "polices")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Police implements Serializable {

    private static final long serialVersionUID = 4053835314175361249L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "investigating")
    private Boolean investigating;

    @OneToOne(fetch = FetchType.LAZY)
    private Bike stolenBike;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private PoliceDepartment policeDepartment;

}
