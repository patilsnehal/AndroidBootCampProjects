package com.yahoo.snehalpatil.todoapp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemLongClickListener;

public class TodoActivity extends Activity {
	public EditText etEditText;
	ListView lvListView;
	ArrayList<String> items;
	ArrayAdapter<String> itemAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        lvListView = (ListView) findViewById(R.id.lvTodoItems);
        readItems();
        itemAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lvListView.setAdapter(itemAdapter);
        setupListViewListener();
    }
    
    public void setupListViewListener () {
    	lvListView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
            	items.remove(pos);
            	// Write to the file
            	saveItems();
            	itemAdapter.notifyDataSetChanged();
            	          	
                if (items.size() == 0)
                {
                	System.out.println("list is empty");
                }
 
            	return true;
            }
        });
    }
    
    // read items from the file and load them into items
    private void readItems()
    {
        File fileDir = getFilesDir();
        File todoFile = new File(fileDir, "todo.txt");
        try {
        	items = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
        	items = new ArrayList<String>();
        }
    }
    
    // save items from the file and load them into items
    private void saveItems ()
    {
        File fileDir = getFilesDir();
        File todoFile = new File(fileDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void addTodoItems(View v) {
		// get the text from the EditText, check for empty string, if not empty, add it to the list
    	
    	EditText etEditText = (EditText) findViewById(R.id.etNewItem);
    	String str = etEditText.getText().toString();
    	if (str!=null && !str.isEmpty())
    		itemAdapter.add(str);
    	etEditText.setText("");

    	// Write to the file
    	saveItems();
	}
    
}
