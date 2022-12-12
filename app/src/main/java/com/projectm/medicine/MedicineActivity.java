package com.projectm.medicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.projectm.R;

public class MedicineActivity extends AppCompatActivity {

    ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        RelativeLayout row1_1=findViewById(R.id.row1_1);
        RelativeLayout row1_2=findViewById(R.id.row1_2);
        RelativeLayout row1_3=findViewById(R.id.row1_3);
        RelativeLayout row2_1=findViewById(R.id.row2_1);
        RelativeLayout row2_2=findViewById(R.id.row2_2);
        RelativeLayout row2_3=findViewById(R.id.row2_3);
        RelativeLayout row3_1=findViewById(R.id.row3_1);
        RelativeLayout row3_2=findViewById(R.id.row3_2);
        RelativeLayout row3_3=findViewById(R.id.row3_3);
        RelativeLayout row4_1=findViewById(R.id.row4_1);
        RelativeLayout row4_2=findViewById(R.id.row4_2);
        RelativeLayout row4_3=findViewById(R.id.row4_3);

        ImageView btn1=findViewById(R.id.btn1);
        ImageView btn2=findViewById(R.id.btn2);
        ImageView btn3=findViewById(R.id.btn3);
        ImageView btn4=findViewById(R.id.btn4);
        ImageView btn5=findViewById(R.id.btn5);
        ImageView btn6=findViewById(R.id.btn6);
        ImageView btn7=findViewById(R.id.btn7);
        ImageView btn8=findViewById(R.id.btn8);
        ImageView btn9=findViewById(R.id.btn9);
        ImageView btn10=findViewById(R.id.btn10);
        ImageView btn11=findViewById(R.id.btn11);
        ImageView btn12=findViewById(R.id.btn12);

        ImageView subBtn1_1=findViewById(R.id.subBtn1_1);
        ImageView subBtn1_2=findViewById(R.id.subBtn1_2);
        ImageView subBtn1_3=findViewById(R.id.subBtn1_3);
        ImageView subBtn1_4=findViewById(R.id.subBtn1_4);
        ImageView subBtn1_5=findViewById(R.id.subBtn1_5);
        ImageView subBtn2_1=findViewById(R.id.subBtn2_1);
        ImageView subBtn2_2=findViewById(R.id.subBtn2_2);
        ImageView subBtn2_3=findViewById(R.id.subBtn2_3);
        ImageView subBtn2_4=findViewById(R.id.subBtn2_4);
        ImageView subBtn2_5=findViewById(R.id.subBtn2_5);
        ImageView subBtn3_1=findViewById(R.id.subBtn3_1);
        ImageView subBtn3_2=findViewById(R.id.subBtn3_2);
        ImageView subBtn3_3=findViewById(R.id.subBtn3_3);
        ImageView subBtn3_4=findViewById(R.id.subBtn3_4);
        ImageView subBtn4_1=findViewById(R.id.subBtn4_1);
        ImageView subBtn4_2=findViewById(R.id.subBtn4_2);
        ImageView subBtn4_3=findViewById(R.id.subBtn4_3);
        ImageView subBtn4_4=findViewById(R.id.subBtn4_4);
        ImageView subBtn5_1=findViewById(R.id.subBtn5_1);
        ImageView subBtn5_2=findViewById(R.id.subBtn5_2);
        ImageView subBtn6_1=findViewById(R.id.subBtn6_1);
        ImageView subBtn6_2=findViewById(R.id.subBtn6_2);
        ImageView subBtn6_3=findViewById(R.id.subBtn6_3);
        ImageView subBtn6_4=findViewById(R.id.subBtn6_4);
        ImageView subBtn7_1=findViewById(R.id.subBtn7_1);
        ImageView subBtn7_2=findViewById(R.id.subBtn7_2);
        ImageView subBtn7_3=findViewById(R.id.subBtn7_3);
        ImageView subBtn7_4=findViewById(R.id.subBtn7_4);
        ImageView subBtn8_1=findViewById(R.id.subBtn8_1);
        ImageView subBtn8_2=findViewById(R.id.subBtn8_2);
        ImageView subBtn8_3=findViewById(R.id.subBtn8_3);
        ImageView subBtn8_4=findViewById(R.id.subBtn8_4);
        ImageView subBtn9_1=findViewById(R.id.subBtn9_1);
        ImageView subBtn9_2=findViewById(R.id.subBtn9_2);
        ImageView subBtn9_3=findViewById(R.id.subBtn9_3);
        ImageView subBtn10_1=findViewById(R.id.subBtn10_1);
        ImageView subBtn10_2=findViewById(R.id.subBtn10_2);
        ImageView subBtn10_3=findViewById(R.id.subBtn10_3);
        ImageView subBtn10_4=findViewById(R.id.subBtn10_4);
        ImageView subBtn12_1=findViewById(R.id.subBtn12_1);
        ImageView subBtn12_2=findViewById(R.id.subBtn12_2);
        ImageView subBtn12_3=findViewById(R.id.subBtn12_3);

