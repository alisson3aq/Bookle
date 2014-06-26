/**
 * Bookle Sistema Acadêmico<br>
 * Autor: Kélvin Santiago<br>
 * Data: 11/06/2014.
 */
package br.com.infofix.bookle.interfaces;

import br.com.infofix.bookle.conexao.ConexaoMysql;
import br.com.infofix.bookle.modelos.ModeloTabela;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 * Classe responsável em disponibilizar algumas modificações dos Livros com:<br>
 * - Cadastrar novo Livro<br>
 * - Excluir Livro<br>
 * - Visualizar livros cadastradas.
 *
 * @author Kélvin Santiago
 */
public class TelaCadastrarLivro extends javax.swing.JInternalFrame {

    TelaPesquisar telapesquisar = new TelaPesquisar();
    ConexaoMysql conectmysql = new ConexaoMysql();

    /**
     * Construtor inicia componentes básicos da interface gráfica, e inicia o
     * listarlivros que consiste em preencher um componente jtable com os dados
     * da consulta SQL dos livros cadastrados, o construtor também inicia o
     * preencherCombobox da Classe telaPesquisar, que preenche um combobox com o
     * nome das disciplinas.
     */
    public TelaCadastrarLivro() {
        initComponents();
        listaLivros();
        telapesquisar.preencheComboBox("select * from tbdisciplina", "nomedisciplina", jcomboboxLivros);
        jcomboboxLivros.setSelectedIndex(-1);
    }

    /**
     * Método faz uma consulta na tabela tblivros do banco de dados bookle e
     * preenche uma jtable com os livros cadastrados.
     */
    public void listaLivros() {
        ArrayList dadoslivros = new ArrayList();
        dadoslivros.clear();
        String[] colunas = new String[]{"Código", "Nome Livro", "Exemplares", "Biblioteca", "Status"};
        try {
            conectmysql.abrirConexao();
            conectmysql.executaSQL("SELECT * FROM tblivros");

            while (conectmysql.resultset.next()) {
                dadoslivros.add(new Object[]{conectmysql.resultset.getString("codlivro"), conectmysql.resultset.getString("nomelivro"), conectmysql.resultset.getString("contexemplares"), conectmysql.resultset.getString("localbiblioteca"), conectmysql.resultset.getString("statuslivro")});
            }

            conectmysql.fecharConexao();

        } catch (Exception erro) {
            System.out.println("Erro preencher Tabela Livros: " + erro);

        }
        ModeloTabela modeltable = new ModeloTabela(dadoslivros, colunas);

        jtableListaLivros.setModel(modeltable);
        jtableListaLivros.getColumnModel().getColumn(0).setPreferredWidth(60);
        jtableListaLivros.getColumnModel().getColumn(0).setResizable(false);
        jtableListaLivros.getColumnModel().getColumn(1).setPreferredWidth(350);
        jtableListaLivros.getColumnModel().getColumn(1).setResizable(false);
        jtableListaLivros.getColumnModel().getColumn(2).setPreferredWidth(80);
        jtableListaLivros.getColumnModel().getColumn(2).setResizable(false);
        jtableListaLivros.getColumnModel().getColumn(3).setPreferredWidth(70);
        jtableListaLivros.getColumnModel().getColumn(3).setResizable(false);
        jtableListaLivros.getColumnModel().getColumn(4).setPreferredWidth(70);
        jtableListaLivros.getColumnModel().getColumn(4).setResizable(false);

        jtableListaLivros.getTableHeader().setReorderingAllowed(false);
        jtableListaLivros.setAutoResizeMode(jtableListaLivros.AUTO_RESIZE_OFF);
        jtableListaLivros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtableListaLivros.setRowHeight(50);
    }

