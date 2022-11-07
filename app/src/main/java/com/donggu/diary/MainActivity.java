package com.donggu.diary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRvDiary; // 리사이클러 뷰 ( 리스트 뷰)
    DiaryListAdapter mAdapter; // 리사이클러 뷰와 연동할 어댑터
    ArrayList<DiaryModel> mLstDiary; // 리스트에 표현할 다이어리 데이터들 (배열)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLstDiary = new ArrayList<>();
        mRvDiary = findViewById(R.id.rv_diary);
        mAdapter = new DiaryListAdapter(); // 리사이클러 뷰 어뎁터 인스터슨 생성


        for (int i = 0; i < 5; i++) {
            DiaryModel item = new DiaryModel(i, "title" + i , "cotent" + i, i, "2022.11.03", "2022.11.03");
            mLstDiary.add(item);
        }

        mAdapter.setSampleList(mLstDiary);
        mRvDiary.setAdapter(mAdapter);


        // 액티비티 (화면)이 실행될떄 가장 먼저 실행되는 곳
        FloatingActionButton floatingActionButton = findViewById(R.id.btn_write);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 작성하기 버튼을 누를떄 호출 되는 곳
                Intent intent = new Intent(MainActivity.this, DiaryDetailActivity.class);
                startActivity(intent);
            }
        });
    }
}