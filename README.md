# [LA Foodie](http://www.masesk.com)

LA Foodie is a software created to find restaurants for the<br>
users and save their favorites on a database using the Yelp API<br>

[![Screen Shot](http://masesk.com/img/4.jpg)](http://georgeosddev.github.com/markdown-edit)


## Features

* The application uses the Yelp API to find restaurants in the area of the user<br>
and suggest new places based on their preferences of the types of food they prefer.
* The user can add a restaurant to their favorites tab. This gets saved on a database, and the user can go back and check the restaurant and its reviews anytime.
* The user can create an account to save and restore his/her favorite restaurants.
* The software caches the searches, in case of a repeat search result.



## Getting Started


#### Download Sources

Change the ***locations*** and ***terms*** arrays to a locations near the user and terms of food they prefer in:

```bash
src/api/APIRequest.java
```

Change the ***CONSUMER_KEY***, ***CONSUMER_SECRET***, ***TOKEN***, and ***TOKEN_SECRET*** to their respective values from the Yelp API developer console. File to be edited can be found in:
```bash
src/api/YelpAPI.java
```
Change the database ***url***, ***username***, ***password***, and the ***database name***. Files to be edited can be found in:

```bash
src/application/Database.java
```

## Licence

Source code can be found on [github](https://github.com/georgeOsdDev/markdown-edit). <br>
[![Screen Shot](https://licensebuttons.net/l/by/4.0/88x31.png)This work is licensed under a Creative Commons Attribution 4.0 International License.](https://creativecommons.org/licenses/by/4.0/)

Developed by [Mases Krikorian](http://masesk.com)

    