                                                  	
A Chat Server System using Socket Programming


Developed by:

Shehab Ahmed Sayem,
Md. Motaher Hossain

Developed in:

Java


**Base Features: 


1. When the server system is loaded, it will initiate the required communication setup so that a client system can join it.
	
2. When the client system is loaded, an option to provide the IP address/port number of the chat server and the name of joining user.
	
3. A proper GUI with a read-only text box to show the exchanged messages, a text entry field to write a new message and a Send button.	

4. Each time Send is pressed with a new message in a client system, all of the users connected to the server will immediately see the new message. 
	
5. A list in the GUI to show the joined user(s) with the server.

6. An option, shown as a menu item, to save the exchanged messages. 

7. When the server system is loaded, it will give an option to enter the port number to open the connection in. 
	
8. The client system will give an option to login as an annonymous user.
	
9. An option in both the client and server system to clear the text area. 
	
10. An option in client system to show the saved messages.
	
11. An option in client system to send personal message to another joined user. 

12. The client system will separate the status message and the user message by using a separate text area.
	



**How to start:


1. There are 2 .jar files in the folder. 

2. Double click the Server_Socket.jar to open the server window.

3. Enter the port number you want to start the server in. The default is 2222. (**Extra Feature no.1) 

4. Press 'START' button to start the server. The server will wait for client to join. (**Base Feature no.1)

5. Double click the Client_Socket.jar to open the client window. Enter the IP address and port number of the server (The default is localhost and 2222) 
   and the name of joining user and press 'Connect'. A 'username'.txt file will be created in the users pc to save exchanged messages. (**Base Feature no.2)

6. You can join the server as an annonymous user by pressing the 'Annonymous Login' button. 
   The program will generate a random username for you. (**Extra Feature no.2)

7. Both the server window and the client window will have read-only text area to show the exchanged messages and the client window will have 
   a text entry field to write a new message and a 'Send All' button. (**Base Feature no.3)

8. Each time 'Send All' button is pressed with a message in a client window, 
   all of the users connected to the server will immediately see the new message. (**Base Feature no.4)

9. Both the server window and the client window will have a text area to show all the currently joined user(s) with the server. (**Base Feature no.5)

10. The client window will have a button 'Save message' to save messages. If you press it then the program will save all the messages in the client window 
    in 'username'.txt file. (**Base Feature no.6)

11. Both the server window and the client window will have a 'Clear' button to clear the text area. (**Extra Feature no.3)

12. The client window will have a 'ShowSaved' button to show the saved messages and a 'ChatBox' button to return to the current chat text area. 
    You CAN'T send message after you press the 'ShowSaved' button, you have to go back to the current chat text area using 
    the 'ChatBox' button to send message. (**Extra Feature no.4)

13. The client window will have a field to send personal message. Enter the username you want to send personal message to, 
    then write the message and press 'Send PM' button. Only that user will see the message. (**Extra Feature no.5)

14. The client window will have a read-only text area to show only the status message thus separate the user message and status message.(**Extra Feature no.6)

15. Press 'Disconnect' to disconnect from the server.






