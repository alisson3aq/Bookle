package br.com.infofix.bookle.interfaces;

import br.com.infofix.bookle.conexao.ConexaoMysql;
import javax.swing.JOptionPane;

public class TelaEditarUsuario extends javax.swing.JInternalFrame {

    ConexaoMysql conectmysql = new ConexaoMysql();
    String tipouser = "";

    public TelaEditarUsuario(String coduser) {
        initComponents();
        preencheCamposEditar(coduser);
    }

    public void preencheCamposEditar(String coduser) {
        labelMatriculaCod.setText(coduser);
        try {

            conectmysql.abrirConexao();
            conectmysql.createStatement();
            conectmysql.executaSQL("SELECT * FROM tbuser where matriculauser LIKE " + coduser);
            conectmysql.resultset.first();
            jtextfieldNome.setText(conectmysql.resultset.getString("nomeuser"));
            jtextfieldSenha.setText(conectmysql.resultset.getString("senhauser"));
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

    public void editarUser() {
        String sql = "UPDATE tbuser set nomeuser = ?, senhauser = ?, permissaouser = ? where matriculauser = ?";
        try {
            conectmysql.abrirConexao();
            conectmysql.prepareStatement(sql);
            conectmysql.preparestatement.setString(1, jtextfieldNome.getText());
            conectmysql.preparestatement.setString(2, jtextfieldSenha.getText());
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
        jtextfieldSenha = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        labelHeader = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        labelMatriculaCod = new javax.swing.JLabel();
        jradiobuttonAluno = new javax.swing.JRadioButton();
        jradiobuttonProfessor = new javax.swing.JRadioButton();
        jradiobuttonAdministrador = new javax.swing.JRadioButton();
        labelTipoUsuario = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setClosable(true);

        jbuttonEditar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infofix/bookle/imagens/editarok.png"))); // NOI18N
        jbuttonEditar.setText("Editar");
        jbuttonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonEditarActionPerformed(evt);
            }
        });

        jbuttonCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infofix/bookle/imagens/delete.png"))); // NOI18N
        jbuttonCancelar.setText("Cancelar");
        jbuttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonCancelarActionPerformed(evt);
            }
        });

        labelMatricula.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelMatricula.setText("Matrícula:");

        labelNome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelNome.setText("Nome:");

        jtextfieldNome.setBackground(new java.awt.Color(255, 255, 204));
        jtextfieldNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        labelSenha.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelSenha.setText("Senha:");

        jtextfieldSenha.setBackground(new java.awt.Color(255, 255, 204));
        jtextfieldSenha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

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

        jradiobuttonAluno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jradiobuttonAluno.setText("Aluno");
        jradiobuttonAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradiobuttonAlunoActionPerformed(evt);
            }
        });

        jradiobuttonProfessor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jradiobuttonProfessor.setText("Professor");
        jradiobuttonProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradiobuttonProfessorActionPerformed(evt);
            }
        });

        jradiobuttonAdministrador.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jradiobuttonAdministrador.setText("Administrador");
        jradiobuttonAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradiobuttonAdministradorActionPerformed(evt);
            }
        });

        labelTipoUsuario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelTipoUsuario.setText("Tipo de usuário:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelSenha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtextfieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtextfieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelMatricula)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(107, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelTipoUsuario)
                        .addGap(178, 178, 178))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbuttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jbuttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jradiobuttonAluno)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jradiobuttonProfessor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jradiobuttonAdministrador)))
                        .addGap(116, 116, 116))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(labelMatricula)
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelNome)
                            .addComponent(jtextfieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelSenha)
                    .addComponent(jtextfieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(labelTipoUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jradiobuttonAluno)
                    .addComponent(jradiobuttonProfessor)
                    .addComponent(jradiobuttonAdministrador))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbuttonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbuttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        setBounds(400, 40, 541, 468);
    }// </editor-fold>//GEN-END:initComponents

    private void jbuttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_jbuttonCancelarActionPerformed

    private void jbuttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonEditarActionPerformed
        editarUser();
    }//GEN-LAST:event_jbuttonEditarActionPerformed

    private void jradiobuttonAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradiobuttonAlunoActionPerformed
        jradiobuttonAluno.setSelected(true);
        jradiobuttonProfessor.setSelected(false);
        jradiobuttonAdministrador.setSelected(false);
        tipouser = jradiobuttonAluno.getText();
    }//GEN-LAST:event_jradiobuttonAlunoActionPerformed

    private void jradiobuttonProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradiobuttonProfessorActionPerformed
        jradiobuttonAluno.setSelected(false);
        jradiobuttonProfessor.setSelected(true);
        jradiobuttonAdministrador.setSelected(false);
        tipouser = jradiobuttonProfessor.getText();
    }//GEN-LAST:event_jradiobuttonProfessorActionPerformed

    private void jradiobuttonAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradiobuttonAdministradorActionPerformed
        jradiobuttonAluno.setSelected(false);
        jradiobuttonProfessor.setSelected(false);
        jradiobuttonAdministrador.setSelected(true);
        tipouser = jradiobuttonAdministrador.getText();
    }//GEN-LAST:event_jradiobuttonAdministradorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbuttonCancelar;
    private javax.swing.JButton jbuttonEditar;
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
