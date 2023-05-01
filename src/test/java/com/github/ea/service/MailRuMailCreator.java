package com.github.ea.service;

import com.github.ea.model.Mail;

public class MailRuMailCreator extends MailCreator {
    private final String TESTDATA_MAIL_SENT_TO = "testdata.mailru.mail.sent.to";
    private final String TESTDATA_MAIL_SENT_SUBJECT = "testdata.mailru.mail.sent.subject";
    private final String TESTDATA_MAIL_SENT_CONTENT = "testdata.mailru.mail.sent.content";
    private final String TESTDATA_MAIL_RECEIVED_FROM = "testdata.mailru.mail.received.from";
    private final String TESTDATA_MAIL_RECEIVED_SUBJECT = "testdata.mailru.mail.received.subject";
    private final String TESTDATA_MAIL_RECEIVED_CONTENT = "testdata.mailru.mail.received.content";
        
    @Override
    public Mail sentWithAllCredentials() {
      return new Mail(TestDataReader.getTestData(TESTDATA_MAIL_SENT_TO),
      			TestDataReader.getTestData(TESTDATA_MAIL_SENT_SUBJECT),
      			TestDataReader.getTestData(TESTDATA_MAIL_SENT_CONTENT));
    }
    
    @Override
    public Mail sentWithEmptySubject() {
      return new Mail(TestDataReader.getTestData(TESTDATA_MAIL_SENT_TO), "",
      			TestDataReader.getTestData(TESTDATA_MAIL_SENT_CONTENT));
    }
    
    @Override
    public Mail sentWithEmptyContent() {
      return new Mail(TestDataReader.getTestData(TESTDATA_MAIL_SENT_TO),
      			TestDataReader.getTestData(TESTDATA_MAIL_SENT_SUBJECT), "");
    }
    
    @Override
    public Mail sentWithEmptySubjectAndEmptyContent() {
      return new Mail(TestDataReader.getTestData(TESTDATA_MAIL_SENT_TO), "", "");
    }
    
    @Override
    public Mail receivedWithAllCredentials() {
      return new Mail(TestDataReader.getTestData(TESTDATA_MAIL_RECEIVED_FROM),
      			TestDataReader.getTestData(TESTDATA_MAIL_RECEIVED_SUBJECT),
      			TestDataReader.getTestData(TESTDATA_MAIL_RECEIVED_CONTENT));
    }
    
    @Override
    public Mail receivedWithEmptySubject() {
      return new Mail(TestDataReader.getTestData(TESTDATA_MAIL_RECEIVED_FROM), "",
      			TestDataReader.getTestData(TESTDATA_MAIL_RECEIVED_CONTENT));    
    }
    
    @Override   
    public Mail receivedWithEmptyContent() {
      return new Mail(TestDataReader.getTestData(TESTDATA_MAIL_RECEIVED_FROM),
      			TestDataReader.getTestData(TESTDATA_MAIL_RECEIVED_SUBJECT), "");    
    }
    
    @Override
    public Mail receivedWithEmptySubjectAndEmptyContent() {
      return new Mail(TestDataReader.getTestData(TESTDATA_MAIL_RECEIVED_FROM), "", "");
    }    
}
