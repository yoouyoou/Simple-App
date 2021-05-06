//과제명: 영화정보 관리 앱
//분반: 02분반
//학번: 20180983 성명: 유나연
//제출일: 2020년 7월 3일

package ddwcom.mobile.finalreport;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final int ADD_CODE = 100;
    final int UPDATE_CODE = 200;
    final int SEARCH_CODE = 300;

    ListView listView;
    MyAdapter myAdapter;
    ArrayList<movieData> movieList = null;
    MovieDBManager movieDBManger;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add:
                Intent intent = new Intent(this, AddActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_search:
                final ConstraintLayout searchLayout = (ConstraintLayout) View.inflate(this, R.layout.dialog_search, null);
                AlertDialog.Builder searchDialog = new AlertDialog.Builder(MainActivity.this);
                searchDialog.setTitle("영화 검색")
                        .setView(searchLayout)
                        .setNegativeButton("취소", null)
                        .setPositiveButton("검색", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText et_search = searchLayout.findViewById(R.id.et_search);
                                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                                intent.putExtra("movieTitle", et_search.getText().toString());
                                startActivity(intent);
                            }
                        })
                        .setCancelable(false)
                        .show();
                break;
            case R.id.menu_introduce:
                final ConstraintLayout introduceLayout = (ConstraintLayout) View.inflate(this, R.layout.dialog_layout, null);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("개발자 소개")
                        .setView(introduceLayout)
                        .setNegativeButton("취소", null)
                        .setPositiveButton("확인", null)
                        .setCancelable(false)
                        .show();
                break;
            case R.id.menu_quit:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("앱 종료")
                        .setMessage("앱을 종료하시겠습니까?")
                        .setNegativeButton("취소", null)
                        .setPositiveButton("종료", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setCancelable(false)
                        .show();
            break;
        }
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.customListView);
        movieList = new ArrayList();
        myAdapter = new MyAdapter(this, R.layout.custom_adapter_view, movieList);
        listView.setAdapter(myAdapter);
        movieDBManger = new MovieDBManager(this);

        //영화 수정
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                movieData movie = movieList.get(position);
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("movie", movie);
                startActivityForResult(intent, UPDATE_CODE);
            }
        });

        //영화 삭제
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("영화 삭제")
                        .setMessage(movieList.get(pos).getTitle() + " 영화를 삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(movieDBManger.removeMovie(movieList.get(pos).get_id())){
                                    Toast.makeText(MainActivity.this, "삭제 완료", Toast.LENGTH_SHORT).show();
                                    onResume();
                                } else{
                                    Toast.makeText(MainActivity.this, "삭제 실패", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("취소", null)
                        .setCancelable(false)
                        .show();
                return true;
            }
        });
    }

    protected void onResume(){
        super.onResume();
        movieList.clear();
        movieList.addAll(movieDBManger.getAllMovies());
        myAdapter.notifyDataSetChanged();

        for(int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).get_id() == 1)
                movieList.get(i).setImage(R.mipmap.movie1);
            else if (movieList.get(i).get_id() == 2)
                movieList.get(i).setImage(R.mipmap.movie2);
            else if (movieList.get(i).get_id() == 3)
                movieList.get(i).setImage(R.mipmap.movie3);
            else if (movieList.get(i).get_id() == 4)
                movieList.get(i).setImage(R.mipmap.movie4);
            else if (movieList.get(i).get_id() == 5)
                movieList.get(i).setImage(R.mipmap.movie5);
            else
                movieList.get(i).setImage(R.mipmap.ic_launcher);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CODE) {
            switch (resultCode) {
                case RESULT_OK:
                    String movie = data.getStringExtra("movie");
                    Toast.makeText(this, movie + " 가 추가되었습니다", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "추가 취소되었습니다", Toast.LENGTH_SHORT).show();
                    break;
            }
        } else if (requestCode == UPDATE_CODE) {
            switch (resultCode) {
                case RESULT_OK:
                    Toast.makeText(this, "영화 수정이 완료되었습니다", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "영화 수정이 취소되었습니다", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

}
