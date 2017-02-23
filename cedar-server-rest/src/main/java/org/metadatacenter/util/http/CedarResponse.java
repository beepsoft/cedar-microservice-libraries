package org.metadatacenter.util.http;

import org.metadatacenter.error.CedarErrorKey;
import org.metadatacenter.error.CedarErrorPack;
import org.metadatacenter.error.CedarErrorReasonKey;
import org.metadatacenter.server.result.BackendCallError;
import org.metadatacenter.server.result.BackendCallResult;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

public abstract class CedarResponse {

  private static CedarResponseBuilder newResponseBuilder() {
    return new CedarResponseBuilder();
  }

  private static CedarResponseBuilder newResponseBuilder(BackendCallResult backendCallResult) {
    if (backendCallResult != null) {
      BackendCallError firstError = backendCallResult.getFirstError();
      if (firstError != null) {
        CedarErrorPack errorPack = firstError.getErrorPack();
        if (errorPack != null) {
          return new CedarResponseBuilder(errorPack);
        }
      }
    }
    return null;
  }


  public static class CedarResponseBuilder {

    private Map<String, Object> parameters;
    private CedarErrorKey errorKey;
    private CedarErrorReasonKey errorReasonKey;
    private String errorMessage;
    private Exception exception;
    private Response.Status status;
    private Object entity;

    protected CedarResponseBuilder() {
      this.parameters = new HashMap<>();
    }

    public CedarResponseBuilder(CedarErrorPack errorPack) {
      parameters = errorPack.getParameters();
      errorKey = errorPack.getErrorKey();
      errorReasonKey = errorPack.getErrorReasonKey();
      errorMessage = errorPack.getMessage();
      exception = errorPack.getOriginalException();
      status = errorPack.getStatus();
    }

    public Response build() {
      Response.ResponseBuilder responseBuilder = Response.noContent();
      responseBuilder.status(status);

      if (entity != null) {
        responseBuilder.entity(entity);
      } else {
        Map<String, Object> r = new HashMap<>();
        r.put("parameters", parameters);
        r.put("errorKey", errorKey);
        r.put("errorReasonKey", errorReasonKey);
        r.put("errorMessage", errorMessage);

        if (exception != null) {
          StackTraceElement[] stackTrace = exception.getStackTrace();
          if (stackTrace != null) {
            r.put("stackTrace", stackTrace);
          }
        }

        if (!r.isEmpty()) {
          responseBuilder.entity(r);
        }
      }
      return responseBuilder.build();
    }

    public CedarResponseBuilder status(Response.Status status) {
      this.status = status;
      return this;
    }

    public CedarResponseBuilder entity(Object entity) {
      this.entity = entity;
      return this;
    }

    public CedarResponseBuilder id(Object id) {
      return this.parameter("id", id);
    }

    public CedarResponseBuilder parameter(String key, Object value) {
      this.parameters.put(key, value);
      return this;
    }

    public CedarResponseBuilder errorKey(CedarErrorKey errorKey) {
      this.errorKey = errorKey;
      return this;
    }

    public CedarResponseBuilder errorReasonKey(CedarErrorReasonKey errorReasonKey) {
      this.errorReasonKey = errorReasonKey;
      return this;
    }

    public CedarResponseBuilder errorMessage(String errorMessage) {
      this.errorMessage = errorMessage;
      return this;
    }

    public CedarResponseBuilder exception(Exception exception) {
      this.exception = exception;
      return this;
    }
  }

  public static CedarResponseBuilder internalServerError() {
    return newResponseBuilder().status(Response.Status.INTERNAL_SERVER_ERROR);
  }

  public static CedarResponseBuilder noContent() {
    return newResponseBuilder().status(Response.Status.NO_CONTENT);
  }

  public static CedarResponseBuilder notFound() {
    return newResponseBuilder().status(Response.Status.NOT_FOUND);
  }

  public static CedarResponseBuilder unauthorized() {
    return newResponseBuilder().status(Response.Status.UNAUTHORIZED);
  }

  public static CedarResponseBuilder forbidden() {
    return newResponseBuilder().status(Response.Status.FORBIDDEN);
  }

  public static CedarResponseBuilder badRequest() {
    return newResponseBuilder().status(Response.Status.BAD_REQUEST);
  }

  protected static CedarResponseBuilder status(Response.Status status) {
    return newResponseBuilder().status(status);
  }

  public static Response from(BackendCallResult backendCallResult) {
    return newResponseBuilder(backendCallResult).build();
  }

}
