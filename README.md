# ImageHoster
"Image Hoster" application using Spring Boot Framework with Database integration.

## Features 
 1. Login/signup with password validity
 2. Image list can be seen by anyone
 3. Image details will be visible to the looged in users
 4. Image upload/Edit/Delete options is available for the owner of the image
 5. Logged in user can post comment on any image
 
## Technical stack 
 1. Spring Boot framework
 2. Thymeleaf
 3. JPA with Postgresql
 4. Hibernate Entitiy manager
 5. Passay (to provide validation and strength to password)

## Code Structure

src 

---main

-------java

-----------ImageHoster

           -----------config
           
           -----------constraint
           
           -----------controller
           
           -----------model
           
           -----------repository
           
           -----------service
           
                      ImageHosterApplication class
                      
 ----------resources
 
           ---------META-INF
           
           ---------static.css
           
           ---------templates
           
--test

------java

----------ImageHoster.controller

                      
                   
