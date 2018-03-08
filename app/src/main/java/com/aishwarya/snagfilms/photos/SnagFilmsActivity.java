package com.aishwarya.snagfilms.photos;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aishwarya.snagfilms.R;
import com.aishwarya.snagfilms.SnagFilmsApplication;
import com.aishwarya.snagfilms.dao.Film;
import com.aishwarya.snagfilms.dao.SnagFilmsResponse;
import com.aishwarya.snagfilms.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by AishwaryaB on 03/7/2018.
 */
public class SnagFilmsActivity extends AppCompatActivity implements AbsListView.OnScrollListener {
    //private GridView mPhotosGrid;
    //private SnagFilmsGridAdapter snagFilmsGridAdapter;
    private int totalPages, mClickCount;
    private ProgressBar mProgressBar;
    private SnagFilmsResponse snagFilmsResponse;
    //Auto loading related
    private final int AUTOLOAD_THRESHOLD = 4;
    private int MAXIMUM_ITEMS;
    private Handler mHandler;
    private boolean mIsLoading = false;
    private boolean mMoreDataAvailable = true;
    private boolean mWasLoading = false;
    private int mCount = 10;//OffSet value
    private RecyclerView recyclerView;
    private Runnable mAddItemsRunnable = new Runnable() {
        @Override
        public void run() {
            // handlePhotosSearch(mClickCount);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snag_films);
        initView();
    }

    private void initView() {
        //mPhotosGrid = findViewById(R.id.photosGrid);
        mProgressBar = findViewById(R.id.progress);

        /*if (snagFilmsGridAdapter == null) {
            snagFilmsGridAdapter = new SnagFilmsGridAdapter(this);
        }
        mProgressBar.setVisibility(View.GONE);
        mPhotosGrid.setVisibility(View.VISIBLE);
        mPhotosGrid.setAdapter(snagFilmsGridAdapter);
        mHandler = new Handler();
        snagFilmsGridAdapter.refresh(SnagFilmsApplication.getInstance().getSnagFilmsResponse().getFilms().getFilm());
        mPhotosGrid.setOnScrollListener(this);*/

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        handleMoviesGrid(1);

    }

    private void handleMoviesGrid(final int mClickCount1) {
        Log.e("PS1", "Page No : " + mClickCount1);
        Call<SnagFilmsResponse> homeResponseCall = SnagFilmsApplication.getInstance().initRetrofit().doMoviesGridView(10);
        homeResponseCall.enqueue(new Callback<SnagFilmsResponse>() {
            @Override
            public void onResponse(Call<SnagFilmsResponse> call, Response<SnagFilmsResponse> response) {
                Log.e("PX", "MS-OK" + response.code());
                if (response.code() == Constants.OK) {
                    SnagFilmsApplication.getInstance().setSnagFilmsResponse(response.body());
                    totalPages = SnagFilmsApplication.getInstance().getSnagFilmsResponse().getFilms().getPageTotal();
                    mClickCount = SnagFilmsApplication.getInstance().getSnagFilmsResponse().getFilms().getPageIndex();
                    MAXIMUM_ITEMS = SnagFilmsApplication.getInstance().getSnagFilmsResponse().getFilms().getTotal();
                    snagFilmsResponse = response.body();
                    if (totalPages >= mClickCount) {
                        mClickCount = mClickCount1 + 1;
                        Log.e("MS2", "Page No : " + mClickCount);
                        mProgressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        DataAdapter adapter = new DataAdapter(getApplicationContext(), snagFilmsResponse.getFilms().getFilm());
                        recyclerView.setAdapter(adapter);
                        //new DataAdapter(getApplicationContext()).addMoreItems(snagFilmsResponse.getFilms().getFilm());
                        mIsLoading = false;
                        mMoreDataAvailable = true;
                    } else {
                        mProgressBar.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        Toast.makeText(SnagFilmsActivity.this, "No more photos", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SnagFilmsActivity.this, "Photos search failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SnagFilmsResponse> call, Throwable t) {
                Log.e("PX", "MS-FAIL" + t.getMessage());
                mProgressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (!mIsLoading && mMoreDataAvailable) {
            if (totalItemCount >= MAXIMUM_ITEMS) {
                mMoreDataAvailable = false;
                mProgressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            } else if (totalItemCount - AUTOLOAD_THRESHOLD <= firstVisibleItem + visibleItemCount) {
                mIsLoading = true;
                mProgressBar.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                mHandler.postDelayed(mAddItemsRunnable, 1000);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mWasLoading) {
            mWasLoading = false;
            mIsLoading = true;
            mProgressBar.setVisibility(View.VISIBLE);
            //mPhotosGrid.setVisibility(View.GONE);
            mHandler.postDelayed(mAddItemsRunnable, 1000);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mHandler.removeCallbacks(mAddItemsRunnable);
        mWasLoading = mIsLoading;
        mIsLoading = false;
        mProgressBar.setVisibility(View.GONE);
        //mPhotosGrid.setVisibility(View.VISIBLE);
    }

    public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
        private List<Film> filmArrayList;
        private Context context;

        public DataAdapter(Context context) {
            this.context = context;
        }

        public DataAdapter(Context context, List<Film> filmArrayList) {
            this.filmArrayList = filmArrayList;
            this.context = context;
        }

        @Override
        public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_photos_list_item, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

            viewHolder.tv_android.setText(filmArrayList.get(i).getTitle());
            Picasso.with(context).load(filmArrayList.get(i).getImages().getImage().get(0).getSrc()).resize(240, 120).into
                (viewHolder
                    .img_android);
        }

        @Override
        public int getItemCount() {
            return filmArrayList.size();
        }

        public void refresh(List<Film> photoList1) {
            Log.e("PSL", "PSL" + photoList1.size());
            filmArrayList.clear();
            filmArrayList.addAll(photoList1);
            notifyDataSetChanged();
        }

        public void addMoreItems(List<Film> filmList) {
            mCount += filmList.size();
            filmArrayList.addAll(filmList.size() - 1, filmList);
            notifyDataSetChanged();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView tv_android;
            private ImageView img_android;

            public ViewHolder(View view) {
                super(view);

                tv_android = view.findViewById(R.id.movieTitle);
                img_android = view.findViewById(R.id.thumbnail);
            }
        }

    }
}
