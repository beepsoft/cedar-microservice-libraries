package org.metadatacenter.model.folderserver.extract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.metadatacenter.model.CedarResourceType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FolderServerTemplateInstanceExtract extends FolderServerInstanceArtifactExtract {

  public FolderServerTemplateInstanceExtract() {
    super(CedarResourceType.INSTANCE);
  }

}
