/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.HibernateProdutoDAO;
import entidades.Produto;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Julio
 */
public class TableModelProduto extends AbstractTableModel {

    private static final int COL_ID = 0;
    private static final int COL_NOME = 1;
    private static final int COL_ATIVO = 2;
    private static final int COL_PRECO = 3;
    List<Produto> linhas;
    private final String[] colunas = new String[]{"Código", "Nome", "Ativo", "Preço"};
    private final HibernateProdutoDAO dao;

    public TableModelProduto(HibernateProdutoDAO dao) {
        this.linhas = new ArrayList<>(dao.getList());
        this.dao = dao;
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
        if (columnIndex == COL_ID) {
            return Long.class;
        }
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Produto m = linhas.get(row);
        switch (column) {
            case COL_ID:
                return m.getId();
            case COL_NOME:
                return m.getNome();
            case COL_ATIVO:
                return m.getAtivo() ? "Sim" : "Não";
            case COL_PRECO:
                DecimalFormat df = new DecimalFormat("#,##0.00");
                String dx = df.format(m.getPreco());
                return dx;
            default:
                break;
        }
        return "";
    }

    public Produto getProduto(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addProduto(Produto produto) {
        linhas.add(produto);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void updateProduto(int indiceLinha, Produto produto) {
        linhas.set(indiceLinha, produto);
        fireTableRowsUpdated(indiceLinha, indiceLinha);
    }

    public void removeProduto(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public void removerTudo() {
        for (int x = 0; x < linhas.size(); x++) {
            removeProduto(x);
        }
        linhas.clear();
    }

    public void pesquisar(String parte) {
        removerTudo();
        List<Produto> retorno = dao.listaFiltrando(parte, "nome");
        retorno.forEach((p) -> {
            addProduto(p);
        });
    }
}
