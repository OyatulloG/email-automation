package com.github.ea.util;

public class StringUtils {
    public static String extractEmailForYahoo(String mailFrom){
      return mailFrom.substring(mailFrom.indexOf("<")+1, mailFrom.indexOf(">"));
    }
    
    public static String extractMailContentForYahoo(String mail) {
      return mail.substring(0, mail.indexOf("--")-2);
    }
}
