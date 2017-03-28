package org.metadatacenter.cedar.util.dw;

import org.metadatacenter.error.CedarErrorPack;
import org.metadatacenter.util.http.CedarResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CedarExceptionMapper extends AbstractExceptionMapper implements ExceptionMapper<Exception> {

  private static final Logger log = LoggerFactory.getLogger(CedarCedarExceptionMapper.class);

  public Response toResponse(Exception exception) {
    log.warn(":CEM:", exception);
    if (exception instanceof BadRequestException) {
      return CedarResponse.badRequest().build();
    } else if (exception instanceof ForbiddenException) {
      return CedarResponse.forbidden().build();
    } else if (exception instanceof NotAcceptableException) {
      return CedarResponse.notAcceptable().build();
    } else if (exception instanceof NotAllowedException) {
      return CedarResponse.methodNotAllowed().build();
    } else if (exception instanceof NotAuthorizedException) {
      return CedarResponse.unauthorized().build();
    } else if (exception instanceof NotFoundException) {
      return CedarResponse.notFound().build();
    } else if (exception instanceof NotSupportedException) {
      return CedarResponse.httpVersionNotSupported().build();
    }
    CedarErrorPack errorPack = new CedarErrorPack();
    if (!hideExceptionConditionally(errorPack)) {
      errorPack.sourceException(exception);
    }
    return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity(errorPack)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

}
