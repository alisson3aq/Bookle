/**
 * Bookle Sistema Acadêmico<br>
 * Autor: Kélvin Santiago<br>
 * Data: 27/06/2014.
 */
package br.com.infofix.bookle.interfaces;

import br.com.infofix.bookle.conexao.ConexaoMysql;
import javax.swing.JOptionPane;

/**
 * Classe responsável pela edição dos dados do usuário<br>
 *
 * @author Kélvin Santiago
 */
public class TelaEditarUsuario extends javax.swing.JInternalFrame {

    ConexaoMysql conectmysql = new ConexaoMysql();
    String tipouser = "";

    /**
     * Este construtor inicializa os componentes de interface GUI, gerados
     * automaticamente, é inicializado também um método preencheCamposEditar que
     * é responsável em preencher cada componente de acordo com a opcao
     * selecionada na jtable do formulário de cadastro de usuários.
     *
     * @param coduser
     */
    public TelaEditarUsuario(String coduser) {
        initComponents();
        preencheCamposEditar(coduser);
    }

    /**
     * O Método é responsável por preencher os componentes do formulário, com
     * base ao codigo do usuário que foi informado via argumento no construtor
     *
     * @param coduser String - Codigo do usuário
     */
    public void preencheCamposEditar(String coduser) {
        labelMatriculaCod.setText(coduser);
        try {

            conectmysql.abrirConexao();
            conectmysql.createStatement();
            conectmysql.executaSQL("SELECT * FROM tbuser where matriculauser LIKE " + coduser);
            conectmysql.resultset.first();
            jtextfieldNome.setText(conectmysql.resultset.getString("nomeuser"));
            jpasswordfieldSenha.setText(conectmysql.resultset.getString("senhauser"));
            tipouser = conectmysql.resultset.getString("permissaouser");
            switch (tipouser) {
                case "Aluno":
                    jradiobuttonAluno.setSelected(true);
                    jradiobuttonProfessor.setSelected(false);
                    jradiobuttonAdministrador.setSelected(false);

                    break;
                case "Professor":
                    jradiobuttonProfessor.setSelected(true);
                    jradiobuttonAluno.setSelected(false);
                    jradiobuttonAdministrador.setSelected(false);
                    break;
                default:
                    jradiobuttonAdministrador.setSelected(true);
                    jradiobuttonAluno.setSelected(false);
                    jradiobuttonProfessor.setSelected(false);
                    break;
            }

            conectmysql.fecharConexao();

        } catch (Exception erro) {
            System.out.println("Erro preencher Campos Editar: " + erro);

        }
    }

