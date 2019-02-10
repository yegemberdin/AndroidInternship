package com.example.lab1;

import android.app.Dialog;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final int DIALOG_EXIT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn2=(Button)findViewById(R.id.button2);
        btn2.setOnLongClickListener(new View.OnLongClickListener(){
            public boolean onLongClick(View view) {
                showDialog(DIALOG_EXIT);
                return false;
            }
        });
    }

    public void button1Click(View view){
        EditText editText = (EditText) findViewById(R.id.editText);
        String name =  editText.getText().toString();

        Intent i=new Intent (this,Main2Activity.class);
        i.putExtra("Value1","Hey "+name );

        startActivity(i);
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_EXIT) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            // заголовок
            adb.setTitle(R.string.delete);
            // сообщение
            adb.setMessage(R.string.delete_button);
            // кнопка положительного ответа
            adb.setPositiveButton(R.string.yes, myClickListener);
            // кнопка отрицательного ответа
            adb.setNegativeButton(R.string.no, myClickListener);
            // создаем диалог
            return adb.create();
        }
        return super.onCreateDialog(id);
    }

    OnClickListener myClickListener = new OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                // положительная кнопка
                case Dialog.BUTTON_POSITIVE:
                    deleteButton();

                    break;
                // негативная кнопка
                case Dialog.BUTTON_NEGATIVE:

                    break;

            }
        }
    };

    void deleteButton() {

        Button btn2=(Button)findViewById(R.id.button2);
        btn2.setVisibility(View.INVISIBLE);

    }
}
