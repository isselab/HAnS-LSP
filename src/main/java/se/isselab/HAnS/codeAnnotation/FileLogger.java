package se.isselab.HAnS.codeAnnotation;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger {

    String name ;
    BufferedWriter writer;
    File logFile;
    Boolean logging = false;
    public FileLogger(Class o) {
        if(logging) {
            name = o.toString();
            logFile = new File("C:\\Users\\Tim\\hans-lsp-vscode-extension\\serverlog\\" + name + ".log");
            try {
                logFile.createNewFile();
                writer = new BufferedWriter(new FileWriter(logFile, true));
                writer.append("--------" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + " Logger Started" + "--------" + System.getProperty("line.separator"));
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
    public void warn(String msg){
        if(logging) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            try {
                writer.append("[WARN]" + now.format(formatter) + " " + msg + System.getProperty("line.separator"));
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void info(String msg){
        if(logging) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            try {
                writer.append(now.format(formatter) + " " + msg + System.getProperty("line.separator"));
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void error(String msg){
        if(logging) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            try {
                writer.append("[!ERROR!]" + now.format(formatter) + " " + msg + System.getProperty("line.separator"));
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