    /**
     * Este método é responsável por pegar as informações dos componentes do
     * formulário e atualiza-las no banco de dados.
     */
    public void editarUser() {
        String sql = "UPDATE tbuser set nomeuser = ?, senhauser = ?, permissaouser = ? where matriculauser = ?";
        try {
            conectmysql.abrirConexao();
            conectmysql.prepareStatement(sql);
            conectmysql.preparestatement.setString(1, jtextfieldNome.getText());
            conectmysql.preparestatement.setString(2, jpasswordfieldSenha.getText());
            conectmysql.preparestatement.setString(3, tipouser);
            conectmysql.preparestatement.setString(4, labelMatriculaCod.getText());
            conectmysql.preparestatement.executeUpdate();
            dispose();
            conectmysql.fecharConexao();
            JOptionPane.showMessageDialog(null, "Usuário editado com sucesso, clique no botão ATUALIZAR", "Usuário Editado", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception erro) {
            System.out.println("Erro Editar Usuario: " + erro);
        }
    }

    //Código gerado automaticamente pelo IDE
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jbuttonEditar = new javax.swing.JButton();
        jbuttonCancelar = new javax.swing.JButton();
        labelMatricula = new javax.swing.JLabel();
        labelNome = new javax.swing.JLabel();
        jtextfieldNome = new javax.swing.JTextField();
        labelSenha = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        labelHeader = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        labelMatriculaCod = new javax.swing.JLabel();
        jradiobuttonAluno = new javax.swing.JRadioButton();
        jradiobuttonProfessor = new javax.swing.JRadioButton();
        jradiobuttonAdministrador = new javax.swing.JRadioButton();
        labelTipoUsuario = new javax.swing.JLabel();
        jpasswordfieldSenha = new javax.swing.JPasswordField();
        jtextfieldSenha = new javax.swing.JTextField();
        jbuttonMostrarSenha = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setClosable(true);
        getContentPane().setLayout(null);

        jbuttonEditar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infofix/bookle/imagens/editarok.png"))); // NOI18N
        jbuttonEditar.setText("Editar");
        jbuttonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonEditarActionPerformed(evt);
            }
        });
        getContentPane().add(jbuttonEditar);
        jbuttonEditar.setBounds(129, 361, 120, 50);

        jbuttonCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infofix/bookle/imagens/delete.png"))); // NOI18N
        jbuttonCancelar.setText("Cancelar");
        jbuttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(jbuttonCancelar);
        jbuttonCancelar.setBounds(289, 361, 120, 50);

        labelMatricula.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelMatricula.setText("Matrícula:");
        getContentPane().add(labelMatricula);
        labelMatricula.setBounds(130, 127, 76, 22);

        labelNome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelNome.setText("Nome:");
        getContentPane().add(labelNome);
        labelNome.setBounds(130, 176, 52, 22);

        jtextfieldNome.setBackground(new java.awt.Color(255, 255, 204));
        jtextfieldNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(jtextfieldNome);
        jtextfieldNome.setBounds(218, 171, 200, 35);

        labelSenha.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelSenha.setText("Senha:");
        getContentPane().add(labelSenha);
        labelSenha.setBounds(130, 228, 54, 22);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelHeader.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelHeader.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infofix/bookle/imagens/editar.png"))); // NOI18N
        labelHeader.setText(" EDITAR USUÁRIO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelHeader)
                .addGap(102, 102, 102))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(labelHeader)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(12, 13, 501, 78);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        labelMatriculaCod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelMatriculaCod, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelMatriculaCod, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(218, 121, 200, 35);

        jradiobuttonAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jradiobuttonAluno.setText("Aluno");
        jradiobuttonAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradiobuttonAlunoActionPerformed(evt);
            }
        });
        getContentPane().add(jradiobuttonAluno);
        jradiobuttonAluno.setBounds(144, 319, 63, 25);

        jradiobuttonProfessor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jradiobuttonProfessor.setText("Professor");
        jradiobuttonProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradiobuttonProfessorActionPerformed(evt);
            }
        });
        getContentPane().add(jradiobuttonProfessor);
        jradiobuttonProfessor.setBounds(211, 319, 85, 25);

        jradiobuttonAdministrador.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jradiobuttonAdministrador.setText("Administrador");
        jradiobuttonAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradiobuttonAdministradorActionPerformed(evt);
            }
        });
        getContentPane().add(jradiobuttonAdministrador);
        jradiobuttonAdministrador.setBounds(296, 319, 113, 25);

        labelTipoUsuario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelTipoUsuario.setText("Tipo de usuário:");
        getContentPane().add(labelTipoUsuario);
        labelTipoUsuario.setBounds(218, 288, 129, 22);
        getContentPane().add(jpasswordfieldSenha);
        jpasswordfieldSenha.setBounds(220, 220, 200, 35);

        jtextfieldSenha.setVisible(false);
        getContentPane().add(jtextfieldSenha);
        jtextfieldSenha.setBounds(220, 220, 200, 35);

        jbuttonMostrarSenha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonMostrarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infofix/bookle/imagens/desbloquear.png"))); // NOI18N
        jbuttonMostrarSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jbuttonMostrarSenhaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jbuttonMostrarSenhaMouseReleased(evt);
            }
        });
        getContentPane().add(jbuttonMostrarSenha);
        jbuttonMostrarSenha.setBounds(430, 220, 40, 35);

        setBounds(400, 40, 541, 468);
    }// </editor-fold>//GEN-END:initComponents

    // Evento button Cancelar
    private void jbuttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_jbuttonCancelarActionPerformed

    // Evento button Editar
    private void jbuttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonEditarActionPerformed
        if (jtextfieldNome.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor insira um nome!", "Nome em Branco", JOptionPane.ERROR_MESSAGE);
        } else if (jpasswordfieldSenha.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor insira uma senha!", "Senha em Branco", JOptionPane.ERROR_MESSAGE);
        } else {
            editarUser();
        }
    }//GEN-LAST:event_jbuttonEditarActionPerformed

    // Evento radio Aluno
    private void jradiobuttonAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradiobuttonAlunoActionPerformed
        jradiobuttonAluno.setSelected(true);
        jradiobuttonProfessor.setSelected(false);
        jradiobuttonAdministrador.setSelected(false);
        tipouser = jradiobuttonAluno.getText();
    }//GEN-LAST:event_jradiobuttonAlunoActionPerformed

    // Evento radio Professor
    private void jradiobuttonProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradiobuttonProfessorActionPerformed
        jradiobuttonAluno.setSelected(false);
        jradiobuttonProfessor.setSelected(true);
        jradiobuttonAdministrador.setSelected(false);
        tipouser = jradiobuttonProfessor.getText();
    }//GEN-LAST:event_jradiobuttonProfessorActionPerformed

    // Evento radio Administrador
    private void jradiobuttonAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradiobuttonAdministradorActionPerformed
        jradiobuttonAluno.setSelected(false);
        jradiobuttonProfessor.setSelected(false);
        jradiobuttonAdministrador.setSelected(true);
        tipouser = jradiobuttonAdministrador.getText();
    }//GEN-LAST:event_jradiobuttonAdministradorActionPerformed

    // Evento button Ocultar Senha ao soltar Clique
    private void jbuttonMostrarSenhaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbuttonMostrarSenhaMouseReleased
        jpasswordfieldSenha.setVisible(true);
        jpasswordfieldSenha.setText(jtextfieldSenha.getText());
        jtextfieldSenha.setVisible(false);
    }//GEN-LAST:event_jbuttonMostrarSenhaMouseReleased

    // Evento button Mostrar Senha ao Pressionar
    private void jbuttonMostrarSenhaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbuttonMostrarSenhaMousePressed
        jtextfieldSenha.setVisible(true);
        jtextfieldSenha.setText(jpasswordfieldSenha.getText());
        jpasswordfieldSenha.setVisible(false);
    }//GEN-LAST:event_jbuttonMostrarSenhaMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbuttonCancelar;
    private javax.swing.JButton jbuttonEditar;
    private javax.swing.JButton jbuttonMostrarSenha;
    private javax.swing.JPasswordField jpasswordfieldSenha;
    private javax.swing.JRadioButton jradiobuttonAdministrador;
    private javax.swing.JRadioButton jradiobuttonAluno;
    private javax.swing.JRadioButton jradiobuttonProfessor;
    private javax.swing.JTextField jtextfieldNome;
    private javax.swing.JTextField jtextfieldSenha;
    private javax.swing.JLabel labelHeader;
    private javax.swing.JLabel labelMatricula;
    private javax.swing.JLabel labelMatriculaCod;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JLabel labelTipoUsuario;
    // End of variables declaration//GEN-END:variables
}
