package ddwcom.mobile.finalreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MovieDBManager {
    MovieDBHelper movieDBHelper = null;
    Cursor cursor = null;

    public MovieDBManager(Context context){
        movieDBHelper = new MovieDBHelper(context);
    }

    //DB의 모든 영화 반환
    public ArrayList<movieData> getAllMovies(){
        ArrayList movieList = new ArrayList();
        SQLiteDatabase db = movieDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + MovieDBHelper.TABLE_NAME, null);

        while(cursor.moveToNext()){
            long id = cursor.getInt(cursor.getColumnIndex(MovieDBHelper.COL_ID));
            String title = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_TITLE));
            String mainActor = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_MAINACTOR));
            String rating = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_RATING));
            String director = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_DIRECTOR));
            String category = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_CATEGORY));
            String plot = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_PLOT));
            String release = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_RELEASEDATE));
            movieList.add(new movieData(id, title, mainActor, rating, director, category, plot, release));
        }

        cursor.close();
        movieDBHelper.close();
        return movieList;
    }

    //DB에 새로운 영화 추가
    public boolean addNewMovie(movieData newMovie){
        SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(MovieDBHelper.COL_TITLE, newMovie.getTitle());
        value.put(MovieDBHelper.COL_MAINACTOR, newMovie.getMainActor());
        value.put(MovieDBHelper.COL_RATING, newMovie.getRating());
        value.put(MovieDBHelper.COL_DIRECTOR, newMovie.getDirector());
        value.put(MovieDBHelper.COL_CATEGORY, newMovie.getCategory());
        value.put(MovieDBHelper.COL_PLOT, newMovie.getPlot());
        value.put(MovieDBHelper.COL_RELEASEDATE, newMovie.getReleaseDate());

        long count = db.insert(MovieDBHelper.TABLE_NAME, null, value);
        movieDBHelper.close();
        if(count > 0)
            return true;
        return false;
    }

    //DB 내용 수정
    public boolean updateMovie(movieData movie){
        SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put(MovieDBHelper.COL_TITLE, movie.getTitle());
        row.put(MovieDBHelper.COL_MAINACTOR, movie.getMainActor());
        row.put(MovieDBHelper.COL_RATING, movie.getRating());
        String whereClause = MovieDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(movie.get_id()) };
        int result = db.update(MovieDBHelper.TABLE_NAME, row, whereClause, whereArgs);
        movieDBHelper.close();
        if(result > 0)
            return true;
        return false;
    }

    //DB 내용 삭제
    public boolean removeMovie(long id){
        SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        String whereClause = MovieDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(id) };
        int result = db.delete(MovieDBHelper.TABLE_NAME, whereClause, whereArgs);
        movieDBHelper.close();
        if(result > 0)
            return true;
        return false;
    }

    //영화이름으로 DB검색
    public ArrayList<movieData> getMovieByTitle(String title){
        ArrayList movieList = new ArrayList();
        SQLiteDatabase db = movieDBHelper.getReadableDatabase();

        String selection = MovieDBHelper.COL_TITLE + "=?";
        String[] selectArgs = new String[] {title};

        Cursor cursor = db.query(MovieDBHelper.TABLE_NAME, null, selection, selectArgs, null,null,null, null);
        while(cursor.moveToNext()){
            String s_title = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_TITLE));
            String s_mainActor = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_MAINACTOR));
            String s_rating = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_RATING));
            String s_director = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_DIRECTOR));
            String s_category = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_CATEGORY));
            String s_plot = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_PLOT));
            String s_release = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_RELEASEDATE));
            movieList.add(new movieData(s_title, s_mainActor, s_rating, s_director, s_category, s_plot, s_release));
        }

        cursor.close();
        movieDBHelper.close();
        return movieList;
    }

    //영화평점으로 DB검색
    public ArrayList<movieData> getMovieByCategory(String category){
        ArrayList movieList = new ArrayList();
        SQLiteDatabase db = movieDBHelper.getReadableDatabase();

        String selection = MovieDBHelper.COL_CATEGORY + "=?";
        String[] selectArgs = new String[] {category};

        Cursor cursor = db.query(MovieDBHelper.TABLE_NAME, null, selection, selectArgs, null,null,null, null);
        while(cursor.moveToNext()){
            String s_title = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_TITLE));
            String s_mainActor = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_MAINACTOR));
            String s_rating = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_RATING));
            String s_director = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_DIRECTOR));
            String s_category = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_CATEGORY));
            String s_plot = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_PLOT));
            String s_release = cursor.getString(cursor.getColumnIndex(MovieDBHelper.COL_RELEASEDATE));
            movieList.add(new movieData(s_title, s_mainActor, s_rating, s_director, s_category, s_plot, s_release));
        }

        cursor.close();
        movieDBHelper.close();
        return movieList;
    }

    //clsoe 수행
    public void close(){
        if(movieDBHelper != null)
            movieDBHelper.close();
        if(cursor != null)
            cursor.close();
    }
}