    /**
     * Método cadastra livros de acordo com os campos:<br>
     * - Nome da Disciplina (ComboBox)<br> 
     * - Nome do Livro<br> 
     * - Status<br> 
     * - Quantidade de Exemplares<br>
     * - Local do Livro.
     */
    public void cadastrarLivro() {
        try {
            Boolean cadastrado = false;
            int codigodadisciplina = 0;

            ConexaoMysql conectmysql = new ConexaoMysql();
            conectmysql.abrirConexao();
            conectmysql.executaSQL("SELECT * FROM tbdisciplina");

            while (conectmysql.resultset.next()) {
                if (conectmysql.resultset.getString("nomedisciplina").equals(jcomboboxLivros.getSelectedItem())) {
                    codigodadisciplina = conectmysql.resultset.getInt("coddisciplina");
                }
            }
            conectmysql.executaSQL("SELECT * FROM tblivros");

            while (conectmysql.resultset.next()) {
                if (conectmysql.resultset.getString("nomelivro").equals(jtextfieldNomeLivro.getText())) {
                    JOptionPane.showMessageDialog(null, "O livro ja está Cadastrado!", "Livro Cadastrado!", JOptionPane.INFORMATION_MESSAGE);
                    cadastrado = true;
                }
            }

            if (cadastrado == false) {
                conectmysql.statement.executeUpdate("INSERT INTO tblivros(coddisciplina,nomelivro,statuslivro,contexemplares,localbiblioteca) VALUES ("
                        + "'" + codigodadisciplina + "',"
                        + "'" + jtextfieldNomeLivro.getText() + "',"
                        + "'" + jtextfieldStatusLivro.getText() + "',"
                        + "'" + jtextfieldContExemplares.getText() + "',"
                        + "'" + jcomboboxLocalBiblioteca.getSelectedItem() + "'"
                        + ")");

                JOptionPane.showMessageDialog(null, "Livro Cadastrado com Sucesso!", "Livro Cadastrado!", JOptionPane.INFORMATION_MESSAGE);
                jtextfieldNomeLivro.setText(null);
                jcomboboxLivros.setSelectedIndex(-1);
                jcomboboxLocalBiblioteca.setSelectedIndex(-1);
            }

            conectmysql.fecharConexao();

        } catch (Exception erro) {
            System.out.println("Erro Cadastrar Livro: " + erro);
        }

    }
    
