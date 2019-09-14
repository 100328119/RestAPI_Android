package com.example.restapiexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;
    private JsonPlaceHolderAPI jsonPlaceHolderAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        Gson gson = new GsonBuilder().serializeNulls().create();

        //logging data
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        //initialize retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //implement jsonplaceholderAPi interface to post object
        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

//       getPosts();
//        getComments();
//         getPostsByUserId(new Integer[]{2,3,4});
//        createPost();
        putPost();
//        patchPost();
//        deletePost();
    }

    private void getPosts(){
        Call<List<Post>> call = jsonPlaceHolderAPI.getPosts(null);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("code" + response.code());
                    return;
                }else{

                    List<Post> posts = response.body();
                    for(Post post : posts){
                        String content = "";
                        content += "ID " + post.getId()+"\n";
                        content += "user ID " + post.getUserid()+"\n";
                        content += "Title " + post.getTitle()+"\n";
                        content += "Body " + post.getBody()+"\n\n";
                        textViewResult.append(content);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getComments(){
        Call<List<Comment>> call = jsonPlaceHolderAPI.getComments("/comments?postId=1");

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("code" + response.code());
                    return;
                }else{

                    List<Comment> Comments = response.body();
                    for(Comment Comment : Comments){
                        String content = "";
                        content += "ID " + Comment.getId()+"\n";
                        content += "Post ID " + Comment.getPostId()+"\n";
                        content += "name " + Comment.getName()+"\n";
                        content += "email " + Comment.getEmail()+"\n";
                        content += "Body " + Comment.getBody()+"\n\n";
                        textViewResult.append(content);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getPostsByUserId(Integer[] userId){
        Call<List<Post>> call = jsonPlaceHolderAPI.getPostsByUserId(userId);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("code" + response.code());
                    return;
                }else{

                    List<Post> posts = response.body();
                    for(Post post : posts){
                        String content = "";
                        content += "ID " + post.getId()+"\n";
                        content += "user ID" + post.getUserid()+"\n";
                        content += "Title" + post.getTitle()+"\n";
                        content += "Body" + post.getBody()+"\n\n";
                        textViewResult.append(content);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void createPost(){
        Post post = new Post(12, "kun's title","new text");

        Map<String, String> fields = new HashMap<>();
        fields.put("userId", "25");
        fields.put("title", "new title");


        Call<Post> call = jsonPlaceHolderAPI.createPost(post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("code" + response.code());
                    return;
                }else{

                    Post new_post = response.body();
                        String content = "";
                        content += "Code: " + response.code()+"\n";
                        content += "ID: " + new_post.getId()+"\n";
                        content += "user ID: " + new_post.getUserid()+"\n";
                        content += "Title: " + new_post.getTitle()+"\n";
                        content += "Body: " + new_post.getBody()+"\n\n";
                        textViewResult.append(content);

                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void putPost(){
        Post post = new Post(1, "dsadads","pull text");

        Call<Post> call = jsonPlaceHolderAPI.putPost(1,post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("code" + response.code());
                    return;
                }else{

                    Post new_post = response.body();
                    String content = "";
                    content += "Code: " + response.code()+"\n";
                    content += "ID: " + new_post.getId()+"\n";
                    content += "user ID: " + new_post.getUserid()+"\n";
                    content += "Title: " + new_post.getTitle()+"\n";
                    content += "Body: " + new_post.getBody()+"\n\n";
                    textViewResult.append(content);

                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void patchPost(){
        Post post = new Post(1, null,"patch text");

        Call<Post> call = jsonPlaceHolderAPI.patchPost(2,post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("code" + response.code());
                    return;
                }else{

                    Post new_post = response.body();
                    String content = "";
                    content += "Code: " + response.code()+"\n";
                    content += "ID: " + new_post.getId()+"\n";
                    content += "user ID: " + new_post.getUserid()+"\n";
                    content += "Title: " + new_post.getTitle()+"\n";
                    content += "Body: " + new_post.getBody()+"\n\n";
                    textViewResult.append(content);

                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void deletePost(){
        Call<Void> call = jsonPlaceHolderAPI.deletePost(1);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                    textViewResult.setText("code" + response.code());

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

}
