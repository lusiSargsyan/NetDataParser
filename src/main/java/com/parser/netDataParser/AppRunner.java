package com.parser.netDataParser;

import com.parser.netDataParser.dialog.impl.FileChooserDialog;
import com.parser.netDataParser.dialog.impl.TextDialog;
import com.parser.netDataParser.util.Constants;

import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppRunner {

    public static void main(String[] args) {
        FileChooserDialog okFileChooser  = new FileChooserDialog.DialogBuilder()
                .setTitle(Constants.SELECT_SUCCEEDED_RESULT_TITLE)
                .setWidth(Constants.DIALOG_WIDTH)
                .setHeight(Constants.DIALOG_HEIGHT)
                .setDraggable(true).build();
        File okFile = okFileChooser.show();

        FileChooserDialog nokFileChooser  = new FileChooserDialog.DialogBuilder()
                .setTitle(Constants.SELECT_FAILED_RESULT_TITLE)
                .setWidth(Constants.DIALOG_WIDTH)
                .setHeight(Constants.DIALOG_HEIGHT)
                .setDraggable(true).build();
        File nokFile = nokFileChooser.show();
        List<String> okResourcesList = getResourcesList(okFile);
        List<String> nokResourcesList = getResourcesList(nokFile);
        String oksDiff =  getFilteredResourcesList(okResourcesList,nokResourcesList);
        String noksDiff =  getFilteredResourcesList(nokResourcesList,okResourcesList);
        TextDialog textDialog = new TextDialog.TextDialogBuilder()
                .setTextTitleColor(Color.red)
                .setTitleFontSize(Constants.INFO_DIALOG_FONT)
                .setDraggable(true)
                .setHeight(Constants.INFO_DIALOG_HEIGHT)
                .setWidth(Constants.INFO_DIALOG_WIDTH)
                .setNokContent(noksDiff)
                .setOkContent(oksDiff)
                .build();
        textDialog.show();

    }

    private static List<String> getResourcesList(File csvFile){
        List<String> lines = null;
        try(Stream<String> stream = Files.lines(csvFile.toPath())){
            lines = stream.map((line)->line.substring(0,line.indexOf(","))).collect(Collectors.toList());
        }catch (Exception e){
            e.printStackTrace();
        }
        return  lines;
    }
    private static String getFilteredResourcesList(List<String> list, List<String> subList){
        return subList.stream().filter((x)->x.startsWith("http")
                && !list.contains(x)).collect(Collectors.joining("\n"));
    }
}
