package ddwcom.mobile.finalreport;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MovieDBHelper extends SQLiteOpenHelper {
    final static String TAG = "MovieDBHelper";

    final static String DB_NAME = "movies.db";
    public final static String TABLE_NAME = "movie_table";

    public final static String COL_ID = "_id";
    public final static String COL_TITLE = "title";
    public final static String COL_MAINACTOR = "mainActor";
    public final static String COL_DIRECTOR = "director";
    public final static String COL_RATING = "rating";
    public final static String COL_RELEASEDATE = "releaseDate";
    public final static String COL_PLOT = "plot";
    public final static String COL_CATEGORY = "category";

    public MovieDBHelper(Context context){
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " integer primary key autoincrement, " +
                COL_TITLE + " TEXT, " + COL_MAINACTOR + " TEXT, " + COL_RATING + " TEXT, " +COL_DIRECTOR + " TEXT, "+
                COL_CATEGORY + " TEXT, " + COL_PLOT + " TEXT, " + COL_RELEASEDATE + " TEXT)";
        Log.d(TAG, sql);
        db.execSQL(sql);

        db.execSQL("insert into " + TABLE_NAME + " values (null, '라라랜드', '엠마 스톤, 라이언 고슬링', '8.91', '데이미언 셔젤', '뮤지컬','꿈을 꾸는 사람들을 위한 별들의 도시 라라랜드. 사랑, 희망, 열정 모든 감정이 이 곳에서 폭발한다!', '2016/12/7');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '비긴 어게인', '키이라 나이틀리, 마크 러팔로', '9.13','존 카니', '로맨스','다시 시작해, 너를 빛나게 할 노래를!','2014/08/13');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '살아있다', '유아인, 박신혜', '7.98','감독님','드라마','원인불명 증세의 사람들의 공격에 통제 불능에 빠진 도시','2020/06/24');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '기생충', '송강호', '9.07','봉준호','드라마','두 가족의 만남. 그 뒤로 걷잡을 수 없는 사건이 기다리고 있다.', '2019/05/30');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '신과 함께-죄와 벌', '하정우, 차태현, 주지훈', '8.73','김용화','판타지', '7개의 지옥에서 7번의 재판을 무사히 통과해야 환생할 수 있다.', '2017/12/20');");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
