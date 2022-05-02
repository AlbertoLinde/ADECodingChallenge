package com.linde.codingchallenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "polices")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Police implements Serializable {

    private static final long serialVersionUID = 4053835314175361249L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Column(name = "investigating")
    Boolean investigating;

    @OneToOne(fetch = FetchType.LAZY)
    Bike stolenBike;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "police_department_id", referencedColumnName = "id")
    PoliceDepartment policeDepartment;

}
