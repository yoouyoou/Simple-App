package ddwcom.mobile.finalreport;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity{
    movieData movie;
    EditText etTitle;
    EditText etMainActor;
    EditText etRating;
    EditText etDirector;
    EditText etCategory;
    EditText etPlot;
    EditText etReleaseDate;

    MovieDBManager movieDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etTitle = findViewById(R.id.et_title);
        etMainActor = findViewById(R.id.et_mainActor);
        etRating = findViewById(R.id.et_rating);
        etDirector = findViewById(R.id.et_director);
        etCategory = findViewById(R.id.et_category);
        etPlot = findViewById(R.id.et_plot);
        etReleaseDate = findViewById(R.id.et_releaseDate);

        movieDBManager = new MovieDBManager(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_add:
                if(etTitle.getText().toString().equals(""))
                    Toast.makeText(this, "필수항목을 입력해주세요!", Toast.LENGTH_SHORT).show();
                else if(etMainActor.getText().toString().equals(""))
                    Toast.makeText(this, "필수항목을 입력해주세요!", Toast.LENGTH_SHORT).show();
                else if(etRating.getText().toString().isEmpty())
                    Toast.makeText(this, "필수항목을 입력해주세요!", Toast.LENGTH_SHORT).show();
                else {
                    boolean result = movieDBManager.addNewMovie(
                            new movieData(etTitle.getText().toString(), etMainActor.getText().toString(), etRating.getText().toString(),
                                    etDirector.getText().toString(), etCategory.getText().toString(), etPlot.getText().toString(), etReleaseDate.getText().toString())
                    );
                    if (result) {
                        Toast.makeText(this, "새로운 영화가 추가되었습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(this, "새로운 영화를 추가하지 못했습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btn_cancle:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}
