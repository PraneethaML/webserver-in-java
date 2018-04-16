# webserver-in-java
- Done as an assignment for  'COEN 317 Distributed Systems' course in Santa Clara University 

# High level description of assignment and what this program does:

* This program is a simple HTTP webserver which accepts HTTP requests and responds accordingly
* The application is multithreaded so once the connection is accepted, it will spawn a new thread to handle the request.
* images of type jpeg/jpg, gif and files of type txt/ html is supported

# Brief explanation about the program:
- server_socket is an object of ServerSocket class that takes port number as command line argument. It listens for incoming connections
- Inside while loop we infinetly wait for TCP connection
  - A Socket object con_socket is created to that waits for incoming client 
  - An object(req) is created to process HTTP request message
  - A thread is created to process each request and is started
- each request is handled in handleRequest() method of HttpRequest class that implements Runnable interface
- in handleRequest() method, we get reference for socket input and output streams
- we take the inital request line of the HTTP request message and extract the filename, which is then parsed and type of content is determined
- the requested resource is written to a buffer and displayed and connection is closed

- Http 404,403,200 are handled
- for Http 400 error also 404 is returned


	
# Instructions for running program:
javac MyServer1.java
java MyServer1 -document_root "/path/to/webserver_files" -port 8080
 	# open the browser and give the address as localhost:8080


	# open another terminal
telnet localhost 8080
get / HTTP/1.1



# References:
1. https://www.youtube.com/watch?v=G4Z2PQfOHdY
2. https://www.tutorialspoint.com/java/java_networking.htm
3. https://www.codeproject.com/Articles/86387/Implementing-a-Web-Server-in-Java
4. http://cs.fit.edu/~mmahoney/cse3103/java/Webserver.java
5. http://www.jmarshall.com/easy/http/
6. http://www.javatpoint.com/multithreading-in-java
