package com.philipemosv.jachegouapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.philipemosv.jachegouapp.R;
import com.philipemosv.jachegouapp.helpers.ListaAlertasView;
import com.philipemosv.myapplication.models.Alerta;

public class ListaAlertasActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Alertas";
    private ListaAlertasView listaAlertasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        setContentView(R.layout.activity_main);

        setTitle(TITULO_APPBAR);

        listaAlertasView = new ListaAlertasView(this);

        configuraFabNovoAlerta();
        configuraLista();
    }

    private void configuraFabNovoAlerta() {
        FloatingActionButton botaoNovoAlerta = findViewById(R.id.activity_lista_alertas_fab_novo_alerta);
        botaoNovoAlerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListaAlertasActivity.this.abreFormularioModoNovoAlerta();
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alertas_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.activity_lista_alertas_menu_remover) {
            listaAlertasView.confirmaRemocao(item);
        }

        return super.onContextItemSelected(item);
    }

    private void abreFormularioModoNovoAlerta() {
        startActivity(new Intent(this, FormularioAlertaActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAlertasView.atualiza();
    }

    private void configuraLista() {
        ListView listaAlertas = findViewById(R.id.activity_lista_alertas_listview);
        listaAlertasView.configuraAdapter(listaAlertas);
        configuraListenerDeCliquePorItem(listaAlertas);
        registerForContextMenu(listaAlertas);
    }

    private void configuraListenerDeCliquePorItem(ListView listaAlertas) {
        listaAlertas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Alerta alertaEscolhido = (Alerta) parent.getItemAtPosition(position);
                ListaAlertasActivity.this.abreFormularioModoEditaAlerta(alertaEscolhido);
            }
        });
    }

    private void abreFormularioModoEditaAlerta(Alerta alerta) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlertasActivity.this, FormularioAlertaActivity.class);
        vaiParaFormularioActivity.putExtra("alerta", alerta);
        startActivity(vaiParaFormularioActivity);
    }
}
