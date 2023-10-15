package com.czetsuyatech.google.api.services;

import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

public interface GmailService {

  ListMessagesResponse getMessages(int maxResults);

  Message getMessage(String id);
}
