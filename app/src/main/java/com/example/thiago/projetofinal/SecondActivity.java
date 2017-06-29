//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Thiago Silva Pereira
//******************************************************

package com.example.thiago.projetofinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private DBHelper dh;
    //private DBHelper2 dh;

    Button btVoltar, btInserirDados, btListar;
    EditText etNome, etCpf, etIdade, etTelefone, etEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        this.dh = new DBHelper(SecondActivity.this);

        etCpf = (EditText) findViewById(R.id.editTextCpf);
        etNome = (EditText) findViewById(R.id.editTextNome);
        etIdade = (EditText) findViewById(R.id.editTextIdade);
        etTelefone = (EditText) findViewById(R.id.editTextTelefone);
        etEmail = (EditText) findViewById(R.id.editTextEmail);

        btVoltar = (Button) findViewById(R.id.buttonVoltar);
        btInserirDados = (Button) findViewById(R.id.buttonInserirDados);
        btListar = (Button) findViewById(R.id.buttonListar);

        btInserirDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etNome.getText().length()>0 && etCpf.getText().length()>0 && etIdade.getText().length()>0 && etTelefone.getText().length()>0 && etEmail.getText().length()>0){
                    dh.insert(etNome.getText().toString(), etCpf.getText().toString(),etIdade.getText().toString(),etTelefone.getText().toString(),etEmail.getText().toString());
                    AlertDialog.Builder adb = new  AlertDialog.Builder(SecondActivity.this);
                    adb.setTitle("Sucesso");
                    adb.setMessage("Cadastro Realizado");
                    adb.show();
                    etNome.setText("");
                    etCpf.setText("");
                    etIdade.setText("");
                    etTelefone.setText("");
                    etEmail.setText("");
                }else{
                    AlertDialog.Builder adb = new  AlertDialog.Builder(SecondActivity.this);
                    adb.setTitle("Erro ao Inserir");
                    adb.setMessage("Todos os campos deve ser inserido");
                    adb.show();
                    etNome.setText("");
                    etCpf.setText("");
                    etIdade.setText("");
                    etTelefone.setText("");
                    etEmail.setText("");
                }
            }
        });

        btListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Cadastro> contatos = dh.queryGetAll();
                if(contatos == null){
                    AlertDialog.Builder adb = new  AlertDialog.Builder(SecondActivity.this);
                    adb.setTitle("Mensagem");
                    adb.setMessage("Não há registros cadastrados");
                    adb.show();
                    return;
                }

                for(int i = 0; i < contatos.size(); i++){
                    Cadastro contato = (Cadastro) contatos.get(i);
                    AlertDialog.Builder adb = new  AlertDialog.Builder(SecondActivity.this);
                    adb.setTitle("Registro " + i);
                    adb.setMessage("Nome: " + contato.getNome()+ "\nCPF: " + contato.getCpf() + "\nIdade: " + contato.getIdade() + "\nTelefone: " + contato.getTelefone() + "\nEmail: " + contato.getEmail());
                    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    adb.show();
                }

            }
        });

        btListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Cadastro> contatos = dh.queryGetAll();
                if(contatos == null){
                    AlertDialog.Builder adb = new  AlertDialog.Builder(SecondActivity.this);
                    adb.setTitle("Mensagem");
                    adb.setMessage("Não há registros cadastrados");
                    adb.show();
                    return;
                }

                for(int i = 0; i < contatos.size(); i++){
                    Cadastro contato = (Cadastro) contatos.get(i);
                    AlertDialog.Builder adb = new  AlertDialog.Builder(SecondActivity.this);
                    adb.setTitle("Registro " + i);
                    adb.setMessage("Nome: " + contato.getNome()+ "\nCPF: " + contato.getCpf() + "\nIdade: " + contato.getIdade() + "\nTelefone: " + contato.getTelefone() + "\nEmail: " + contato.getEmail());
                    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    adb.show();
                }

            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltarPrimeiraTela();
            }
        });
    }

    void voltarPrimeiraTela(){
        Intent intent = new Intent();
        intent.setClass(SecondActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
