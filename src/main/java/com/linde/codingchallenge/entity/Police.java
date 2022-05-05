package com.linde.codingchallenge.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "polices")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Police implements Serializable {

    private static final long serialVersionUID = 4053835314175361249L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column
    private String name;

    @Column(name = "investigating")
    private Boolean investigating;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private PoliceDepartment policeDepartment;

}
