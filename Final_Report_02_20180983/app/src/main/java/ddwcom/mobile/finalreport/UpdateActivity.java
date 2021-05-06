package ddwcom.mobile.finalreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        movie = (movieData) getIntent().getSerializableExtra("movie");

        etTitle = findViewById(R.id.et_title);
        etMainActor = findViewById(R.id.et_mainActor);
        etRating = findViewById(R.id.et_rating);
        etDirector = findViewById(R.id.et_director);
        etCategory = findViewById(R.id.et_category);
        etPlot = findViewById(R.id.et_plot);
        etReleaseDate = findViewById(R.id.et_releaseDate);

        //movie.setImage(movie.getImage());
        imageView = findViewById(R.id.et_image);
        imageView.setImageResource(movie.getImage());

        etTitle.setHint(movie.getTitle());
        etMainActor.setHint(movie.getMainActor());
        etRating.setHint(movie.getRating());
        etDirector.setHint(movie.getDirector());
        etCategory.setHint(movie.getCategory());
        etPlot.setHint(movie.getPlot());
        etReleaseDate.setHint(movie.getReleaseDate());

        movieDBManager = new MovieDBManager(this);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.btn_ok:
                movie.setTitle(etTitle.getText().toString());
                movie.setMainActor(etMainActor.getText().toString());
                movie.setRating(etRating.getText().toString());
                movie.setTitle(etTitle.getText().toString());
                movie.setDirector(etDirector.getText().toString());
                movie.setCategory(etCategory.getText().toString());
                movie.setPlot(etPlot.getText().toString());
                movie.setReleaseDate(etReleaseDate.getText().toString());

                if(etTitle.getText().toString().equals(""))
                    Toast.makeText(this, "필수항목을 입력해주세요!", Toast.LENGTH_SHORT).show();
                else if(etMainActor.getText().toString().equals(""))
                    Toast.makeText(this, "필수항목을 입력해주세요!", Toast.LENGTH_SHORT).show();
                else if(etRating.getText().toString().isEmpty())
                    Toast.makeText(this, "필수항목을 입력해주세요!", Toast.LENGTH_SHORT).show();
                else{
                    if(movieDBManager.updateMovie(movie)){
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("movie", movie);
                        setResult(RESULT_OK, resultIntent);
                    } else{
                        setResult(RESULT_CANCELED);
                    }
                    finish();
                }
                break;
            case R.id.btn_cancle:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}
