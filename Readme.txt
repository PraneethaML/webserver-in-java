Name: Lakshmi Praneetha Mulukutla
Scu id: 00001332576

Assignment: Build a functional Web Server
Date of submission: 02-08-2017

High level description of assignment and what this program does:

* This program is a simple HTTP webserver which accepts HTTP requests and responds accordingly
* The application is multithreaded so once the connection is accepted, it will spawn a new thread to handle the request.
* images of type jpeg/jpg, gif and files of type txt/ html is supported

Brief explanation about the program:
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
List of submitted files:
> PA1-Lakshmi_Mulukutla.tar
  >coen317_webserver
	> MyServer1.java
	> index.html
	> Ready.txt
	> Makefile.txt
	> Scriptfile.docx
	> footer-mission@2x.png
	> md-1701-kreiner.jpg
	> md-1701-roussos.jpg
	> md-1701-warner.jpg
	> MillerCtrCollage760x480.jpg
	> Screen Shot 2017-02-07 at 11.10.13 AM
	> Screen Shot 2017-02-07 at 11.46.01 AM
	> Screen Shot 2017-02-07 at 11.46.23 AM
	> Screen Shot 2017-02-05 at 11.38.15 PM

Instructions for running program:
# This program is executed on port 8080
# open the browser and give the address as localhost:8080

References:
1. https://www.youtube.com/watch?v=G4Z2PQfOHdY
2. https://www.tutorialspoint.com/java/java_networking.htm
3. https://www.codeproject.com/Articles/86387/Implementing-a-Web-Server-in-Java
4. http://cs.fit.edu/~mmahoney/cse3103/java/Webserver.java
5. http://www.jmarshall.com/easy/http/
6. http://www.javatpoint.com/multithreading-in-java

	