    /**
     * Método exclui livro de acordo com o getSelectedRow, ou seja de acordo com a seleção
     * feita no jtable pelo usuário.
     */
    public void excluirLivro() {
        int selecionado = jtableListaLivros.getSelectedRow();
        if (selecionado != -1) {
            try {
                conectmysql.abrirConexao();

                String nomelivro = jtableListaLivros.getValueAt(jtableListaLivros.getSelectedRow(), 1).toString();
                int opcao = JOptionPane.showConfirmDialog(null, "Deseja Excluir o Livro: " + nomelivro, "Exclusão de Livro", JOptionPane.YES_NO_OPTION);

                if (opcao == JOptionPane.YES_OPTION) {
                    conectmysql.statement.executeUpdate("DELETE FROM tblivros  WHERE nomelivro ='" + nomelivro + "'");
                    JOptionPane.showMessageDialog(null, "Livro " + nomelivro + " Excluído com sucesso!", "Livro Excluído", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Exclusão de Livro Cancelada!");
                }
                listaLivros();
                conectmysql.fecharConexao();

            } catch (Exception erro) {
                System.err.println("Erro Excluir Livro: " + erro);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Você deve selecionar pelo menos um livro para excluir!");
        }

    }

    // Método gerado automaticamente GUI
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtableListaLivros = new javax.swing.JTable();
        jtabelLivrosCadastrados = new javax.swing.JLabel();
        labelTituloCadLivro = new javax.swing.JLabel();
        jbuttonNovoLivro = new javax.swing.JButton();
        jbuttonVoltarMenu = new javax.swing.JButton();
        jbuttonExcluirLivro = new javax.swing.JButton();
        jbuttonCancelarLivro = new javax.swing.JButton();
        jbuttonSalvarLivro = new javax.swing.JButton();
        jpanelCadastroLivro = new javax.swing.JPanel();
        labelLocalBiblioteca = new javax.swing.JLabel();
        jtextfieldStatusLivro = new javax.swing.JTextField();
        labelStatus = new javax.swing.JLabel();
        jcomboboxLivros = new javax.swing.JComboBox();
        jtextfieldNomeLivro = new javax.swing.JTextField();
        labelContExemplares = new javax.swing.JLabel();
        labelNomeLivro = new javax.swing.JLabel();
        jtextfieldContExemplares = new javax.swing.JTextField();
        labelNomeDisciplina = new javax.swing.JLabel();
        jcomboboxLocalBiblioteca = new javax.swing.JComboBox();

        setPreferredSize(new java.awt.Dimension(1290, 615));

        jtableListaLivros.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtableListaLivros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtableListaLivros);

        jtabelLivrosCadastrados.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jtabelLivrosCadastrados.setText("Livros Cadastradas no Sistema");

        labelTituloCadLivro.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelTituloCadLivro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infofix/bookle/imagens/booklelogo.png"))); // NOI18N
        labelTituloCadLivro.setText("BOOKLE CADASTRAR LIVRO");
        labelTituloCadLivro.setEnabled(false);

        jbuttonNovoLivro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonNovoLivro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infofix/bookle/imagens/add_page.png"))); // NOI18N
        jbuttonNovoLivro.setText("Novo Livro");
        jbuttonNovoLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonNovoLivroActionPerformed(evt);
            }
        });

        jbuttonVoltarMenu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonVoltarMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infofix/bookle/imagens/back.png"))); // NOI18N
        jbuttonVoltarMenu.setText("Voltar ao Menu");
        jbuttonVoltarMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonVoltarMenuActionPerformed(evt);
            }
        });

        jbuttonExcluirLivro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonExcluirLivro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infofix/bookle/imagens/block.png"))); // NOI18N
        jbuttonExcluirLivro.setText("Excluir Livro");
        jbuttonExcluirLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonExcluirLivroActionPerformed(evt);
            }
        });

        jbuttonCancelarLivro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonCancelarLivro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infofix/bookle/imagens/delete.png"))); // NOI18N
        jbuttonCancelarLivro.setText("Cancelar");
        jbuttonCancelarLivro.setEnabled(false);
        jbuttonCancelarLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonCancelarLivroActionPerformed(evt);
            }
        });

        jbuttonSalvarLivro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonSalvarLivro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infofix/bookle/imagens/save.png"))); // NOI18N
        jbuttonSalvarLivro.setText("Salvar");
        jbuttonSalvarLivro.setEnabled(false);
        jbuttonSalvarLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonSalvarLivroActionPerformed(evt);
            }
        });

        jpanelCadastroLivro.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelLocalBiblioteca.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelLocalBiblioteca.setText("Local Biblioteca");
        labelLocalBiblioteca.setEnabled(false);

        jtextfieldStatusLivro.setBackground(new java.awt.Color(255, 255, 204));
        jtextfieldStatusLivro.setEnabled(false);

        labelStatus.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelStatus.setText("Status");
        labelStatus.setEnabled(false);

        jcomboboxLivros.setModel(new javax.swing.DefaultComboBoxModel());
        jcomboboxLivros.setEnabled(false);

        jtextfieldNomeLivro.setBackground(new java.awt.Color(255, 255, 204));
        jtextfieldNomeLivro.setEnabled(false);

        labelContExemplares.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelContExemplares.setText("Qnt Exemplares (Somente números)");
        labelContExemplares.setEnabled(false);

        labelNomeLivro.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelNomeLivro.setText("Nome do Livro");
        labelNomeLivro.setEnabled(false);

        jtextfieldContExemplares.setBackground(new java.awt.Color(255, 255, 204));
        jtextfieldContExemplares.setEnabled(false);
        jtextfieldContExemplares.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtextfieldContExemplaresKeyTyped(evt);
            }
        });

        labelNomeDisciplina.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelNomeDisciplina.setText("Selecione a Disciplina");
        labelNomeDisciplina.setEnabled(false);

        jcomboboxLocalBiblioteca.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Campus I", "Campus II", "Campus III"}));
        jcomboboxLocalBiblioteca.setSelectedIndex(-1);
        jcomboboxLocalBiblioteca.setEnabled(false);

        javax.swing.GroupLayout jpanelCadastroLivroLayout = new javax.swing.GroupLayout(jpanelCadastroLivro);
        jpanelCadastroLivro.setLayout(jpanelCadastroLivroLayout);
        jpanelCadastroLivroLayout.setHorizontalGroup(
            jpanelCadastroLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelCadastroLivroLayout.createSequentialGroup()
                .addGroup(jpanelCadastroLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanelCadastroLivroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpanelCadastroLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtextfieldNomeLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNomeLivro)
                            .addComponent(labelContExemplares)
                            .addComponent(jtextfieldContExemplares, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpanelCadastroLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelStatus)
                            .addComponent(jtextfieldStatusLivro, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                            .addComponent(labelLocalBiblioteca)
                            .addComponent(jcomboboxLocalBiblioteca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jpanelCadastroLivroLayout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addGroup(jpanelCadastroLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcomboboxLivros, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpanelCadastroLivroLayout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(labelNomeDisciplina)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpanelCadastroLivroLayout.setVerticalGroup(
            jpanelCadastroLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelCadastroLivroLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelNomeDisciplina)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcomboboxLivros, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpanelCadastroLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpanelCadastroLivroLayout.createSequentialGroup()
                        .addComponent(labelLocalBiblioteca)
                        .addGap(53, 53, 53))
                    .addGroup(jpanelCadastroLivroLayout.createSequentialGroup()
                        .addGroup(jpanelCadastroLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelNomeLivro)
                            .addComponent(labelStatus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpanelCadastroLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtextfieldNomeLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtextfieldStatusLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(labelContExemplares)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpanelCadastroLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtextfieldContExemplares, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpanelCadastroLivroLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jcomboboxLocalBiblioteca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelTituloCadLivro)
                        .addGap(403, 403, 403))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(208, 208, 208)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(269, 269, 269))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jtabelLivrosCadastrados)
                                    .addGap(437, 437, 437))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jpanelCadastroLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(39, 39, 39))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jbuttonNovoLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jbuttonSalvarLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(12, 12, 12)
                                    .addComponent(jbuttonCancelarLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(13, 13, 13)
                                    .addComponent(jbuttonExcluirLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jbuttonVoltarMenu)))
                            .addGap(217, 217, 217)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTituloCadLivro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpanelCadastroLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtabelLivrosCadastrados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbuttonNovoLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbuttonSalvarLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbuttonCancelarLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbuttonExcluirLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbuttonVoltarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
        );

        setBounds(0, 0, 1290, 615);
    }// </editor-fold>//GEN-END:initComponents

    private void jbuttonNovoLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonNovoLivroActionPerformed
        jbuttonNovoLivro.setEnabled(false);
        labelNomeDisciplina.setEnabled(true);
        labelNomeLivro.setEnabled(true);
        labelContExemplares.setEnabled(true);
        labelLocalBiblioteca.setEnabled(true);
        labelStatus.setEnabled(true);
        jbuttonExcluirLivro.setEnabled(false);
        jbuttonCancelarLivro.setEnabled(true);
        jbuttonSalvarLivro.setEnabled(true);
        jbuttonVoltarMenu.setEnabled(false);
        jtextfieldNomeLivro.setEnabled(true);
        jtextfieldStatusLivro.setEnabled(true);
        jtextfieldContExemplares.setEnabled(true);
        jcomboboxLocalBiblioteca.setEnabled(true);
        jcomboboxLivros.setEnabled(true);
    }//GEN-LAST:event_jbuttonNovoLivroActionPerformed

    private void jbuttonVoltarMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonVoltarMenuActionPerformed
        dispose();
    }//GEN-LAST:event_jbuttonVoltarMenuActionPerformed

    private void jbuttonExcluirLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonExcluirLivroActionPerformed
        excluirLivro();
    }//GEN-LAST:event_jbuttonExcluirLivroActionPerformed

    private void jbuttonCancelarLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonCancelarLivroActionPerformed
        jtextfieldNomeLivro.setText(null);
        jbuttonNovoLivro.setEnabled(true);
        labelNomeLivro.setEnabled(false);
        labelContExemplares.setEnabled(false);
        labelLocalBiblioteca.setEnabled(false);
        labelStatus.setEnabled(false);
        labelNomeDisciplina.setEnabled(false);
        jtextfieldNomeLivro.setEnabled(false);
        jtextfieldContExemplares.setEnabled(false);
        jcomboboxLocalBiblioteca.setEnabled(false);
        jtextfieldStatusLivro.setEnabled(false);
        jbuttonExcluirLivro.setEnabled(true);
        jbuttonCancelarLivro.setEnabled(false);
        jbuttonSalvarLivro.setEnabled(false);
        jbuttonVoltarMenu.setEnabled(true);
        jcomboboxLivros.setEnabled(false);
        jtextfieldNomeLivro.setText("");
        jtextfieldContExemplares.setText("");
        jcomboboxLocalBiblioteca.setSelectedIndex(-1);
        jcomboboxLivros.setSelectedIndex(-1);
        jtextfieldNomeLivro.setText("");
        jtextfieldContExemplares.setText("");
        jtextfieldStatusLivro.setText("");

    }//GEN-LAST:event_jbuttonCancelarLivroActionPerformed

    private void jbuttonSalvarLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonSalvarLivroActionPerformed
        String cursoselecionado = ((String) jcomboboxLivros.getSelectedItem());
        String localselecionado = ((String) jcomboboxLocalBiblioteca.getSelectedItem());
        if (cursoselecionado == null) {
            JOptionPane.showMessageDialog(null, "Selecione uma Disciplina!", "Campo Disciplina Vazio", JOptionPane.ERROR_MESSAGE);
        } else if (jtextfieldNomeLivro.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Insira o nome do Livro!", "Campo Livro Vazio", JOptionPane.ERROR_MESSAGE);
        } else if (jtextfieldStatusLivro.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Insira o status do Livro!", "Campo Livro Vazio", JOptionPane.ERROR_MESSAGE);
        } else if (jtextfieldContExemplares.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Insira a quantidade de Exemplares!", "Campo Livro Vazio", JOptionPane.ERROR_MESSAGE);
        } else if (localselecionado == null) {
            JOptionPane.showMessageDialog(null, "Selecione o local do Livro!", "Campo Livro Vazio", JOptionPane.ERROR_MESSAGE);
        } else {
            cadastrarLivro();
            jtextfieldNomeLivro.setText(null);
            jbuttonNovoLivro.setEnabled(true);
            labelNomeLivro.setEnabled(false);
            labelContExemplares.setEnabled(false);
            labelLocalBiblioteca.setEnabled(false);
            labelStatus.setEnabled(false);
            labelNomeDisciplina.setEnabled(false);
            jtextfieldNomeLivro.setEnabled(false);
            jtextfieldContExemplares.setEnabled(false);
            jcomboboxLocalBiblioteca.setEnabled(false);
            jtextfieldStatusLivro.setEnabled(false);
            jbuttonExcluirLivro.setEnabled(true);
            jbuttonCancelarLivro.setEnabled(false);
            jbuttonSalvarLivro.setEnabled(false);
            jbuttonVoltarMenu.setEnabled(true);
            jcomboboxLivros.setEnabled(false);
            listaLivros();
        }
    }//GEN-LAST:event_jbuttonSalvarLivroActionPerformed

    private void jtextfieldContExemplaresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtextfieldContExemplaresKeyTyped
        String caracteres = "0123456789";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_jtextfieldContExemplaresKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbuttonCancelarLivro;
    private javax.swing.JButton jbuttonExcluirLivro;
    private javax.swing.JButton jbuttonNovoLivro;
    private javax.swing.JButton jbuttonSalvarLivro;
    private javax.swing.JButton jbuttonVoltarMenu;
    private javax.swing.JComboBox jcomboboxLivros;
    private javax.swing.JComboBox jcomboboxLocalBiblioteca;
    private javax.swing.JPanel jpanelCadastroLivro;
    private javax.swing.JLabel jtabelLivrosCadastrados;
    private javax.swing.JTable jtableListaLivros;
    private javax.swing.JTextField jtextfieldContExemplares;
    private javax.swing.JTextField jtextfieldNomeLivro;
    private javax.swing.JTextField jtextfieldStatusLivro;
    private javax.swing.JLabel labelContExemplares;
    private javax.swing.JLabel labelLocalBiblioteca;
    private javax.swing.JLabel labelNomeDisciplina;
    private javax.swing.JLabel labelNomeLivro;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JLabel labelTituloCadLivro;
    // End of variables declaration//GEN-END:variables
}
