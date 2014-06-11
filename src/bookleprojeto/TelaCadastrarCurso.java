/**
 * Bookle Sistema Acadêmico<br>
 * Autor: Kélvin Santiago<br>
 * Data: 11/06/2014.
 */
package bookleprojeto;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 * Classe responsável em disponibilizar algumas modificações dos cursos com:<br>
 * - Cadastrar novo curso<br>
 * - Excluir curso<br>
 * - Visualizar cursos cadastrados.
 * @author Kélvin Santiago
 */
public class TelaCadastrarCurso extends javax.swing.JInternalFrame {

    ConexaoMysql conectmysql = new ConexaoMysql();

    /**
     * Construtor inicia componentes básicos da interface gráfica, e inicia o
     * listarCursos que consiste em preencher um componente jtable com os dados
     * da consulta SQL.
     */
    public TelaCadastrarCurso() {
        initComponents();
        listarCursos();

    }

    /**
     * Método faz uma consulta na tabela tbcursos do banco de dados bookle e
     * preenche uma jtable com os cursos.
     */
    public void listarCursos() {
        ArrayList dadoscurso = new ArrayList();
        dadoscurso.clear();

        String[] colunas = new String[]{"Código do Curso", "Nome do Curso"};
        try {

            conectmysql.abrirConexao();
            conectmysql.createStatement();
            conectmysql.executaSQL("SELECT * FROM tbcurso");

            while (conectmysql.resultset.next()) {

                dadoscurso.add(new Object[]{conectmysql.resultset.getString("codcurso"), conectmysql.resultset.getString("nomecurso")});
            }

            conectmysql.fecharConexao();

        } catch (Exception erro) {
            System.out.println("Erro preencher Tabela Cursos: " + erro);

        }

        ModeloTabela modeltable = new ModeloTabela(dadoscurso, colunas);

        jtableListaCursos.setModel(modeltable);
        jtableListaCursos.getColumnModel().getColumn(0).setPreferredWidth(120);
        jtableListaCursos.getColumnModel().getColumn(0).setResizable(false);
        jtableListaCursos.getColumnModel().getColumn(1).setPreferredWidth(230);
        jtableListaCursos.getColumnModel().getColumn(1).setResizable(false);

        jtableListaCursos.getTableHeader().setReorderingAllowed(false);
        jtableListaCursos.setAutoResizeMode(jtableListaCursos.AUTO_RESIZE_OFF);
        jtableListaCursos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtableListaCursos.setRowHeight(50);
    }

    /**
     * Método cadastra curso de acordo com o jtextfield informado, se não estiver cadastrado.
     */
    public void cadastrarCurso() {

        try {
            Boolean cadastrado = false;
            conectmysql.abrirConexao();
            conectmysql.createStatement();
            conectmysql.executaSQL("select * from tbcurso");

            while (conectmysql.resultset.next()) {
                if (conectmysql.resultset.getString("nomecurso").equals(jtextfieldCurso.getText())) {
                    JOptionPane.showMessageDialog(null, "O Curso ja está cadastrado", "Curso Cadastrado!", JOptionPane.INFORMATION_MESSAGE);
                    cadastrado = true;
                }
            }

            if (cadastrado == false) {
                conectmysql.statement.executeUpdate("INSERT INTO tbcurso (nomecurso) VALUES (" + "'" + jtextfieldCurso.getText() + "')");
                JOptionPane.showMessageDialog(null, "Curso Cadastrado com Sucesso!", "Usuario Cadastrado!", JOptionPane.INFORMATION_MESSAGE);
                jtextfieldCurso.setText(null);
            }

            conectmysql.fecharConexao();

        } catch (Exception erro) {
            System.out.println("Erro Cadastrar Curso: " + erro);

        }
    }
    
