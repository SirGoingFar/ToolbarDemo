package com.example.toolbardemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.MenuRes;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Toolbar toolbar;
    public Button btn_hide_menu_option;
    public Menu menu;
    private Button btn_show_menu_option;
    private Button btn_hide_overflow_menu;
    private Button btn_show_overflow_menu;
    private Button btn_swap_menu;
    private int menuToChange = R.menu.menu_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_hide_menu_option = findViewById(R.id.btn_hide_menu);
        btn_hide_menu_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideMenuOptionAt(R.id.action_delete_all_journals);
            }
        });

        btn_show_menu_option = findViewById(R.id.btn_show_menu);
        btn_show_menu_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuOptionAt(R.id.action_delete_all_journals);
            }
        });

        btn_hide_overflow_menu = findViewById(R.id.btn_hide_all_menu);
        btn_hide_overflow_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideOverflowMenu(true);
            }
        });

        btn_show_overflow_menu = findViewById(R.id.btn_show_all_menu);
        btn_show_overflow_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideOverflowMenu(false);
            }
        });

        btn_swap_menu = findViewById(R.id.btn_swap_menu);
        btn_swap_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeMenu(R.menu.menu_2);
            }
        });

        // Image has changed! Update the intent:
        // myShareIntent.putExtra(Intent.EXTRA_STREAM, myNewImageUri);
        // myShareActionProvider.setShareIntent(myShareIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(menuToChange, menu);
        return true;
    }

    private void hideMenuOptionAt(@IdRes int menuOptionId) {
        if (menu != null) {
            menu.findItem(menuOptionId).setVisible(false);
        }
    }

    private void showMenuOptionAt(@IdRes int menuOptionId) {
        if (menu != null) {
            menu.findItem(menuOptionId).setVisible(true);
        }
    }

    private void hideOverflowMenu(boolean hide) {
        if (menu != null) {
            if (hide)
                menu.clear();
            else
                onCreateOptionsMenu(menu);
        }
    }

    private void changeMenu(@MenuRes int menuXmlId) {
        menuToChange = menuXmlId;
        invalidateOptionsMenu();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_share){
            ShareActionProvider myShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

            Intent myShareIntent = new Intent(Intent.ACTION_SEND);
            myShareIntent.setType("text/*");
            myShareIntent.putExtra(Intent.EXTRA_TEXT, "Share");

            myShareActionProvider.setShareIntent(myShareIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
