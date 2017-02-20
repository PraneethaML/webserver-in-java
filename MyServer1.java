import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;
import java.net.*;

public class MyServer1
{
  
  public static void main(String args[]) throws IOException 
  {
  	  if(args.length!=4)
	  {
		  System.out.println("4 arguments must be given");
		  System.exit(0);
	  }
    
	
	ServerSocket server_socket=new ServerSocket(Integer.parseInt(args[3]));  // listen for incomming connection 
    
	while (true) // infinte loop to process HTTP request
	{
		Socket con_socket = server_socket.accept(); // accept() Waits for incoming client/TCP connection request
		HttpRequest req = new HttpRequest( con_socket ); // req  processes the HTTP request message.
		Thread th = new Thread(req); // Create a new thread to handle each request
	    th.start(); // Start the thread.
	}
  }
}

class HttpRequest implements Runnable
{
 Socket socket;
 public HttpRequest(Socket socket) throws IOException
 {
  this.socket = socket;
 }

 public void run()
 {
  try 
  {
	handleRequest();
	System.out.println(socket);
	
  }
  catch (Exception e) 
  {
    System.out.println(e);
  }
}

 private void handleRequest() throws Exception
 {
   BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
   PrintStream o=new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
	 
   String command = in.readLine(); // command is  the request line (get /path/file.html version of http) of the HTTP request message.
   System.out.println();
   System.out.println(command);  // printing  the request line.
	 
	 // get the file from the request line.
   String file="";
	 StringTokenizer tokens = new StringTokenizer(command);
     try {

       // Parse the file from the GET command
       if (tokens.hasMoreElements() && tokens.nextToken().equalsIgnoreCase("GET") && tokens.hasMoreElements())
         file = tokens.nextToken();
       else
         throw new FileNotFoundException();  // Bad request

       if (file.endsWith("/")) // Append "/" with "index.html"
         file+="index.html";
       
       while (file.indexOf("/")==0)// Remove leading / from filename
         file=file.substring(1);
	   
 	  if((new File(file).exists()) && !(new File(file).canRead()))
 	   {
            o.print("HTTP/1.0 403 Forbidden\r\n"+
              "Location: /"+file+"/\r\n\r\n");
            o.close();
            return;
 	   }

     
       FileInputStream f=new FileInputStream(file);
	   SimpleDateFormat format1 = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:Ss z");

       // Determine the content type and print HTTP header
       String content="text/plain";
       if (file.endsWith(".html") || file.endsWith(".htm"))
         content="text/html";
       else if (file.endsWith(".jpg") || file.endsWith(".jpeg"))
         content="image/jpeg";
       else if (file.endsWith(".gif"))
         content="image/gif";
       else if (file.endsWith(".class"))
         content="application/octet-stream";
	   o.print("HTTP/1.0 200 OK\r\n"+
         "Content-type: "+content+"\r\n\r\n");
	   
	   
	   //o.print("HTTP/1.0 200 OK\r\n"+"Content-length:"+(new File(file)).length()+"\r\n\r\n"+"Date: "+format1.format(new Date())+"\r\n\r\n");
	   //System.out.println("HTTP/1.0 200 OK\r\n");
	   
	
        byte buffer[]=new byte[5000];
        int i;
        while ((i=f.read(buffer))>0)
        o.write(buffer, 0, i);
        o.close();
		socket.close();
		//System.out.println("connection closed");
      }
	  
      catch (FileNotFoundException fnfe) 
	  {
          o.print("HTTP/1.0 404 Not Found\r\n"+
            "Content-type: text/html\r\n\r\n"+
            "<html><head></head><body>HTTP/1.0 404 Not Found<br>"+file+" not found</body></html>\n");
        o.close();
		in.close();
		socket.close();
		//System.out.println("connection closed");
      }   
}
}

