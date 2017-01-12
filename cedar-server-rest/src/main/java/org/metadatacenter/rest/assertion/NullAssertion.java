package org.metadatacenter.rest.assertion;

import org.metadatacenter.rest.CedarAssertionNoun;
import org.metadatacenter.rest.assertion.noun.CedarParameter;
import org.metadatacenter.rest.context.CedarRequestContext;
import org.metadatacenter.error.CedarAssertionResult;

public class NullAssertion implements CedarAssertion {

  NullAssertion() {
  }

  @Override
  public CedarAssertionResult check(CedarRequestContext requestContext, CedarAssertionNoun target) {
    if (target == null) {
      return null;
    } else {
      if (target instanceof CedarParameter) {
        CedarParameter param = (CedarParameter) target;
        if (param.isNull()) {
          return null;
        } else {
          return new CedarAssertionResult(new StringBuilder().append("The parameter named '").append(param.getName())
              .append("' from ").append(param.getSource()).append(" should be null").toString())
              .parameter("name", param.getName())
              .parameter("source", param.getSource());
        }
      } else {
        return new CedarAssertionResult("The object should be null");
      }
    }
  }

  @Override
  public CedarAssertionResult check(CedarRequestContext requestContext, Object target) {
    if (target == null) {
      return null;
    } else {
      return new CedarAssertionResult("The object should be null");
    }
  }

}
