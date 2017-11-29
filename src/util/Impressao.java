/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Julio
 */
import entidades.Festa;
import entidades.ItemVenda;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.JOptionPane;

public final class Impressao {

    private static PrintService impressoraPadrao;
    private JasperReportUtil jasperReportUtil;
    private SimpleDateFormat formato;

    public Impressao() {
        detectaImpressoras();
    }

    public void detectaImpressoras() {
        try {
            impressoraPadrao = PrintServiceLookup.lookupDefaultPrintService();
            formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            jasperReportUtil = new JasperReportUtil();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }

    public synchronized void imprime(List<ItemVenda> itens) {
        if (impressoraPadrao == null) {
            String msg = "Nennhuma impressora foi encontrada. Instale uma impressora padrão \r\n(Generic Text Only) e reinicie o programa.";
            JOptionPane.showMessageDialog(null, "Erro: " + msg);
        } else {
            try {
                for (ItemVenda it : itens) {
                    for (int x = 0; x < it.getQuantidade(); x++) {
                        Map<String, Object> hashMap = new HashMap<>();
                        hashMap.put("LOCAL", "Paróquia São Pedro Apóstolo");
                        hashMap.put("FESTA", it.getVenda().getFesta().getNome().toUpperCase());
                        hashMap.put("PRODUTO", it.getProduto().toUpperCase());
                        String dataFormatada = formato.format(new Date());
                        hashMap.put("RODAPE", "Boa Festa! " + dataFormatada);
                        jasperReportUtil.geraPdf("Fichas.jasper", hashMap);
                        imprimirText(gerarRodape());
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        }
    }

    public synchronized void imprimeExtrato(Festa festa, List<Object[]> linhas) {
        if (impressoraPadrao == null) {
            String msg = "Nennhuma impressora foi encontrada. Instale uma impressora padrão \r\n(Generic Text Only) e reinicie o programa.";
            JOptionPane.showMessageDialog(null, "Erro: " + msg);
        } else {
            try {
                imprimirText(gerarExtrato(festa, linhas));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        }
    }

    public void imprimirText(String text) {
        try {
            DocPrintJob dpj = impressoraPadrao.createPrintJob();
            InputStream stream = new ByteArrayInputStream(text.getBytes());
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Doc doc = new SimpleDoc(stream, flavor, null);
            dpj.print(doc, null);
        } catch (PrintException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }

    public void imprimirImagemPNG(File file) {
        try {
            DocFlavor docFlavor = DocFlavor.INPUT_STREAM.PNG;
            FileInputStream stream = new FileInputStream(file);
            Doc doc = new SimpleDoc(stream, docFlavor, null);
            DocPrintJob docPrintJob = impressoraPadrao.createPrintJob();
            try {
                docPrintJob.print(doc, null);
            } catch (PrintException e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
        }
    }

    private static String gerarRodape() {
        String nota = "";
        nota += "                                          ";
        nota += "                                          ";
        nota += "                                          ";
        nota += "                                          " + System.lineSeparator();
        nota += acionarGuilhotinaCorteTotal();
        return nota;
    }

    private static String gerarExtrato(Festa festa, List<Object[]> linhas) {
        NumberFormat patter = NumberFormat.getCurrencyInstance();
        String nota = "Festa: " + festa.getNome() + System.lineSeparator();
        nota += "Caixa Inicial: " + patter.format(festa.getValorInicial()) + System.lineSeparator();
        nota += "------------------------------------------" + System.lineSeparator();
        BigDecimal total = BigDecimal.ZERO;
        for (Object[] obj : linhas) {
            nota += "  Produto: " + obj[0] + System.lineSeparator();
            nota += "  Quantidade: " + obj[2] + System.lineSeparator();
            nota += "  Preço: " + obj[1] + System.lineSeparator();
            nota += "  Total: " + obj[3] + System.lineSeparator();
            nota += "------------------------------------------" + System.lineSeparator();
            total = total.add((BigDecimal) obj[3]);
        }
        nota += "                                          " + System.lineSeparator();
        nota += "                                          " + System.lineSeparator();
        nota += "Total Geral: " + patter.format(total) + System.lineSeparator();
        nota += "==========================================" + System.lineSeparator();
        nota += "                                          " + System.lineSeparator();
        nota += "                                          " + System.lineSeparator();
        nota += "                                          " + System.lineSeparator();
        nota += "                                          " + System.lineSeparator();
        nota += "                                          " + System.lineSeparator();
        nota += acionarGuilhotinaCorteTotal();
        return nota;
    }


    /*    27 105 (corte total)
          27 109 (corte parcial)
          29 86 1 (corte total)
          29 86 0 (corte parcial)*/
    public static String acionarGuilhotinaCorteTotal() {
        return "" + (char) 27 + (char) 105;
    }

    public static String acionarGuilhotinaCorteParcial() {
        return "" + System.lineSeparator() + (char) 29 + (char) 86 + (char) 0;
    }

    public static String acionarLogo() {
        return "0" + (char) 28 + (char) 112 + (char) 001 + (char) 003;
    }

    private static File criarImagem(String nome) throws IOException {
        File toFile = Paths.get(nome + ".png").toFile();
        if (toFile.length() == 0) {
            String[] palavrasSeparadas = nome.split(" ");
            int linhas = palavrasSeparadas.length;
            int tamanhoFonte = 24;
            int width = 129;
            int height = (linhas * tamanhoFonte);
            BufferedImage buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
            Graphics g = buffer.createGraphics();
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, width, height);
            g.setColor(Color.WHITE);
            Font fonte = new Font("Serif", Font.BOLD, tamanhoFonte);
            g.setFont(fonte);
            int x = 1;
            for (String palavara : palavrasSeparadas) {
                g.setFont(fonte);
                g.drawString(palavara.toUpperCase(), 0, tamanhoFonte * x);
                x++;
            }
            ImageIO.write(buffer, "png", toFile);
        }
        return toFile;
    }

    public int ColPrintCenter(String text, Integer tamanho) {
        return (tamanho - text.length()) / 2;
    }

    public static String center(String text, int len) {
        if (len <= text.length()) {
            return text.substring(0, len);
        }
        int before = (len - text.length()) / 2;
        if (before == 0) {
            return String.format("%-" + len + "s", text);
        }
        int rest = len - before;
        return String.format("%" + before + "s%-" + rest + "s", "", text);
    }
}
