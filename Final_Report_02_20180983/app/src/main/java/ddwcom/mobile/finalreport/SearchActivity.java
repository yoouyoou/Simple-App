package ddwcom.mobile.finalreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    movieData movie;
    EditText etTitle;
    EditText etMainActor;
    EditText etRating;
    EditText etDirector;
    EditText etCategory;
    EditText etPlot;
    EditText etReleaseDate;
    ImageView imageView;
    MovieDBManager movieDBManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        movieDBManager = new MovieDBManager(this);
        ArrayList<movieData> searchMovie;
        Intent intent = getIntent();
        String movieTitle = intent.getStringExtra("movieTitle");
        searchMovie = movieDBManager.getMovieByTitle(movieTitle);

        etTitle = findViewById(R.id.et_title);
        etMainActor = findViewById(R.id.et_mainActor);
        etRating = findViewById(R.id.et_rating);
        etDirector = findViewById(R.id.et_director);
        etCategory = findViewById(R.id.et_category);
        etPlot = findViewById(R.id.et_plot);
        etReleaseDate = findViewById(R.id.et_releaseDate);

        if(searchMovie.size() > 1){
            AlertDialog.Builder select = new AlertDialog.Builder(this);
            select.setTitle("검색 실패")
                    .setMessage("중복된 결과가 존재합니다. 다른 방법을 이용해 검색해 주십시오")
                    .setPositiveButton("확인", null)
                    .setCancelable(false)
                    .show();
        }else if(searchMovie.size() == 1){
            etTitle.setText(searchMovie.get(0).getTitle());
            etMainActor.setText(searchMovie.get(0).getMainActor());
            etRating.setText(searchMovie.get(0).getRating());
            etDirector.setText(searchMovie.get(0).getDirector());
            etCategory.setText(searchMovie.get(0).getCategory());
            etPlot.setText(searchMovie.get(0).getPlot());
            etReleaseDate.setText(searchMovie.get(0).getReleaseDate());
        } else{
            Toast.makeText(this, "찾으시는 검색결과가 없습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }

        movieDBManager = new MovieDBManager(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_ok:
            case R.id.btn_cancle:
                finish();
                break;
        }
    }
}
