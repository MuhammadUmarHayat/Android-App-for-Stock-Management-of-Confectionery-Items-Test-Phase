package com.example.fahadtestphase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminConsole extends AppCompatActivity
{
//etPName,etPackingDetails,etQty,etPPrice,etSPrice,etFN,etSearchPName
    //btnSaveProducts,btnViewAllProducts,btnSearchProduct,btnDeleteProduct
EditText etPName1,etPackingDetails1,etQty1,etPPrice1,etSPrice1,etFN1,etSearchPName1;
Button btnSaveProducts1,btnViewAllProducts1,btnSearchProduct1,btnDeleteProduct1 ;
ProductTable productTable;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_console);

        productTable=new ProductTable(this);

        etPName1=(EditText) findViewById(R.id.etPName);//etPName
        etPackingDetails1=(EditText) findViewById(R.id.etPackingDetails);//etPackingDetails

        etQty1=(EditText) findViewById(R.id.etQty);//etQty
        etPPrice1=(EditText) findViewById(R.id.etPPrice);//etPPrice
        etSPrice1=(EditText) findViewById(R.id.etSPrice);//etSPrice


        etFN1=(EditText) findViewById(R.id.etFN);//etFN
        etSearchPName1=(EditText) findViewById(R.id.etSearchPName);//etSearchPName

        btnSaveProducts1=(Button) findViewById(R.id. btnSaveProducts);//btnSaveProducts



        btnViewAllProducts1=(Button) findViewById(R.id. btnViewAllProducts);//btnViewAllProducts
        btnSearchProduct1=(Button) findViewById(R.id. btnSearchProduct);//btnSearchProduct
        btnDeleteProduct1=(Button) findViewById(R.id. btnDeleteProduct);//btnDeleteProduct

btnSaveProducts1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        SaveProducts();

    }
});

        btnViewAllProducts1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ViewAllProducts();
            }
        });

        btnSearchProduct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SearchProducts();

            }
        });

        btnDeleteProduct1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                DeleteProducts();

            }
        });






    }//on create


    //define methods


    private void SaveProducts()
    {
try
{
//etPName1,etPackingDetails1,etQty1,etPPrice1,etSPrice1,etFN1,etSearchPName1;
    String p = etPPrice1.getText().toString();
    int purchasing = Integer.parseInt(p);
    String s = etSPrice1.getText().toString();
    int sale = Integer.parseInt(s);
    String q = etQty1.getText().toString();
    int qty1 = Integer.parseInt(q);
//insertData(String productName,String Packing,int purchasePrice,int SalePrice,String Manufactures,int qty)
    boolean isInserted = productTable.insertData(
            etPName1.getText().toString(),
            etPackingDetails1.getText().toString(),
            purchasing, sale, etFN1.getText().toString(), qty1);

    if (isInserted == true)
        Toast.makeText(AdminConsole.this, "Product saved into table", Toast.LENGTH_LONG).show();
    else
        Toast.makeText(AdminConsole.this, "Eror: data is not saved", Toast.LENGTH_LONG).show();
}
catch(Exception exception)
        {

            Toast.makeText(AdminConsole.this, "Eror:"+exception.getMessage(), Toast.LENGTH_LONG).show();
        }


    }
    private void ViewAllProducts()
    {
        Cursor res = productTable.getAllData();
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found");
            return;
        }


        StringBuffer buffer = new StringBuffer();

        while (res.moveToNext()) {

            buffer.append("Id :" + res.getString(0) + "\n");
            buffer.append("Name :" + res.getString(1) + "\n");
            buffer.append("Packing :" + res.getString(2) + "\n");
            buffer.append("Purchase Price :" + res.getString(3) + "\n");

            buffer.append("Sale Price :" + res.getString(4) + "\n");
            buffer.append("Manufactures :" + res.getString(5) + "\n");
            buffer.append("Quantity :" + res.getString(6) + "\n");
        }//while
        showMessage("StockItems ",buffer.toString());

    }
    private void SearchProducts()
    {
        String id=etSearchPName1.getText().toString();
        Cursor res = productTable.searchData(id);
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found");
            return;
        }


        StringBuffer buffer = new StringBuffer();
//insertData(String productName,String Packing,int purchasePrice,int SalePrice,String Manufactures,int qty)
        while (res.moveToNext()) {

            buffer.append("Id :" + res.getString(0) + "\n");
            buffer.append("Name :" + res.getString(1) + "\n");
            buffer.append("Packing :" + res.getString(2) + "\n");
            buffer.append("Purchase Price :" + res.getString(3) + "\n");

            buffer.append("Sale Price :" + res.getString(4) + "\n");
            buffer.append("Manufactures :" + res.getString(5) + "\n");
            buffer.append("Quantity :" + res.getString(6) + "\n");

        }//while
        showMessage("StockItems ",buffer.toString());
//////////////
    }
    private void DeleteProducts()
    {
        String id=etSearchPName1.getText().toString();
        int result=productTable.deleteData(id);
        if(result > 0)
            Toast.makeText( AdminConsole.this,"Data Deleted",Toast.LENGTH_LONG).show();
        else
            Toast.makeText( AdminConsole.this,"Data is not Deleted",Toast.LENGTH_LONG).show();

    }

    public void showMessage(String title,String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





}
