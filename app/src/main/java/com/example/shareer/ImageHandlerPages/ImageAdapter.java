package com.example.shareer.ImageHandlerPages;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shareer.ItemClickListener;
import com.example.shareer.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewerHolder>{
    private Context mContext;
    private List<ImageUploadHandler> mUpload;

    public ImageAdapter(Context context,List<ImageUploadHandler> uploads)
    {
        mContext=context;
        mUpload=uploads;
    }
    @NonNull
    @Override
    public ImageViewerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_item,parent,false);
        return new ImageViewerHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewerHolder holder, int position) {

        final ImageUploadHandler uploadCurrent=mUpload.get(position);
        holder.textViewName.setText(uploadCurrent.getmName());
        Picasso.get().load(uploadCurrent.getmImageUri()).fit().centerCrop().into(holder.imageView);
        holder.mDescription.setText(uploadCurrent.getmImageUri());

        holder.shareUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlLink=holder.mDescription.getText().toString();
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,urlLink);
                intent.setType("text/plain");
                mContext.startActivity(intent);
            }
        });
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(mContext,ImageDetailActivity.class);
//                intent.putExtra("imgKey",uploadCurrent.getmKey());
//                intent.putExtra("DownloadUri", uploadCurrent.getmImageUri());
//                mContext.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mUpload.size();
    }

    public class ImageViewerHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView textViewName;
        public ImageView imageView;
        public TextView mDescription;
        public ImageButton shareUrl;
        public ItemClickListener listener;
        public ImageViewerHolder(@NonNull View itemView) {
            super(itemView);

            textViewName=itemView.findViewById(R.id.title);
            imageView=itemView.findViewById(R.id.image_view_upload);
            mDescription=itemView.findViewById(R.id.descriptionIv);
            shareUrl=itemView.findViewById(R.id.copyLinkUrl);
        }
        public void setOnclickListener(ItemClickListener listener){
            this.listener=listener;
        }
        @Override
        public void onClick(View v) {
            listener.onClick(v,getAdapterPosition(),false);
        }
    }
}
