package com.deepshooter.birthdayapp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.deepshooter.birthdayapp.R;
import com.deepshooter.birthdayapp.model.BirthdaysInfo;
import com.deepshooter.birthdayapp.ui.profile.ProfileActivity;
import com.deepshooter.birthdayapp.utils.FireBaseAnalyticsUtil;
import com.deepshooter.birthdayapp.utils.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BirthdayGreetingsAdapter extends RecyclerView.Adapter<BirthdayGreetingsAdapter.BirthdayGreetingsViewHolder> {

    private Context mContext;

    private List<String> mBirthdayGreetingList;

    public BirthdayGreetingsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public BirthdayGreetingsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new BirthdayGreetingsViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_greetings, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final BirthdayGreetingsViewHolder holder, int i) {

        holder.mGreetingsTextView.setText(mBirthdayGreetingList.get(i));

        holder.mCopyImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = holder.mGreetingsTextView.getText().toString();
                FireBaseAnalyticsUtil.getInstance(mContext).setAnalyticsEvent(FireBaseAnalyticsUtil.COPY_WISH);
                Util.copy(mContext, text);

            }
        });

        holder.mShareImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = holder.mGreetingsTextView.getText().toString();
                FireBaseAnalyticsUtil.getInstance(mContext).setAnalyticsEvent(FireBaseAnalyticsUtil.SHARE_WISH);
                Util.share(mContext, text);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (null == mBirthdayGreetingList) return 0;
        return mBirthdayGreetingList.size();
    }

    public class BirthdayGreetingsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.greetings_textView)
        TextView mGreetingsTextView;
        @BindView(R.id.copy_imageView)
        ImageView mCopyImageView;
        @BindView(R.id.share_imageView)
        ImageView mShareImageView;

        public BirthdayGreetingsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setBirthdayGreetingListData(List<String> birthdayGreetingList) {
        mBirthdayGreetingList = birthdayGreetingList;
        notifyDataSetChanged();
    }
}