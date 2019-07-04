package com.a1nu.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "selector_entity")
public class Selector {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "name shouldn't be empty")
    private String name;
}
