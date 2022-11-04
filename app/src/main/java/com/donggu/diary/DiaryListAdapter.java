package com.donggu.diary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DiaryListAdapter extends RecyclerView.Adapter<DiaryListAdapter.ViewHolder> {

    ArrayList<DiaryModel> mLstDiary;
    Context mContext;

    @NonNull
    @Override
    public DiaryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // onCreateViewHolder() : 아이템 뷰가 최초로 생성이 될때 호출되는 곳
        mContext = parent.getContext();
        View holder = LayoutInflater.from(mContext).inflate(R.layout.list_item_diary, parent, false); // list view 안에 item을 붙혀나가는 행위
        return new ViewHolder(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryListAdapter.ViewHolder holder, int position) {
        // onBindViewHolder() : 생성된 아이템 뷰가 실제 연동이 되어지는 곳

        // 날씨의 경우의 수 작성
        int weatherType = mLstDiary.get(position).getWeatherType();
        switch (weatherType) {

            case 0:
                // 맑음
                holder.iv_weather.setImageResource(R.drawable.img_sun);
                break;
            case 1:
                // 흐림뒤갬
                holder.iv_weather.setImageResource(R.drawable.img_cloudy);
                break;
            case 2:
                // 흐림
                holder.iv_weather.setImageResource(R.drawable.img_cloud);
                break;
            case 3:
                // 매우흐림
                holder.iv_weather.setImageResource(R.drawable.img_bad_cloud);
                break;
            case 4:
                // 비
                holder.iv_weather.setImageResource(R.drawable.img_rainy);
                break;
            case 5:
                // 눈
                holder.iv_weather.setImageResource(R.drawable.img_snowy);
                break;
        }

        // 날씨 제목 , 일시
        String title = mLstDiary.get(position).getTitle();
        String userDate = mLstDiary.get(position).getUserDate();

        holder.tv_user_date.setText(userDate);
        holder.tv_title.setText(title);


    }

    @Override
    public int getItemCount() {
        // 아이템 뷰의 총 갯수를 관리하는 곳
        return mLstDiary.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_weather;
        TextView tv_title, tv_user_date;

        // 하나의 아이템 뷰
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_weather = itemView.findViewById(R.id.iv_weather); // 날씨 이미지
            tv_title = itemView.findViewById(R.id.tv_title); // 다이어리 제목
            tv_user_date = itemView.findViewById(R.id.tv_user_date); //
        }
    }

    public void setSampleList(ArrayList<DiaryModel> lstDiary) {
        mLstDiary = lstDiary;
    }
}