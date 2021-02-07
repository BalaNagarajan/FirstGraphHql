package com.jcircle.firstgraphhql.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    private String vinNo;
    private String make;
    private String manufacturedDate;

}
