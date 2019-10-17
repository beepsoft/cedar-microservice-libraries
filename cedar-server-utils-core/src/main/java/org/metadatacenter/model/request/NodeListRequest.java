package org.metadatacenter.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.metadatacenter.model.CedarResourceType;
import org.metadatacenter.server.security.model.user.ResourcePublicationStatusFilter;
import org.metadatacenter.server.security.model.user.ResourceVersionFilter;

import java.util.List;

public class NodeListRequest {

  @JsonProperty("resource_types")
  private List<CedarResourceType> resourceTypes;
  private ResourceVersionFilter version;
  private ResourcePublicationStatusFilter publicationStatus;
  private long limit;
  private long offset;
  private List<String> sort;
  private String q;
  private String id;
  private String categoryId;
  private String categoryName;
  @JsonProperty("is_based_on")
  private String isBasedOn;

  public List<CedarResourceType> getResourceTypes() {
    return resourceTypes;
  }

  public void setResourceTypes(List<CedarResourceType> resourceTypes) {
    this.resourceTypes = resourceTypes;
  }

  public ResourceVersionFilter getVersion() {
    return version;
  }

  public void setVersion(ResourceVersionFilter version) {
    this.version = version;
  }

  public ResourcePublicationStatusFilter getPublicationStatus() {
    return publicationStatus;
  }

  public void setPublicationStatus(ResourcePublicationStatusFilter publicationStatus) {
    this.publicationStatus = publicationStatus;
  }

  public long getLimit() {
    return limit;
  }

  public void setLimit(long limit) {
    this.limit = limit;
  }

  public long getOffset() {
    return offset;
  }

  public void setOffset(long offset) {
    this.offset = offset;
  }

  public List<String> getSort() {
    return sort;
  }

  public void setSort(List<String> sort) {
    this.sort = sort;
  }

  public String getQ() {
    return q;
  }

  public void setQ(String q) {
    this.q = q;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getIsBasedOn() {
    return isBasedOn;
  }

  public void setIsBasedOn(String isBasedOn) {
    this.isBasedOn = isBasedOn;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }
}
