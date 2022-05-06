package com.linde.codingchallenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bikes")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Bike implements Serializable {

    private static final long serialVersionUID = 201176687649266603L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    Long id;

    @NotEmpty
    @Column(length = 7, unique = true)
    String licenceNumber;

    @NotEmpty
    @Column
    String type;

    @NotEmpty
    @Column
    String color;

    @NotEmpty
    @Column
    String ownerName;

    @NotEmpty
    @Column
    String email;

    @NotEmpty
    @Column(length = 100)
    String thiefDescription;

    @NotEmpty
    @Column
    String stolenAddress;

    @NotEmpty
    @Column
    Boolean stolenStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    Police police;

}
