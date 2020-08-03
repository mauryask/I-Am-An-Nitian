package in.co.iamannitian.iamannitian;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.at.nitsxr.StoryAdapter;
import me.at.nitsxr.StoryGetterSetter;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class SuccessStory extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private StoryAdapter storyAdapter;
    private ArrayList<StoryGetterSetter> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*=========>>> Setting Up dark Mode <<<==========*/
        boolean mode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES;
        if (mode)
        {
            setTheme(R.style.DarkTheme);
        }
        else
        {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_story);

        setUpToolbarMenu(mode);
        initRecyclerView();
        setResources();
    }

    /*=======>>>>>>> Setting up toolbar menu <<<<<<<<<=========*/
    private void setUpToolbarMenu(boolean mode) {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Success Story");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (mode)
        {
            toolbar.setTitleTextColor(getResources().getColor(R.color.textColor2));
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.textColor2), PorterDuff.Mode.SRC_ATOP);
            actionBar.setIcon(R.drawable.app_logo_dark);
        }
        else
        {
            toolbar.setTitleTextColor(getResources().getColor(R.color.textColor1));
            toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.textColor1), PorterDuff.Mode.SRC_ATOP);
            actionBar.setIcon(R.drawable.app_logo);
        }
    }


    /*=========>>>>>>> Setting up overflow menu (when toolbar used as action bar) <<<<<<<<<=========*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    /*=======>>>>>>> Overflow menu item Click listener <<<<<<<<<=========*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                startActivity(new Intent(SuccessStory.this, AboutUs.class));
                break;
            case R.id.app_info:
                startActivity(new Intent(SuccessStory.this, AppInfo.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initRecyclerView()
    {
        mList = new ArrayList<>();
        recyclerView = findViewById(R.id.story_recycler_view);
        recyclerView.setHasFixedSize(true); //recycler view don't change its width and height
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        storyAdapter = new StoryAdapter(SuccessStory.this, mList);
        recyclerView.setAdapter(storyAdapter);
    }

    public void setResources() {
        String a = "http://app.thenextsem.com/images/Agartala.jpg";
        String b = "http://app.thenextsem.com/images/Bhopal.jpg";
        String c = "http://app.thenextsem.com/images/Calicut.jpg";
        String d = "http://app.thenextsem.com/images/Raipur.jpg";
        String e = "http://app.thenextsem.com/images/Nagpur.jpg";
        String f = "http://app.thenextsem.com/images/Jamshedpur.jpg";
        String g = "http://app.thenextsem.com/images/toppers/rajat_soni.jpg";
        String h = "http://app.thenextsem.com/images/toppers/ankit_singh.jpg";
        String i = "http://app.thenextsem.com/images/toppers/ankur_agarwal.jpg";
        String j = "http://app.thenextsem.com/images/toppers/abhishek_kumar.jpg";

        mList.add(new StoryGetterSetter(a, "Shubham Maurya","85","Compuer Science"));
        mList.add(new StoryGetterSetter(b, "Abhay Mishra","56","Civil Engineering"));
        mList.add(new StoryGetterSetter(c, "Priya Khangan","5","Civil Engineering"));
        mList.add(new StoryGetterSetter(d, "Tarun Mishra","122","Mechanical Engineering"));
        mList.add(new StoryGetterSetter(e, "Lokesh Yadav","74","Computer Science"));
        mList.add(new StoryGetterSetter(f, "Saurav Kumar","89","Electrical Engineering"));
        mList.add(new StoryGetterSetter(g, "Varsha Chaudhary","78","Computer Science"));
        mList.add(new StoryGetterSetter(h, "Shubhdeep Das","8","Automobile Engineering"));
        mList.add(new StoryGetterSetter(i, "Tanmoy Koner","60","Aerospace Engineering"));
        mList.add(new StoryGetterSetter(j, "Shivam Panday","25","Chemical Engineering"));
    }
}