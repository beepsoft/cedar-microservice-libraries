package org.metadatacenter.model.folderserver.report;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.metadatacenter.model.BiboStatus;
import org.metadatacenter.model.CedarResourceType;
import org.metadatacenter.model.folderserver.datagroup.ResourceWithOpenFlag;
import org.metadatacenter.model.folderserver.datagroup.VersionDataGroup;
import org.metadatacenter.server.neo4j.cypher.NodeProperty;
import org.metadatacenter.server.security.model.auth.ResourceWithCurrentUserPermissionsAndPublicationStatus;

public abstract class FolderServerSchemaArtifactReport extends FolderServerArtifactReport
    implements ResourceWithCurrentUserPermissionsAndPublicationStatus, ResourceWithOpenFlag {

  private VersionDataGroup versionData;

  public FolderServerSchemaArtifactReport(CedarResourceType resourceType) {
    super(resourceType);
    versionData = new VersionDataGroup();
  }

  @JsonProperty(NodeProperty.Label.PUBLICATION_STATUS)
  public BiboStatus getPublicationStatus() {
    return versionData.getPublicationStatus();
  }

  @JsonProperty(NodeProperty.Label.PUBLICATION_STATUS)
  public void setPublicationStatus(String s) {
    versionData.setPublicationStatus(BiboStatus.forValue(s));
  }

}
