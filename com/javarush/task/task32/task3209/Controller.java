package com.javarush.task.task32.task3209;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;


    public Controller(View view) {
        this.view = view;
    }

    public void init() {
        createNewDocument();
    }

    public void exit() {
        System.exit(0);
    }

    public HTMLDocument getDocument() {
        return document;
    }

    public static void main(String[] args) {
        View viewMain = new View();
        Controller controller = new Controller(viewMain);
        viewMain.setController(controller);
        viewMain.init();
        controller.init();
    }

    public void resetDocument() {
        if (document != null) {
            document.removeUndoableEditListener(view.getUndoListener());
        }
            HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
            document = (HTMLDocument) htmlEditorKit.createDefaultDocument();
            document.addUndoableEditListener(view.getUndoListener());
            view.update();

    }

    public void setPlainText(String text) {
        resetDocument();
        StringReader strReader = new StringReader(text);
        try {
            HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
            htmlEditorKit.read(strReader, document, 0);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public String getPlainText() {
        StringWriter strWriter = new StringWriter();
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        try {
            htmlEditorKit.write(strWriter, document, 0, document.getLength());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
        return strWriter.toString();
    }

    public void createNewDocument() {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile = null;
    }

    public void openDocument() {
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());
        int x = jFileChooser.showOpenDialog(view);
        if (x == JFileChooser.APPROVE_OPTION) {
            try {
                resetDocument();
                currentFile = jFileChooser.getSelectedFile();
                view.setTitle(currentFile.getName());
                FileReader fileReader = new FileReader(currentFile);
                HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
                htmlEditorKit.read(fileReader, document, 0);
                view.resetUndo();
            }
            catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocument(){
        view.selectHtmlTab();
        if (currentFile != null) {
            try {
                FileWriter fileWriter = new FileWriter(currentFile);
                HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
                htmlEditorKit.write(fileWriter, document, 0, document.getLength());
            }
            catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
        else
            saveDocumentAs();
    }

    public void saveDocumentAs() {
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());
        int x = jFileChooser.showSaveDialog(view);
        if (x == JFileChooser.APPROVE_OPTION) {
            try{
            currentFile = jFileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            FileWriter fileWriter = new FileWriter(currentFile);
            HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
                htmlEditorKit.write(fileWriter, document, 0, document.getLength());
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }


}
