package com.example.BridgeAndCoCursach.Securing;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DBManaging {
    public final class DatabaseUtilBackup {

        public static void backup() {

            System.out.println("Backup Started at " + new Date());

            Date backupDate = new Date();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            String backupDateStr = format.format(backupDate);
            String dbNameList = "BridgesAndCo";
            String home = System.getProperty("user.home");
            String fileName = "DB_Backup"; // default file name
            String folderPath = "C:\\home";
            File f1 = new File(folderPath);
            f1.mkdir(); // create folder if not exist

            String saveFileName = fileName + "_" + backupDateStr + ".sql";
            String savePath =home+"\\Downloads" + File.separator + saveFileName;

            String executeCmd = "C:\\Program Files\\mysql-8.0.31-winx64\\bin\\mysqldump -u " + "root" + " -p" + "" + "  --databases " + dbNameList
                    + " -r " + savePath;

            Process runtimeProcess = null;
            try {
                runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int processComplete = 0;
            try {
                processComplete = runtimeProcess.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (processComplete == 0) {
                System.out.println("Backup Complete at " + new Date());
            } else {
                System.out.println("Backup Failure");
            }
        }
        public final class DatabaseUtilRestore {
            public static boolean restore(String dbUsername, String dbPassword, String dbName, String sourceFile)
                    throws IOException, InterruptedException {
                String[] command = new String[]{
                        "mysql",
                        "-u" + dbUsername,
                        "-p" + dbPassword,
                        "-e",
                        " source " + sourceFile,
                        dbName
                };
                Process runtimeProcess = Runtime.getRuntime().exec(command);
                int processComplete = runtimeProcess.waitFor();
                return processComplete == 0;
            }
        }
    }
}
