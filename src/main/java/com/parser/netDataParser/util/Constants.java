package com.parser.netDataParser.util;

import javax.swing.*;
import java.io.InputStream;
import java.util.Properties;

public class Constants {

    public static String INVALID_FILE_MESSAGE ;
    public static String SELECT_SUCCEEDED_RESULT_TITLE ;
    public static String SELECT_FAILED_RESULT_TITLE;
    public static String ERROR_TITLE = "Error";
    public static int DIALOG_WIDTH ;
    public static int DIALOG_HEIGHT ;
    public static String SUCCEEDED_TITLE ;
    public static String FAILED_TITLE;
    public static int INFO_DIALOG_WIDTH;
    public static int INFO_DIALOG_HEIGHT;
    public static int INFO_DIALOG_FONT;

     static {
        Properties properties = new Properties();
        try(InputStream str = Constants.class.getResourceAsStream("/properties/dialog.properties")){
            properties.load(str);
            INVALID_FILE_MESSAGE = properties.getProperty("invalidFileMsg");
            SELECT_FAILED_RESULT_TITLE = properties.getProperty("failedFileChooserTitle");
            SELECT_SUCCEEDED_RESULT_TITLE = properties.getProperty("succeededFileChooserTitle");
            ERROR_TITLE = properties.getProperty("ERROR");
            DIALOG_HEIGHT = Integer.valueOf(properties.getProperty("fileChooserHeight"));
            DIALOG_WIDTH = Integer.valueOf(properties.getProperty("fileChooserWidth"));
            SUCCEEDED_TITLE = properties.getProperty("succeededTitle");
            FAILED_TITLE = properties.getProperty("failedTitle");
            INFO_DIALOG_HEIGHT = Integer.valueOf(properties.getProperty("infoDialogHeight"));
            INFO_DIALOG_WIDTH = Integer.valueOf(properties.getProperty("infoDialogWidth"));
            INFO_DIALOG_FONT = Integer.valueOf(properties.getProperty("infoDialogFont"));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null,e.getMessage() ,Constants.ERROR_TITLE,JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
}
