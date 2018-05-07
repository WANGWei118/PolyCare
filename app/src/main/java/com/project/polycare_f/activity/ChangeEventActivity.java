package com.project.polycare_f.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.project.polycare_f.R;

import java.util.ArrayList;
import java.util.List;

public class ChangeEventActivity extends AppCompatActivity{
    List<String> urgences = new ArrayList<>();
    List<String> categories = new ArrayList<>();
    List<String> states = new ArrayList<>();
    private ArrayAdapter<String> adapterUr;
    private ArrayAdapter<String> adapterCate;
    private ArrayAdapter<String> adapterSta;
    EditText prenom, title,description, number;
    Spinner urg, cate, state;
    String arthor, titre, des, phone, importance, etat, category;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.change_event_item_land);
        }
        else {
            setContentView(R.layout.change_event_item);
        }

        prenom = (EditText) findViewById(R.id.prenom_input);
        title = (EditText) findViewById(R.id.title_input);
        description = (EditText) findViewById(R.id.description_input);
        number = (EditText) findViewById(R.id.phone_input);

        Intent intent = getIntent();
        arthor = intent.getExtras().getString("Name");
        titre = intent.getExtras().getString("Title");
        des =intent.getExtras().getString("Description");
        phone = intent.getExtras().getString("Phone");
        importance = intent.getExtras().getString("Importance");
        category = intent.getExtras().getString("Category");


        prenom.setText(arthor);
        title.setText(titre);
        description.setText(des);
        number.setText(phone);

        createCategorySpinner();
        createUrgenceSpinner();
    }


    private void createUrgenceSpinner() {
        urgences.add(importance);

        urg = (Spinner) findViewById(R.id.Spinner_urgence);
        //第二步：为下拉列表定义一个适配器，这里就用到里前面定义的list。
        adapterUr = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, urgences);
        //第三步：为适配器设置下拉列表下拉时的菜单样式。
        adapterUr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        urg.setAdapter(adapterUr);
        //第五步：为下拉列表设置各种事件的响应，这个事响应菜单被选中
        urg.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
               // importance = urg.getSelectedItem().toString();
            }

            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
        /*下拉菜单弹出的内容选项触屏事件处理*/
        urg.setOnTouchListener(new Spinner.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                return false;
            }
        });
        /*下拉菜单弹出的内容选项焦点改变事件处理*/
        urg.setOnFocusChangeListener(new Spinner.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub

            }
        });
    }

    private void createCategorySpinner() {
        categories.add(category);

        cate = (Spinner) findViewById(R.id.Spinner_category);
        //第二步：为下拉列表定义一个适配器，这里就用到里前面定义的list。
        adapterCate = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        //第三步：为适配器设置下拉列表下拉时的菜单样式。
        adapterCate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        cate.setAdapter(adapterCate);
        //：为下拉列表设置各种事件的响应，这个事响应菜单被选中
        cate.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
              //  category = cate.getSelectedItem().toString();
            }

            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
        /*下拉菜单弹出的内容选项触屏事件处理*/
        cate.setOnTouchListener(new Spinner.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                return false;
            }
        });
        /*下拉菜单弹出的内容选项焦点改变事件处理*/
        cate.setOnFocusChangeListener(new Spinner.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub

            }
        });
    }

    public void onclick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_add_alert_black_24dp);//设置图标
        builder.setTitle("Validation de la modification");//设置对话框的标题
        builder.setMessage("Vous voulez applique la modification？");//设置对话框的内容
        builder.setPositiveButton("Appliquer", new DialogInterface.OnClickListener() {  //这个是设置确定按钮

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
//                List<String> strings = getInput();
//                String sql = "\n" +
//                        "INSERT INTO events (event_title, event_category, event_description,event_reporter ," +
//                        "event_importance,event_state, event_date,event_number )\n" +
//                        "VALUES('"+strings.get(0) + "','" + cate + "','"+ strings.get(1) +"','" + strings.get(4) + "','" +
//                        urg + "','" + strings.get(5) + "','" + strings.get(3) + "','" + strings.get(2) + "');";
//                Log.i(ACTIVITY_TAG, sql);
//                inertOrUpdateDateBatch(sql);
                Toast.makeText(ChangeEventActivity.this, "Réussi", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(ChangeEventActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {  //取消按钮

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(ChangeEventActivity.this, "Annuler", Toast.LENGTH_SHORT).show();

            }
        });
        AlertDialog b = builder.create();
        b.show();  //必须show一下才能看到对话框，跟Toast一样的道理

    }

}
