# Programming Test Content (3rd Term, DAM/DAW Course) - Year 22-23

This repository contains all needed information of DataBase connection and Read/Write files for Programming subject Test (3rd Term).

## Content:

The repository is divided into the two main topics of the exam.

### DataBase Connection: [Directory](DB/)

> JDBC - Java DataBase Connectivity

We'll use (and have been using in the 3rd term) the JDBC to connect a database trhough our code. (Other ways to connect to a databse with Java - [Sockets](https://www.baeldung.com/a-guide-to-java-sockets "Java Socket Programming")).

For the databse connection is needed the database manager driver (in our case, [MySQL](https://www.mysql.com/ "MySQL web page") driver). The driver can be downloaded in the following link:

-  [Download Link](https://dev.mysql.com/downloads/connector/j/) : Select `Platform Independent` and uncompress the file.

Or just get it from this repository: [JAR file](DB/mysql-connector-j-8.0.33.jar).

We need the `.jar` file because it will be added to the project's Libraries/lib (or something similar depending on IDE) directory.

### Read/Write Files: [Directory](Files/)

In this repository will be explained 3 ways to read&write files:

-  FileInputStream & FileOutputStream (**byte Stream**)
-  FileReader & FileWriter (**character Stream**)
-  BufferedReader & BufferedWriter (**buffered Stream**)
