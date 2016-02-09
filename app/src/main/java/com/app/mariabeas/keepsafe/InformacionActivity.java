package com.app.mariabeas.keepsafe;

import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.nearby.sharing.SharedContent;

/**
 * Created by Maria on 03/02/2016.
 */
public class InformacionActivity extends AppCompatActivity {
    ImageView logo;
    private static String idFB="1654873761420623";
    private FacebookSdk fb;
    /*
    * callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        // this part is optional
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() { ... });
    }*/
    CallbackManager cb;
    ShareDialog sh;
    Bitmap imgLogo;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        cb=CallbackManager.Factory.create();
        sh=new ShareDialog(this);

        setContentView(R.layout.informacion);

        //Declarar elementos de la interfaz
        logo=(ImageView)findViewById(R.id.logo);
        TextView info=(TextView)(findViewById(R.id.txtInfo));
        Button btnFB=(Button)(findViewById(R.id.btnFB));

        imgLogo= BitmapFactory.decodeResource(getApplicationContext().getResources(),R.id.logo);

        MiListener listener=new MiListener();
        btnFB.setOnClickListener(listener);


    }


    private class MiListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.btnFB) {
                SharePhoto foto=new SharePhoto.Builder().setBitmap(imgLogo).build();
                //SharePhotoContent contenido= new SharePhotoContent.Builder().addPhoto(imgLogo).build();

              //  ShareDialog.show(InformacionActivity.this, contenido);
                //PASAR A LA SIGUIENTE PANTALLA
               // Intent nuevoIntent = new Intent(InformacionActivity.this, InformacionActivity.class);
               // startActivity(nuevoIntent);
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle("Keep Safe")
                            .setContentDescription(
                                    "App para personas con discapacidad auditiva.")
                            .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
                            .build();

                    sh.show(linkContent);
                }

            }
        }
    }
}
