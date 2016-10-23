package com.cs246.spencer.threadinggui;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//import static android.R.attr.path;

public class MainActivity extends AppCompatActivity {

    public static final String FILE_NAME = "Numbers.txt";

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = null;
    }

    public void writeToFile(View v) {

        Context context = this.getApplicationContext();
//        File file = new File(getFilesDir(), FILE_NAME);
        new WriteThread().execute(context);

        /*
        FileWriter out = null;
        BufferedWriter writer = null;

        // create the file
        Context context = this.getApplicationContext();
        File file = new File(context.getFilesDir(), "numbers.txt");


        try {
            out = new FileWriter(file);
            writer = new BufferedWriter(out);
            for (int i = 1; i < 11; i++) {
                Integer myInt = new Integer(i);
                String temp = myInt.toString();
                temp += "\n";
                out.write(temp);
                Thread.sleep(250);
            }
            //outputStream.write(.getBytes());
            out.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        finally {
            if (writer != null) {
                writer = null;
            }
            if (out != null) {
                out = null;
            }
        }
        */

    }

    public void readFile(View v) {
        // read the file
        Context context = this.getApplicationContext();
        //new ReadThread().execute(context);


        try {
            InputStream in = openFileInput(FILE_NAME);
            InputStreamReader input = new InputStreamReader(in);
            BufferedReader reader = new BufferedReader(input);

            List<String> list = new ArrayList<String>();
            String temp;
            while ((temp = reader.readLine()) != null) {
                list.add(temp);
            }

            in.close();
            input.close();
            reader.close();
            // add the list to the list view
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

            ListView listView = (ListView)findViewById(R.id.list);
            listView.setAdapter(adapter);

        }

        catch (FileNotFoundException ex) {
            Log.e("ERROR", ex.getMessage().toString());
        }
        catch (IOException ex) {
            Log.e("ERROR", ex.getMessage().toString());
        }

    }

    public void clearButton(View v) {
        adapter.clear();
    }

}

