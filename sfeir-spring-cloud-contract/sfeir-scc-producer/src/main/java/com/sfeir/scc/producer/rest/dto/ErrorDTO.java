package com.sfeir.scc.producer.rest.dto;

import java.util.Objects;

public class ErrorDTO {
  private String scope;
  private String description;

  public ErrorDTO() {
  }

  public ErrorDTO(String scope, String description) {
    this.scope = scope;
    this.description = description;
  }

  public String getScope() {
    return scope;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ErrorDTO errorDTO = (ErrorDTO) o;
    return Objects.equals(scope, errorDTO.scope) &&
      Objects.equals(description, errorDTO.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(scope, description);
  }
}
