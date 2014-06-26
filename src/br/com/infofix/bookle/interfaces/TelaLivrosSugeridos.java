/**
 * Bookle Sistema Acadêmico<br>
 * Autor: Kélvin Santiago<br>
 * Data: 11/06/2014.
 */
package br.com.infofix.bookle.interfaces;

import br.com.infofix.bookle.conexao.ConexaoMysql;
import br.com.infofix.bookle.modelos.ModeloTabela;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 * Classe responsável em mostrar o resultado da consulta de livros da
 * telaPesquisa, a tela é composta basicamente por três jtables com os
 * resultados dos livros sugeridos na ementa conforme a seleção feita na
 * telaPesquisar<br>
 *
 * @author Kélvin Santiago
 */
public class TelaLivrosSugeridos extends javax.swing.JInternalFrame {

    ConexaoMysql conectmysql = new ConexaoMysql();
    TelaPesquisar telapesquisar = new TelaPesquisar();

    /**
     * Construtor inicia componentes básicos da interface gráfica, e inicia o
     * iniciaTables que consiste em preencher um componente jtable com os dados
     * da consulta SQL conforme a seleção na telaPesquisar.
     */
    public TelaLivrosSugeridos() {
        initComponents();
        iniciaTables();

    }

    /**
     * Método chama o método selectTable passando os argumentos.
     */
    public void iniciaTables() {

        selectTable("SELECT * FROM tblivros INNER JOIN tbdisciplina ON tblivros.coddisciplina = tbdisciplina.coddisciplina AND tbdisciplina.nomedisciplina LIKE" + "'" + telapesquisar.resNomedisciplina + "'", "nomelivro", "contexemplares", "localbiblioteca", "statuslivro", jtableLivros);

        if (telapesquisar.resNomedisciplina02 != null) {
            selectTable("SELECT * FROM tblivros INNER JOIN tbdisciplina ON tblivros.coddisciplina = tbdisciplina.coddisciplina AND tbdisciplina.nomedisciplina LIKE" + "'" + telapesquisar.resNomedisciplina02 + "'", "nomelivro", "contexemplares", "localbiblioteca", "statuslivro", jtableLivros02);
        }

        if (telapesquisar.resNomedisciplina03 != null) {
            selectTable("SELECT * FROM tblivros INNER JOIN tbdisciplina ON tblivros.coddisciplina = tbdisciplina.coddisciplina AND tbdisciplina.nomedisciplina LIKE" + "'" + telapesquisar.resNomedisciplina03 + "'", "nomelivro", "contexemplares", "localbiblioteca", "statuslivro", jtableLivros03);
        }
    }

    /**
     * Método consistem em preencher uma jtable com os dados da consulta feita
     * no banco de dados.
     *
     * @param selectQuery String - Query Sql,ex: SELEC * FROM nometabela
     * @param nomecoluna String - Nome Coluna 1 da tabela
     * @param nomecoluna2 String - Nome Coluna 2 da tabela
     * @param nomecoluna3 String - Nome Coluna 3 da tabela
     * @param nomecoluna4 String - Nome Coluna 4 da tabela
     * @param jtable Jtable - Componente jtable a ser preenchido
     */
    public void selectTable(String selectQuery, String nomecoluna, String nomecoluna2, String nomecoluna3, String nomecoluna4, JTable jtable) {
        ArrayList dados = new ArrayList();
        dados.clear();
        String[] colunas = new String[]{"Nome Livro", "Exemplares", "Biblioteca", "Status"};
        try {
            conectmysql.abrirConexao();
            conectmysql.createStatement();
            conectmysql.executaSQL(selectQuery);

            while (conectmysql.resultset.next()) {
                dados.add(new Object[]{conectmysql.resultset.getString(nomecoluna), conectmysql.resultset.getString(nomecoluna2), conectmysql.resultset.getString(nomecoluna3), conectmysql.resultset.getString(nomecoluna4)});
            }

            conectmysql.fecharConexao();

        } catch (Exception erro) {
            System.out.println("Erro preencher Tabela dos Livros Sugeridos: " + erro);

        }
        ModeloTabela modeltable = new ModeloTabela(dados, colunas);

        if (jtable == jtableLivros) {
            jtableLivros.setModel(modeltable);
            jtableLivros.getColumnModel().getColumn(0).setPreferredWidth(350);
            jtableLivros.getColumnModel().getColumn(0).setResizable(false);
            jtableLivros.getColumnModel().getColumn(1).setPreferredWidth(80);
            jtableLivros.getColumnModel().getColumn(1).setResizable(false);
            jtableLivros.getColumnModel().getColumn(2).setPreferredWidth(100);
            jtableLivros.getColumnModel().getColumn(2).setResizable(false);
            jtableLivros.getColumnModel().getColumn(3).setPreferredWidth(90);
            jtableLivros.getColumnModel().getColumn(3).setResizable(false);
            jtableLivros.getTableHeader().setReorderingAllowed(false);
            jtableLivros.setAutoResizeMode(jtable.AUTO_RESIZE_OFF);
            jtableLivros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jtableLivros.setRowHeight(50);

        } else if (jtable == jtableLivros02) {
            jtableLivros02.setModel(modeltable);
            jtableLivros02.getColumnModel().getColumn(0).setPreferredWidth(350);
            jtableLivros02.getColumnModel().getColumn(0).setResizable(false);
            jtableLivros02.getColumnModel().getColumn(1).setPreferredWidth(80);
            jtableLivros02.getColumnModel().getColumn(1).setResizable(false);
            jtableLivros02.getColumnModel().getColumn(2).setPreferredWidth(100);
            jtableLivros02.getColumnModel().getColumn(2).setResizable(false);
            jtableLivros02.getColumnModel().getColumn(3).setPreferredWidth(90);
            jtableLivros02.getColumnModel().getColumn(3).setResizable(false);
            jtableLivros02.getTableHeader().setReorderingAllowed(false);
            jtableLivros02.setAutoResizeMode(jtable.AUTO_RESIZE_OFF);
            jtableLivros02.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jtableLivros02.setRowHeight(50);
        } else {
            jtableLivros03.setModel(modeltable);
            jtableLivros03.getColumnModel().getColumn(0).setPreferredWidth(350);
            jtableLivros03.getColumnModel().getColumn(0).setResizable(false);
            jtableLivros03.getColumnModel().getColumn(1).setPreferredWidth(80);
            jtableLivros03.getColumnModel().getColumn(1).setResizable(false);
            jtableLivros03.getColumnModel().getColumn(2).setPreferredWidth(100);
            jtableLivros03.getColumnModel().getColumn(2).setResizable(false);
            jtableLivros03.getColumnModel().getColumn(3).setPreferredWidth(90);
            jtableLivros03.getColumnModel().getColumn(3).setResizable(false);
            jtableLivros03.getTableHeader().setReorderingAllowed(false);
            jtableLivros03.setAutoResizeMode(jtable.AUTO_RESIZE_OFF);
            jtableLivros03.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jtableLivros03.setRowHeight(50);
        }
    }

