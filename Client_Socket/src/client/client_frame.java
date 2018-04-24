package client;

import java.net.*;
import java.io.*;
import java.util.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.tools.ShellFunctions.input;

class FilesUtil {
    public static String readTextFile(String fileName) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(fileName)));
        return content;
    }

    public static List<String> readTextFileByLines(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        return lines;
    }

    public static void writeToTextFile(String fileName, String content) throws IOException {
        Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE);
    }

}

public class client_frame extends javax.swing.JFrame 
{
    String username,address = "localhost";
    //String address="192.168.0.105";
    ArrayList<String> users = new ArrayList();
    int port = 2222;
    Boolean isConnected = false;
    
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;
    
    File allMsg;
    String chatBoxCurrent;
    int showSaved=0;
    
    public void ListenThread() 
    {
         Thread IncomingReader = new Thread(new IncomingReader());
         IncomingReader.start();
    }

    
    public void addUser(String data) 
    {
         users.add(data);
    }
    
   
    
    public void removeUser(String data) 
    {
         ta_status.append(data + " is now offline.\n");
         users.remove(data);
    }
    
    
    
    public void writeUsers() 
    {
         String[] tempList = new String[(users.size())];
         users.toArray(tempList);
         for (String token:tempList) 
         {
             //users.append(token + "\n");
         }
    }
    
   
    
    public void sendDisconnect() 
    {
        String bye = (username + ": :Disconnect");
        try
        {
            writer.println(bye); 
            writer.flush(); 
        } catch (Exception e) 
        {
            ta_status.append("Could not send Disconnect message.\n");
        }
    }

   
    
    public void Disconnect() 
    {
        try 
        {
            ta_status.append("Disconnected.\n");
            sock.close();
        } catch(Exception ex) {
            ta_status.append("Failed to disconnect. \n");
        }
        isConnected = false;
        tf_username.setEditable(true);
        tf_port.setEditable(true);
        tf_address.setEditable(true);
            
    }
    
