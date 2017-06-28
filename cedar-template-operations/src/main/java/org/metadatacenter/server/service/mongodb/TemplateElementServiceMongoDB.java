package org.metadatacenter.server.service.mongodb;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.mongodb.MongoClient;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.metadatacenter.server.dao.mongodb.TemplateElementDaoMongoDB;
import org.metadatacenter.server.service.FieldNameInEx;
import org.metadatacenter.server.service.TemplateElementService;

import javax.management.InstanceNotFoundException;
import java.io.IOException;
import java.util.List;

public class TemplateElementServiceMongoDB extends GenericTemplateServiceMongoDB<String, JsonNode> implements
    TemplateElementService<String, JsonNode> {

  @NonNull
  private final TemplateElementDaoMongoDB templateElementDao;

  public TemplateElementServiceMongoDB(@NonNull MongoClient mongoClient, @NonNull String db, @NonNull String
      templateElementsCollection) {
    this.templateElementDao = new TemplateElementDaoMongoDB(mongoClient, db, templateElementsCollection);
  }

  @Override
  @NonNull
  public JsonNode createTemplateElement(@NonNull JsonNode templateElement) throws IOException {
    return templateElementDao.create(templateElement);
  }

  @Override
  @NonNull
  public List<JsonNode> findAllTemplateElements() throws IOException {
    return templateElementDao.findAll();
  }

  @Override
  @NonNull
  public List<JsonNode> findAllTemplateElements(List<String> fieldNames, FieldNameInEx includeExclude) throws
      IOException {
    return templateElementDao.findAll(fieldNames, includeExclude);
  }

  @Override
  @NonNull
  public List<JsonNode> findAllTemplateElements(Integer limit, Integer offset, List<String> fieldNames, FieldNameInEx
      includeExclude) throws IOException {
    return templateElementDao.findAll(limit, offset, fieldNames, includeExclude);
  }

  @Override
  public JsonNode findTemplateElement(@NonNull String templateElementId) throws IOException, ProcessingException {
    return templateElementDao.find(templateElementId);
  }

  @Override
  @NonNull
  public JsonNode updateTemplateElement(@NonNull String templateElementId, @NonNull JsonNode content)
      throws InstanceNotFoundException, IOException {
    return templateElementDao.update(templateElementId, content);
  }

  @Override
  public void deleteTemplateElement(@NonNull String templateElementId) throws InstanceNotFoundException, IOException {
    templateElementDao.delete(templateElementId);
  }

  @Override
  public boolean existsTemplateElement(@NonNull String templateElementId) throws IOException {
    return templateElementDao.exists(templateElementId);
  }

  @Override
  public void deleteAllTemplateElements() {
    templateElementDao.deleteAll();
  }

  @Override
  public long count() {
    return templateElementDao.count();
  }

}
