/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookleprojeto;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author admin
 */
public class TelaCadastrarDisciplina extends javax.swing.JInternalFrame {

    TelaPesquisar telapesquisar = new TelaPesquisar();

    /**
     * Creates new form TelaCadastrarDisciplina
     */
    public TelaCadastrarDisciplina() {
        initComponents();
        listarDisciplina();
        telapesquisar.preencheComboBox("select * from tbcurso", "nomecurso", jcomboboxCursoCadDisciplina);
        jcomboboxCursoCadDisciplina.setSelectedIndex(-1);
    }

    public void listarDisciplina() {
        ArrayList dadosdisciplina = new ArrayList();

        dadosdisciplina.clear();

        String[] colunas = new String[]{"Código da Disciplina", "Nome da Disciplina"};
        try {

            ConexaoMysql conectmysql = new ConexaoMysql();
            conectmysql.abrirConexao();
            conectmysql.statement = conectmysql.conexao.createStatement();
            conectmysql.resultset = conectmysql.statement.executeQuery("SELECT * FROM tbdisciplina");

            while (conectmysql.resultset.next()) {

                dadosdisciplina.add(new Object[]{conectmysql.resultset.getString("coddisciplina"), conectmysql.resultset.getString("nomedisciplina")});
            }

            conectmysql.statement.close();

        } catch (Exception erro) {
            System.out.println("Erro preencher Tabela Cursos: " + erro);

        }
        ModeloTabela modeltable = new ModeloTabela(dadosdisciplina, colunas);

        jtableListaDisciplinas.setModel(modeltable);
        jtableListaDisciplinas.getColumnModel().getColumn(0).setPreferredWidth(120);
        jtableListaDisciplinas.getColumnModel().getColumn(0).setResizable(false);
        jtableListaDisciplinas.getColumnModel().getColumn(1).setPreferredWidth(230);
        jtableListaDisciplinas.getColumnModel().getColumn(1).setResizable(false);

        jtableListaDisciplinas.getTableHeader().setReorderingAllowed(false);
        jtableListaDisciplinas.setAutoResizeMode(jtableListaDisciplinas.AUTO_RESIZE_OFF);
        jtableListaDisciplinas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtableListaDisciplinas.setRowHeight(50);

    }

    public void cadastrarDisciplina() {
        try {
            Boolean cadastrado = false;
            int codigodocurso = 0;

            ConexaoMysql conectmysql = new ConexaoMysql();

            conectmysql.abrirConexao();

            // Percorrendo tabela Curso
            conectmysql.statement = conectmysql.conexao.createStatement();

            conectmysql.resultset = conectmysql.statement.executeQuery("select * from tbcurso");

            while (conectmysql.resultset.next()) {

                if (conectmysql.resultset.getString("nomecurso").equals(jcomboboxCursoCadDisciplina.getSelectedItem())) {
                    codigodocurso = conectmysql.resultset.getInt("codcurso");
                }
            }

            // Percorrendo Tabela Disciplina
            conectmysql.statement = conectmysql.conexao.createStatement();

            conectmysql.resultset = conectmysql.statement.executeQuery("select * from tbdisciplina");

            while (conectmysql.resultset.next()) {

                if (conectmysql.resultset.getString("nomedisciplina").equals(jtextfieldDisciplina.getText())) {
                    JOptionPane.showMessageDialog(null, "A Disciplina ja está cadastrada", "Disciplina Cadastrada!", JOptionPane.INFORMATION_MESSAGE);
                    cadastrado = true;
                }
            }

            if (cadastrado == false) {

                conectmysql.statement.executeUpdate("INSERT INTO tbdisciplina(codcurso,nomedisciplina) VALUES (" + codigodocurso + "," + "'" + jtextfieldDisciplina.getText() + "')");
                conectmysql.statement = conectmysql.conexao.createStatement();
                conectmysql.resultset = conectmysql.statement.executeQuery("select * from tbuser");

                JOptionPane.showMessageDialog(null, "Disciplina Cadastrada com Sucesso!", "Disciplina Cadastrada!", JOptionPane.INFORMATION_MESSAGE);
                jtextfieldDisciplina.setText(null);
                jcomboboxCursoCadDisciplina.setSelectedIndex(-1);
            }

            conectmysql.statement.close();

        } catch (Exception erro) {
            System.out.println("Erro Cadastrar Disciplina: " + erro);

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtableListaDisciplinas = new javax.swing.JTable();
        jtabelDisciplinasCadastrados = new javax.swing.JLabel();
        jbuttonVoltarMenu = new javax.swing.JButton();
        jbuttonExcluirCurso = new javax.swing.JButton();
        jbuttonCancelarCurso = new javax.swing.JButton();
        jbuttonSalvarCurso = new javax.swing.JButton();
        jbuttonNovoDisciplina = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        labelTituloCadCurso = new javax.swing.JLabel();
        jcomboboxCursoCadDisciplina = new javax.swing.JComboBox();
        labelNomeCurso = new javax.swing.JLabel();
        jtextfieldDisciplina = new javax.swing.JTextField();
        labelNomeDisciplina = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1290, 615));

