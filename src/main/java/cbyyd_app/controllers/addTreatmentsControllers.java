package cbyyd_app.controllers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class addTreatmentsControllers {


    public static void FileClear(String FileName) throws IOException {
        FileWriter fstream = new FileWriter(FileName,true);
        BufferedWriter out = new BufferedWriter(fstream);
        out.write("");
    }

    public static void FileWriters(String FileName, String Content) throws IOException
    {
        FileWriter fstream = new FileWriter(FileName,true);
        BufferedWriter out = new BufferedWriter(fstream);
        out.append(Content);
        out.newLine();

    }

}
