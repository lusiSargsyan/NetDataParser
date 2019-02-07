package com.parser.netDataParser.dialog.impl;

import com.parser.netDataParser.dialog.IDialog;
import com.parser.netDataParser.util.Constants;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.io.File;

public class TextDialog implements IDialog {

    private String title;
    private String okContent;
    private String nokContent;
    private int width;
    private int height;
    private int titleFontSize ;
    private Color textTitleColor;
    private boolean draggable;

    private TextDialog(TextDialogBuilder b) {
        this.title = b.title;
        this.okContent = b.okContent;
        this.nokContent = b.nokContent;
        this.width = b.width;
        this.height = b.height;
        this.textTitleColor = b.textTitleColor;
        this.draggable = b.draggable;
        this.titleFontSize = b.titleFontSize;
    }

    public String getTitle() {
        return title;
    }

    public String getOkContent() {
        return okContent;
    }
    public String getNokContent() {
        return nokContent;
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getTextTitleColor() {
        return textTitleColor;
    }

    public boolean isDraggable() {
        return draggable;
    }


    @Override
    public File show() {
        JTextPane jtp = new JTextPane();
        Document doc = jtp.getDocument();
        jtp.setSize(new Dimension(width, height));
        jtp.setPreferredSize(new Dimension(width, height));
        try {
            SimpleAttributeSet attribute = new SimpleAttributeSet();
            StyleConstants.setFontSize(attribute, titleFontSize);
            StyleConstants.setForeground(attribute, textTitleColor);
            StyleConstants.setBold(attribute, true);
            StyleConstants.setUnderline(attribute, true);
            doc.insertString(doc.getLength(), Constants.SUCCEEDED_TITLE,attribute);
            doc.insertString(doc.getLength(),okContent+"\n",null);
            doc.insertString(doc.getLength(),Constants.FAILED_TITLE ,attribute);
            doc.insertString(doc.getLength(),nokContent,null);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage() ,Constants.ERROR_TITLE,JOptionPane.ERROR_MESSAGE);
        }
        JScrollPane jsp = new JScrollPane(jtp){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(width, height);
            }
        };
        JOptionPane.showMessageDialog(null,jsp ,"Result",JOptionPane.INFORMATION_MESSAGE);
        return null;
    }

    public static final class TextDialogBuilder {
        private int titleFontSize;
        private String title;
        private String okContent;
        private String nokContent;
        private int width;
        private int height;
        private Color textTitleColor;
        private boolean draggable;

        public TextDialogBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public TextDialogBuilder setOkContent(String okContent) {
            this.okContent = okContent;
            return this;
        }
        public TextDialogBuilder setNokContent(String nokContent) {
            this.nokContent = nokContent;
            return this;
        }

        public TextDialogBuilder setWidth(int width) {
            this.width = width;
            return this;
        }
        public TextDialogBuilder setHeight(int height) {
            this.height = height;
            return this;
        }
        public TextDialogBuilder setTitleFontSize(int titleFontSize) {
            this.titleFontSize = titleFontSize;
            return this;
        }

        public TextDialogBuilder setTextTitleColor(Color textTitleColor) {
            this.textTitleColor = textTitleColor;
            return this;
        }

        public TextDialogBuilder setDraggable(boolean draggable) {
            this.draggable = draggable;
            return this;
        }

        public TextDialog build() {
            return new TextDialog(this);
        }
    }

}