    /**
     * Método exclui curso de acordo com o getSelectedRow, ou seja de acordo com a seleção
     * feita no jtable pelo usuário.
     */
    public void excluirCurso(){
       int selecionado = jtableListaCursos.getSelectedRow();
        if (selecionado != -1) {
            try {
                conectmysql.abrirConexao();
                conectmysql.createStatement();
                String nomecurso = jtableListaCursos.getValueAt(jtableListaCursos.getSelectedRow(), 1).toString();
                int opcao = JOptionPane.showConfirmDialog(null, "Deseja Excluir o Curso: " + nomecurso, "Exclusão de Curso", JOptionPane.YES_NO_OPTION);

                if (opcao == JOptionPane.YES_OPTION) {
                    conectmysql.statement.executeUpdate("DELETE FROM tbcurso  WHERE nomecurso ='" + (jtableListaCursos.getValueAt(jtableListaCursos.getSelectedRow(), 1)) + "'");
                    
                    JOptionPane.showMessageDialog(null, "Curso " + nomecurso + " Excluído com sucesso!","Curso Excluído",JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Exclusão de curso cancelada!");
                }
                listarCursos();
                conectmysql.fecharConexao();

            } catch (Exception erro) {

                if (erro instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException) {
                    JOptionPane.showMessageDialog(null, "Existem Disciplinas vinculadas a este curso, exclua as disciplinas do curso", "Erro SQL Foreign", JOptionPane.ERROR_MESSAGE);
                } else {
                    System.out.println("Erro Excluir Curso: " + erro);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Um curso deve ser seleciona para excluir!");
        }
    
    }
   
    // Método gerado automaticamente da interface gráfica GUI
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtabelCursoCadastrados = new javax.swing.JLabel();
        jbuttonVoltarMenu = new javax.swing.JButton();
        jbuttonCancelarCurso = new javax.swing.JButton();
        jbuttonExcluirCurso = new javax.swing.JButton();
        jbuttonNovoCurso = new javax.swing.JButton();
        jbuttonSalvarCurso = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtableListaCursos = new javax.swing.JTable();
        jpanelCadastroCurso = new javax.swing.JPanel();
        labelCursoCad = new javax.swing.JLabel();
        jtextfieldCurso = new javax.swing.JTextField();
        labelTituloCadCurso = new javax.swing.JLabel();

        setClosable(true);
        setPreferredSize(new java.awt.Dimension(1290, 615));

        jtabelCursoCadastrados.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jtabelCursoCadastrados.setText("Cursos Cadastrados no Sistema");

        jbuttonVoltarMenu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonVoltarMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookleprojeto/imagensBookle/back.png"))); // NOI18N
        jbuttonVoltarMenu.setText("Voltar ao Menu");
        jbuttonVoltarMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonVoltarMenuActionPerformed(evt);
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

        jbuttonExcluirCurso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonExcluirCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookleprojeto/imagensBookle/block.png"))); // NOI18N
        jbuttonExcluirCurso.setText("Excluir Curso");
        jbuttonExcluirCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonExcluirCursoActionPerformed(evt);
            }
        });

        jbuttonNovoCurso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonNovoCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookleprojeto/imagensBookle/add_page.png"))); // NOI18N
        jbuttonNovoCurso.setText("Novo Curso");
        jbuttonNovoCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonNovoCursoActionPerformed(evt);
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

