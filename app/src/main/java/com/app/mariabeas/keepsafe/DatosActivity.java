package com.app.mariabeas.keepsafe;

import android.app.AlertDialog;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Maria on 02/02/2016.
 */
public class DatosActivity extends AppCompatActivity {
    ImageView logo;
    ImageView avatar;
    EditText edtUser;
    EditText edtNombre;
    EditText edtApellido;
    EditText edtFecha;
    EditText edtSexo;
    EditText edtSangre;
    EditText edtNum;
    final int CAMERA_REQUEST;
    final int SELECT_FILE;

    LoginDataBaseAdapter loginDBAdapter;
    private AdapterUsuario adaptador;

    public DatosActivity() {
        SELECT_FILE = 1;
        CAMERA_REQUEST = 1888;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos);

        //Crear una instancia de SQLiteDataBase
        loginDBAdapter = new LoginDataBaseAdapter(this);
        loginDBAdapter = loginDBAdapter.open();

        adaptador=new AdapterUsuario(this);


        //ELEMENTOS DE LA INTERFAZ
        Button btnCambiar=(Button)findViewById(R.id.btnCambiar);
        Button btnFoto=(Button)findViewById(R.id.btnFoto);
        Button btnGuardar=(Button)findViewById(R.id.btnGuardar);
        logo=(ImageView)findViewById(R.id.logo);
        avatar=(ImageView)findViewById(R.id.avatarGuardado);
        edtUser=(EditText)findViewById(R.id.edtUser);
        edtNombre=(EditText)findViewById(R.id.edtNombreAgenda);
        edtApellido=(EditText)findViewById(R.id.edtApellido);
        edtFecha=(EditText)findViewById(R.id.edtFecha);
        edtSexo=(EditText)findViewById(R.id.edtSexo);
        edtSangre=(EditText)findViewById(R.id.edtSangre);
        edtNum=(EditText)findViewById(R.id.edtNum);

        MiListener listener=new MiListener();
        btnCambiar.setOnClickListener(listener);
        btnGuardar.setOnClickListener(listener);
        btnFoto.setOnClickListener(listener);
    }




    private class MiListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            String usuario=((EditText)findViewById(R.id.edtUser)).getText().toString();
            String nombre=((EditText)findViewById(R.id.edtNombreAgenda)).getText().toString();
            String apellido=((EditText)findViewById(R.id.edtApellido)).getText().toString();
            String fecha=((EditText)findViewById(R.id.edtFecha)).getText().toString();
            String sexo=((EditText)findViewById(R.id.edtSexo)).getText().toString();
            String sangre=((EditText)findViewById(R.id.edtSangre)).getText().toString();
            String num=((EditText)findViewById(R.id.edtNum)).getText().toString();
            String foto=((Button)findViewById(R.id.btnFoto)).getText().toString();

            if(v.getId()==R.id.btnGuardar){
                //comprobar que los campos no estan vacios
                if (nombre.equals(null) || usuario.equals(null) || apellido.equals(null) ||
                        fecha.equals(null) || sexo.equals(null) || sangre.equals(null) || num.equals(null)) {
                    Toast.makeText(getApplicationContext(), "Completa los datos", Toast.LENGTH_SHORT).show();
                    return;

            } else{
                    LoginDataBaseAdapter.updateEntry(usuario, nombre, apellido, fecha, sexo, sangre, num);
                    Toast.makeText(getApplicationContext(), "Datos modificados", Toast.LENGTH_SHORT).show();
                    //GUARDAR DATOS

                    Intent intentGuardar=new Intent (DatosActivity.this,DatosGuardadosActivity.class);
                    intentGuardar.putExtra("nombre",edtNombre.getText().toString());
                    intentGuardar.putExtra("email",edtUser.getText().toString());
                    intentGuardar.putExtra("apellido",edtApellido.getText().toString());
                    intentGuardar.putExtra("fecha",edtFecha.getText().toString());
                    intentGuardar.putExtra("sexo",edtSexo.getText().toString());
                    intentGuardar.putExtra("sangre",edtSangre.getText().toString());
                    intentGuardar.putExtra("numSeguridad",edtNum.getText().toString());
                    intentGuardar.putExtra("image",R.drawable.avatar);
                    startActivity(intentGuardar);

                }
                if(v.getId()==R.id.btnCambiar) {
                    //PARA PASAR DE UNA PANTALLA A OTRA
                    Intent intentactivity = new Intent(DatosActivity.this, CambiarPassActivity.class);
                    startActivity(intentactivity);
                }
            }else if(v.getId()==R.id.btnFoto){
                //SELECCIONAR FOTO
                selectImage();
            }
        }
    }
    private void selectImage(){
        final CharSequence[] items = { "Hacer foto", "Acceder al carrete", "Cancelar" };
        AlertDialog.Builder builder = new AlertDialog.Builder(DatosActivity.this);
        builder.setTitle("Añadir foto");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Hacer foto")) {
                    //final int CAMERA_REQUEST = 1888;
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,CAMERA_REQUEST);
                } else if (items[item].equals("Acceder al carrete")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    //REVISAR!!!
                    //final int SELECT_FILE=1888;
                    startActivityForResult(Intent.createChooser(intent, "Seleccionar foto"),SELECT_FILE);
                } else if (items[item].equals("Cancelar")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
           // final int CAMERA_REQUEST = 1888;
            if (requestCode == CAMERA_REQUEST) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                File destination = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                avatar.setImageBitmap(thumbnail);
               // final int SELECT_FILE=1888;
            } else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();
                String[] projection = {MediaStore.MediaColumns.DATA};
                CursorLoader cursorLoader = new CursorLoader(this, selectedImageUri, projection, null, null,
                        null);
                Cursor cursor = cursorLoader.loadInBackground();
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();
                String selectedImagePath = cursor.getString(column_index);
                Bitmap bm;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(selectedImagePath, options);
                final int REQUIRED_SIZE = 200;
                int scale = 1;
                while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                        && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                    scale *= 2;
                options.inSampleSize = scale;
                options.inJustDecodeBounds = false;
                bm = BitmapFactory.decodeFile(selectedImagePath, options);
                avatar.setImageBitmap(bm);
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //CERRAR LA DB
        loginDBAdapter.close();
    }
}
