/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.HibernateFestaDAO;
import entidades.Festa;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Julio
 */
public class TableModelFesta extends AbstractTableModel {

    private static final int COL_ID = 0;
    private static final int COL_NOME = 1;
    private static final int COL_DATA = 2;
    private static final int COL_VL_INICIAL = 3;
    List<Festa> linhas;
    private final String[] colunas = new String[]{"Código", "Nome", "Data", "Valor Inicial"};
    private final HibernateFestaDAO dao;

    public TableModelFesta(HibernateFestaDAO dao) {
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
        Festa m = linhas.get(row);
        switch (column) {
            case COL_ID:
                return m.getId();
            case COL_NOME:
                return m.getNome();
            case COL_DATA:
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                String dataFormatada = formato.format(m.getData());
                return dataFormatada;
            case COL_VL_INICIAL:
                DecimalFormat df = new DecimalFormat("#,##0.00");
                String dx = df.format(m.getValorInicial());
                return dx;
            default:
                break;
        }
        return "";
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        Festa u = linhas.get(row);
        switch (column) {
            case COL_ID:
                u.setId((Long) aValue);
                break;
            case COL_NOME:
                u.setNome(aValue.toString());
                break;
            case COL_DATA:
                break;
            case COL_VL_INICIAL:
                u.setValorInicial((BigDecimal) aValue);
                break;
            default:
                break;
        }
    }

    public Festa getFesta(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addFesta(Festa festa) {
        linhas.add(festa);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void updateFesta(int indiceLinha, Festa festa) {
        linhas.set(indiceLinha, festa);
        fireTableRowsUpdated(indiceLinha, indiceLinha);
    }

    public void removeFesta(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public void removerTudo() {
        for (int x = 0; x < linhas.size(); x++) {
            removeFesta(x);
        }
        linhas.clear();
    }

    public void pesquisar(String parte) {
        removerTudo();
        List<Festa> retorno = dao.listaFiltrando(parte, "nome");
        retorno.forEach((p) -> {
            addFesta(p);
        });
    }
}
