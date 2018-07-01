package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

public class DetailViewActivity extends Activity {

    private EditText numField, nameField, primaryField, addressField, provinceField;
    Business receivedBusinessInfo;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedBusinessInfo = (Business)getIntent().getSerializableExtra("Business");
        appState = ((MyApplicationData) getApplicationContext());

        numField = (EditText) findViewById(R.id.businessNum);
        nameField = (EditText) findViewById(R.id.businessName);
        primaryField = (EditText) findViewById(R.id.primaryBusiness);
        addressField = (EditText) findViewById(R.id.businessAddress);
        provinceField = (EditText) findViewById(R.id.province);

        if(receivedBusinessInfo != null){
            numField.setText(receivedBusinessInfo.businessNumber);
            nameField.setText(receivedBusinessInfo.businessName);
            primaryField.setText(receivedBusinessInfo.primaryBusiness);
            addressField.setText(receivedBusinessInfo.address);
            provinceField.setText(receivedBusinessInfo.province);

        }
    }
    /**
    *Method for updating business
    * @param v
    */
    public void updateBusiness(View v){
        String businessID = receivedBusinessInfo.uid;
        String businessNum = numField.getText().toString();
        String businessName = nameField.getText().toString();
        String primaryBusiness = primaryField.getText().toString();
        String businessAdd = addressField.getText().toString();
        String province = provinceField.getText().toString();


        Business updateBusiness = new Business(businessID,businessNum, businessName, primaryBusiness, businessAdd, province);

        appState.firebaseReference.child(businessID).setValue(updateBusiness);
        finish();
    }

    /**
    * Method for erasing a business
    * @param v
    */
    public void eraseBusiness(View v)
    {
        appState.firebaseReference.child(receivedBusinessInfo.uid).removeValue();
        finish();
    }
}
