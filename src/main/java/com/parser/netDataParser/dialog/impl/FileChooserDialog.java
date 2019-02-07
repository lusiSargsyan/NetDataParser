package com.parser.netDataParser.dialog.impl;

import com.parser.netDataParser.dialog.IDialog;
import com.parser.netDataParser.util.Constants;
import com.parser.netDataParser.util.Util;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;

public class FileChooserDialog implements IDialog {
    private String title;
    private int width;
    private int height;
    private boolean draggable;

    private FileChooserDialog(DialogBuilder b) {
        this.title = b.title;
        this.width = b.width;
        this.height = b.height;
        this.draggable = b.draggable;
    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isDraggable() {
        return draggable;
    }

    @Override
    public File show() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle(title);
        jfc.setDragEnabled(draggable);
        jfc.setPreferredSize(new Dimension(width, height));
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            if (Util.isValidFileFormat(selectedFile.getName())) {
                return selectedFile;
            } else {
                JOptionPane.showMessageDialog(null, Constants.INVALID_FILE_MESSAGE, Constants.ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
        return null;
    }

    public static final class DialogBuilder {
        private String title;
        private int width;
        private int height;
        private boolean draggable;

        public DialogBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public DialogBuilder setWidth(int width) {
            this.width = width;
            return this;
        }

        public DialogBuilder setHeight(int height) {
            this.height = height;
            return this;
        }


        public DialogBuilder setDraggable(boolean draggable) {
            this.draggable = draggable;
            return this;
        }

        public FileChooserDialog build() {
            return new FileChooserDialog(this);
        }
    }

}
