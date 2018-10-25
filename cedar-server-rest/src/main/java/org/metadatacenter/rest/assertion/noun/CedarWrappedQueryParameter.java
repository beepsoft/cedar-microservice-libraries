package org.metadatacenter.rest.assertion.noun;

import org.metadatacenter.rest.context.CedarParameterSource;

import java.util.Optional;

public class CedarWrappedQueryParameter extends CedarParameterNoun {

  private final String name;
  private final CedarParameterSource source;
  private final Optional<?> wrapped;

  public CedarWrappedQueryParameter(String name, Optional<?> wrapped) {
    this.name = name;
    this.source = CedarParameterSource.QueryString;
    this.wrapped = wrapped;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public CedarParameterSource getSource() {
    return source;
  }

  @Override
  public String stringValue() {
    if (wrapped != null && wrapped.isPresent() && wrapped.get() != null) {
      return wrapped.get().toString();
    } else {
      return null;
    }
  }

  @Override
  public boolean booleanValue() {
    if (wrapped != null && wrapped.isPresent() && wrapped.get() != null) {
      return "true".equals(wrapped.get().toString());
    } else {
      return false;
    }
  }

  @Override
  public boolean isNull() {
    return isMissing() || wrapped.get() == null;
  }

  @Override
  public boolean isPresentAndNull() {
    return wrapped != null && wrapped.isPresent() && wrapped.get() == null;
  }

  @Override
  public boolean isMissing() {
    return wrapped == null || !wrapped.isPresent();
  }

  @Override
  public boolean isEmpty() {
    return isNull() || stringValue().trim().isEmpty();
  }

}
