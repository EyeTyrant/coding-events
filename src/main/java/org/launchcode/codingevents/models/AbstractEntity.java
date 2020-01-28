package org.launchcode.codingevents.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;


@MappedSuperclass
public abstract class AbstractEntity {


  @Id
  @GeneratedValue
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