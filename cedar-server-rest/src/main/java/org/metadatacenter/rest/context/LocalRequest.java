package org.metadatacenter.rest.context;

import org.metadatacenter.exception.CedarProcessingException;
import org.metadatacenter.rest.assertion.noun.CedarRequestBody;
import org.metadatacenter.rest.assertion.noun.CedarRequestNoun;
import org.metadatacenter.server.security.model.user.CedarUser;

import static org.metadatacenter.constant.HttpConstants.CONTENT_TYPE_APPLICATION_JSON;

public class LocalRequest extends CedarRequestNoun {

  private final CedarUser cedarUser;

  LocalRequest(CedarUser cedarUser) {
    if (cedarUser == null) {
      throw new IllegalArgumentException("The CedarUser should never be null at this point");
    }
    this.cedarUser = cedarUser;
  }

  @Override
  public CedarRequestBody getRequestBody() throws CedarProcessingException {
    return new HttpRequestEmptyBody();
  }

  @Override
  public String getContentType() {
    return CONTENT_TYPE_APPLICATION_JSON;
  }

  @Override
  public String getAuthorizationHeader() {
    return cedarUser.getFirstApiKeyAuthHeader();
  }

  @Override
  public String getDebugHeader() {
    return "true";
  }

}
