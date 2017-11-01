package com.example.andritom.contohtugas4;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static int notifikasi_id = 1;
    private Button btndialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btndialog = (Button) findViewById(R.id.btndialog);
        btndialog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tampilkanDialog();
            }
        });
    }

    public void tampilNotifikasi(View v)
    {
        notifikasi_id += 1;
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher)                  // icon notifikasi
                .setContentTitle("Notifikasi baruuu!!")             // judul notif
                .setAutoCancel(true)                                // utk menswipe atau menghapus notifikasi
                .setContentText("Haloo ada notifikasi baru nihh");  // isi teks notifikasi

        /* Kemudian kita harus menambahkan notification dengan menggunakan NotificationManager*/
        NotificationManager notifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notifManager.notify(notifikasi_id, builder.build());
    }

    public void tampilNotifikasiWithIntent(View v)
    {
        notifikasi_id += 1;

        // meng-inisialisasi intent
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://banyumasmemilih.com"));

        // untuk memanggil intent di notification
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher)                      // icon notifikasi
                .setContentTitle("Notifikasi dengan Intent baruuu!!")   // judul notif
                .setContentIntent(pendingIntent)                        // memenggil object pending intent
                .setAutoCancel(true)                                    // utk menswipe atau menghapus notifikasi
                .setContentText("Haloo, klik aku dong..");              // isi teks notifikasi

        /* Kemudian kita harus menambahkan notification dengan menggunakan NotificationManager*/
        NotificationManager notifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notifManager.notify(notifikasi_id, builder.build());
    }

    public void tampilkanDialog()
    {
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);

        // set title dialog
        alertdialog.setTitle("Keluar dari aplikasi?");

        // set pesan dari dialog
        alertdialog.setMessage("Klik Ya untuk keluar")
                    .setIcon(R.mipmap.ic_launcher)
                    .setCancelable(false)
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // jika tombol ya di klik maka akan menutup activity
                            MainActivity.this.finish();
                        }
                    })
                    .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // jika tidak maka dialog tertutup dan tidak terjadi apa-apa
                            dialog.cancel();
                        }
                    });
        // membuat alert dialog dari builder
        AlertDialog tampildialog = alertdialog.create();
        tampildialog.show();
    }


}
