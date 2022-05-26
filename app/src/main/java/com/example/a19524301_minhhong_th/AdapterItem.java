package com.example.a19524301_minhhong_th;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class AdapterItem extends BaseAdapter {
    private Context context;
    private List<Item> lstItem;
    private int idLayout;
    private int positionSelection = -1;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    //private FirebaseStorage storage = FirebaseStorage.getInstance();
    //private final StorageReference storageRef = storage.getReference();
    private DatabaseReference mDatabase;


    public AdapterItem(Context context, int idLayout, List<Item> itemList){
        this.context = context;
        this.idLayout = idLayout;
        this.lstItem = itemList ;
    }
    @Override
    public int getCount() {
        if(!lstItem.isEmpty() && lstItem.size()>0)
            return lstItem.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(idLayout,viewGroup,false);
        }
        TextView tvTitle = view.findViewById(R.id.tv_Title);
        TextView tvCount = view.findViewById(R.id.tv_Count);
        TextView tvPrice = view.findViewById(R.id.tv_Price);
        ImageView imgBook = view.findViewById(R.id.img);

        //Picasso.with(context).load(lstItem.get(i).getImg()).into(imgBook);
        tvTitle.setText(lstItem.get(i).getTitle());
        tvCount.setText("Count: "+lstItem.get(i).getCount());
        tvPrice.setText("Price: "+lstItem.get(i).getPrice()+" VND");

        ImageButton btnDelete = view.findViewById(R.id.btn_delete);
        ImageButton btnEdit = view.findViewById(R.id.btn_edit);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("Books").document(lstItem.get(i).getID())
                        .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        mDatabase.child("Books").child(lstItem.get(i).getID()).removeValue();
                        //StorageReference desertRef = storageRef.child(lstBook.get(i).getID()+".png");
                        // Delete the file
                        //desertRef.delete();
                        lstItem.remove(i);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Delete Successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Delete Failure", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String stringID = String.valueOf(lstItem.get(i).getID());
                        String stringImg = lstItem.get(i).getImg().toString();

                        //Intent intent = new Intent(context, EditBookActivity.class);
                        //intent.putExtra("ID", stringID);
                        //intent.putExtra("title", lstBook.get(i).getTitle());
                        //intent.putExtra("count", String.valueOf(lstBook.get(i).getCount()));
                        //intent.putExtra("price", String.valueOf(lstBook.get(i).getPrice()));
                       // intent.putExtra("img", stringImg);
                       // context.startActivity(intent);
                    }
                });
            }
        });


        return view;
    }
}
