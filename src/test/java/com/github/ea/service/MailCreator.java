package com.github.ea.service;

import com.github.ea.model.Mail;

public abstract class MailCreator {
    public String TESTDATA_MAIL_SENT_TO;
    public String TESTDATA_MAIL_SENT_SUBJECT;
    public String TESTDATA_MAIL_SENT_CONTENT;
    public String TESTDATA_MAIL_RECEIVED_FROM;
    public String TESTDATA_MAIL_RECEIVED_SUBJECT;
    public String TESTDATA_MAIL_RECEIVED_CONTENT;

    
    public static MailCreator getCreator(String mailProvider){
      if (mailProvider.equalsIgnoreCase("MailRu")){
        return new MailRuMailCreator();
      } else if (mailProvider.equalsIgnoreCase("Yahoo")){
        return new YahooMailCreator();
      } else {
        throw new IllegalArgumentException("Invalid Mail Provider: " + mailProvider);
      }
    }
    
    public abstract Mail sentWithAllCredentials();
    public abstract Mail sentWithEmptySubject();
    public abstract Mail sentWithEmptyContent();
    public abstract Mail sentWithEmptySubjectAndEmptyContent();    
    public abstract Mail receivedWithAllCredentials();
    public abstract Mail receivedWithEmptySubject();
    public abstract Mail receivedWithEmptyContent();
    public abstract Mail receivedWithEmptySubjectAndEmptyContent();        
}
