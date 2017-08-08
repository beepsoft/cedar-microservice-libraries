package org.metadatacenter.server.security.model;


public final class CedarObjectConstants {


  public static final String SEARCH_INDEX = "search_index";
  public static final String LOGGED_IN = "logged_in";
  public static final String NOT_READABLE_NODE = "not_readable_node";
  public static final String NOT_WRITABLE_NODE = "not_writable_node";
  public static final String NOT_WRITABLE_NODE_PERMISSIONS = "not_writable_node_permissions";
  public static final String PROCESS_MESSAGE = "process_message";
  public static final String POST_SUBMISSION = "post_submission";
  //public static final String NOT_WRITABLE_FOLDER = "not_writable_folder";

  public static final String ACCESS_CREATE = "create";
  public static final String ACCESS_READ = "read";
  public static final String ACCESS_UPDATE = "update";
  public static final String ACCESS_DELETE = "delete";
  // This does not align with the other ACCESS
  public static final String ACCESS_WRITE = "write";

  private CedarObjectConstants() {
  }
}