    // Método gerado automaticamente GUI  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelNomeDisciplina = new javax.swing.JLabel();
        jbuttonVoltar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtableLivros = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtableLivros02 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtableLivros03 = new javax.swing.JTable();
        labelNomeDisciplina02 = new javax.swing.JLabel();
        labelNomeDisciplina03 = new javax.swing.JLabel();

        setClosable(true);
        setResizable(true);
        setTitle("Livros Sugeridos");
        setPreferredSize(new java.awt.Dimension(1289, 615));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Bookle Sistema Acadêmico");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(391, 12, 544, 43);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Curso selecionado:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(461, 61, 166, 22);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setText(telapesquisar.resNomecurso);
        getContentPane().add(jLabel4);
        jLabel4.setBounds(639, 61, 362, 22);

        labelNomeDisciplina.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelNomeDisciplina.setText(telapesquisar.resNomedisciplina);
        getContentPane().add(labelNomeDisciplina);
        labelNomeDisciplina.setBounds(470, 110, 420, 22);

        jbuttonVoltar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jbuttonVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infofix/bookle/imagens/back.png"))); // NOI18N
        jbuttonVoltar.setText("Voltar");
        jbuttonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonVoltarActionPerformed(evt);
            }
        });
        getContentPane().add(jbuttonVoltar);
        jbuttonVoltar.setBounds(326, 490, 130, 50);

        jtableLivros.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtableLivros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jtableLivros);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(326, 140, 638, 73);

        jtableLivros02.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtableLivros02.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtableLivros02);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(326, 259, 638, 73);

        jtableLivros03.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtableLivros03.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jtableLivros03);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(326, 387, 638, 73);

        labelNomeDisciplina02.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        labelNomeDisciplina02.setText(telapesquisar.resNomedisciplina02);
        getContentPane().add(labelNomeDisciplina02);
        labelNomeDisciplina02.setBounds(470, 230, 420, 22);

        labelNomeDisciplina03.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        labelNomeDisciplina03.setText(telapesquisar.resNomedisciplina03);
        getContentPane().add(labelNomeDisciplina03);
        labelNomeDisciplina03.setBounds(470, 350, 420, 22);

        setBounds(0, 0, 1285, 629);
    }// </editor-fold>//GEN-END:initComponents

    private void jbuttonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonVoltarActionPerformed
        dispose();
    }//GEN-LAST:event_jbuttonVoltarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jbuttonVoltar;
    private javax.swing.JTable jtableLivros;
    private javax.swing.JTable jtableLivros02;
    private javax.swing.JTable jtableLivros03;
    private javax.swing.JLabel labelNomeDisciplina;
    private javax.swing.JLabel labelNomeDisciplina02;
    private javax.swing.JLabel labelNomeDisciplina03;
    // End of variables declaration//GEN-END:variables
}
