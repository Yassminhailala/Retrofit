package com.example.etrofittp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.etrofittp.adapter.UserAdapter;
import com.example.etrofittp.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {
    private UserAdapter adapter;
    private UserViewModel viewModel;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configuration du RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new UserAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Configuration de la SearchView
        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        // Initialisation du ViewModel
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // Observer les changements de données
        viewModel.getUsers().observe(this, users -> {
            if (users != null) {
                adapter.setUserList(users);
            } else {
                Toast.makeText(this, "Erreur lors du chargement des données", Toast.LENGTH_SHORT).show();
            }
        });

        // Observer l'état de chargement
        viewModel.getIsLoading().observe(this, isLoading -> {
            // En cas de besoin, on pourrait ajouter un ProgressBar ici
        });
    }
}