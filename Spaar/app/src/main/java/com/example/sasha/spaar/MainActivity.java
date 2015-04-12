package com.example.sasha.spaar;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private List<String> productList = new ArrayList<String>();
    private Button addToListBtn;
    private EditText productInput;
    private LinearLayout mainRelativeLayout;

    private static final String TAG = "MainActivity";

    RelativeLayout.LayoutParams lparams = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addToListBtn = (Button)findViewById(R.id.listBtn);
        productInput = (EditText)findViewById(R.id.productInput);
        mainRelativeLayout = (LinearLayout)findViewById(R.id.prdListLayout);

        addToListBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String textEditProduct = productInput.getText().toString();
                if(textEditProduct.isEmpty()){
                    Toast.makeText(MainActivity.this, R.string.empty_input, Toast.LENGTH_SHORT).show();
                }

                Intent openProductSelectionActivity = new Intent(getApplicationContext(), ProductSelectionActivity.class);
                //passing data from EditText to ProductSelectionActivity
                openProductSelectionActivity.putExtra("product_key", textEditProduct);
                startActivityForResult(openProductSelectionActivity, 0);
        }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == Activity.RESULT_CANCELED){
            //When we click back button
            Toast.makeText(this, "Result canceled", Toast.LENGTH_LONG);
        }
        else {
            String result = data.getStringExtra("SELECTED_VALUE");
            productList.add(result);
            TextView newTextView = new TextView(MainActivity.this);
            newTextView.setLayoutParams(lparams);
            newTextView.setText(result);
            MainActivity.this.mainRelativeLayout.addView(newTextView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /**
     * Created by Sasha on 12.04.2015.
     */
    public static class Uploader {

    }
}
