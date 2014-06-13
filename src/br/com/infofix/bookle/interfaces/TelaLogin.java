/**
 * Bookle Sistema Acadêmico<br>
 * Autor: Kélvin Santiago<br>
 * Data: 11/06/2014.
 */
package br.com.infofix.bookle.interfaces;

import br.com.infofix.bookle.conexao.ConexaoMysql;
import javax.swing.JOptionPane;

/**
 * Classe responsável por montar a tela de login do sistema que consite
 * em input de usuário e senha, se esses dados de autenticação forem
 * verdadeiros é aberto a tela da Janela principal do sistema Bookle.
 * @author Kélvin Santiago
 */
public class TelaLogin extends javax.swing.JFrame {

    // Declarando Variaveis
    public static String nomelogado;
    public static String matriculalogado;
    public static String senhalogado;
    public static String tipouser;
    
    /**
     * Construtor inicia componentes básicos da interface gráfica.
    */ 
    public TelaLogin() {
        initComponents();
    }
    
    /**
     * Método instancia objeto da janela principal do sistema e seta 
     * como visível.
     */
    public void acessarSistema() {
        TelaPrincipalBookle telaprin = new TelaPrincipalBookle();
        telaprin.setVisible(true);
    }

    /**
     * Método verifica autenticação no banco de dados mysql 
     * se os dados de usuário/matrícula e senha forem corretos
     * o sistema retorna true, caso não seja ele retorna false
     * informando que o usuário/matrícula ou senha estão incorretos.
     * @return acesso Boolean
     */
    public boolean verificaAutenticacao() {
        boolean acesso = false;

        try {
            ConexaoMysql conectmysql = new ConexaoMysql();
            conectmysql.abrirConexao();
            conectmysql.createStatement();
            conectmysql.executaSQL("SELECT * FROM tbuser");
           
            while (conectmysql.resultset.next()) {
                if ((textfieldNome.getText().equals(conectmysql.resultset.getString("matriculauser"))
                        || textfieldNome.getText().equals(conectmysql.resultset.getString("nomeuser")))
                        && jpasswordField.getText().equals(conectmysql.resultset.getString("senhauser"))) {
                    nomelogado = conectmysql.resultset.getString("nomeuser");
                    matriculalogado = conectmysql.resultset.getString("matriculauser");
                    senhalogado = conectmysql.resultset.getString("senhauser");
                    tipouser =  conectmysql.resultset.getString("permissaouser");
                    acesso = true;
                }
            }
            conectmysql.fecharConexao();

            if (!acesso) {
                JOptionPane.showMessageDialog(null, "Usuário/Matrícula ou Senha Inválida!", "Acesso Negado", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception erro) {
            System.out.println("Erro Tela Login: " + erro);
        }
        return acesso;
    }

    // Método gerado automaticamente GUI 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        textfieldNome = new javax.swing.JTextField();
        jpasswordField = new javax.swing.JPasswordField();
        labelNome = new javax.swing.JLabel();
        labelPassword = new javax.swing.JLabel();
        buttonSair = new javax.swing.JButton();
        buttonAcessar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        labelImageLogin = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bookle Login");
        setMinimumSize(new java.awt.Dimension(450, 300));
        setResizable(false);
        getContentPane().setLayout(null);

        textfieldNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(textfieldNome);
        textfieldNome.setBounds(122, 143, 280, 30);

        jpasswordField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(jpasswordField);
        jpasswordField.setBounds(122, 220, 280, 30);

        labelNome.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        labelNome.setForeground(new java.awt.Color(0, 102, 153));
        labelNome.setText("Matrícula / Nome Usuário:");
        getContentPane().add(labelNome);
        labelNome.setBounds(122, 117, 260, 25);

        labelPassword.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        labelPassword.setForeground(new java.awt.Color(0, 102, 153));
        labelPassword.setText("Password:");
        getContentPane().add(labelPassword);
        labelPassword.setBounds(122, 191, 120, 25);

        buttonSair.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infofix/bookle/imagens/delete.png"))); // NOI18N
        buttonSair.setText("Sair");
        buttonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSairActionPerformed(evt);
            }
        });
        getContentPane().add(buttonSair);
        buttonSair.setBounds(282, 275, 130, 50);

        buttonAcessar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonAcessar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infofix/bookle/imagens/accept.png"))); // NOI18N
        buttonAcessar.setText("Entrar");
        buttonAcessar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAcessarActionPerformed(evt);
            }
        });
        getContentPane().add(buttonAcessar);
        buttonAcessar.setBounds(109, 275, 130, 50);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        labelTitulo.setText("Login Bookle");

        labelImageLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infofix/bookle/imagens/booklelogo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(109, Short.MAX_VALUE)
                .addComponent(labelImageLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelTitulo)
                    .addComponent(labelImageLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 520, 104);

        setSize(new java.awt.Dimension(520, 381));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSairActionPerformed
        dispose();
    }//GEN-LAST:event_buttonSairActionPerformed

    private void buttonAcessarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAcessarActionPerformed
        boolean acesso;
        acesso = verificaAutenticacao();
        if (acesso) {
            dispose();
            acessarSistema();
        }
    }//GEN-LAST:event_buttonAcessarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAcessar;
    private javax.swing.JButton buttonSair;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jpasswordField;
    private javax.swing.JLabel labelImageLogin;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelPassword;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JTextField textfieldNome;
    // End of variables declaration//GEN-END:variables
}
