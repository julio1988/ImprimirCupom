/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entidades.ItemVenda;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Julio
 */
public class ButtonColumnSubtracao extends AbstractCellEditor
        implements TableCellRenderer, TableCellEditor, ActionListener {

    JTable table;
    JButton renderButton;
    JButton editButton;
    String text;
    private TableModelItemVenda modelItemVenda;
    private JTextField total;
    private JTextField troco;

    public ButtonColumnSubtracao(JTable table, int column) {
        super();
        this.table = table;
        renderButton = new JButton();

        editButton = new JButton();
        editButton.setFocusPainted(false);
        editButton.addActionListener(this);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer(this);
        columnModel.getColumn(column).setCellEditor(this);
        text = columnModel.getColumn(column).getIdentifier().toString();
    }

    public ButtonColumnSubtracao(JTable table, int column, TableModelItemVenda modelItemVenda, JTextField total, JTextField troco) {
        this(table, column);
        this.modelItemVenda = modelItemVenda;
        this.total = total;
        this.troco = troco;
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (hasFocus) {
            renderButton.setForeground(table.getForeground());
            renderButton.setBackground(UIManager.getColor("Button.background"));
        } else if (isSelected) {
            renderButton.setForeground(table.getSelectionForeground());
            renderButton.setBackground(table.getSelectionBackground());
        } else {
            renderButton.setForeground(table.getForeground());
            renderButton.setBackground(UIManager.getColor("Button.background"));
        }
        renderButton.setBorder(null);
        renderButton.setBorderPainted(false);
        renderButton.setContentAreaFilled(false);
        renderButton.setOpaque(false);
        renderButton.setIcon(new ImageIcon(getClass().getResource("/img/minus-6-32.png")));
        return renderButton;
    }

    @Override
    public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column) {
        text = (value == null) ? "" : value.toString();
        editButton.setText(text);
        return editButton;
    }

    @Override
    public Object getCellEditorValue() {
        return text;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
        int selectedRow = table.getSelectedRow();
        ItemVenda itemVenda = modelItemVenda.getItemVenda(selectedRow);
        if (itemVenda.getQuantidade() > 0) {
            itemVenda.setQuantidade(itemVenda.getQuantidade() - 1);
            modelItemVenda.removeItemVenda(table.getSelectedRow());
            modelItemVenda.addItemVenda(itemVenda, selectedRow);
            total.setText(modelItemVenda.getValorTotalFormatado());
            troco.setText(null);
        }
    }
}
