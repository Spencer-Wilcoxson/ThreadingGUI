package com.cs246.spencer.threadinggui;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Spencer on 10/22/2016.
 */

public class WriteThread extends AsyncTask<Context, Integer, Void> {
    protected Void doInBackground(Context... contexts) {
        Context context = contexts[0];

        // create the file
        File file = new File(context.getFilesDir(), MainActivity.FILE_NAME);
        FileWriter out = null;
        BufferedWriter writer = null;

        try {
            out = new FileWriter(file);
            writer = new BufferedWriter(out);

            for (int i = 1; i <= 10; i++) {
                Integer myInt = new Integer(i);
                String temp = myInt.toString();
                temp = temp + "\n";
                out.write(temp);
                Thread.sleep(250);
            }

            out.close();
            writer.close();

        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } catch (InterruptedException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        return null;
    }
}
