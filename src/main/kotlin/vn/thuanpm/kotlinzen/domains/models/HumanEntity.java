package vn.thuanpm.kotlinzen.domains.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "human")
public class HumanEntity {
  @Id
  private  Long id;
  private  String name;
  private  boolean completed;

  public HumanEntity(){}

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public boolean getCompleted() {
    return completed;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    HumanEntity humanEntity = (HumanEntity) o;
    return Objects.equals(id, humanEntity.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name,completed);
  }
}
