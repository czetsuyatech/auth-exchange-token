//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.google.api.services.gmail.model;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;
import java.util.List;

public final class ListMessagesResponse extends GenericJson {

  @Key
  private List<Message> messages;
  @Key
  private String nextPageToken;
  @Key
  private Integer resultSizeEstimate;

  public ListMessagesResponse() {
  }

  public List<Message> getMessages() {
    return this.messages;
  }

  public ListMessagesResponse setMessages(List<Message> messages) {
    this.messages = messages;
    return this;
  }

  public String getNextPageToken() {
    return this.nextPageToken;
  }

  public ListMessagesResponse setNextPageToken(String nextPageToken) {
    this.nextPageToken = nextPageToken;
    return this;
  }

  public Integer getResultSizeEstimate() {
    return this.resultSizeEstimate;
  }

  public ListMessagesResponse setResultSizeEstimate(Integer resultSizeEstimate) {
    this.resultSizeEstimate = resultSizeEstimate;
    return this;
  }

  public ListMessagesResponse set(String fieldName, Object value) {
    return (ListMessagesResponse) super.set(fieldName, value);
  }

  public ListMessagesResponse clone() {
    return (ListMessagesResponse) super.clone();
  }
}
