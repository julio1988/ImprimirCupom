/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.HibernateProdutoDAO;
import entidades.ItemVenda;
import entidades.Produto;
import entidades.Venda;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Julio
 */
public class TableModelItemVenda extends AbstractTableModel {

    private static final int COL_NOME = 0;
    private static final int COL_SUBTRACAO = 1;
    private static final int COL_ADICAO = 2;
    private static final int COL_QUANTIDADE = 3;
    private static final int COL_VL_UNITARIO = 4;
    private static final int COL_TOTAL = 5;

    List<ItemVenda> linhas;
    private final String[] colunas = new String[]{"Produto", "Diminuir", "Aumentar", "Quantidade", "Valor Unitário", "Total"};
    private final HibernateProdutoDAO daoProduto;
    private final Venda venda;

    public TableModelItemVenda(HibernateProdutoDAO daoProduto, Venda venda) {
        this.linhas = new ArrayList<>();
        for (Produto p : daoProduto.buscarProdutosAtivos()) {
            ItemVenda item = new ItemVenda(p, venda);
            this.linhas.add(item);
        }
        this.daoProduto = daoProduto;
        this.venda = venda;
    }

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (COL_SUBTRACAO == columnIndex) {
            return true;
        }
        if (COL_ADICAO == columnIndex) {
            return true;
        }
        return false;
    }

    @Override
    public Object getValueAt(int row, int column) {
        ItemVenda i = linhas.get(row);
        switch (column) {
            case COL_NOME:
                return i.getProduto().toUpperCase();
            case COL_QUANTIDADE:
                return i.getQuantidade().toString();
            case COL_VL_UNITARIO:
                return i.getVlUnitario();
            case COL_TOTAL:
                return i.getTotal();
            default:
                break;
        }
        return "";
    }

    public ItemVenda getItemVenda(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addItemVenda(ItemVenda item) {
        linhas.add(item);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addItemVenda(ItemVenda item, int indiceLinha) {
        linhas.add(indiceLinha, item);
        fireTableRowsInserted(indiceLinha, indiceLinha);
    }

    public void updateItemVenda(int indiceLinha, ItemVenda item) {
        linhas.set(indiceLinha, item);
        fireTableRowsUpdated(indiceLinha, indiceLinha);
    }

    public void removeItemVenda(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public void removerTudo() {
        for (int x = 0; x < linhas.size(); x++) {
            removeItemVenda(x);
        }
        linhas.clear();
    }

    public void novaInstancia() {
        removerTudo();
        for (Produto p : daoProduto.buscarProdutosAtivos()) {
            ItemVenda item = new ItemVenda(p, venda);
            addItemVenda(item);
        }
    }

    public BigDecimal getValorTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (ItemVenda it : linhas) {
            total = total.add(it.getVlTotal());
        }
        return total;
    }

    public String getValorTotalFormatado() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String total = df.format(getValorTotal());
        return "R$: " + total;
    }

    public List<ItemVenda> buscarItensComprados() {
        List<ItemVenda> itens = new ArrayList<>();
        for (ItemVenda it : linhas) {
            if (it.getVlTotal().compareTo(BigDecimal.ZERO) > 0) {
                itens.add(it);
            }
        }
        return itens;
    }
}
