package com.trinhnguyenvyna.d0_4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.trinhnguyenvyna.d0_4.Adapter.Adapterthis;
import com.trinhnguyenvyna.d0_4.databinding.ActivitySqlBinding;
import com.trinhnguyenvyna.d0_4.models.Books;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

public class SQLTest extends AppCompatActivity {
    ActivitySqlBinding binding;
    Adapterthis adapter;
    ArrayList<Books> book;
    Database db;
    private Context context;
    private BreakIterator txtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySqlBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prepareDb();
        loadData();
    }

    private void loadData() {
        adapter = new Adapterthis(SQLTest.this, R.layout.list_item, getDataFromDb());
        binding.lvBooks.setAdapter(adapter);
        addEvent();

    }


    private List<Books> getDataFromDb() {
        book = new ArrayList<>();
        Cursor cursor = db.queryData("SELECT * FROM " + Database.TBL_NAME);
        while (cursor.moveToNext()){
            book.add(new Books(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getDouble(2)
            ));
        }
        cursor.close();
        return book;
    }
/*    public void openEditDialog(Books p){
        //Log.i("test", "ok");
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_edit);

        EditText edtName = dialog.findViewById(R.id.editName);
        edtName.setText(p.getbookName());

        EditText edtPrice = dialog.findViewById(R.id.editPrice);
        edtPrice.setText(String.valueOf(p.getbookPrice()));

        Button btnSave = dialog.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                Double price = Double.valueOf(edtPrice.getText().toString());
                db.execSql("UPDATE " + Database.TBL_NAME
                        + " SET " +
                        Database.COL_NAME + "='" + name + "', "
                        + Database.COL_PRICE + "=" + price 
                        + " WHERE " + Database.COL_CODE + "=" + p.getbookCode());

                loadData();
                dialog.dismiss();
            }
        });
        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

/*    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuAdd){
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_add);

            EditText edtCode = dialog.findViewById(R.id.editCode);
            EditText edtName = dialog.findViewById(R.id.editName);
            EditText edtPrice = dialog.findViewById(R.id.editPrice);

            Button btnSave = dialog.findViewById(R.id.btnSave);
            btnSave.setOnClickListener(new View.OnClickListener() {
                private SQLiteDatabase writableDatabase;

                public void setWritableDatabase(SQLiteDatabase writableDatabase) {
                    this.writableDatabase = writableDatabase;
                }

                public SQLiteDatabase getWritableDatabase() {
                    return writableDatabase;
                }

                @Override
                public void onClick(View v) {
                    //Insert
                    String code = edtCode.getText().toString();
                    String name = edtName.getText().toString();
                    Double price = Double.valueOf(edtPrice.getText().toString());

                    db.execSql("INSERT INTO " + Database.TBL_NAME + " VALUES('"+ code +"','" + name + "', " + price + ")");
                    loadData();
                    dialog.dismiss();
                }
            });
            Button btnCancel = dialog.findViewById(R.id.btnCancel);
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }*/

 /*   public void openDeleteConfirmDialog(Books p){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận xoá ");
        builder.setMessage("Bạn có chắc muốn xóa book '" + p.getbookName() + "'?");

        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                *//* db.execSql("DELETE FROM " + Database.TBL_NAME + " WHERE " + Database.COL_CODE + " = " + p.getbookCode());*//*
                SQLiteDatabase db = SQLTest.this.db.getWritableDatabase();
                db.delete(Database.TBL_NAME, Database.COL_CODE + " = ?", new String[]{String.valueOf(p.getbookCode())});
                loadData();
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        Dialog dialog=builder.create();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }
*/
    private void prepareDb() {
        db = new Database(SQLTest.this);
        db.createSampleData();
    }



    //new
    private void addEvent() {
        binding.lvBooks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Books bookSelected = (Books) adapter.getItem(position);

               /* Dialog dialog = new Dialog(SQLTest.this);
                dialog.setContentView(R.layout.dialog_add);

                //event
                Button btnBack = dialog.findViewById(R.id.btnBack);
                TextView txtCode = dialog.findViewById(R.id.editCode);
                TextView txtName = dialog.findViewById(R.id.editName);
                TextView txtPrice = dialog.findViewById(R.id.editPrice);

                txtCode.setText(bookSelected.getbookCode());
                txtName.setText(bookSelected.getbookName());
                txtPrice.setText(String.valueOf(bookSelected.getbookPrice()));*/

                Dialog dialog = new Dialog(SQLTest.this);
                dialog.setContentView(R.layout.dialog_add);

                Button btnBack = dialog.findViewById(R.id.btnBack);
                EditText edtCode = dialog.findViewById(R.id.editCode);
                EditText edtName = dialog.findViewById(R.id.editName);
                EditText edtPrice = dialog.findViewById(R.id.editPrice);

                Button btnSave = dialog.findViewById(R.id.btnSave);
                btnSave.setOnClickListener(new View.OnClickListener() {
                    private SQLiteDatabase writableDatabase;

                    public void setWritableDatabase(SQLiteDatabase writableDatabase) {
                        this.writableDatabase = writableDatabase;
                    }

                    public SQLiteDatabase getWritableDatabase() {
                        return writableDatabase;
                    }

                    @Override
                    public void onClick(View v) {
                        //Insert
                        String code = edtCode.getText().toString();
                        String name = edtName.getText().toString();
                        Double price = Double.valueOf(edtPrice.getText().toString());

                        db.execSql("INSERT INTO " + Database.TBL_NAME + " VALUES('"+ code +"','" + name + "', " + price + ")");
                        loadData();
                        dialog.dismiss();
                    }
                });

                btnBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                // show dialog
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setGravity(Gravity.BOTTOM);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                return true; // Return true to indicate that the callback consumed the long click
            }
        });
    }


      /*  EditText txtSearch = findViewById(R.id.txtSearch);
        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchText = s.toString();
                if (searchText.isEmpty()) {
                    loadData();
                } else if (searchText.matches("\\d+(\\.\\d+)?")) {
                    double price = Double.parseDouble(searchText);
                    *//*  searchbookByPrice(price);*//*
                } else {
                  *//*  searchbookByName(searchText);*//*
                }
            }
        });*/
    //}







/*private List<Books> searchbookByPrice(double price) {
            List<Books> bookList = new ArrayList<>();
            Cursor cursor = db.execSql("SELECT * FROM " + TBL_NAME + " WHERE " + COL_PRICE + " BETWEEN " + minPrice + " AND " + maxPrice);
            while (cursor.moveToNext()) {
                bookList.add(new Books(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getDouble(3),
                        cursor.getString(4)
                ));
            }
            cursor.close();
            return bookList;
        }*/



   /* private List<Books> searchbookByName(String searchText) {
        List<Books> bookList = new ArrayList<>();
        String name = txtSearch.getText().toString();
        db.execSql("SELECT * FROM " + Database.TBL_NAME + " WHERE " + Database.COL_NAME + " LIKE '%" + name + "%'");
       *//* while (cursor.moveToNext()) {
            bookList.add(new Books(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getDouble(3),
                    cursor.getString(4)
            ));
        }
        cursor.close();*//*
        loadData();
        return bookList;
    }*/
}