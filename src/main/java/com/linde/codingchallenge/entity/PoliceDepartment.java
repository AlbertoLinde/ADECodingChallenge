package com.linde.codingchallenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "police_department")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class PoliceDepartment implements Serializable {

    private static final long serialVersionUID = 608632924154566107L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(unique = true)
    private String departmentName;

    @JsonManagedReference
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "policeDepartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Police> polices;

}
