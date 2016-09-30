package com.gits.rms.action;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.gits.rms.utils.DateUtils;

public class SaasPDF {

        public static void main(String[] arg){
            Vector vData = new Vector();
            vData.add("Client Name");
            vData.add("No. of Reqs");
            vData.add("App. Received From Direct");
            vData.add("App. Received From C2C");
            vData.add("No. Of Submission");
            vData.add("Interviews Scheduled");
            vData.add("Hotlist Submission Made");
            vData.add("Hotlist Interview Scheduled");
            vData.add("Hotlist Onboarded");
            
            Vector vRow = new Vector();
            Vector vEachRow = new Vector();
            vEachRow.add("EDS");
            vEachRow.add("31");
            vEachRow.add("28");
            vEachRow.add("33");
            vEachRow.add("12");
            vEachRow.add("8");
            vEachRow.add("6");
            vEachRow.add("4");
            vEachRow.add("3");
            vRow.add(vEachRow);
            
            Vector vEachRow2 = new Vector();
            vEachRow2.add("Microsoft");
            vEachRow2.add("33");
            vEachRow2.add("24");
            vEachRow2.add("7");
            vEachRow2.add("15");
            vEachRow2.add("7");
            vEachRow2.add("4");
            vEachRow2.add("3");
            vEachRow2.add("2");
            vRow.add(vEachRow2);
            float[] widths = new float[]{15f,15f,10f,10f,10f,10f,10f,10f,10f};
            SaasPDF sr = new SaasPDF();
            String sFilePath = "d:/reports/client_report.pdf";
            String sHeader = "Date Range 10-2-2008 to 11-2-2008";
//          sr.createPdf("Client Report",sHeader,vData,vRow,sFilePath,widths);
        }
    //  public void createPdf(String sTitle,String sHeader,Vector headerData,Vector rowData,String sFilePath,float[] widths){
//          try{
//              Document document = new Document(PageSize.LETTER.rotate());
//              PdfWriter.getInstance(document, new FileOutputStream(sFilePath));
//              document.addTitle(sTitle);
//              document.addAuthor("Admin");
//              document.open();
//              RtfFont titleFont = new RtfFont("Times New Roman", 36, RtfFont.BOLDITALIC);
//              Paragraph titlePara = new Paragraph(sTitle,titleFont);
//              titlePara.setAlignment(Element.ALIGN_CENTER);
//              document.add(titlePara);
//              document.add(new Paragraph("\n\n"));
//              
//              RtfFont headerFont = new RtfFont("Times New Roman", 20, RtfFont.ITALIC);
//              Paragraph headerPara = new Paragraph(sHeader,headerFont);
//              titlePara.setAlignment(Element.ALIGN_LEFT);
//              document.add(headerPara);
//              document.add(new Paragraph("\n\n"));
//              
//              PdfPTable table = new PdfPTable(headerData.size());
//              table.setWidthPercentage(100f);
//              table.setWidths(widths);
//              table = setTableHeader(table,headerData);
//              table = setTableData(table,rowData);
//              document.add(table);
//              HeaderFooter footer = new HeaderFooter(new Phrase("Admin"), true);
//              document.setFooter(footer);
//              document.close();
//          }
//          catch(Exception e){
//             
//          }
    //  }
        private PdfPTable setTableData(PdfPTable table,Vector vData){
            Vector eachRow = null;
            for(int i=0;i<vData.size();i++){
                eachRow = (Vector)vData.get(i);
                for(int j=0;j<eachRow.size();j++){
                    table.addCell(String.valueOf(eachRow.get(j)));
                }
            }
            return table;
        }
        private PdfPTable setTableHeader(PdfPTable table,Vector vData){
            for(int i=0;i<vData.size();i++){
                table.addCell(setTableHeaderSingleCell(String.valueOf(vData.get(i))));
                
            }
            return table;
        }
        private PdfPCell setTableHeaderSingleCell(String sTitle){
            PdfPCell cell = new PdfPCell(new Paragraph(sTitle));
            cell.setGrayFill(0.8f);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            return cell;
        }
    /*  public String saveSaasPDF(String sAdminEmail, String sContent, String sFolderPath,String sFileName){
            Document document = null;
            StringBuffer sbTemp = new StringBuffer();
            try{
                sContent = sContent.replaceAll("</br>","");
                sContent = sContent.replaceAll("</b>","");
                sContent = sContent.replaceAll("<br><br>", "");
                sContent = sContent.replaceAll("&nbsp;", "");
                sContent = sContent.replaceAll("<b>","");
                
                java.io.File file = new java.io.File(sFolderPath);
                if (!(file.exists())) {
                    file.mkdirs();
                }
                sFolderPath= sFolderPath + sFileName;
                document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(sFolderPath));
                
                
                document.addTitle("Saas Contract");
                document.addSubject("Saas Contract");
                
                document.open();
                RtfFont rtfFont = new RtfFont("Times New Roman", 12, RtfFont.BOLD);
                RtfFont bodyFont = new RtfFont("Times New Roman", 10, RtfFont.NORMAL);
                
                Paragraph paraBody = new Paragraph();
                document.add(new Paragraph("", rtfFont));
                document.add(new Paragraph("", rtfFont));
                
                paraBody = new Paragraph("Saas Contract",rtfFont);
                paraBody.setAlignment(Element.ALIGN_CENTER);
                document.add(paraBody);
                
                document.add(new Paragraph("\n\n"));
                document.add(new Paragraph(sContent,bodyFont));
                document.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            return sFolderPath;
        }   
    */  public String saveSaasPDF(String sAdminEmail, String sFolderPath,String sFileName, String sAppRootPath,
                                        String sBy, String sIts, String sDesignation, String sSaasImagePath){
            String sDate = DateUtils.getCurrentDateTime().toString();
            // step 1: creation of a document-object
            Document document = new Document();
            try {
                // step 2:
                java.io.File file = new java.io.File(sFolderPath);
                if (!(file.exists())) {
                    file.mkdirs();
                }
                sFolderPath= sFolderPath + sFileName;
                
                // we create a writer
                PdfWriter.getInstance(
                // that listens to the document
                        document,
                        // and directs a PDF-stream to a file
                        new FileOutputStream(sFolderPath));
                // step 3: we open the document
                document.open();
                // step 4: we add a paragraph to the document
                Image img1 = Image.getInstance(sAppRootPath+sSaasImagePath+"Rooster_HR_SAAS_Agreement1.jpg");
                img1.scaleToFit(550, 750);
                document.add(img1);
                document.newPage();
                Image img2 = Image.getInstance(sAppRootPath+sSaasImagePath+"Rooster_HR_SAAS_Agreement2.jpg");
                img2.scaleToFit(550, 750);
                document.add(img2);
                document.newPage();
                Image img3 = Image.getInstance(sAppRootPath+sSaasImagePath+"Rooster_HR_SAAS_Agreement3.jpg");
                img3.scaleToFit(550, 750);
                document.add(img3);
                document.newPage();
                Image img4 = Image.getInstance(sAppRootPath+sSaasImagePath+"Rooster_HR_SAAS_Agreement4.jpg");
                img4.scaleToFit(550, 750);
                document.add(img4);
                document.newPage();
                Image img5 = Image.getInstance(sAppRootPath+sSaasImagePath+"Rooster_HR_SAAS_Agreement5.jpg");
                img5.scaleToFit(550, 750);
                document.add(img5);
                document.newPage();
                Image img6 = Image.getInstance(sAppRootPath+sSaasImagePath+"Rooster_HR_SAAS_Agreement6.jpg");
                img6.scaleToFit(550, 750);
                document.add(img6);
                document.newPage();
                Image img7 = Image.getInstance(sAppRootPath+sSaasImagePath+"Rooster_HR_SAAS_Agreement7.jpg");
                img7.scaleToFit(550, 750);
                document.add(img7);
                document.newPage();
                Image img8 = Image.getInstance(sAppRootPath+sSaasImagePath+"Rooster_HR_SAAS_Agreement8a.jpg");
                img8.scaleToFit(550, 750);
                document.add(img8);
                
                PdfPTable table = new PdfPTable(4);
                float[] widths = {0.1f, 0.4f, 0.1f, 0.4f};
                table.addCell(getCell("By:"));
                table.addCell(getCell(sBy));
                table.addCell(getCell("By:"));
                table.addCell(getCell("_____________"));
                table.addCell(getCell("Its:"));
                table.addCell(getCell(sIts+" ("+sDesignation+")"));
                table.addCell(getCell("Its:"));
                table.addCell(getCell("_____________"));
                table.addCell(getCell("Dated:"));
                table.addCell(getCell(sDate));
                table.addCell(getCell("Dated:"));
                table.addCell(getCell(sDate));
                table.setWidthPercentage(80);
                table.setWidths(widths);
//              table.addCell("By:");
//              table.addCell(sBy);
//              table.addCell("By:");
//              table.addCell("_____________");
//              table.addCell("Its:");
//              table.addCell(sIts+" ("+sDesignation+")");
//              table.addCell("Its:");
//              table.addCell("_____________");
//              table.addCell("Dated:");
//              table.addCell(sDate);
//              table.addCell("Dated:");
//              table.addCell(sDate);
//              table.setWidthPercentage(80);
//              table.setWidths(widths);
                
                document.add(table);            
            } catch (DocumentException de) {
                System.err.println(de.getMessage());
            } catch (IOException ioe) {
                System.err.println(ioe.getMessage());
            }
     
            // step 5: we close the document
            document.close();
            return sFolderPath;
        }
        private static PdfPCell getCell(String sValue){
//          RtfFont rtfFont = new RtfFont("Times New Roman", 10, RtfFont.NORMAL);
            PdfPCell cell = new PdfPCell(new Paragraph(sValue));
            Rectangle border = new Rectangle(0f, 0f);
            border.setBorderColor(Color.WHITE);
            cell.cloneNonPositionParameters(border);
            return cell;
        }
}
