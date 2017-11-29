/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

/**
 *
 * @author Julio
 */
public class JasperReportUtil {

    public JasperReportUtil() {
    }

    public void geraPdf(String jrxml, Map<String, Object> parametros) {
        try {
            JasperPrint print = JasperFillManager.fillReport(getClass().getResource("/reports/"+jrxml).getFile(), parametros);
            JasperPrintManager.printPage(print, 0, false);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar relatório", e);
        }
    }
}
