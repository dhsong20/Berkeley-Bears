package com.example.cs160_sp18.prog3;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

// Displays a list of comments for a particular landmark.
public class CommentFeedActivity extends AppCompatActivity {

    private static final String TAG = CommentFeedActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private static ArrayList<Comment> mComments = new ArrayList<Comment>();

    // UI elements
    EditText commentInputBox;
    RelativeLayout layout;
    Button sendButton;
    Toolbar mToolbar;

    private String username;
    private String landmark_name;

    /* TODO: right now mRecyclerView is using hard coded comments.
     * You'll need to add functionality for pulling and posting comments from Firebase
     */

    //Firebase variables
    private DatabaseReference database;
    private DatabaseReference bearRef;

    private boolean run = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_feed);


//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference dogsRef = database.getReference("Dogs");
//        DatabaseReference molly = dogsRef.child("Molly");
//        molly.child("age").setValue(13);
//        molly.child("toy").setValue("lemon");


        //getting username from intent
        Bundle extras = getIntent().getExtras();
        this.username = extras.getString("username_text");
        this.landmark_name = extras.getString("landmark_name");


        // hook up UI elements
        layout = (RelativeLayout) findViewById(R.id.comment_layout);
        commentInputBox = (EditText) layout.findViewById(R.id.comment_input_edit_text);
        sendButton = (Button) layout.findViewById(R.id.send_button);

        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        mToolbar.setTitle(this.landmark_name + " Posts");
        setSupportActionBar(mToolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.comment_recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // create an onclick for the send button
        setOnClickForSendButton();

        // make some test comment objects that we add to the recycler view
//        makeTestComments();

        // use the comments in mComments to create an adapter. This will populate mRecyclerView
        // with a custom cell (with comment_cell_layout) for each comment in mComments



//        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance().getReference();
        bearRef = database.child(this.landmark_name);

//        bearRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (run) {
//                    for (DataSnapshot existingChild : dataSnapshot.getChildren()) {
//                        Comment existingComment = existingChild.getValue(Comment.class);
//                        mComments.add(existingComment);
//
//                    }
////                    if (mComments.size() != 0) {
////                        setAdapterAndUpdateData();
////                    }
//                    run = false;
//                }
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

//        if (mComments.size() != 0) {
//            setAdapterAndUpdateData();
//        }


        bearRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mComments.clear();

               for (DataSnapshot child : dataSnapshot.getChildren()) {
                   Comment comment = child.getValue(Comment.class);

                   mComments.add(comment);


               }
               if (mComments.size() != 0) {
                   setAdapterAndUpdateData();
               }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.w(TAG, "onCancelled: Failed to read value", databaseError.toException());
            }
        });

    }

//    // TODO: delete me
//    private void makeTestComments() {
//        String randomString = "hello world hello world ";
//        Comment newComment = new Comment(randomString, "test_user1", new Date());
//        Comment hourAgoComment = new Comment(randomString + randomString, "test_user2", new Date(System.currentTimeMillis() - (60 * 60 * 1000)));
//        Comment overHourComment = new Comment(randomString, "test_user3", new Date(System.currentTimeMillis() - (2 * 60 * 60 * 1000)));
//        Comment dayAgoComment = new Comment(randomString, "test_user4", new Date(System.currentTimeMillis() - (25 * 60 * 60 * 1000)));
//        Comment daysAgoComment = new Comment(randomString + randomString + randomString, "test_user5", new Date(System.currentTimeMillis() - (48 * 60 * 60 * 1000)));
//        mComments.add(newComment);mComments.add(hourAgoComment); mComments.add(overHourComment);mComments.add(dayAgoComment); mComments.add(daysAgoComment);
//
//    }

    private void setOnClickForSendButton() {
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = commentInputBox.getText().toString();
                if (TextUtils.isEmpty(comment)) {
                    // don't do anything if nothing was added
                    commentInputBox.requestFocus();
                } else {
                    // clear edit text, post comment
                    commentInputBox.setText("");
                    postNewComment(comment);
                }
            }
        });
    }

    private void setAdapterAndUpdateData() {
        // create a new adapter with the updated mComments array
        // this will "refresh" our recycler view
        mAdapter = new CommentAdapter(this, mComments);
        mRecyclerView.setAdapter(mAdapter);

        // scroll to the last comment
        mRecyclerView.smoothScrollToPosition(mComments.size() - 1);
    }

    private void postNewComment(String commentText) {
        Comment newComment = new Comment(commentText, this.username, new Date());
        mComments.add(newComment);
        setAdapterAndUpdateData();

        String commentKey = bearRef.push().getKey();
        bearRef.child(commentKey).setValue(newComment);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
