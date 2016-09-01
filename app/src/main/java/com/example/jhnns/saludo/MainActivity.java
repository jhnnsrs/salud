package com.example.jhnns.saludo;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private CustomAdapter mAdapter;
    private SaludSpeechRecognition saludspeech;

    public static final int PATIENT__DETAIL = 0;
    public static final int PERSONALIA__DETAIL = 1;
    public static final int SYMPTOM__LIST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        final Button recbutton = (Button) findViewById(R.id.button);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new CustomAdapter();

        // DATA HOLDER MUST HAVE ACCESS TO ADAPTER(S)
        final SaludData data = new SaludData(mAdapter);
        // SPEECH ALWAYS ACCESS THE DATA HOLDER
        saludspeech = new SaludSpeechRecognition(this,data);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        final SaludApi salud = SaludApiGenerator.createService(SaludApi.class);

        CollapsingToolbarLayout stoolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        stoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<SaludPatientWrapper> call = salud.getPatient("1");
                call.enqueue(new Callback<SaludPatientWrapper>() {
                    @Override
                    public void onResponse(Call<SaludPatientWrapper> call, Response<SaludPatientWrapper> response) {
                        if (response.isSuccessful()) {
                            if (response.body().detail == null) {
                                Patient patient = response.body().patient;
                                patient.registerWith(data);
                                mAdapter.addSaludView(new SaludView(PATIENT__DETAIL,patient));
                                mAdapter.addSaludView(new SaludView(PERSONALIA__DETAIL,patient.personalia));
                                mAdapter.notifyDataSetChanged();
                            }


                            // tasks available
                        } else {
                        }
                    }

                    @Override
                    public void onFailure(Call<SaludPatientWrapper> call, Throwable t) {
                        // something went completely south (like no internet connection)
                        Log.d("Error", t.getMessage());
                    }
                });
            }
        });
        recbutton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
;
                    saludspeech.stopListening();
                    // Do what you want
                    return true;
                }
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    saludspeech.cancel();
                    Log.i("LOG","START");
                    saludspeech.startListening();
                    return true;
                }
                return false;
            }
        });
    }
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (saludspeech.speech != null) {
            saludspeech.speech.destroy();
            Log.i("NANA", "destroy");
        }

    }

    public void onReadyForSpeech(Bundle params) {

    }

}
