# Email Automation Framework
Email Automation Framework is designed to write tests for Yahoo and MailRu email services.

## Requirements
Before running the project, make sure that you have installed Maven and Java.

## Usage
Firstly, clone or download the project   
Secondly, open project folder in terminal   
Thirdly, run the following command:
```bash
mvn -Dbrowser=chrome -Denvironment=dev -Dsurefire.suiteXmlFiles=src/test/resources/testng-mailru-all.xml clean test
```

## Details
```bash
-Dbrowser can get the following values:   
1. chrome    
2. firefox

-Denvironment can get the following values:
1. dev
2. qa
3. staging
NB: These files can be found in the directory: src/test/resources/

-Dsurefire.suiteXmlFiles values are all files that start with "testng" in the folder:
src/test/resources/
```

## Tests
To demonstrate the project, 7 tests are written for both Yahoo and  MailRu email services. They are:     
1. Login with valid email and valid password 
2. Login with valid email and wrong password
3. Login with wrong email
4. Login with empty account
5. Login with valid email and empty password
6. Send a new mail
7. Check a mail is recieved
