package il.ac.hit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.view.View;

public class DisplayCardsActivity extends AppCompatActivity {

    private RecyclerView cardRecyclerView;
    CardAdapter cardAdapter;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser mCurrentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_cards);

        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("users").child(mCurrentUser.getUid()).child("cards");

        cardRecyclerView = findViewById(R.id.rvCards);
        cardRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Card> options =
                new FirebaseRecyclerOptions.Builder<Card>().setQuery(myRef, Card.class).build();
        cardAdapter = new CardAdapter(options);
        cardRecyclerView.setAdapter(cardAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        cardAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cardAdapter.stopListening();
    }
//
//    public void deleteCard(View view){
//        cardRecyclerView
//    }
}