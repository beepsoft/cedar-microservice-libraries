package org.metadatacenter.server.neo4j;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.metadatacenter.model.CedarResourceType;

public enum NodeLabel {

  SCOPE(ComposedLabel.SCOPE, SimpleLabel.SCOPE),
  FOLDER(ComposedLabel.FOLDER, SimpleLabel.FOLDER),
  SYSTEM_FOLDER(ComposedLabel.SYSTEM_FOLDER, SimpleLabel.SYSTEM_FOLDER),
  USER_HOME_FOLDER(ComposedLabel.USER_HOME_FOLDER, SimpleLabel.USER_HOME_FOLDER),
  RESOURCE(ComposedLabel.RESOURCE, SimpleLabel.RESOURCE),
  USER(ComposedLabel.USER, SimpleLabel.USER),
  GROUP(ComposedLabel.GROUP, SimpleLabel.GROUP),
  FIELD(ComposedLabel.FIELD, SimpleLabel.FIELD),
  ELEMENT(ComposedLabel.ELEMENT, SimpleLabel.ELEMENT),
  TEMPLATE(ComposedLabel.TEMPLATE, SimpleLabel.TEMPLATE),
  INSTANCE(ComposedLabel.INSTANCE, SimpleLabel.INSTANCE),
  FSNODE(SimpleLabel.FSNODE, SimpleLabel.FSNODE);

  private static final String S = ":";

  public static class SimpleLabel {
    public static final String SCOPE = "CEDAR";

    public static final String FOLDER = "Folder";
    public static final String SYSTEM_FOLDER = "SystemFolder";
    public static final String USER_HOME_FOLDER = "UserHomeFolder";
    public static final String RESOURCE = "Resource";
    public static final String USER = "User";
    public static final String GROUP = "Group";
    public static final String FIELD = "Field";
    public static final String ELEMENT = "Element";
    public static final String TEMPLATE = "Template";
    public static final String INSTANCE = "Instance";
    public static final String FSNODE = "FSNode";
  }

  public static class ComposedLabel {
    public static final String SCOPE = SimpleLabel.SCOPE;
    public static final String FOLDER = SimpleLabel.FOLDER + S + SimpleLabel.FSNODE + S + SimpleLabel.SCOPE;
    public static final String SYSTEM_FOLDER = SimpleLabel.SYSTEM_FOLDER + S + FOLDER;
    public static final String USER_HOME_FOLDER = SimpleLabel.USER_HOME_FOLDER + S + FOLDER;
    public static final String RESOURCE = SimpleLabel.RESOURCE + S + SimpleLabel.FSNODE + S + SimpleLabel.SCOPE;
    public static final String USER = SimpleLabel.USER + S + SimpleLabel.SCOPE;
    public static final String GROUP = SimpleLabel.GROUP + S + SimpleLabel.SCOPE;
    public static final String FIELD = SimpleLabel.FIELD + S + RESOURCE;
    public static final String ELEMENT = SimpleLabel.ELEMENT + S + RESOURCE;
    public static final String TEMPLATE = SimpleLabel.TEMPLATE + S + RESOURCE;
    public static final String INSTANCE = SimpleLabel.INSTANCE + S + RESOURCE;
  }

  private final String composedLabel;
  private final String simpleLabel;

  NodeLabel(String composedLabel, String simpleLabel) {
    this.composedLabel = composedLabel;
    this.simpleLabel = simpleLabel;
  }

  public String getComposedLabel() {
    return composedLabel;
  }

  public String getSimpleLabel() {
    return simpleLabel;
  }

  public static NodeLabel forComposedLabel(String composedLabel) {
    for (NodeLabel t : values()) {
      if (t.getComposedLabel().equals(composedLabel)) {
        return t;
      }
    }
    return null;
  }

  public static NodeLabel forSimpleLabel(String simpleLabel) {
    for (NodeLabel t : values()) {
      if (t.getSimpleLabel().equals(simpleLabel)) {
        return t;
      }
    }
    return null;
  }

  public static NodeLabel forCedarResourceType(CedarResourceType resourceType) {
    switch (resourceType) {
      case FIELD:
        return FIELD;
      case ELEMENT:
        return ELEMENT;
      case TEMPLATE:
        return TEMPLATE;
      case INSTANCE:
        return INSTANCE;
    }
    return null;
  }

  @JsonIgnore
  public boolean isFolder() {
    return this == FOLDER || this == SYSTEM_FOLDER || this == USER_HOME_FOLDER;
  }

  @Override
  public String toString() {
    return composedLabel;
  }
}
