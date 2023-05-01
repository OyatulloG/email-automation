package com.github.ea.model;

import java.util.Objects;

public class Mail {
    private String email;
    private String subject;
    private String content;

    public Mail(String email, String subject, String content){
      this.email = email;
      this.subject = subject;
      this.content = content;
    }

    public String getEmail(){
      return email;
    }
    
    public void setEmail(String email){
      this.email = email;
    }
    
    public String getSubject(){
      return subject;
    }
    
    public void setSubject(String subject){
      this.subject = subject;
    }
    
    public String getContent(){
      return content;
    }
    
    public void setContent(String content){
      this.content = content;
    }
    
    @Override
    public String toString(){
      return "Mail{" + 
      		"email='" + email + "'" + 
      		"subject='" + subject + "'" + 
      		"content='" + content + "'" + 
      		"}";
    }
        
    @Override
    public boolean equals(Object o){
      if (o == this) {
        return true;
      }
      if (!(o instanceof Mail)){
        return false;
      }      
      Mail mail = (Mail) o;
      return Objects.equals(getEmail(), mail.getEmail()) &&
      		Objects.equals(getSubject(), mail.getSubject()) &&
      		Objects.equals(getContent(), mail.getContent());      
    }   
    
    @Override
    public int hashCode(){
      return Objects.hash(getEmail(), getSubject(), getContent());
    }
}
