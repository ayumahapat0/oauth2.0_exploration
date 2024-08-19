# oauth2.0_exploration


 ##### During my time as a Staples Backend SWE Intern, I needed to learn how to use an External API that utilized OAuth2.0 Security Configuration

 ##### It was the most challenging part of the internship as I had no experience working with secured APIs.
 ##### However, it was very rewarding learning about the Oauth2.0 Security Flow with Client Credentials grante type!
 ## OAuth2.0 Security Flow  - Client Cred

 ![alt text](https://github.com/ayumahapat0/oauth2.0_exploration/blob/main/OAuth2.0_withBackground.png?raw=true)

##### 1. The Token Requester Class calls on the POST Method for the Access Token, giving the client ID, client Secret, and Grant Type
##### 2. Given the client ID, client Secret and grant type, the authorization server authenticates the client and request
##### 3 & 4. The Authorization Server and Resource Owner then work together to grant access by creating the Access Token
##### 5 & 6. Authorization Server sends Access Token back to the Request Class

##### After the Token Requester class get the access token, I wrote then the Response Interceptor Class, which included 
##### a method that dynamically added the Authorization header before any API call was made
