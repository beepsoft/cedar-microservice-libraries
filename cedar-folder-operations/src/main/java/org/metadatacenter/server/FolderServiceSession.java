package org.metadatacenter.server;

import org.metadatacenter.model.CedarNodeType;
import org.metadatacenter.model.folderserver.FolderServerFolder;
import org.metadatacenter.model.folderserver.FolderServerNode;
import org.metadatacenter.model.folderserver.FolderServerResource;
import org.metadatacenter.model.folderserver.FolderServerUser;
import org.metadatacenter.server.neo4j.NodeLabel;

import java.util.List;
import java.util.Map;

public interface FolderServiceSession {
  // Expose methods of PathUtil
  String sanitizeName(String name);

  String normalizePath(String path);

  String getChildPath(String path, String name);

  String getRootPath();

  String getResourceUUID(String resourceId, CedarNodeType nodeType);

  FolderServerFolder findFolderById(String folderURL);

  List<FolderServerNode> findAllNodes(int limit, int offset, List<String> sortList);

  long findAllNodesCount();

  FolderServerResource findResourceById(String resourceURL);

  FolderServerFolder createFolderAsChildOfId(String parentFolderURL, String name, String displayName, String
      description, NodeLabel label);

  FolderServerFolder createFolderAsChildOfId(String parentFolderURL, String name, String displayName, String
      description, NodeLabel label, Map<String, Object> extraProperties);

  FolderServerResource createResourceAsChildOfId(String parentFolderURL, String childURL, CedarNodeType
      nodeType, String name, String description, NodeLabel label);

  FolderServerResource createResourceAsChildOfId(String parentFolderURL, String childURL, CedarNodeType
      nodeType, String name, String description, NodeLabel label, Map<String, Object> extraProperties);

  FolderServerFolder updateFolderById(String folderURL, Map<String, String> updateFields);

  FolderServerResource updateResourceById(String resourceURL, CedarNodeType nodeType, Map<String,
      String> updateFields);

  boolean deleteFolderById(String folderURL);

  boolean deleteResourceById(String resourceURL, CedarNodeType nodeType);

  FolderServerFolder findFolderByPath(String path);

  FolderServerFolder findFolderByParentIdAndName(FolderServerFolder parentFolder, String name);

  FolderServerNode findNodeByParentIdAndName(FolderServerFolder parentFolder, String name);

  List<FolderServerFolder> findFolderPathByPath(String path);

  List<FolderServerFolder> findFolderPath(FolderServerFolder folder);

  List<FolderServerNode> findFolderContents(String folderURL, List<CedarNodeType> nodeTypeList, int
      limit, int offset, List<String> sortList);

  long findFolderContentsCount(String folderURL);

  long findFolderContentsCount(String folderURL, List<CedarNodeType> nodeTypeList);

  void addPathAndParentId(FolderServerFolder folder);

  void addPathAndParentId(FolderServerResource resource);

  String getHomeFolderPath();

  boolean moveResource(FolderServerResource sourceResource, FolderServerFolder targetFolder);

  boolean moveFolder(FolderServerFolder sourceFolder, FolderServerFolder targetFolder);

  FolderServerFolder ensureUserHomeExists();
}
