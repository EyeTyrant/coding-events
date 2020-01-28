package org.launchcode.codingevents.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;


@MappedSuperclass
public abstract class AbstractEntity {


  @Id // @Id is an annotation that denotes that an integer id field is to be used as an id in the corresponding table in the database.
  @GeneratedValue // @GeneratedValue is used in conjunction with the @Id annotation to create a primary key for the entity.
  private Integer id;


  public Integer getId() {
    return id;
}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AbstractEntity)) return false;
    AbstractEntity entity = (AbstractEntity) o;
    return getId() == entity.getId();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
