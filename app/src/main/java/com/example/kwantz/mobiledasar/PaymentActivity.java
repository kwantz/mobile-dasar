package com.example.kwantz.mobiledasar;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

<<<<<<< HEAD
import com.example.kwantz.mobiledasar.Model.Barang;
import com.example.kwantz.mobiledasar.Model.ListBarang;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
=======
>>>>>>> 0afd228037eed396057b21516e02ed5eca850bed

import static com.example.kwantz.mobiledasar.R.layout.dialog_waspada;


public class PaymentActivity extends AppCompatActivity {
    final Context context = this;
<<<<<<< HEAD
    private TextView waktu, detail, salinHarga, salinBca, salinMandiri, salinSyariah, salinBni, salinBri, detailTagihan;
    private ImageView closeDialog, closeButton;
    private Button detail_tagihan;
    private Barang barang;
    private String harga;
=======
    private TextView detail, salinHarga, salinBca, salinMandiri, salinSyariah, salinBni, salinBri, detailTagihan,
    judulAtm, judulInternet, judulMobile, salinVa;
    private ImageView closeDialog, closeButton, panahAtm, panahInternet, panahMobile;
    private Button detail_tagihan;
    private LinearLayout vaAtm, vaInternet, vaMobile, textVaAtm, textVaInternet, textVaMobile;
>>>>>>> 0afd228037eed396057b21516e02ed5eca850bed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        detail = (TextView) findViewById(R.id.detail);
        salinHarga = (TextView) findViewById(R.id.salin_harga);
        salinBca = (TextView) findViewById(R.id.salin_bca);
        salinMandiri = (TextView) findViewById(R.id.salin_mandiri);
        salinSyariah = (TextView) findViewById(R.id.salin_syariah);
        salinBni = (TextView) findViewById(R.id.salin_bni);
        salinBri = (TextView) findViewById(R.id.salin_bri);
        waktu = (TextView) findViewById(R.id.waktu);

        waktu.setText(getBatasWaktuPembayaran());

        this.barang = ListBarang.getBarangByImage(Integer.parseInt(getIntent().getExtras().getString("icon")));
        this.harga = getIntent().getExtras().getString("harga");

        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                dialog.setContentView(dialog_waspada);
                dialog.show();
                final ImageView closeDialog = dialog.findViewById(R.id.close_dialog);
                closeDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (dialog != null && dialog.isShowing())
                            dialog.dismiss();
                    }
                });

            }
        });

        salinHarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("nominal", "Seratus Ribu");
                clipboardManager.setPrimaryClip(clipData);

                Toast toast = Toast.makeText(PaymentActivity.this, "Berhasil salin nominal pembayaran", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        View.OnClickListener salinRek = new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(PaymentActivity.this, "Berhasil salin no rekening bank", Toast.LENGTH_SHORT);
                toast.show();
            }
        };

        salinBca.setOnClickListener(salinRek);
        salinMandiri.setOnClickListener(salinRek);
        salinSyariah.setOnClickListener(salinRek);
        salinBni.setOnClickListener(salinRek);
        salinBri.setOnClickListener(salinRek);

        salinVa = (TextView)findViewById(R.id.salin_va);
        salinVa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Toast toast = Toast.makeText(PaymentActivity.this, "Nomor Virtual Account berhasil disalin", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        detailTagihan = (TextView)findViewById(R.id.tv_detail_tagihan);
        detail_tagihan = (Button)findViewById(R.id.btn_detail_tagihan);
        View.OnClickListener ke_detail = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentActivity.this, DetailTagihanActivity.class);
                intent.putExtra("icon", Integer.toString(barang.getIcon()));
                startActivity(intent);
            }
        };
        detail_tagihan.setOnClickListener(ke_detail);
        detailTagihan.setOnClickListener(ke_detail);

        closeButton = (ImageView)findViewById(R.id.close_btn);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        vaAtm = (LinearLayout)findViewById(R.id.va_atm);
        vaInternet = (LinearLayout)findViewById(R.id.va_internet);
        vaMobile = (LinearLayout)findViewById(R.id.va_mobile);
        textVaAtm = (LinearLayout)findViewById(R.id.text_va_atm);
        textVaInternet = (LinearLayout)findViewById(R.id.text_va_internet);
        textVaMobile = (LinearLayout)findViewById(R.id.text_va_mobile);
        judulAtm = (TextView)findViewById(R.id.judul_atm);
        judulInternet = (TextView)findViewById(R.id.judul_internet);
        judulMobile = (TextView)findViewById(R.id.judul_mobile);
        panahAtm = (ImageView)findViewById(R.id.panah_atm);
        panahInternet = (ImageView)findViewById(R.id.panah_internet);
        panahMobile = (ImageView)findViewById(R.id.panah_mobile);
        vaAtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textVaAtm.getVisibility()==View.GONE){
                    judulAtm.setTypeface(Typeface.DEFAULT_BOLD);
                    textVaAtm.setVisibility(View.VISIBLE);
                    panahAtm.setImageResource(R.drawable.ic_expand_less_pink);
                }
                else{
                    judulAtm.setTypeface(Typeface.DEFAULT);
                    textVaAtm.setVisibility(View.GONE);
                    panahAtm.setImageResource(R.drawable.ic_expand_more_pink);
                }
            }
        });
        vaInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textVaInternet.getVisibility()==View.GONE){
                    judulInternet.setTypeface(Typeface.DEFAULT_BOLD);
                    textVaInternet.setVisibility(View.VISIBLE);
                    panahInternet.setImageResource(R.drawable.ic_expand_less_pink);
                }
                else{
                    judulInternet.setTypeface(Typeface.DEFAULT);
                    textVaInternet.setVisibility(View.GONE);
                    panahInternet.setImageResource(R.drawable.ic_expand_more_pink);
                }
            }
        });
        vaMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textVaMobile.getVisibility()==View.GONE){
                    judulMobile.setTypeface(Typeface.DEFAULT_BOLD);
                    textVaMobile.setVisibility(View.VISIBLE);
                    panahMobile.setImageResource(R.drawable.ic_expand_less_pink);
                }
                else{
                    judulMobile.setTypeface(Typeface.DEFAULT);
                    textVaMobile.setVisibility(View.GONE);
                    panahMobile.setImageResource(R.drawable.ic_expand_more_pink);
                }
            }
        });
    }

    private String getBatasWaktuPembayaran()
    {
        String[] weeks = { "Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu" };
        String[] months = { "Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul", "Ags", "Sep", "Okt", "Nov", "Des" };

        GregorianCalendar waktuSekarang = new GregorianCalendar();
        waktuSekarang.add(Calendar.DAY_OF_WEEK, 1);

        String minggu = weeks[waktuSekarang.get(Calendar.DAY_OF_WEEK)];
        String hari = Integer.toString(waktuSekarang.get(Calendar.DATE));
        String bulan = months[waktuSekarang.get(Calendar.MONTH)];
        String tahun = Integer.toString(waktuSekarang.get(Calendar.YEAR));
        String jam = Integer.toString(waktuSekarang.get(Calendar.HOUR));
        String menit = Integer.toString(waktuSekarang.get(Calendar.MINUTE));

        if (hari.length() == 1) hari = "0" + hari;
        if (jam.length() == 1) jam = "0" + jam;
        if (menit.length() == 1) menit = "0" + menit;

        return minggu + ", " + hari + " " + bulan + " " + tahun + ", " + jam + ":" + menit + " WIB";
    }
}
