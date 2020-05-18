package com.philipemosv.jachegouapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.philipemosv.jachegouapp.R;
import com.philipemosv.jachegouapp.database.AlertaDatabase;
import com.philipemosv.myapplication.database.dao.AlertaDAO;
import com.philipemosv.myapplication.models.Alerta;

public class FormularioAlertaActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_EDITA_ALERTA = "Edita Alerta";
    private static final String TITULO_APPBAR_NOVO_ALERTA = "Novo Alerta";
    private AlertaDAO dao;
    private EditText campoNome;
    private Alerta alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_alerta);

        AlertaDatabase database = AlertaDatabase.getInstance(this);

        dao = database.getRoomAlertaDAO();
        inicializacaoDosCampos();

        configuraBotaoAdicionarLocalizacao();

        carregaAlerta();
    }

    private void configuraBotaoAdicionarLocalizacao() {
        Button btnAdicionarLocalizacao = (Button) findViewById(R.id.addBtn);

        btnAdicionarLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vaiParaMapsActivity = new Intent(FormularioAlertaActivity.this, MapsActivity.class);
                EditText value = (EditText)findViewById(R.id.activity_formulario_alerta_nome);
                String nome = value.getText().toString();
                alerta.setNome(nome);
                vaiParaMapsActivity.putExtra("alertaParaMap", alerta);
                startActivity(vaiParaMapsActivity);
            }
        });
    }

    private void carregaAlerta() {
        Intent dados = getIntent();
        if (dados.hasExtra("alerta")) {
            setTitle(TITULO_APPBAR_EDITA_ALERTA);
            alerta = (Alerta) dados.getSerializableExtra("alerta");
            Button mButton = (Button)findViewById(R.id.addBtn);
            mButton.setText("Alterar Localização");
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_ALERTA);
            alerta = new Alerta();
        }
    }

    private void preencheCampos() {
        campoNome.setText(alerta.getNome());
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_alerta_nome);
    }

    private void preencheAlerta() {
        String nome = campoNome.getText().toString();
        alerta.setNome(nome);
    }
}