        progressbar = findViewById(R.id.progressBar);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row1_1.setVisibility(View.VISIBLE);
                row1_2.setVisibility(View.GONE);
                row1_3.setVisibility(View.GONE);
                row2_1.setVisibility(View.GONE);
                row2_2.setVisibility(View.GONE);
                row2_3.setVisibility(View.GONE);
                row3_1.setVisibility(View.GONE);
                row3_2.setVisibility(View.GONE);
                row3_3.setVisibility(View.GONE);
                row4_1.setVisibility(View.GONE);
                row4_2.setVisibility(View.GONE);
                row4_3.setVisibility(View.GONE);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row1_1.setVisibility(View.GONE);
                row1_2.setVisibility(View.VISIBLE);
                row1_3.setVisibility(View.GONE);
                row2_1.setVisibility(View.GONE);
                row2_2.setVisibility(View.GONE);
                row2_3.setVisibility(View.GONE);
                row3_1.setVisibility(View.GONE);
                row3_2.setVisibility(View.GONE);
                row3_3.setVisibility(View.GONE);
                row4_1.setVisibility(View.GONE);
                row4_2.setVisibility(View.GONE);
                row4_3.setVisibility(View.GONE);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row1_1.setVisibility(View.GONE);
                row1_2.setVisibility(View.GONE);
                row1_3.setVisibility(View.VISIBLE);
                row2_1.setVisibility(View.GONE);
                row2_2.setVisibility(View.GONE);
                row2_3.setVisibility(View.GONE);
                row3_1.setVisibility(View.GONE);
                row3_2.setVisibility(View.GONE);
                row3_3.setVisibility(View.GONE);
                row4_1.setVisibility(View.GONE);
                row4_2.setVisibility(View.GONE);
                row4_3.setVisibility(View.GONE);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row1_1.setVisibility(View.GONE);
                row1_2.setVisibility(View.GONE);
                row1_3.setVisibility(View.GONE);
                row2_1.setVisibility(View.VISIBLE);
                row2_2.setVisibility(View.GONE);
                row2_3.setVisibility(View.GONE);
                row3_1.setVisibility(View.GONE);
                row3_2.setVisibility(View.GONE);
                row3_3.setVisibility(View.GONE);
                row4_1.setVisibility(View.GONE);
                row4_2.setVisibility(View.GONE);
                row4_3.setVisibility(View.GONE);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row1_1.setVisibility(View.GONE);
                row1_2.setVisibility(View.GONE);
                row1_3.setVisibility(View.GONE);
                row2_1.setVisibility(View.GONE);
                row2_2.setVisibility(View.VISIBLE);
                row2_3.setVisibility(View.GONE);
                row3_1.setVisibility(View.GONE);
                row3_2.setVisibility(View.GONE);
                row3_3.setVisibility(View.GONE);
                row4_1.setVisibility(View.GONE);
                row4_2.setVisibility(View.GONE);
                row4_3.setVisibility(View.GONE);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row1_1.setVisibility(View.GONE);
                row1_2.setVisibility(View.GONE);
                row1_3.setVisibility(View.GONE);
                row2_1.setVisibility(View.GONE);
                row2_2.setVisibility(View.GONE);
                row2_3.setVisibility(View.VISIBLE);
                row3_1.setVisibility(View.GONE);
                row3_2.setVisibility(View.GONE);
                row3_3.setVisibility(View.GONE);
                row4_1.setVisibility(View.GONE);
                row4_2.setVisibility(View.GONE);
                row4_3.setVisibility(View.GONE);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row1_1.setVisibility(View.GONE);
                row1_2.setVisibility(View.GONE);
                row1_3.setVisibility(View.GONE);
                row2_1.setVisibility(View.GONE);
                row2_2.setVisibility(View.GONE);
                row2_3.setVisibility(View.GONE);
                row3_1.setVisibility(View.VISIBLE);
                row3_2.setVisibility(View.GONE);
                row3_3.setVisibility(View.GONE);
                row4_1.setVisibility(View.GONE);
                row4_2.setVisibility(View.GONE);
                row4_3.setVisibility(View.GONE);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row1_1.setVisibility(View.GONE);
                row1_2.setVisibility(View.GONE);
                row1_3.setVisibility(View.GONE);
                row2_1.setVisibility(View.GONE);
                row2_2.setVisibility(View.GONE);
                row2_3.setVisibility(View.GONE);
                row3_1.setVisibility(View.GONE);
                row3_2.setVisibility(View.VISIBLE);
                row3_3.setVisibility(View.GONE);
                row4_1.setVisibility(View.GONE);
                row4_2.setVisibility(View.GONE);
                row4_3.setVisibility(View.GONE);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row1_1.setVisibility(View.GONE);
                row1_2.setVisibility(View.GONE);
                row1_3.setVisibility(View.GONE);
                row2_1.setVisibility(View.GONE);
                row2_2.setVisibility(View.GONE);
                row2_3.setVisibility(View.GONE);
                row3_1.setVisibility(View.GONE);
                row3_2.setVisibility(View.GONE);
                row3_3.setVisibility(View.VISIBLE);
                row4_1.setVisibility(View.GONE);
                row4_2.setVisibility(View.GONE);
                row4_3.setVisibility(View.GONE);
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row1_1.setVisibility(View.GONE);
                row1_2.setVisibility(View.GONE);
                row1_3.setVisibility(View.GONE);
                row2_1.setVisibility(View.GONE);
                row2_2.setVisibility(View.GONE);
                row2_3.setVisibility(View.GONE);
                row3_1.setVisibility(View.GONE);
                row3_2.setVisibility(View.GONE);
                row3_3.setVisibility(View.GONE);
                row4_1.setVisibility(View.VISIBLE);
                row4_2.setVisibility(View.GONE);
                row4_3.setVisibility(View.GONE);
            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row1_1.setVisibility(View.GONE);
                row1_2.setVisibility(View.GONE);
                row1_3.setVisibility(View.GONE);
                row2_1.setVisibility(View.GONE);
                row2_2.setVisibility(View.GONE);
                row2_3.setVisibility(View.GONE);
                row3_1.setVisibility(View.GONE);
                row3_2.setVisibility(View.GONE);
                row3_3.setVisibility(View.GONE);
                row4_1.setVisibility(View.GONE);
                row4_2.setVisibility(View.VISIBLE);
                row4_3.setVisibility(View.GONE);
            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row1_1.setVisibility(View.GONE);
                row1_2.setVisibility(View.GONE);
                row1_3.setVisibility(View.GONE);
                row2_1.setVisibility(View.GONE);
                row2_2.setVisibility(View.GONE);
                row2_3.setVisibility(View.GONE);
                row3_1.setVisibility(View.GONE);
                row3_2.setVisibility(View.GONE);
                row3_3.setVisibility(View.GONE);
                row4_1.setVisibility(View.GONE);
                row4_2.setVisibility(View.GONE);
                row4_3.setVisibility(View.VISIBLE);
            }
        });

        subBtn1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String diseaseName = new String("Sneezing");
                String diseaseType = new String("Common Disease");
                Intent intent=new Intent(MedicineActivity.this, SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);

            }
        });

        subBtn1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Headache");
                String diseaseType = new String("Common Disease");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn1_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Stomach ache");
                String diseaseType = new String("Common Disease");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn1_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Gas");
                String diseaseType = new String("Common Disease");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn1_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Fever");
                String diseaseType = new String("Common Disease");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Hairfall");
                String diseaseType = new String("Skin and Hair Issues");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Spots On Skin");
                String diseaseType = new String("Skin and Hair Issues");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn2_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Dry Skin");
                String diseaseType = new String("Skin and Hair Issues");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn2_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Rashes");
                String diseaseType = new String("Skin and Hair Issues");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn2_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Dark Circles");
                String diseaseType = new String("Skin and Hair Issues");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn3_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String diseaseName = new String("Redness of Eye");
                String diseaseType = new String("Eye Issues");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);

            }
        });

        subBtn3_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Itching of Eyes");
                String diseaseType = new String("Eye Issues");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn3_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Watering of Eye");
                String diseaseType = new String("Eye Issues");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn3_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Discharge from Eye");
                String diseaseType = new String("Eye Issues");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn4_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Depression");
                String diseaseType = new String("Mental Health Issues");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn4_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Anxiety");
                String diseaseType = new String("Mental Health Issues");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn4_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Stress");
                String diseaseType = new String("Mental Health Issues");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn4_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("OCD");
                String diseaseType = new String("Mental Health Issues");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn5_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Type1");
                String diseaseType = new String("Diabetes");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn5_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Type2");
                String diseaseType = new String("Diabetes");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn6_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String diseaseName = new String("Tooth Sensitivity");
                String diseaseType = new String("Dental Issues");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);

            }
        });

        subBtn6_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Jaw Pain");
                String diseaseType = new String("Dental Issues");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn6_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Toothache");
                String diseaseType = new String("Dental Issues");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn6_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Mouth Ulcer");
                String diseaseType = new String("Dental Issues");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn7_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Memory Loss");
                String diseaseType = new String("Nerve and Brain Issues");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn7_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Loss of Consciousness");
                String diseaseType = new String("Nerve and Brain Issues");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn7_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Loss of Balance");
                String diseaseType = new String("Nerve and Brain Issues");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn7_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Neck Pain");
                String diseaseType = new String("Nerve and Brain Issues");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn8_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Asthma");
                String diseaseType = new String("Lung Issues");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
