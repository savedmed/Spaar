package com.example.sasha.spaar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ProductSelectionActivity extends ActionBarActivity {

    List<String> products = new ArrayList<String>();

    String editTextProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_selection);

        products.add("Butter");
        products.add("Milk");
        //get data from main activity
        editTextProduct = getIntent().getStringExtra("product_key");
        products.add(editTextProduct);

        ListView listView = (ListView)findViewById(R.id.lvProducts);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, products);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String chosedLitViewValue = ((TextView)view).getText().toString();
                Toast.makeText(getApplicationContext(), chosedLitViewValue, Toast.LENGTH_SHORT);

                Intent backToMainActivityIntent = new Intent(ProductSelectionActivity.this, MainActivity.class);
                backToMainActivityIntent.putExtra("SELECTED_VALUE", chosedLitViewValue);
                setResult(RESULT_OK, backToMainActivityIntent);
                //finishActivity(RESULT_OK);
                finish();
                /*Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();*/
            }
        });
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chosedLitViewValue = ((TextView)view).getText().toString();
                Toast.makeText(getApplicationContext(), chosedLitViewValue, Toast.LENGTH_SHORT);

                Intent backToMainActivityIntent = new Intent(ProductSelectionActivity.this, MainActivity.class);
                backToMainActivityIntent.putExtra("SELECTED_VALUE", chosedLitViewValue);
                setResult(RESULT_OK, backToMainActivityIntent);
                finishActivity(RESULT_OK);
                //startActivity(backToMainActivityIntent);
            }
        });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
