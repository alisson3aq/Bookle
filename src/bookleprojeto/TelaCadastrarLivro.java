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
public class TelaCadastrarLivro extends javax.swing.JInternalFrame {

    TelaPesquisar telapesquisar = new TelaPesquisar();

    /**
     * Creates new form TelaCadastrarLivro
     */
    public TelaCadastrarLivro() {
        initComponents();
        listaLivros();
        telapesquisar.preencheComboBox("select * from tbdisciplina", "nomedisciplina", jcomboboxLivros);
        jcomboboxLivros.setSelectedIndex(-1);
    }

    public void listaLivros() {
        ArrayList dadoslivros = new ArrayList();

        dadoslivros.clear();

        String[] colunas = new String[]{"Código", "Nome Livro", "Exemplares", "Biblioteca", "Status"};
        try {

            ConexaoMysql conectmysql = new ConexaoMysql();
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

    public void cadastrarLivro() {
        try {
            Boolean cadastrado = false;
            int codigodadisciplina = 0;

            ConexaoMysql conectmysql = new ConexaoMysql();
            conectmysql.abrirConexao();
            conectmysql.executaSQL("select * from tbdisciplina");

            while (conectmysql.resultset.next()) {

                if (conectmysql.resultset.getString("nomedisciplina").equals(jcomboboxLivros.getSelectedItem())) {
                    codigodadisciplina = conectmysql.resultset.getInt("coddisciplina");
                }
            }

            conectmysql.executaSQL("select * from tblivros");

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
                        + "'" + jtextfieldLocalBiblioteca.getText() + "'"
                        + ")");

//conectmysql.statement = conectmysql.conexao.createStatement();
                //conectmysql.resultset = conectmysql.statement.executeQuery("select * from tbuser");
                JOptionPane.showMessageDialog(null, "Livro Cadastrado com Sucesso!", "Livro Cadastrado!", JOptionPane.INFORMATION_MESSAGE);
                jtextfieldNomeLivro.setText(null);
                jcomboboxLivros.setSelectedIndex(-1);
            }

            conectmysql.statement.close();

        } catch (Exception erro) {
            System.out.println("Erro Cadastrar Livro: " + erro);

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        jtextfieldLocalBiblioteca = new javax.swing.JTextField();
        labelLocalBiblioteca = new javax.swing.JLabel();
        jtextfieldStatusLivro = new javax.swing.JTextField();
        labelStatus = new javax.swing.JLabel();
        jcomboboxLivros = new javax.swing.JComboBox();
        jtextfieldNomeLivro = new javax.swing.JTextField();
        labelContExemplares = new javax.swing.JLabel();
        labelNomeLivro = new javax.swing.JLabel();
        jtextfieldContExemplares = new javax.swing.JTextField();
        labelNomeDisciplina = new javax.swing.JLabel();

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
        labelTituloCadLivro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookleprojeto/imagensBookle/booklelogo.png"))); // NOI18N
        labelTituloCadLivro.setText("BOOKLE CADASTRAR LIVRO");
        labelTituloCadLivro.setEnabled(false);

        jbuttonNovoLivro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonNovoLivro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookleprojeto/imagensBookle/add_page.png"))); // NOI18N
        jbuttonNovoLivro.setText("Novo Livro");
        jbuttonNovoLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonNovoLivroActionPerformed(evt);
            }
        });

        jbuttonVoltarMenu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonVoltarMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookleprojeto/imagensBookle/back.png"))); // NOI18N
        jbuttonVoltarMenu.setText("Voltar ao Menu");
        jbuttonVoltarMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonVoltarMenuActionPerformed(evt);
            }
        });

        jbuttonExcluirLivro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonExcluirLivro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookleprojeto/imagensBookle/block.png"))); // NOI18N
        jbuttonExcluirLivro.setText("Excluir Livro");
        jbuttonExcluirLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonExcluirLivroActionPerformed(evt);
            }
        });

        jbuttonCancelarLivro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonCancelarLivro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookleprojeto/imagensBookle/delete.png"))); // NOI18N
        jbuttonCancelarLivro.setText("Cancelar");
        jbuttonCancelarLivro.setEnabled(false);
        jbuttonCancelarLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonCancelarLivroActionPerformed(evt);
            }
        });

        jbuttonSalvarLivro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jbuttonSalvarLivro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookleprojeto/imagensBookle/accept.png"))); // NOI18N
        jbuttonSalvarLivro.setText("Salvar");
        jbuttonSalvarLivro.setEnabled(false);
        jbuttonSalvarLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbuttonSalvarLivroActionPerformed(evt);
            }
        });

        jpanelCadastroLivro.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jtextfieldLocalBiblioteca.setBackground(new java.awt.Color(255, 255, 204));
        jtextfieldLocalBiblioteca.setEnabled(false);

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
        jcomboboxLivros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcomboboxLivrosActionPerformed(evt);
            }
        });

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
        jtextfieldContExemplares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtextfieldContExemplaresActionPerformed(evt);
            }
        });
        jtextfieldContExemplares.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtextfieldContExemplaresKeyTyped(evt);
            }
        });

        labelNomeDisciplina.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelNomeDisciplina.setText("Selecione a Disciplina");
        labelNomeDisciplina.setEnabled(false);

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
                        .addGroup(jpanelCadastroLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelStatus)
                            .addComponent(jtextfieldStatusLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtextfieldLocalBiblioteca, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelLocalBiblioteca)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpanelCadastroLivroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelCadastroLivroLayout.createSequentialGroup()
                        .addComponent(labelNomeLivro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtextfieldNomeLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelContExemplares)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtextfieldContExemplares, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanelCadastroLivroLayout.createSequentialGroup()
                        .addComponent(labelStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtextfieldStatusLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(labelLocalBiblioteca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtextfieldLocalBiblioteca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(247, 287, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelTituloCadLivro)
                        .addGap(403, 403, 403))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jtabelLivrosCadastrados)
                                .addGap(220, 220, 220))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                    .addComponent(jbuttonVoltarMenu))))
                        .addGap(217, 217, 217))))
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

    private void jcomboboxLivrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcomboboxLivrosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcomboboxLivrosActionPerformed

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
        jtextfieldLocalBiblioteca.setEnabled(true);
        jcomboboxLivros.setEnabled(true);
    }//GEN-LAST:event_jbuttonNovoLivroActionPerformed

    private void jbuttonVoltarMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonVoltarMenuActionPerformed

        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jbuttonVoltarMenuActionPerformed

    private void jbuttonExcluirLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonExcluirLivroActionPerformed
        int selecionado = jtableListaLivros.getSelectedRow();
        if (selecionado != -1) {
            try {

                ConexaoMysql conectmysql = new ConexaoMysql();
                conectmysql.abrirConexao();

                String nomelivro = jtableListaLivros.getValueAt(jtableListaLivros.getSelectedRow(), 1).toString();

                int opcao = JOptionPane.showConfirmDialog(null, "Deseja Excluir o Livro: " + nomelivro, "Exclusão de Livro", JOptionPane.YES_NO_OPTION);

                if (opcao == JOptionPane.YES_OPTION) {
                    conectmysql.statement.executeUpdate("DELETE FROM tblivros  WHERE nomelivro ='" + nomelivro + "'");
                    JOptionPane.showMessageDialog(null, "Livro " + nomelivro + " Excluído com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Operação Cancelada");
                }
                listaLivros();

            } catch (Exception erro) {
                System.err.println("Erro Excluir Livro: " + erro);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecione um Livro para excluir!");
        }
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
        jtextfieldLocalBiblioteca.setEnabled(false);
        jtextfieldStatusLivro.setEnabled(false);
        jbuttonExcluirLivro.setEnabled(true);
        jbuttonCancelarLivro.setEnabled(false);
        jbuttonSalvarLivro.setEnabled(false);
        jbuttonVoltarMenu.setEnabled(true);
        jcomboboxLivros.setEnabled(false);
        jtextfieldNomeLivro.setText("");
        jtextfieldContExemplares.setText("");
        jtextfieldLocalBiblioteca.setText("");
         jtextfieldNomeLivro.setText("");
        jtextfieldContExemplares.setText("");
        jtextfieldLocalBiblioteca.setText("");
        jtextfieldStatusLivro.setText("");
        
    }//GEN-LAST:event_jbuttonCancelarLivroActionPerformed

    private void jbuttonSalvarLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbuttonSalvarLivroActionPerformed
        String cursoselecionado = ((String) jcomboboxLivros.getSelectedItem());
        if (cursoselecionado == null) {
            JOptionPane.showMessageDialog(null, "Selecione uma Disciplina!", "Campo Disciplina Vazio", JOptionPane.ERROR_MESSAGE);
        } else if (jtextfieldNomeLivro.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Insira o nome do Livro!", "Campo Livro Vazio", JOptionPane.ERROR_MESSAGE);
        } else if (jtextfieldStatusLivro.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Insira o status do Livro!", "Campo Livro Vazio", JOptionPane.ERROR_MESSAGE);
        } else if (jtextfieldContExemplares.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Insira a quantidade de Exemplares!", "Campo Livro Vazio", JOptionPane.ERROR_MESSAGE);
        } else if (jtextfieldLocalBiblioteca.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Insira o local do Livro!", "Campo Livro Vazio", JOptionPane.ERROR_MESSAGE);
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
            jtextfieldLocalBiblioteca.setEnabled(false);
            jtextfieldStatusLivro.setEnabled(false);
            jbuttonExcluirLivro.setEnabled(true);
            jbuttonCancelarLivro.setEnabled(false);
            jbuttonSalvarLivro.setEnabled(false);
            jbuttonVoltarMenu.setEnabled(true);
            jcomboboxLivros.setEnabled(false);

            listaLivros();
        }
    }//GEN-LAST:event_jbuttonSalvarLivroActionPerformed

    private void jtextfieldContExemplaresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtextfieldContExemplaresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtextfieldContExemplaresActionPerformed

    private void jtextfieldContExemplaresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtextfieldContExemplaresKeyTyped
        String caracteres="0123456789";
        if(!caracteres.contains(evt.getKeyChar()+"")){
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
    private javax.swing.JPanel jpanelCadastroLivro;
    private javax.swing.JLabel jtabelLivrosCadastrados;
    private javax.swing.JTable jtableListaLivros;
    private javax.swing.JTextField jtextfieldContExemplares;
    private javax.swing.JTextField jtextfieldLocalBiblioteca;
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
