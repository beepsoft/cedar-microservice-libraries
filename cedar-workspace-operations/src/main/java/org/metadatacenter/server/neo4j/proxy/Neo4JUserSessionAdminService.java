package org.metadatacenter.server.neo4j.proxy;

import org.metadatacenter.config.CedarConfig;
import org.metadatacenter.exception.CedarProcessingException;
import org.metadatacenter.id.CedarCategoryId;
import org.metadatacenter.id.CedarFolderId;
import org.metadatacenter.id.CedarGroupId;
import org.metadatacenter.id.CedarUserId;
import org.metadatacenter.model.CedarResourceType;
import org.metadatacenter.model.folderserver.basic.FolderServerCategory;
import org.metadatacenter.model.folderserver.basic.FolderServerFolder;
import org.metadatacenter.model.folderserver.basic.FolderServerGroup;
import org.metadatacenter.model.folderserver.basic.FolderServerUser;
import org.metadatacenter.server.AdminServiceSession;
import org.metadatacenter.server.neo4j.*;
import org.metadatacenter.server.neo4j.cypher.NodeProperty;
import org.metadatacenter.server.security.model.user.CedarUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Neo4JUserSessionAdminService extends AbstractNeo4JUserSession implements AdminServiceSession {

  protected static final Logger log = LoggerFactory.getLogger(Neo4JUserSessionAdminService.class);

  private Neo4JUserSessionAdminService(CedarConfig cedarConfig, Neo4JProxies proxies, CedarUser cu, String globalRequestId, String localRequestId) {
    super(cedarConfig, proxies, cu, globalRequestId, localRequestId);
  }

  public static AdminServiceSession get(CedarConfig cedarConfig, Neo4JProxies proxies, CedarUser cedarUser, String globalRequestId,
                                        String localRequestId) {
    return new Neo4JUserSessionAdminService(cedarConfig, proxies, cedarUser, globalRequestId, localRequestId);
  }

  @Override
  public boolean wipeAllData() {
    return proxies.admin().wipeAllData();
  }

  @Override
  public void ensureGlobalObjectsExists() {
    Neo4jConfig config = proxies.config;
    PathUtil pathUtil = proxies.pathUtil;

    boolean addAdminToEverybody = false;

    log.info("Checking/Creating Global Objects");

    CedarUserId userId = cu.getResourceId();
    log.info("Current User Id: " + userId);

    log.info("Looking for Admin User in Neo4j");
    FolderServerUser cedarAdmin = proxies.user().findUserById(userId);
    if (cedarAdmin == null) {
      log.info("Admin user not found, trying to create it");
      cedarAdmin = proxies.user().createUser(cu);
      log.info("Admin user created, returned:" + cedarAdmin);
      addAdminToEverybody = true;
    } else {
      log.info("Admin user found");
    }

    log.info("Looking for Everybody Group in Neo4j");
    FolderServerGroup everybody = proxies.group().findGroupBySpecialValue(Neo4JFieldValues.SPECIAL_GROUP_EVERYBODY);
    CedarGroupId everybodyGroupId = null;
    if (everybody == null) {
      log.info("Everybody Group not found, trying to create it");
      everybodyGroupId = linkedDataUtil.buildNewLinkedDataIdObject(CedarGroupId.class);
      log.info("Everybody Group URL just generated:" + everybodyGroupId);
      everybody = proxies.group().createGroup(everybodyGroupId, config.getEverybodyGroupName(), config.getEverybodyGroupDescription(), userId,
          Neo4JFieldValues.SPECIAL_GROUP_EVERYBODY);
      log.info("Everybody Group created, returned:" + everybody);
      addAdminToEverybody = true;
    } else {
      log.info("Everybody Group found");
    }

    if (addAdminToEverybody) {
      log.info("Adding Admin user to Everybody Group");
      boolean added = proxies.user().addUserToGroup(cedarAdmin.getResourceId(), everybodyGroupId);
      log.info("Admin user added to Everybody Group, returned:" + added);
    } else {
      log.info("Adding Admin user to Everybody Group is not needed");
    }

    FolderServerFolder rootFolder = proxies.folder().findFolderByPath(config.getRootFolderPath());
    CedarFolderId rootFolderId = null;
    if (rootFolder == null) {
      rootFolder = proxies.folder().createRootFolder(userId);
    }
    if (rootFolder != null) {
      rootFolderId = rootFolder.getResourceId();
    }

    FolderServerFolder usersFolder = proxies.folder().findFolderByPath(config.getUsersFolderPath());
    if (usersFolder == null) {
      String name = pathUtil.extractName(config.getUsersFolderPath());

      FolderServerFolder newUsersFolder = new FolderServerFolder();
      newUsersFolder.setName(name);
      newUsersFolder.setDescription(config.getUsersFolderDescription());
      newUsersFolder.setCreatedByTotal(cedarAdmin.getResourceId());
      newUsersFolder.setRoot(false);
      newUsersFolder.setSystem(true);
      newUsersFolder.setUserHome(false);

      proxies.folder().createFolderAsChildOfId(newUsersFolder, rootFolderId);
    }

    log.info("Looking for Root Category in Neo4j");
    FolderServerCategory rootCategory = proxies.category().getRootCategory();
    if (rootCategory == null) {
      log.info("Root Category not found, trying to create it");
      String rootCategoryId = linkedDataUtil.buildNewLinkedDataId(CedarResourceType.CATEGORY);
      CedarCategoryId ccRootId;
      try {
        ccRootId = CedarCategoryId.build(rootCategoryId);
        log.info("Root Category URL just generated:" + ccRootId.getId());
        rootCategory = proxies.category().createCategory(null, ccRootId, config.getRootCategoryName(),
            config.getRootCategoryDescription(), config.getRootCategoryIdentifier(), userId);
        log.info("Root Category created, returned:" + rootCategory);
      } catch (CedarProcessingException e) {
        log.error("Error while creating root category", e);
      }
    } else {
      log.info("Root Category found");
    }
  }

  @Override
  public boolean createUniqueConstraint(NodeLabel nodeLabel, NodeProperty property) {
    return proxies.admin().createUniqueConstraint(nodeLabel, property);
  }

  @Override
  public boolean createIndex(NodeLabel nodeLabel, NodeProperty property) {
    return proxies.admin().createIndex(nodeLabel, property);
  }
}