        jtableListaCursos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtableListaCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtableListaCursos);

        jpanelCadastroCurso.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelCursoCad.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        labelCursoCad.setText("Nome do Curso");
        labelCursoCad.setEnabled(false);

        jtextfieldCurso.setBackground(new java.awt.Color(255, 255, 204));
        jtextfieldCurso.setEnabled(false);

        labelTituloCadCurso.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelTituloCadCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookleprojeto/imagensBookle/booklelogo.png"))); // NOI18N
        labelTituloCadCurso.setText("BOOKLE CADASTRAR CURSO");

        javax.swing.GroupLayout jpanelCadastroCursoLayout = new javax.swing.GroupLayout(jpanelCadastroCurso);
        jpanelCadastroCurso.setLayout(jpanelCadastroCursoLayout);
        jpanelCadastroCursoLayout.setHorizontalGroup(
            jpanelCadastroCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelCadastroCursoLayout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addGroup(jpanelCadastroCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelCadastroCursoLayout.createSequentialGroup()
                        .addComponent(labelTituloCadCurso)
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelCadastroCursoLayout.createSequentialGroup()
                        .addComponent(labelCursoCad)
                        .addGap(185, 185, 185))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelCadastroCursoLayout.createSequentialGroup()
                        .addComponent(jtextfieldCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114))))
        );
        jpanelCadastroCursoLayout.setVerticalGroup(
            jpanelCadastroCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelCadastroCursoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTituloCadCurso)
                .addGap(24, 24, 24)
                .addComponent(labelCursoCad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtextfieldCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 278, Short.MAX_VALUE)
                .addComponent(jbuttonNovoCurso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbuttonSalvarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jbuttonCancelarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jbuttonExcluirCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbuttonVoltarMenu)
                .addGap(227, 227, 227))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(453, 453, 453)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(369, 369, 369)
                        .addComponent(jpanelCadastroCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(489, 489, 489)
                        .addComponent(jtabelCursoCadastrados)))
                .addContainerGap(371, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jpanelCadastroCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jtabelCursoCadastrados)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbuttonNovoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbuttonSalvarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbuttonCancelarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbuttonExcluirCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbuttonVoltarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        setBounds(0, 0, 1290, 615);
    }// </editor-fold>//GEN-END:initComponents

    private void jbuttonVoltarMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonVoltarMenuActionPerformed
        dispose();     
    }//GEN-LAST:event_jbuttonVoltarMenuActionPerformed

    private void jbuttonCancelarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonCancelarCursoActionPerformed
        jbuttonNovoCurso.setEnabled(true);
        labelCursoCad.setEnabled(false);
        jtextfieldCurso.setEnabled(false);
        jbuttonExcluirCurso.setEnabled(true);
        jbuttonSalvarCurso.setEnabled(false);
        jbuttonCancelarCurso.setEnabled(false);
        jbuttonVoltarMenu.setEnabled(true);
        jtextfieldCurso.setText("");

    }//GEN-LAST:event_jbuttonCancelarCursoActionPerformed

    private void jbuttonExcluirCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonExcluirCursoActionPerformed
     excluirCurso();
    }//GEN-LAST:event_jbuttonExcluirCursoActionPerformed

    private void jbuttonNovoCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonNovoCursoActionPerformed
        jbuttonNovoCurso.setEnabled(false);
        labelCursoCad.setEnabled(true);
        jtextfieldCurso.setEnabled(true);
        jbuttonExcluirCurso.setEnabled(false);
        jbuttonCancelarCurso.setEnabled(true);
        jbuttonSalvarCurso.setEnabled(true);
        jbuttonVoltarMenu.setEnabled(false);
    }//GEN-LAST:event_jbuttonNovoCursoActionPerformed

    private void jbuttonSalvarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonSalvarCursoActionPerformed
        if (!jtextfieldCurso.getText().equals("")) {
            cadastrarCurso();
            jtextfieldCurso.setText(null);
            jbuttonNovoCurso.setEnabled(true);
            labelCursoCad.setEnabled(false);
            jtextfieldCurso.setEnabled(false);
            jbuttonExcluirCurso.setEnabled(true);
            jbuttonCancelarCurso.setEnabled(true);
            jbuttonSalvarCurso.setEnabled(false);
            jbuttonVoltarMenu.setEnabled(true);
            listarCursos();
        } else {
            JOptionPane.showMessageDialog(null, "Insira o nome do Curso!", "Campo Curso Vazio", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbuttonSalvarCursoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbuttonCancelarCurso;
    private javax.swing.JButton jbuttonExcluirCurso;
    private javax.swing.JButton jbuttonNovoCurso;
    private javax.swing.JButton jbuttonSalvarCurso;
    private javax.swing.JButton jbuttonVoltarMenu;
    private javax.swing.JPanel jpanelCadastroCurso;
    private javax.swing.JLabel jtabelCursoCadastrados;
    private javax.swing.JTable jtableListaCursos;
    private javax.swing.JTextField jtextfieldCurso;
    private javax.swing.JLabel labelCursoCad;
    private javax.swing.JLabel labelTituloCadCurso;
    // End of variables declaration//GEN-END:variables
}
