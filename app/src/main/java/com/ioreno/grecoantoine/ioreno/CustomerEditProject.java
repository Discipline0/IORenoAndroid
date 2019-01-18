package com.ioreno.grecoantoine.ioreno;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;

import DBManager.DBSQLiteManager;
import Model.Customer;
import Model.Project;

public class CustomerEditProject extends AppCompatActivity {
    EditText editTitleIn;
    EditText editDescriptionIn;
    Spinner  editTypeIn;
    EditText editAddressIn;
    EditText editCityIn;
    EditText editBudgetIn;
    ImageView editImageIn;
    Project project;
    public final static int IMAGE_PICKER =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit_project);

        editTitleIn = (EditText) findViewById(R.id.editEditProjectTitle);
        editDescriptionIn = (EditText) findViewById(R.id.editEditProjectDescription);
        editTypeIn = (Spinner)findViewById(R.id.spinnerEditProjectType);
        editAddressIn = (EditText) findViewById(R.id.editEditProjectAddress);
        editCityIn = (EditText)findViewById(R.id.editEditProjectCity);
        editBudgetIn = (EditText) findViewById(R.id.numEditProjectBudget);
        editImageIn = (ImageView) findViewById(R.id.imgEditProjectImage);

        //sticky
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if(extras != null){
            project =(Project) extras.getSerializable("project");
            editTitleIn.setText(project.getTitle());
            editDescriptionIn.setText(project.getProjectDescription());
            String spinnerVal = project.getProjectType();

            //sticky spinner
            Context context = getApplicationContext();
            String[] spinnerVals =  context.getResources().getStringArray(R.array.type_arrays);
            int counter = 0;
            for(String val : spinnerVals){
                if(val.equals(project.getProjectType())){
                    editTypeIn.setSelection(counter);
             }
             counter++;
         }
            editAddressIn.setText(project.getAddress());
            editCityIn.setText(project.getCity());
            editBudgetIn.setText("" + project.getProjectBudget());
            // Converting the Byte Array into Bitmap -----------------------------------------------
            Bitmap bmp = BitmapFactory.decodeByteArray(project.getImage(), 0, project.getImage().length);
            editImageIn.setImageBitmap(bmp);
        }
    }


    public void onEditGalleryPicker(View v){
        Intent getImageIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getImageIntent.setType("image/*");
        startActivityForResult(getImageIntent , IMAGE_PICKER );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode== IMAGE_PICKER  && resultCode == RESULT_OK) {
            Uri fullPhotoUri = data.getData();
            ImageView projImageIn = (ImageView) findViewById(R.id.imgEditProjectImage);
            projImageIn.setImageURI(fullPhotoUri);
        }
    }


    public void onEditProjectConfirm(View v) {
        TextView editErrorIn = (TextView)findViewById(R.id.txtEditProjectError);
        editErrorIn.setSingleLine(false);

        String editTitle = editTitleIn.getText().toString().trim();
        String editDescription = editDescriptionIn.getText().toString().trim();
        String editType = editTypeIn.getSelectedItem().toString();
        String editAddress = editAddressIn.getText().toString().trim();
        String editCity = editCityIn.getText().toString().trim();
        String editBudget = editBudgetIn.getText().toString().trim();

        //imageview to bitmap to bype[]
        Bitmap bitmap = ((BitmapDrawable) editImageIn.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageInByte = baos.toByteArray();

        //save your stuff

        DBSQLiteManager db = new DBSQLiteManager(this);

        String error= "";

        if (TextUtils.isEmpty(editTitle)) {
            error+="\nTitle can't be empty";

        }
        else if(editTitle.length() <= 3){
            error+="\nTitle must be at least 3 characters";
        }

        if(TextUtils.isEmpty(editDescription)){
            error+="\nPlease add a Description";
        }

        if(TextUtils.isEmpty(editAddress)){
            error+="Address can't be empty";
        }

        if(TextUtils.isEmpty(editCity)){
            error+="\nCity can't be empty";
        }
        double editBudgetParsed =0;
        try{
            editBudgetParsed =  Double.parseDouble(editBudgetIn.getText().toString().trim());
        }
        catch(Exception e){
            error+= "Invalid Budget";
        }
        if(error.equals("")){
            Project p = new Project(project.getProjectID() ,Customer.currUser, editDescription, editType, editBudgetParsed, editTitle, editAddress, editCity, imageInByte);
            db.editProject(p);
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Project: "+p.getTitle()+" has sucessfully been edited!",
                    Toast.LENGTH_SHORT);
            toast.show();

            Intent i = new Intent(this, CustomerHome.class);
            startActivity(i);


        }
        else{
            editErrorIn.setText(error);
        }
    }

    public void onEditGoBack(View v){
        super.finish();
    }

}
