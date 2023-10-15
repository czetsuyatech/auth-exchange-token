package com.czetsuyatech.google.api.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = 3986494663579679129L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Version
  @Column(name = "version")
  private Integer version;
}
