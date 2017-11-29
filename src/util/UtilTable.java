/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Julio
 */
public class UtilTable {

    public static void alinhaTableCentro(JTable table, int[] posicoesDireita) {
        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
        cellRender.setHorizontalAlignment(SwingConstants.CENTER);
        for (int numCol = 0; numCol < table.getColumnCount(); numCol++) {
            for (int i = 0; i < posicoesDireita.length; i++) {
                if (numCol == posicoesDireita[i]) {
                    table.getColumnModel().getColumn(numCol).setCellRenderer(
                            cellRender);
                }
            }
        }
    }

    public static void alinhaTableRight(JTable table, int[] posicoesDireita) {
        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
        cellRender.setHorizontalAlignment(SwingConstants.RIGHT);
        for (int numCol = 0; numCol < table.getColumnCount(); numCol++) {
            for (int i = 0; i < posicoesDireita.length; i++) {
                if (numCol == posicoesDireita[i]) {
                    table.getColumnModel().getColumn(numCol).setCellRenderer(
                            cellRender);
                }
            }
        }
    }
    
     public static void alinhaTableLeft(JTable table, int[] posicoesDireita) {
        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
        cellRender.setHorizontalAlignment(SwingConstants.LEFT);
        for (int numCol = 0; numCol < table.getColumnCount(); numCol++) {
            for (int i = 0; i < posicoesDireita.length; i++) {
                if (numCol == posicoesDireita[i]) {
                    table.getColumnModel().getColumn(numCol).setCellRenderer(
                            cellRender);
                }
            }
        }
    }
}
