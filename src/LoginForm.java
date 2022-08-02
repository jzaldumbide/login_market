import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm extends JDialog{
    private JTextField emailTF;
    private JPasswordField passwordTF;
    private JButton cancelButton;
    private JButton OKButton;
    private JPanel loginPanel;
    public User user;
    public LoginForm (JFrame parent){
        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(640,480));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        //setVisible(true);

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email=emailTF.getText();
                String password=String.valueOf(passwordTF.getPassword());
                System.out.println("boton ok");
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
                System.out.println("boton cancel");
                dispose();
            }
        });
        setVisible(true);
    }


    private User getAuthenticationUser(String email, String password){
        User user =null;

        final String DB_URL="jdbc:mysql://localhost/mitienda?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        try{
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt= conn.createStatement();
            String sql="SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            System.out.println("conexion ok");
            ResultSet resultSet=preparedStatement.executeQuery();

            if(resultSet.next()){
                user=new User();
                user.nombre=resultSet.getString("nombre");
                user.email=resultSet.getString("email");
                user.celular=resultSet.getString("celular");
                user.direccion=resultSet.getString("direccion");
                user.password=resultSet.getString("password");
            }

            stmt.close();
            conn.close();

        }catch(Exception e){
            System.out.println("error de...");
            e.printStackTrace();
        }

        return user;
    }


    public static void main(String[] args) {
        LoginForm loginForm=new LoginForm(null);
        User user =loginForm.user;

        if(user!=null){
            System.out.println("Autenticacion correcta:"+user.nombre);
            System.out.println("email: "+user.email);
            System.out.println("celular: "+user.celular);
            System.out.println("direccion: "+user.direccion);
            System.out.println("clave: "+user.password);
        }
        else{
            System.out.println("Autenticacion fallida");
        }
    }
}