    public client_frame() 
    {
        initComponents();
    }
    
    
    public class IncomingReader implements Runnable
    {
        @Override
        public void run() 
        {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", 
                    chat = "Chat", pm="PM", connectchat="Conchat";

            try 
            {
                while ((stream = reader.readLine()) != null) 
                {
                     data = stream.split(":");
                     if(data[0].equals("Server")&& data[1].equals("Disconnect")){
                        ta_chat.append("Server: Disconnected");
                        ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                        
                     }

                     if (data[2].equals(chat)) 
                     {
                        ta_chat.append(data[0] + ": " + data[1] + "\n");
                        ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                     }
                     else if (data[2].equals(pm)) 
                     {
                        //System.out.println("Got PM\n");
                        ta_chat.append(data[0] + ": " + data[1] + "\n");
                        ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                     }
                     else if (data[2].equals(connectchat)) 
                     {
                        ta_status.append(data[0] + ": " + data[1] + "\n");
                        ta_status.setCaretPosition(ta_status.getDocument().getLength());
                     }
                     else if (data[2].equals(connect))
                     {
                        ta_chat.removeAll();
                       
                        addUser(data[0]);
                        
                        ta_usersOnline.setText("");
                        for (String current_user : users)
                        {   
                            ta_usersOnline.append(current_user);
                            ta_usersOnline.append("\n");
                        }
                     } 
                     else if (data[2].equals(disconnect)) 
                     {
                        
                        removeUser(data[0]);
                        
                        ta_usersOnline.setText("");
                        for (String current_user : users)
                        {
                            ta_usersOnline.append(current_user);
                            ta_usersOnline.append("\n");
                        } 
                     } 
                     else if (data[2].equals(done)) 
                     {
                        users.clear();
                     }
                }
           }catch(Exception ex) { }
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_address = new javax.swing.JLabel();
        tf_address = new javax.swing.JTextField();
        lb_port = new javax.swing.JLabel();
        tf_port = new javax.swing.JTextField();
        lb_username = new javax.swing.JLabel();
        tf_username = new javax.swing.JTextField();
        b_connect = new javax.swing.JButton();
        b_disconnect = new javax.swing.JButton();
        b_anonymous = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_chat = new javax.swing.JTextArea();
        tf_chat = new javax.swing.JTextField();
        b_send = new javax.swing.JButton();
        b_pmSend = new javax.swing.JButton();
        b_savemsg = new javax.swing.JButton();
        b_clear = new javax.swing.JButton();
        b_showSaved = new javax.swing.JButton();
        b_chatBox = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        ta_usersOnline = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        tf_pmUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ta_status = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat - Client's frame");
        setName("client"); // NOI18N
        setResizable(false);

        lb_address.setText("Address : ");

        tf_address.setText("localhost");
        tf_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_addressActionPerformed(evt);
            }
        });

        lb_port.setText("Port :");

        tf_port.setText("2222");
        tf_port.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_portActionPerformed(evt);
            }
        });

        lb_username.setText("Username :");

        tf_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_usernameActionPerformed(evt);
            }
        });

        b_connect.setText("Connect");
        b_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_connectActionPerformed(evt);
            }
        });

        b_disconnect.setText("Disconnect");
        b_disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_disconnectActionPerformed(evt);
            }
        });

        b_anonymous.setText("Anonymous Login");
        b_anonymous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_anonymousActionPerformed(evt);
            }
        });

        ta_chat.setEditable(false);
        ta_chat.setColumns(20);
        ta_chat.setRows(5);
        jScrollPane1.setViewportView(ta_chat);

        b_send.setText("Send All");
        b_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_sendActionPerformed(evt);
            }
        });

        b_pmSend.setText("Send PM");
        b_pmSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_pmSendActionPerformed(evt);
            }
        });

        b_savemsg.setText("Save message");
        b_savemsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_savemsgActionPerformed(evt);
            }
        });

        b_clear.setText("Clear");
        b_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_clearActionPerformed(evt);
            }
        });

        b_showSaved.setText("ShowSaved");
        b_showSaved.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_showSavedActionPerformed(evt);
            }
        });

        b_chatBox.setText("ChatBox");
        b_chatBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_chatBoxActionPerformed(evt);
            }
        });

        ta_usersOnline.setEditable(false);
        ta_usersOnline.setColumns(20);
        ta_usersOnline.setRows(5);
        jScrollPane2.setViewportView(ta_usersOnline);

        jLabel1.setText("Currently Online:");

        jLabel2.setText("Send personal Message to:");

        ta_status.setEditable(false);
        ta_status.setColumns(20);
        ta_status.setRows(5);
        jScrollPane3.setViewportView(ta_status);

        jLabel3.setText("Status:");

        jLabel4.setText("Chat Box:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lb_username, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lb_address, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tf_address)
                                .addGap(18, 18, 18)
                                .addComponent(lb_port))
                            .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_port, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b_connect)
                        .addGap(18, 18, 18)
                        .addComponent(b_anonymous, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jScrollPane1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(b_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b_showSaved, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(b_chatBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(b_savemsg, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(143, 143, 143)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tf_chat, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(b_send, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(b_disconnect)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_pmUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_pmSend, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lb_port)
                        .addComponent(tf_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(b_anonymous)
                        .addComponent(b_connect)
                        .addComponent(b_disconnect))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tf_address, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lb_address)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_username))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel1))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_pmUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(b_clear)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(b_savemsg)
                                .addComponent(b_chatBox)
                                .addComponent(b_showSaved, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tf_chat, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(b_send, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b_pmSend, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_addressActionPerformed
       
    }//GEN-LAST:event_tf_addressActionPerformed

    private void tf_portActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_portActionPerformed
   
    }//GEN-LAST:event_tf_portActionPerformed

    private void tf_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_usernameActionPerformed
    
    }//GEN-LAST:event_tf_usernameActionPerformed

    private void b_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_connectActionPerformed
        if (isConnected == false) 
        {
            username = tf_username.getText();
            tf_username.setEditable(false);
            tf_port.setEditable(false);
            tf_address.setEditable(false);
            
            String add=tf_address.getText();
            String po=tf_port.getText();
            
            if(add.length()>0){
                address = add;
            } 
            if(po.length()>0){
                port = Integer.parseInt(po);
            }

            try 
            {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(username + ":has connected.:Connect");
                writer.flush(); 
                isConnected = true; 
                allMsg=new File(username+".txt");
                if(!allMsg.exists()){
                    allMsg.createNewFile();
                }
            } 
            catch (Exception ex) 
            {
                ta_status.append("Cannot Connect! Try Again. \n");
                tf_username.setEditable(true);
                tf_port.setEditable(true);
                tf_address.setEditable(true);
            }
            
            ListenThread();
            
        } else if (isConnected == true) 
        {
            ta_status.append("You are already connected. \n");
        }
    }//GEN-LAST:event_b_connectActionPerformed

    private void b_disconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_disconnectActionPerformed
        ta_usersOnline.setText("");
        ta_chat.setText("");
        sendDisconnect();
        Disconnect();
    }//GEN-LAST:event_b_disconnectActionPerformed

    private void b_anonymousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_anonymousActionPerformed
        tf_username.setText("");
        if (isConnected == false) 
        {
            String anon="anonymous";
            Random generator = new Random(); 
            int i = generator.nextInt(999) + 1;
            String is=String.valueOf(i);
            anon=anon.concat(is);
            username=anon;
            
            tf_username.setText(anon);
            tf_username.setEditable(false);
            tf_port.setEditable(false);
            tf_address.setEditable(false);
            
            String add=tf_address.getText();
            String po=tf_port.getText();
            
            if(add.length()>0){
                address = add;
            } 
            if(po.length()>0){
                port = Integer.parseInt(po);
            }

            try 
            {
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(anon + ":has connected.:Connect");
                writer.flush(); 
                isConnected = true; 
            } 
            catch (Exception ex) 
            {
                ta_status.append("Cannot Connect! Try Again. \n");
                tf_username.setEditable(true);
                tf_port.setEditable(true);
                tf_address.setEditable(true);
            }
            
            ListenThread();
            
        } else if (isConnected == true) 
        {
            ta_status.append("You are already connected. \n");
        }        
    }//GEN-LAST:event_b_anonymousActionPerformed

    private void b_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_sendActionPerformed
        if(isConnected==true)
        {
            if(showSaved==0){
                String nothing = "";
                if ((tf_chat.getText()).equals(nothing)) {
                    tf_chat.setText("");
                    tf_chat.requestFocus();
                } else {
                    try {
                       writer.println(username + ":" + tf_chat.getText() + ":" + "Chat");
                       writer.flush(); // flushes the buffer
                    } catch (Exception ex) {
                        ta_status.append("Message was not sent. \n");
                    }
                    tf_chat.setText("");
                    tf_chat.requestFocus();
                }

                tf_chat.setText("");
                tf_chat.requestFocus();
            }
        }
        else{
            ta_status.append("You're not connected!\n");
        }
        
    }//GEN-LAST:event_b_sendActionPerformed

    private void b_savemsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_savemsgActionPerformed
        if(isConnected==true)
        {
            String str=ta_chat.getText();
        
            System.out.println(str);

            try {
                FilesUtil.writeToTextFile(username+".txt", str);
            } catch (IOException ex) {
                Logger.getLogger(client_frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            ta_status.append("You're not connected!\n");
        }
        
        
    }//GEN-LAST:event_b_savemsgActionPerformed

    private void b_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_clearActionPerformed
        ta_chat.setText("");
        ta_status.setText("");
    }//GEN-LAST:event_b_clearActionPerformed

    private void b_showSavedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_showSavedActionPerformed
        if(isConnected==true)
        {
            if(showSaved==0){
                try {
                    //savedMsgString
                    chatBoxCurrent=ta_chat.getText();
                    ta_chat.setText("Saved Message:\n");
                    String input = FilesUtil.readTextFile(username+".txt");
                    ta_chat.append(input+"\n");
                    showSaved=1;
                } catch (IOException ex) {
                    Logger.getLogger(client_frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else{
            ta_status.append("You're not connected!\n");
        }
        
    }//GEN-LAST:event_b_showSavedActionPerformed

    private void b_chatBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_chatBoxActionPerformed
        if(isConnected==true)
        {
            if(showSaved==1){
                showSaved=0;
                ta_chat.setText(chatBoxCurrent);
            }
        }
        else{
            ta_status.append("You're not connected!\n");
        }
    }//GEN-LAST:event_b_chatBoxActionPerformed

    private void b_pmSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_pmSendActionPerformed
        if(isConnected==true){
            if(showSaved==0){
                String nothing = "",index="";
                int i=-1;
                if ((tf_chat.getText()).equals(nothing)) {
                    tf_chat.setText("");
                    tf_chat.requestFocus();
                } 
                else if((tf_pmUsername.getText()).equals(nothing)){
                    ta_status.append("Enter the username to whom you want to send personal message\n");
                } 
                else {
                    try {
                      
                        while (true) 
                        {
                            i++;
//                            System.out.println(users.get(i));
//                            System.out.println(tf_pmUsername.getText());
//                            System.out.println(i);
                            if((tf_pmUsername.getText()).equals((users.get(i)))){
                                ta_chat.append(username + ": "+tf_chat.getText()+"\n");
                                index = Integer.toString(i);
                                break;
                            }
                        } 
                       
                       writer.println(username + ":" + tf_chat.getText() + ":" + "PM" + ":" + index);
                       writer.flush(); // flushes the buffer
                    } catch (Exception ex) {
                        ta_status.append("Message was not sent. \n");
                    }
                    tf_chat.setText("");
                    tf_chat.requestFocus();
                }

                tf_chat.setText("");
                tf_chat.requestFocus();
            }
        }
        else{
            ta_status.append("You're not connected!\n");
        }
    }//GEN-LAST:event_b_pmSendActionPerformed

    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                new client_frame().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_anonymous;
    private javax.swing.JButton b_chatBox;
    private javax.swing.JButton b_clear;
    private javax.swing.JButton b_connect;
    private javax.swing.JButton b_disconnect;
    private javax.swing.JButton b_pmSend;
    private javax.swing.JButton b_savemsg;
    private javax.swing.JButton b_send;
    private javax.swing.JButton b_showSaved;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lb_address;
    private javax.swing.JLabel lb_port;
    private javax.swing.JLabel lb_username;
    private javax.swing.JTextArea ta_chat;
    private javax.swing.JTextArea ta_status;
    private javax.swing.JTextArea ta_usersOnline;
    private javax.swing.JTextField tf_address;
    private javax.swing.JTextField tf_chat;
    private javax.swing.JTextField tf_pmUsername;
    private javax.swing.JTextField tf_port;
    private javax.swing.JTextField tf_username;
    // End of variables declaration//GEN-END:variables
}
