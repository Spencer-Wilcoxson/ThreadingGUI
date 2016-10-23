package com.cs246.spencer.threadinggui;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Spencer on 10/22/2016.
 */

public class ReadThread extends AsyncTask<Context, Void, ArrayAdapter<String>> {

    private Context context;
    private List<String> list;
    private ArrayAdapter<String> adapter;

    protected ArrayAdapter<String> doInBackground(Context... contexts) {
        context = contexts[0];
        InputStream in;
        InputStreamReader input = null;
        BufferedReader reader = null;

        try {
            in = context.openFileInput(MainActivity.FILE_NAME);
            input = new InputStreamReader(in);
            reader = new BufferedReader(input);

            list = new ArrayList<String>();
            String temp = null;
            while ((temp = reader.readLine()) != null) {
                list.add(temp);
                Thread.sleep(250);
            }

            ArrayAdapter<String> tempAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list);
            adapter = tempAdapter;
            return adapter;
        }
        catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        catch (InterruptedException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        finally {
            if (reader != null) {
                reader = null;
            }
            if (input != null) {
                input = null;
            }
        }

        return null;
    }

    protected ArrayAdapter<String> onPostExectue(ArrayAdapter<String> tempAdapter) {
        Activity activity = new Activity();
        ListView list = (ListView)activity.findViewById(R.id.list);
        list.setAdapter(tempAdapter);

        return adapter;

    }
}