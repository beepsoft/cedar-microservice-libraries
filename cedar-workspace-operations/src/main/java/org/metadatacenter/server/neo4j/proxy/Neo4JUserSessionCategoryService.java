package org.metadatacenter.server.neo4j.proxy;

import org.metadatacenter.config.CedarConfig;
import org.metadatacenter.model.CedarResourceType;
import org.metadatacenter.model.folderserver.basic.FolderServerCategory;
import org.metadatacenter.model.folderserver.recursive.FolderServerCategoryWithChildren;
import org.metadatacenter.id.CedarArtifactId;
import org.metadatacenter.id.CedarCategoryId;
import org.metadatacenter.server.CategoryServiceSession;
import org.metadatacenter.server.neo4j.AbstractNeo4JUserSession;
import org.metadatacenter.server.neo4j.cypher.NodeProperty;
import org.metadatacenter.server.security.model.user.CedarUser;

import java.util.List;
import java.util.Map;

public class Neo4JUserSessionCategoryService extends AbstractNeo4JUserSession implements CategoryServiceSession {

  private Neo4JUserSessionCategoryService(CedarConfig cedarConfig, Neo4JProxies proxies, CedarUser cu,
                                          String globalRequestId, String localRequestId) {
    super(cedarConfig, proxies, cu, globalRequestId, localRequestId);
  }

  public static CategoryServiceSession get(CedarConfig cedarConfig, Neo4JProxies proxies, CedarUser cedarUser,
                                           String globalRequestId, String localRequestId) {
    return new Neo4JUserSessionCategoryService(cedarConfig, proxies, cedarUser, globalRequestId, localRequestId);
  }

  @Override
  public FolderServerCategory createCategory(CedarCategoryId parentId, String name, String description, String identifier) {
    CedarCategoryId categoryId = linkedDataUtil.buildNewLinkedDataCategoryId();
    return proxies.category().createCategory(parentId, categoryId, name, description, identifier, cu.getId());
  }

  @Override
  public FolderServerCategory getCategoryById(CedarCategoryId categoryId) {
    return proxies.category().getCategoryById(categoryId);
  }

  @Override
  public long getCategoryCount() {
    return proxies.category().getCategoryCount();
  }

  @Override
  public FolderServerCategory updateCategoryById(CedarCategoryId categoryId, Map<NodeProperty, String> updateFields) {
    return proxies.category().updateCategoryById(categoryId, updateFields, cu.getId());
  }

  @Override
  public boolean deleteCategoryById(CedarCategoryId categoryId) {
    return proxies.category().deleteCategoryById(categoryId);
  }

  @Override
  public FolderServerCategory getRootCategory() {
    return null;
  }

  @Override
  public List<FolderServerCategory> getChildrenOf(CedarCategoryId parentCategoryId, int limit, int offset) {
    return null;
  }

  @Override
  public List<FolderServerCategory> getAllCategories(int limit, int offset) {
    return proxies.category().getAllCategories(limit, offset);
  }

  @Override
  public FolderServerCategoryWithChildren getCategoryTree() {
    return null;
  }

  @Override
  public Object getCategoryPermissions(CedarCategoryId categoryId) {
    return null;
  }

  @Override
  public Object updateCategoryPermissions(CedarCategoryId categoryId, Object permissions) {
    return null;
  }

  @Override
  public Object getCategoryDetails(CedarCategoryId categoryId) {
    return null;
  }

  @Override
  public boolean attachCategoryToArtifact(CedarCategoryId categoryId, CedarArtifactId artifactId) {
    return false;
  }

  @Override
  public boolean detachCategoryFromArtifact(CedarCategoryId categoryId, CedarArtifactId artifactId) {
    return false;
  }

  @Override
  public FolderServerCategory getCategoryByParentAndName(CedarCategoryId parentId, String name) {
    return proxies.category().getCategoryByParentAndName(parentId, name);
  }
}
