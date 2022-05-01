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
@Table(name = "bikes")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Bike implements Serializable {

    private static final long serialVersionUID = 201176687649266603L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 7, unique = true)
    String licenceNumber;

    @Column
    String type;

    @Column
    String color;

    @Column
    String ownerName;

    @Column(length = 100)
    String thiefDescription;

    @Column
    String stolenAddress;

    @Column
    Boolean stolenStatus;

}
