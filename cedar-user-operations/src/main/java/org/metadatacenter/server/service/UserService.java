package org.metadatacenter.server.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.checkerframework.checker.nullness.qual.NonNull;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import org.metadatacenter.server.security.IUserService;
import org.metadatacenter.server.security.model.user.CedarUser;

import javax.management.InstanceNotFoundException;
import java.io.IOException;
import java.util.List;

public interface UserService extends IUserService {

  @NonNull CedarUser createUser(@NonNull CedarUser user) throws IOException;

  CedarUser findUser(@NonNull String userId) throws IOException, ProcessingException;

  CedarUser findUserByApiKey(@NonNull String apiKey) throws IOException, ProcessingException;

  CedarUser updateUser(@NonNull String userId, JsonNode modifications) throws IOException, ProcessingException,
      InstanceNotFoundException;

  List<CedarUser> findAll() throws IOException, ProcessingException;

}