//        subBtn8_2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String diseaseName = new String("Headache");
//                String diseaseType = new String("Lung Issues");
//                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
//                intent.putExtra("name",diseaseName);
//                intent.putExtra("type",diseaseType);
//                startActivity(intent);
//            }
//        });
        subBtn8_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String diseaseName = new String("Sleep Disorder");
                String diseaseType = new String("Lung Issues");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);

            }
        });

        subBtn8_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Chronic Cough");
                String diseaseType = new String("Lung Issues");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn9_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Chest Pain");
                String diseaseType = new String("Heart Disease");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn9_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Leg Sweeling");
                String diseaseType = new String("Heart Disease");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn9_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Palpitation");
                String diseaseType = new String("Heart Disease");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn10_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Brain Cancer");
                String diseaseType = new String("Cancer");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn10_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Lung Cancer");
                String diseaseType = new String("Cancer");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn10_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Blood Cancer");
                String diseaseType = new String("Cancer");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn10_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Skin Cancer");
                String diseaseType = new String("Cancer");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn12_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Gaining Weight");
                String diseaseType = new String("Diet and Nutrition");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn12_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Diabetes");
                String diseaseType = new String("Diet and Nutrition");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });
        subBtn12_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = new String("Weight Loss");
                String diseaseType = new String("Diet and Nutrition");
                Intent intent=new Intent(MedicineActivity.this,SubMedicineActivity.class);
                intent.putExtra("name",diseaseName);
                intent.putExtra("type",diseaseType);
                startActivity(intent);
            }
        });


    }
}