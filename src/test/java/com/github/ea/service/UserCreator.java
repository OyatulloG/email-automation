package com.github.ea.service;

import com.github.ea.model.User;

public abstract class UserCreator {
    public String TESTDATA_USER_VALID_EMAIL;
    public String TESTDATA_USER_VALID_PASSWORD;
    public String TESTDATA_USER_WRONG_EMAIL;
    public String TESTDATA_USER_WRONG_PASSWORD;

    public static UserCreator getCreator(String userProvider){
      if (userProvider.equalsIgnoreCase("MailRu")){
        return new MailRuUserCreator();
      } else if (userProvider.equalsIgnoreCase("Yahoo")){
        return new YahooUserCreator();
      } else {
        throw new IllegalArgumentException("Invalid User Provider: " + userProvider);
      }      
    }

    public abstract User withValidCredentials();    
    public abstract User withWrongEmail();
    public abstract User withWrongPassword();
    public abstract User withEmptyEmail();
    public abstract User withEmptyPassword();      
}
