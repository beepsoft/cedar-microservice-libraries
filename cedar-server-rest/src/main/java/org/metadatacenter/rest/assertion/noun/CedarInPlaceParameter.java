package org.metadatacenter.rest.assertion.noun;

import org.metadatacenter.rest.context.CedarParameterSource;

public class CedarInPlaceParameter extends CedarParameterNoun {

  private final String name;
  private final CedarParameterSource source;
  private final Object value;

  public CedarInPlaceParameter(String name, Object value) {
    this.name = name;
    this.source = CedarParameterSource.InPlace;
    this.value = value;
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
    if (value != null) {
      return value.toString();
    } else {
      return null;
    }
  }

  @Override
  public boolean booleanValue() {
    if (value != null) {
      return "true".equals(value.toString());
    } else {
      return false;
    }
  }

  @Override
  public boolean isNull() {
    return isMissing() || value == null;
  }

  @Override
  public boolean isPresentAndNull() {
    return isNull();
  }

  @Override
  public boolean isMissing() {
    return value == null;
  }

  @Override
  public boolean isEmpty() {
    return isNull() || stringValue().trim().isEmpty();
  }

}