        jtableListaDisciplinas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtableListaDisciplinas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtableListaDisciplinas);

        jtabelDisciplinasCadastrados.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jtabelDisciplinasCadastrados.setText("Disciplinas Cadastradas no Sistema");

        jbuttonVoltarMenu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonVoltarMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookleprojeto/imagensBookle/back.png"))); // NOI18N
        jbuttonVoltarMenu.setText("Voltar ao Menu");
        jbuttonVoltarMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonVoltarMenuActionPerformed(evt);
            }
        });

        jbuttonExcluirCurso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonExcluirCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookleprojeto/imagensBookle/block.png"))); // NOI18N
        jbuttonExcluirCurso.setText("Excluir Disciplina");
        jbuttonExcluirCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonExcluirCursoActionPerformed(evt);
            }
        });

        jbuttonCancelarCurso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonCancelarCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookleprojeto/imagensBookle/delete.png"))); // NOI18N
        jbuttonCancelarCurso.setText("Cancelar");
        jbuttonCancelarCurso.setEnabled(false);
        jbuttonCancelarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonCancelarCursoActionPerformed(evt);
            }
        });

        jbuttonSalvarCurso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonSalvarCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookleprojeto/imagensBookle/accept.png"))); // NOI18N
        jbuttonSalvarCurso.setText("Salvar");
        jbuttonSalvarCurso.setEnabled(false);
        jbuttonSalvarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonSalvarCursoActionPerformed(evt);
            }
        });

        jbuttonNovoDisciplina.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonNovoDisciplina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookleprojeto/imagensBookle/add_page.png"))); // NOI18N
        jbuttonNovoDisciplina.setText("Nova Disciplina");
        jbuttonNovoDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonNovoDisciplinaActionPerformed(evt);
            }
        });

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelTituloCadCurso.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelTituloCadCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookleprojeto/imagensBookle/booklelogo.png"))); // NOI18N
        labelTituloCadCurso.setText("Bookle Cadastrar Disciplina");

        jcomboboxCursoCadDisciplina.setModel(new javax.swing.DefaultComboBoxModel());
        jcomboboxCursoCadDisciplina.setEnabled(false);

        labelNomeCurso.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelNomeCurso.setText("Escolha o Curso:");
        labelNomeCurso.setEnabled(false);

        jtextfieldDisciplina.setBackground(new java.awt.Color(255, 255, 204));
        jtextfieldDisciplina.setEnabled(false);

        labelNomeDisciplina.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelNomeDisciplina.setText("Nome da Disciplina:");
        labelNomeDisciplina.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(labelTituloCadCurso)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtextfieldDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNomeCurso)
                            .addComponent(jcomboboxCursoCadDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNomeDisciplina))
                        .addGap(40, 40, 40))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(labelTituloCadCurso)
                .addGap(30, 30, 30)
                .addComponent(labelNomeCurso)
                .addGap(2, 2, 2)
                .addComponent(jcomboboxCursoCadDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelNomeDisciplina)
                .addGap(7, 7, 7)
                .addComponent(jtextfieldDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(447, 447, 447))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jtabelDisciplinasCadastrados)
                        .addGap(470, 470, 470))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 247, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(424, 424, 424))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jbuttonNovoDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jbuttonSalvarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbuttonCancelarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbuttonExcluirCurso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbuttonVoltarMenu)
                        .addGap(248, 248, 248))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jtabelDisciplinasCadastrados)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbuttonNovoDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbuttonSalvarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbuttonCancelarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbuttonExcluirCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbuttonVoltarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        setBounds(0, 0, 1290, 615);
    }// </editor-fold>//GEN-END:initComponents

    private void jbuttonVoltarMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonVoltarMenuActionPerformed

        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jbuttonVoltarMenuActionPerformed

    private void jbuttonExcluirCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonExcluirCursoActionPerformed
        int selecionado = jtableListaDisciplinas.getSelectedRow();
        if (selecionado != -1) {
            try {

                ConexaoMysql conectmysql = new ConexaoMysql();
                conectmysql.abrirConexao();
                conectmysql.statement = conectmysql.conexao.createStatement();
                String nomedisciplina = jtableListaDisciplinas.getValueAt(jtableListaDisciplinas.getSelectedRow(), 1).toString();

                int opcao = JOptionPane.showConfirmDialog(null, "Deseja Excluir a Disciplina: " + nomedisciplina, "Exclusão de Disciplina", JOptionPane.YES_NO_OPTION);

                if (opcao == JOptionPane.YES_OPTION) {
                    conectmysql.statement.executeUpdate("DELETE FROM tbdisciplina  WHERE nomedisciplina ='" + nomedisciplina+ "'");
                    JOptionPane.showMessageDialog(null, "Curso " + nomedisciplina + " Excluído com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Operação Cancelada");
                }
                listarDisciplina();

            } catch (Exception erro) {
                if (erro instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException) {
                    JOptionPane.showMessageDialog(null, "Existem Livros vinculados a esta disciplina, exclua os livros do curso", "Erro SQL Foreign", JOptionPane.ERROR_MESSAGE);
                } else {
                    System.out.println("Erro Excluir Disciplina: " + erro);
                }
                
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma Disciplina para excluir!");
        }
    }//GEN-LAST:event_jbuttonExcluirCursoActionPerformed

    private void jbuttonCancelarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonCancelarCursoActionPerformed
        jbuttonNovoDisciplina.setEnabled(true);
        labelNomeCurso.setEnabled(false);
        labelNomeDisciplina.setEnabled(false);
        jcomboboxCursoCadDisciplina.setEnabled(false);
        jcomboboxCursoCadDisciplina.setSelectedIndex(-1);
        jtextfieldDisciplina.setEnabled(false);
        jbuttonExcluirCurso.setEnabled(true);
        jbuttonSalvarCurso.setEnabled(false);
        jbuttonCancelarCurso.setEnabled(false);
        jbuttonVoltarMenu.setEnabled(true);
        jtextfieldDisciplina.setText("");
    }//GEN-LAST:event_jbuttonCancelarCursoActionPerformed

    private void jbuttonSalvarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonSalvarCursoActionPerformed
        String cursoselecionado = ((String) jcomboboxCursoCadDisciplina.getSelectedItem());
        if (cursoselecionado == null) {
             JOptionPane.showMessageDialog(null, "Selecione um curso!", "Campo Curso Vazio", JOptionPane.ERROR_MESSAGE);
        } else if (jtextfieldDisciplina.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Insira o nome da Disciplina!", "Campo Disciplina Vazio", JOptionPane.ERROR_MESSAGE);
        } else {

            cadastrarDisciplina();
            jtextfieldDisciplina.setText(null);
            jbuttonNovoDisciplina.setEnabled(true);
            labelNomeCurso.setEnabled(false);
            jtextfieldDisciplina.setEnabled(false);
            jbuttonExcluirCurso.setEnabled(true);
            jbuttonCancelarCurso.setEnabled(false);
            jbuttonSalvarCurso.setEnabled(false);
            jbuttonVoltarMenu.setEnabled(true);
            jcomboboxCursoCadDisciplina.setEnabled(false);

            listarDisciplina();
        }
    }//GEN-LAST:event_jbuttonSalvarCursoActionPerformed

    private void jbuttonNovoDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonNovoDisciplinaActionPerformed
        jbuttonNovoDisciplina.setEnabled(false);
        labelNomeCurso.setEnabled(true);
        labelNomeDisciplina.setEnabled(true);
        jbuttonExcluirCurso.setEnabled(false);
        jbuttonCancelarCurso.setEnabled(true);
        jbuttonSalvarCurso.setEnabled(true);
        jbuttonVoltarMenu.setEnabled(false);
        jtextfieldDisciplina.setEnabled(true);
        jcomboboxCursoCadDisciplina.setEnabled(true);
    }//GEN-LAST:event_jbuttonNovoDisciplinaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbuttonCancelarCurso;
    private javax.swing.JButton jbuttonExcluirCurso;
    private javax.swing.JButton jbuttonNovoDisciplina;
    private javax.swing.JButton jbuttonSalvarCurso;
    private javax.swing.JButton jbuttonVoltarMenu;
    private javax.swing.JComboBox jcomboboxCursoCadDisciplina;
    private javax.swing.JLabel jtabelDisciplinasCadastrados;
    private javax.swing.JTable jtableListaDisciplinas;
    private javax.swing.JTextField jtextfieldDisciplina;
    private javax.swing.JLabel labelNomeCurso;
    private javax.swing.JLabel labelNomeDisciplina;
    private javax.swing.JLabel labelTituloCadCurso;
    // End of variables declaration//GEN-END:variables
}
