package org.metadatacenter.rest.context;

import org.metadatacenter.rest.assertion.noun.CedarParameter;
import org.metadatacenter.rest.assertion.noun.CedarParameterImpl;
import org.metadatacenter.rest.assertion.noun.CedarRequestBody;
import org.metadatacenter.rest.exception.CedarAssertionException;

public class HttpRequestEmptyBody implements CedarRequestBody {

  public HttpRequestEmptyBody() {
  }

  @Override
  public CedarParameter get(String name) {
    return new CedarParameterImpl(name, CedarParameterSource.EmptyBody);
  }

  @Override
  public <T> T convert(Class<T> type) throws CedarAssertionException {
    if (type == HttpRequestEmptyBody.class) {
      return (T) (new HttpRequestEmptyBody());
    }
    // TODO: add an enum to the source here
    throw new CedarAssertionException("An empty body can not be converted into " + type, "cedarAssertionFramework");
  }
}
