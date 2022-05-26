package com.example.a19524301_minhhong_th;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ListActivity extends AppCompatActivity {
    @BindView(R.id.btn_add)
    Button btn_List_Add;
    @BindView(R.id.lv_main)
    ListView lvBook;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        loadListView();

        List<String> title = getListName();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, title);


        btn_List_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }
    public void loadListView(){
        List<Item> listItem = new ArrayList<>();
        db.collection("Item").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty()){
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d: list) {
                                Item item = d.toObject(Item.class);
                                listItem.add(item);
                            }
                            AdapterItem adapterBook = new AdapterItem(ListActivity.this, R.layout.item_listview, listItem);
                            lvBook.setAdapter(adapterBook);
                        }
                    }
                });
    }
    public List<String> getListName() {
        List<String> listItem = new ArrayList<>();
        db.collection("Item").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                Item item = d.toObject(Item.class);
                                list.add(item.getTitle());
                            }
                        }
                    }
                });
        return listItem;
    }
}
