package com.czetsuyatech.oauth.services;

import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.gmail.model.ListMessagesResponse;

public interface GmailService {

  ListMessagesResponse getMessages(int maxResults);
}
