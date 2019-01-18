package com.ioreno.grecoantoine.ioreno;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ioreno.grecoantoine.ioreno.Fragments.about_frag;
import com.ioreno.grecoantoine.ioreno.Fragments.contact_frag;
import com.ioreno.grecoantoine.ioreno.Fragments.index_frag;
import com.ioreno.grecoantoine.ioreno.Fragments.terms_of_use_frag;

import java.io.ByteArrayOutputStream;

import DBManager.DBSQLiteManager;
import Model.Customer;
import Model.Project;

public class CustomerCreateNewProject extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    public static final int IMAGE_PICKER = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_create_new_project);

        EditText projBudgetIn      = (EditText) findViewById(R.id.numCreateNewProjectBudget);
        projBudgetIn.setInputType(InputType.TYPE_CLASS_NUMBER);
 /*       NavigationView nv = findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);*/

    }


    public void onGalleryPicker(View v){
        Intent getImageIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getImageIntent.setType("image/*");
        startActivityForResult(getImageIntent , IMAGE_PICKER );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode== IMAGE_PICKER  && resultCode == RESULT_OK) {
            Uri fullPhotoUri = data.getData();
            ImageView projImageIn = (ImageView) findViewById(R.id.imgProjectImage);
            projImageIn.setImageURI(fullPhotoUri);
        }
    }


    public void onGoBack(View v){
        super.finish();
    }
    public void onConfirm(View v){
        EditText projTitleIn       = (EditText)findViewById(R.id.editCreateNewProjectTitle);
        EditText projDescriptionIn = (EditText) findViewById(R.id.editCreateNewProjectDescription);
        Spinner  projTypeIn        = (Spinner)findViewById(R.id.spinnerCreateNewProjectType);
        EditText projAddressIn     = (EditText) findViewById(R.id.editCreateNewProjectAddress);
        EditText projCityIn        = (EditText) findViewById(R.id.editCreateNewProjectCity);
        EditText projBudgetIn      = (EditText) findViewById(R.id.numCreateNewProjectBudget);
        ImageView projImageIn      = (ImageView) findViewById(R.id.imgProjectImage);


        TextView projErrorIn       = (TextView)findViewById(R.id.txtCreateNewProjectError);
        projErrorIn.setSingleLine(false);

        String projTitle      = projTitleIn.getText().toString().trim();
        String projDescription = projDescriptionIn.getText().toString().trim();
        String projType       = projTypeIn.getSelectedItem().toString();
        String projAddress    = projAddressIn.getText().toString().trim();
        String projCity       = projCityIn.getText().toString().trim();
        double projBudget     = 0;

        //imageview to bitmap to byte[]
        Bitmap bitmap = ((BitmapDrawable) projImageIn.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageInByte = baos.toByteArray();
        //save your stuff

        DBSQLiteManager db = new DBSQLiteManager(this);

        String error = "";

        if (TextUtils.isEmpty(projTitle)) {
            error+="\nTitle can't be empty";

        }
        else if(projTitle.length() <= 3){
            error+="\nTitle must be at least 3 characters";
        }

        if(TextUtils.isEmpty(projDescription)){
            error+="\nPlease add a Description";
        }

        if(TextUtils.isEmpty(projAddress)){
            error+="\nAddress can't be empty";
        }

        if(TextUtils.isEmpty(projCity)){
            error+="\nCity can't be empty";
        }
        try{
            projBudget = Double.parseDouble(projBudgetIn.getText().toString().trim());
        }
        catch(Exception e){
            error+= "\nInvalid Budget";
        }

        if(error.equals("")){
            //id set 0, it will be updated when inserted in db
            Project p = new Project(0, Customer.currUser, projDescription, projType, projBudget, projTitle, projAddress, projCity, imageInByte);
            db.addProject(p);
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Project: "+p.getTitle()+" has successfully been created!",
                    Toast.LENGTH_SHORT);
            Intent i = new Intent(this, CustomerHome.class);
            startActivity(i);

            toast.show();
        }
        else{
            projErrorIn.setText(error);
        }
    }
}
