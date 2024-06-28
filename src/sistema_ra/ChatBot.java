/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema_ra;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


/**
 *
 * @author Yuiridia
 */

public class ChatBot extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private DatabaseHandler dbHandler;
    
    public ChatBot(){
        //Inicializa componentes
        chatArea= new JTextArea();
        inputField = new JTextField();
        sendButton = new JButton("Enviar");
        dbHandler= new DatabaseHandler();
          
        //Configura JFrame
        setTitle("ChatBot");
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setSize(400, 300);
       setLayout(new BorderLayout());
       
       // Cargar y establecer el nuevo icono
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/new_icon.png")));
       
       //Añade componentes al JFrame
       add(new JScrollPane(chatArea),BorderLayout.CENTER);
       add(inputField,BorderLayout.SOUTH);
       add(sendButton,BorderLayout.EAST);
       
       //Accion Listener para el boton de enviar
       sendButton.addActionListener( new ActionListener() {
        @Override 
        public void actionPerformed(ActionEvent e)
        {
            String userInput= inputField.getText();
            chatArea.append("User:  " +userInput +"\n");
            inputField.setText("");
            
            //logica de respuesta automatica
            String botResponse = dbHandler.getAnswer(userInput);
            chatArea.append("Bot: " + botResponse + "\n");    
        }
       });
       setVisible(true);
    }
    public static void main(String[]args){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                new ChatBot().setVisible(true);
            }
        });
        
    }
}