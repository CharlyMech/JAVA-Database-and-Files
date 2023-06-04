# JDBC With MySQL

This is the way I think is the most optimal and organized, at least for me and the way I code. Some methods must not fit with other programming ways but the bases are the same for every type of porogrammer.

> Remember that this is the way I create my code and think is the best for the way I code.

## Connection Class: [`Conn.java`](Conn.java)

This class is only meant to establish the connection with the database server. The only attribute (`conn`) holds the connection (as a pointer). Is set to static and protectec (encapsulation) to be access only in the `Query.java` class (inside a database package). The main reason is because is the only file that needs it and it will be access statically. The rest of files (windows and the other packages) do not need to access the attribute and they'll instanciate a `Conn` object to execute the connection

There are only 3 methods:

-  Constructor: Whenever an object is created, the constructor will call the `connect()` method.
-  `connect()` > _void_ : private method, only called whenever a `Conn` object is created. If the connection is successful, the attribute `conn` will change its value.
-  `connectionValid()` > _boolean_ : Not a necessary method. This method only checks if the connection is successfull within certain time (set to 50000 seconds in the code). Is set to public and not static just to be called if an object is instanciated.
-  `closeConnection()` > _void_ : Public and static method, this method can be called whenever it's needed inside the project (mayvbe there are mothe than one way to close the program). Whenever the program is closed (finish execution) this method must be called too. It's not 100% necessary but is a good practice to close every connection the program execution could have opened.

## Queries Class: [`Query.java`](Query.java)

This class has only public static methods so there's no need to instanciate any object to access the methods. This class uses (as said) the `Conn.conn` attribute (pointer) to create the cursors (Statements/PreparedStatements) to execute the queries.

In this file is shown all CRUD methods.
