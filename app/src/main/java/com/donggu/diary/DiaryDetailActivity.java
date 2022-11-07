package com.donggu.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * 상세보기 화면 or 작성하기 화면
 */
public class DiaryDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mTvDate; // 일시 설정 텍스트
    private EditText mEtTitle, mEtContent; // 일기 제목, 일기 내용
    private RadioGroup mRgWeather;

    private String mDetailMode = ""; // intent로 받아낸 게시글 모드
    private String mBeforeDate = ""; // intent로 받아낸 게시글 날짜
    private String mSelectedUserDate = ""; // 선택된 일시 값
    private int mSelectedWeatherType = -1; // 선택한 날씨 값 (1~6)



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_detail);


        mTvDate = findViewById(R.id.tv_date); // 일시 설정 텍스트
        mEtTitle = findViewById(R.id.et_title); // 제목
        mEtContent = findViewById(R.id.et_content); // 내용
        mRgWeather = findViewById(R.id.rg_weather);  // 날씨선택

        ImageView iv_back = findViewById(R.id.iv_back); // 뒤로가기 버튼
        ImageView iv_check = findViewById(R.id.iv_check); // 작성완료 버튼

        mTvDate.setOnClickListener(this); // 클릭 기능 부여
        iv_back.setOnClickListener(this); // 클릭 기능 부여
        iv_check.setOnClickListener(this); // 클릭 기능 부여

        // 기본으로 설정될 날짜의 값을 지정하는 부분
        mSelectedUserDate = new SimpleDateFormat("yyyy.MM.dd (E요일)", Locale.KOREAN).format(new Date());
        mTvDate.setText(mSelectedUserDate);

    }

    @Override
    public void onClick(View view) {
        // setOnClickListener가 붙어있는 뷰들은 클릭이 발생하면 모두 이곳을 거쳐간다.
        switch (view.getId()) {
            case R.id.iv_back:
                // 뒤로가기
                finish();
                break;
            case R.id.iv_check:
                // 작성완료

                // 현재 클릭된 라디오 버튼
                mSelectedWeatherType = mRgWeather.indexOfChild(findViewById(mRgWeather.getCheckedRadioButtonId()));

                // 입력 필드 작성란이 비어있는지 체크
                if (mEtTitle.getText().length() == 0)  {
                    Toast.makeText(this, "제목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (mEtContent.getText().length() == 0) {
                    Toast.makeText(this, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 날씨 선택이 되어있는지 체크
                if (mSelectedWeatherType == -1) {
                    Toast.makeText(this, "날씨를 선택해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String title = mEtTitle.getText().toString(); // 제목 입력 값
                String content = mEtContent.getText().toString(); // 내용 입력 값
                String userDate = mSelectedUserDate; // 사용자가 선택한 일시

                String writeDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREAN).format(new Date());


                // 데이터베이스에 저장


                // 끝
                finish();
                break;

            case R.id.tv_date:
                // 날짜

                // 달력을 띄워서 사용자에게 일시를 입력 받는다.
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        // 달력에 선택된 년원일을 가지고와서 다시 캘린더 함수에 넣어줘서 사용자가 선택한 요일을 알아낸다.
                        Calendar innerCal = Calendar.getInstance();
                        innerCal.set(Calendar.YEAR, year);
                        innerCal.set(Calendar.MONTH, month);
                        innerCal.set(Calendar.DAY_OF_MONTH, day);

                        mSelectedUserDate = new SimpleDateFormat("yyyy.MM.dd (E요일)", Locale.KOREAN).format(innerCal.getTime());
                        mTvDate.setText(mSelectedUserDate);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));

                dialog.show();

                break;

        }
    }
}