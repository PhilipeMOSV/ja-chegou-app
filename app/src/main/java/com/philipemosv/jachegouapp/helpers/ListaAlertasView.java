package com.philipemosv.jachegouapp.helpers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.philipemosv.jachegouapp.database.AlertaDatabase;
import com.philipemosv.myapplication.database.dao.AlertaDAO;
import com.philipemosv.myapplication.models.Alerta;

public class ListaAlertasView {

    private final ArrayAdapter<Alerta> adapter;
    private final AlertaDAO dao;
    private final Context context;

    public ListaAlertasView(Context context) {
        this.context = context;

        dao = AlertaDatabase.getInstance(context)
                .getRoomAlertaDAO();

        this.adapter = new ArrayAdapter<Alerta>(context, android.R.layout.simple_list_item_1, dao.todos());
    }

    public void confirmaRemocao(@NonNull final MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle("Removendo alerta")
                .setMessage("Tem certeza que deseja remover o alerta")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Alerta alertaEscolhido = adapter.getItem(menuInfo.position);
                        ListaAlertasView.this.remove(alertaEscolhido);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void atualiza() {
        adapter.clear();
        adapter.addAll(dao.todos());
        adapter.notifyDataSetChanged();
    }

    private void remove(Alerta aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
    }

    public void configuraAdapter(ListView listaAlerta) {
        listaAlerta.setAdapter(adapter);
    }
}
