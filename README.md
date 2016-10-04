# Wordily

Description: 

Wordily is a  text editor with support of Multiple features:

1. File saving and editing

2. Font change and Bold italic and undeline

3. Typing Tutor which highlights the pressed keys by acting as a virtual keyboard.

4. Spell Checker


Images: 
  The Editor looks like this: 
 ![alt tag](https://github.com/amanparashar/Wordily/blob/master/The_Editor.png)
 
  The typingtutor is this:  
 ![alt tag](https://github.com/amanparashar/Wordily/blob/master/Typing_tutor.png)

Set up Description:

1. The project was created on Netbeans IDE and the src folder contains the main source code.

2. Rest is the Netbeans class files and build files. 

3. It requires JDBC connection and db.sql contains the database dump file.

Setting up the project:
 
1. Clone the complete project using:
    git clone https://github.com/amanparashar/Wordily

2. Now open the project in Netbeans. 

3. Download Mysql server and create database Words. 

4. Run db.sql  and this will create the required database of dictionary words. 

5. Now go to Services and right click databases and create New Connection. 
   -> select host at localhost  and port:3306
      Username : root 
      and your password
   -> Click ok and the database will be connected

6. Finally, right click on Wordily (project name) and click properties
   -> Select Libraries and add myql-connector-java jar file 
      ** This file can be downloaded from: https://dev.mysql.com/downloads/connector/j/
   -> Click ok and the project is ready to build and run
   
Running the Project:

Simply, make the document.java file as the main file of the project
build and run the project 

   **   Your Text Editor starts Running 
        You can also directly run the Jar file stored in dist fiolder
        
        

