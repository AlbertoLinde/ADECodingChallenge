package com.linde.codingchallenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "police_department")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class PoliceDepartment implements Serializable {

    private static final long serialVersionUID = 608632924154566107L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String departmentName;

}
