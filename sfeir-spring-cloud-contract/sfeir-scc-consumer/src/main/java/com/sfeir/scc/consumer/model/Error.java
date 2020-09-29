package com.sfeir.scc.consumer.model;

import java.util.Objects;

public class Error {
  private String scope;
  private String description;

  public Error() {
  }

  public Error(String scope, String description) {
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
    Error error = (Error) o;
    return Objects.equals(scope, error.scope) &&
      Objects.equals(description, error.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(scope, description);
  }
}

