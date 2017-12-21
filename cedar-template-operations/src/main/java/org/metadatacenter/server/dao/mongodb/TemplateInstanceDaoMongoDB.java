package org.metadatacenter.server.dao.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.model.Filters;

import static org.metadatacenter.constant.CedarConstants.SCHEMA_IS_BASED_ON;

public class TemplateInstanceDaoMongoDB extends GenericLDDaoMongoDB {

  public TemplateInstanceDaoMongoDB(MongoClient mongoClient, String dbName, String
      collectionName) {
    super(mongoClient, dbName, collectionName);
  }

  public long countReferencingTemplate(String templateId) {
    return entityCollection.count(Filters.eq(SCHEMA_IS_BASED_ON, templateId));
  }
}
