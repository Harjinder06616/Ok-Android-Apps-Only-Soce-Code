
      FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("User"), Model.class)
                        .build();
        mDataAdapter = new myadapter(options, this);
        rvList.setAdapter(mDataAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mDataAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mDataAdapter.stopListening();
    }


}