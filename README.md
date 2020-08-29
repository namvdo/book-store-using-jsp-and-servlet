## Book Store using JSP and Servlet

This is a complete project for a simple book store application using JSP and Servlet. I use the JSP model 2 architecture with JSP files for user's view, servlets act as controllers and Beans and DAOs for interacting with database. Some features have not yet implemented such as Filter, or Listener.
Here are some basic features included in this project:
  * Sort books by view
  * Sort books by prices
  * Leave a rating for a book
  * View orders and order items 
  * Update and edit user's information
  * Rating statistic for a book using bar chart and pie chart
  * Some other miscellaneous stuff...

I use MSSQL for this project, the script for creating the database is provided under the script folder. There are still many essential features need to include such as pagnigation, footer, sort book by range, edit a rating, etc...Freely to implement any of them if you want, and perhaps the hardest part if you want to use code for this project is this project is poorly-structured, a lot of duplicate files and I did write a lot of spaghetti code and it needs to adjust for an actual use.

### How to run 
First off to use this project you need to create a database by the provided script and add some books to this database manually. Then, open the project in your editor (NetBeans or IntelliJ is preferable) and run the "registrationForm.jsp" file to create an account under the `filter` folder.

Some preview images:

![](https://github.com/namvdo/book-store-using-jsp-and-servlet/blob/master/preview/preview-1.png)
![](https://github.com/namvdo/book-store-using-jsp-and-servlet/blob/master/preview/preview-2.png)
![](https://github.com/namvdo/book-store-using-jsp-and-servlet/blob/master/preview/preview-3.png)

#### Project structure:
```
├───build
│   ├───empty
│   ├───generated
│   │   ├───classes
│   │   │   └───org
│   │   │       └───apache
│   │   │           └───jsp
│   │   │               └───filter
│   │   └───src
│   │       └───org
│   │           └───apache
│   │               └───jsp
│   │                   └───filter
│   ├───generated-sources
│   │   └───ap-source-output
│   └───web
│       ├───css1
│       │   ├───css
│       │   ├───fonts
│       │   │   ├───font-awesome-4.7.0
│       │   │   │   ├───css
│       │   │   │   ├───fonts
│       │   │   │   ├───less
│       │   │   │   └───scss
│       │   │   ├───Linearicons-Free-v1.0.0
│       │   │   │   └───WebFont
│       │   │   ├───montserrat
│       │   │   └───poppins
│       │   ├───images
│       │   │   └───icons
│       │   ├───js
│       │   └───vendor
│       │       ├───animate
│       │       ├───animsition
│       │       │   ├───css
│       │       │   └───js
│       │       ├───bootstrap
│       │       │   ├───css
│       │       │   └───js
│       │       ├───countdowntime
│       │       ├───css-hamburgers
│       │       ├───daterangepicker
│       │       ├───jquery
│       │       ├───perfect-scrollbar
│       │       └───select2
│       ├───css2
│       │   ├───css
│       │   ├───fonts
│       │   │   ├───material-icon
│       │   │   │   ├───css
│       │   │   │   └───fonts
│       │   │   └───poppins
│       │   ├───images
│       │   ├───js
│       │   ├───scss
│       │   │   ├───common
│       │   │   └───layouts
│       │   └───vendor
│       │       └───jquery
│       ├───filter
│       ├───img
│       ├───META-INF
│       ├───template
│       ├───view
│       └───WEB-INF
│           ├───classes
│           │   ├───bean
│           │   ├───controller
│           │   └───db
│           └───lib
├───dist
├───img
├───nbproject
│   └───private
├───src
│   ├───conf
│   └───java
│       ├───bean
│       ├───controller
│       └───db
├───test
└───web
    ├───css1
    │   ├───css
    │   ├───fonts
    │   │   ├───font-awesome-4.7.0
    │   │   │   ├───css
    │   │   │   ├───fonts
    │   │   │   ├───less
    │   │   │   └───scss
    │   │   ├───Linearicons-Free-v1.0.0
    │   │   │   └───WebFont
    │   │   ├───montserrat
    │   │   └───poppins
    │   ├───images
    │   │   └───icons
    │   ├───js
    │   └───vendor
    │       ├───animate
    │       ├───animsition
    │       │   ├───css
    │       │   └───js
    │       ├───bootstrap
    │       │   ├───css
    │       │   └───js
    │       ├───countdowntime
    │       ├───css-hamburgers
    │       ├───daterangepicker
    │       ├───jquery
    │       ├───perfect-scrollbar
    │       └───select2
    ├───css2
    │   ├───css
    │   ├───fonts
    │   │   ├───material-icon
    │   │   │   ├───css
    │   │   │   └───fonts
    │   │   └───poppins
    │   ├───images
    │   ├───js
    │   ├───scss
    │   │   ├───common
    │   │   └───layouts
    │   └───vendor
    │       └───jquery
    ├───filter
    ├───img
    ├───template
    ├───view
    └───WEB-INF
        └───lib
 ```
