/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.HibernateFestaDAO;
import dao.HibernateProdutoDAO;
import dao.HibernateVendaDAO;
import entidades.Festa;
import entidades.ItemVenda;
import entidades.Venda;
import util.Impressao;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JOptionPane;
import model.ButtonColumnAdicao;
import model.ButtonColumnSubtracao;
import model.TableModelItemVenda;
import util.UtilTable;

/**
 *
 * @author Julio
 */
public class TelaDeVenda extends javax.swing.JDialog {

    private Venda venda;
    private TableModelItemVenda modelItemVenda;
    private Impressao impressao;
    private HibernateVendaDAO hibernateVendaDAO;

    /**
     * Creates new form TelaDeVenda
     */
    public TelaDeVenda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        venda = new Venda(null);
    }

    public TelaDeVenda(java.awt.Frame parent, boolean modal, Festa festa, HibernateProdutoDAO produtoDAO) {
        super(parent, modal);
        initComponents();
        this.venda = new Venda(festa);
        modelItemVenda = new TableModelItemVenda(produtoDAO, venda);
        jTableItemVenda.setModel(modelItemVenda);
        int[] colunasCentro = {1, 2, 3};
        int[] colunasLeft = {0};
        int[] colunasRight = {4, 5};
        UtilTable.alinhaTableCentro(jTableItemVenda, colunasCentro);
        UtilTable.alinhaTableLeft(jTableItemVenda, colunasLeft);
        UtilTable.alinhaTableRight(jTableItemVenda, colunasRight);
        ButtonColumnAdicao buttonAdicao = new ButtonColumnAdicao(jTableItemVenda, 2, modelItemVenda, jTextFieldTotal, jTextFieldTroco);
        ButtonColumnSubtracao buttonSubtracao = new ButtonColumnSubtracao(jTableItemVenda, 1, modelItemVenda, jTextFieldTotal, jTextFieldTroco);
        impressao = new Impressao();
        hibernateVendaDAO = new HibernateVendaDAO();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonEfetuar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jTextFieldUsuario = new javax.swing.JTextField();
        jLabelUsuario = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableItemVenda = new javax.swing.JTable();
        jTextFieldTotal = new javax.swing.JTextField();
        jTextFieldTroco = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextVlRecebido = new javax.swing.JFormattedTextField();
        jButtonCalcularTroco = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        jButtonEfetuar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonEfetuar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/check-mark-8-32 (1).png"))); // NOI18N
        jButtonEfetuar.setText("Efetuar Venda");
        jButtonEfetuar.setToolTipText("Click aqui para efetuar a venda e gerar as fichas.");
        jButtonEfetuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEfetuarActionPerformed(evt);
            }
        });

        jButtonCancelar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancel-32.png"))); // NOI18N
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setToolTipText("Click aqui para limpar a tabela e começar uma nova venda");
        jButtonCancelar.setMaximumSize(new java.awt.Dimension(121, 25));
        jButtonCancelar.setMinimumSize(new java.awt.Dimension(121, 25));
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jTextFieldUsuario.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldUsuario.setToolTipText("Informe o nome do usuário que esta trabalhando no caixa");

        jLabelUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/usuario.png"))); // NOI18N

        jTableItemVenda.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTableItemVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableItemVenda.setToolTipText("");
        jTableItemVenda.setRowHeight(32);
        jTableItemVenda.setRowMargin(3);
        jScrollPane2.setViewportView(jTableItemVenda);

        jTextFieldTotal.setEditable(false);
        jTextFieldTotal.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldTotal.setFocusable(false);

        jTextFieldTroco.setEditable(false);
        jTextFieldTroco.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextFieldTroco.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldTroco.setToolTipText("Nesse campo irá informar o troco que deverá ser devolvido ao cliente.");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setText("Recebido (R$):");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel2.setText("Total (R$):");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel3.setText("Troco (R$):");

        jFormattedTextVlRecebido.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextVlRecebido.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextVlRecebido.setToolTipText("Informe o valor recebido do cliente para calcular o troco.");
        jFormattedTextVlRecebido.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jButtonCalcularTroco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/calculator.png"))); // NOI18N
        jButtonCalcularTroco.setToolTipText("Click aqui para calcular o troco.");
        jButtonCalcularTroco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCalcularTrocoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonEfetuar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextVlRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCalcularTroco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTroco, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldUsuario)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEfetuar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 3, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextVlRecebido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldTroco, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonCalcularTroco, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        limparCampos();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    public void limparCampos() {
        modelItemVenda.novaInstancia();
        jTextFieldTotal.setText(null);
        jTextFieldTroco.setText(null);
        jFormattedTextVlRecebido.setText(null);
    }

    private void jButtonEfetuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEfetuarActionPerformed
        if (jTextFieldUsuario.getText() != null && !jTextFieldUsuario.getText().isEmpty()) {
            if (modelItemVenda.getValorTotal().compareTo(BigDecimal.ZERO) > 0) {
                List<ItemVenda> itens = modelItemVenda.buscarItensComprados();
                impressao.imprime(itens);
                venda.setUsuario(jTextFieldUsuario.getText());
                venda.getItens().addAll(itens);
                venda.setTotal(venda.getTotal().add(modelItemVenda.getValorTotal()));
                if (venda != null && venda.getId() != null) {
                    venda = hibernateVendaDAO.salvar(venda);
                } else {
                    venda = hibernateVendaDAO.atualizar(venda);
                }
                limparCampos();
                JOptionPane.showMessageDialog(null, "Venda Realizada com Sucesso!  Aguarde a Impressão.");
            } else {
                JOptionPane.showMessageDialog(null, "Informe ao menos um produto para efetuar a venda, inserindo o produto com o botão (+) na tabela.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Informe o nome do usuário que está trabalhando no caixa para efetuar a venda.");
        }
    }//GEN-LAST:event_jButtonEfetuarActionPerformed

    private void jButtonCalcularTrocoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCalcularTrocoActionPerformed
        if (jFormattedTextVlRecebido.getValue() != null) {
            BigDecimal troco = BigDecimal.ZERO;
            BigDecimal VlRecebido = BigDecimal.ZERO;
            BigDecimal total = modelItemVenda.getValorTotal();
            if (jFormattedTextVlRecebido.getValue() instanceof Long) {
                VlRecebido = new BigDecimal((Long) jFormattedTextVlRecebido.getValue());
            }
            if (jFormattedTextVlRecebido.getValue() instanceof Double) {
                VlRecebido = new BigDecimal((Double) jFormattedTextVlRecebido.getValue());
            }
            troco = VlRecebido.subtract(total);
            DecimalFormat df = new DecimalFormat("#,##0.00");
            String trocoFormatado = df.format(troco);
            jTextFieldTroco.setText(trocoFormatado);
        } else {
            jTextFieldTroco.setText(null);
        }
    }//GEN-LAST:event_jButtonCalcularTrocoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaDeVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaDeVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaDeVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaDeVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaDeVenda dialog = new TelaDeVenda(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCalcularTroco;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEfetuar;
    private javax.swing.JFormattedTextField jFormattedTextVlRecebido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableItemVenda;
    private javax.swing.JTextField jTextFieldTotal;
    private javax.swing.JTextField jTextFieldTroco;
    private javax.swing.JTextField jTextFieldUsuario;
    // End of variables declaration//GEN-END:variables
}