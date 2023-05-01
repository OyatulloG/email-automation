package com.github.ea.service;

import com.github.ea.model.User;

public class MailRuUserCreator extends UserCreator {
    private final String TESTDATA_USER_VALID_EMAIL = "testdata.mailru.user.valid.email";
    private final String TESTDATA_USER_VALID_PASSWORD = "testdata.mailru.user.valid.password";
    private final String TESTDATA_USER_WRONG_EMAIL = "testdata.mailru.user.wrong.email";
    private final String TESTDATA_USER_WRONG_PASSWORD = "testdata.mailru.user.wrong.password";
    
    @Override
    public User withValidCredentials(){
      return new User(TestDataReader.getTestData(TESTDATA_USER_VALID_EMAIL),
      			TestDataReader.getTestData(TESTDATA_USER_VALID_PASSWORD));
    }
    
    @Override
    public User withWrongEmail(){
      return new User(TestDataReader.getTestData(TESTDATA_USER_WRONG_EMAIL),
      			TestDataReader.getTestData(TESTDATA_USER_VALID_PASSWORD));
    }
    
    @Override
    public User withWrongPassword(){
      return new User(TestDataReader.getTestData(TESTDATA_USER_VALID_EMAIL),
      			TestDataReader.getTestData(TESTDATA_USER_WRONG_PASSWORD));
    }
    
    @Override
    public User withEmptyEmail(){
      return new User("", TestDataReader.getTestData(TESTDATA_USER_VALID_PASSWORD));
    }
    
    @Override
    public User withEmptyPassword(){
      return new User(TestDataReader.getTestData(TESTDATA_USER_VALID_EMAIL), "");
    }
}
