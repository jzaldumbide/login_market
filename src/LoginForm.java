import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD
import java.sql.Connection;
import java.sql.DriverManager;
=======
import java.sql.*;
>>>>>>> 5a2516f (Initial commit)

public class LoginForm extends JDialog{
    private JTextField emailTF;
    private JPasswordField passwordTF;
    private JButton cancelButton;
    private JButton OKButton;
    private JPanel loginPanel;

    public LoginForm (JFrame parent){
        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(640,480));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //setDefaultLookAndFeelDecorated();
        setVisible(true);

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email=emailTF.getText();
                String password=String.valueOf(passwordTF.getPassword());

                user=getAuthenticationUser(email,password);

                if (user!=null){
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(
                            LoginForm.this,"email o password incorrectos",
                            "intente nuevamente",
                           JOptionPane.ERROR_MESSAGE
                    );
                }

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD

            }
        });
=======
dispose();
            }
        });
        setVisible(true);
>>>>>>> 5a2516f (Initial commit)
    }

    public static void main(String[] args) {
        LoginForm loginForm=new LoginForm(null);
<<<<<<< HEAD

=======
        User user =loginForm.user;

        if(user!=null){
            System.out.println("Autenticacion correcta:"+user.nombre);
            System.out.println("email: "+user.email);
            System.out.println("celular: "+user.celular);
            System.out.println("direccion: "+user.direccion);
        }
        else{
            System.out.println("Autenticaci'on fallida");
        }
>>>>>>> 5a2516f (Initial commit)
    }

    public User user;
    private User getAuthenticationUser(String email, String password){
        User user =null;

        final String DB_URL="jdbc:mysql://localhost/mitienda?serverTimezone=UTC";
<<<<<<< HEAD
        final String USERNAME="rooT";
=======
        final String USERNAME="root";
>>>>>>> 5a2516f (Initial commit)
        final String PASSWORD="";

        try{
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
<<<<<<< HEAD
=======
            Statement stmt= conn.createStatement();
            String sql="SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);

            ResultSet resultSet=preparedStatement.executeQuery();

            if(resultSet.next()){
                user=new User();
             /*   user.nombre=resultSet.getString(nombre);
                user.email=resultSet.getString(email);
                user.celular=resultSet.getString(celular);
                user.direccion=resultSet.getString(direccion);
                user.password=resultSet.getString(password);*/
            }



>>>>>>> 5a2516f (Initial commit)
        }catch(Exception e){
            e.printStackTrace();
        }

        return user;
    }
}
