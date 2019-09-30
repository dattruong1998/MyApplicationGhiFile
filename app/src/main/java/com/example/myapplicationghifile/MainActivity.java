package com.example.myapplicationghifile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
     EditText et_filename;
     EditText et_noidung;
     Button bt_NhapMoi,bt_Luu,bt_Mo;
     Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_filename=(EditText)findViewById(R.id.editTextFile);
        et_noidung=(EditText)findViewById(R.id.editTextNoiDung);
        spinner=(Spinner)findViewById(R.id.spinner);
        bt_NhapMoi=(Button)findViewById(R.id.buttonNhapMoi);
        bt_Luu=(Button)findViewById(R.id.buttonLuu);
        bt_Mo=(Button)findViewById(R.id.buttonMo);
        bt_NhapMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_filename.setText("");
                et_noidung.setText("");
            }
        });
        final List<String>list=new ArrayList<String>();
        list.add("Hello");
        ArrayAdapter<String>adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                et_filename.setText(list.get(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        bt_Luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename=et_filename.getText().toString();
                list.add(filename);
                try {
//                    FileOutputStream fout=openFileOutput(filename, Context.MODE_PRIVATE);
//                    fout.write(et_noidung.getText().toString().getBytes());
 //                   fout.close();
                    //cache
                    File newCacheFile=new File(getCacheDir(),filename);
                    newCacheFile.createNewFile();
                    FileOutputStream fout=new FileOutputStream(newCacheFile.getAbsolutePath());
                    fout.write(et_noidung.getText().toString().getBytes());
                    fout.close();
                }
                catch (Exception e)
                {
                    Toast.makeText(MainActivity.this,"Loi luu file",Toast.LENGTH_SHORT);
                }

            }
        });
    bt_Mo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String filename=et_filename.getText().toString();
            String strCacheFileName=filename;
            File newCacheFile=new File(getCacheDir(),strCacheFileName);
            try {
                Scanner sc=new Scanner(newCacheFile);
                String data="";
                while ((sc.hasNext()))
                {
                    data+=sc.nextLine();
                }
                et_noidung.setText(data);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

//            StringBuffer buffer=new StringBuffer();
//            String line=null;
//            try {
//                FileInputStream fin=openFileInput(filename);
//                BufferedReader br=new BufferedReader(new InputStreamReader(fin));
//                while((line=br.readLine())!=null)
//                {
//                    buffer.append(line+"\n");
//                    et_noidung.setText(buffer.toString());
//                }
//            }
//            catch (Exception e)
//            {
//
//            }
        }
    });
    }



}
