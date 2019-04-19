package com.example.quemtocahoje.Banco;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quemtocahoje.POJO.Espectador;

public class Banco extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "BancoQuemTocaHoje.db";


// CRIANDO AS TABELAS DO BANCO ATRAVÉS DE STRINGS

    public static final String Musico_Banda = "CREATE TABLE Musico_Banda(idMusico INT NOT NULL,idBanda INT NOT NULL, PRIMARY KEY (idMusico, idBanda), FOREIGN KEY (idMusico) REFERENCES Musico(idMusico), FOREIGN KEY (idBanda) REFERENCES Banda(idBanda));";
    public static final String Convite = "CREATE TABLE Convite(IdConvite INT NOT NULL,EmailConvidadoConvite VARCHAR(45) NOT NULL,StatusConvidadoConvite VARCHAR(45) NOT NULL,TokenConvite VARCHAR(45) NOT NULL, DataCriacao DATE NOT NULL,idMusico INT NOT NULL, idBanda INT NOT NULL, idEstabelecimento INT NOT NULL,PRIMARY KEY (IdConvite), FOREIGN KEY (idMusico) REFERENCES Musico(idMusico), FOREIGN KEY (idBanda) REFERENCES Banda(idBanda), FOREIGN KEY (idEstabelecimento) REFERENCES Estabelecimento(idEstabelecimento));";
    public static final String Musico = "CREATE TABLE Musico(NomeMusico VARCHAR(45) NOT NULL, NomeArtMusico VARCHAR(45) NOT NULL,CodConviteMusico INT NOT NULL, idMusico INT NOT NULL, DataCriacao DATE NOT NULL,TelMusico VARCHAR(45) NOT NULL,idAutenticacao INT NOT NULL, idArquivo INT NOT NULL,idEndereco INT NOT NULL, PRIMARY KEY (idMusico), FOREIGN KEY (idAutenticacao) REFERENCES Autenticacao(idAutenticacao),FOREIGN KEY (idArquivo) REFERENCES Arquivo(idArquivo),FOREIGN KEY (idEndereco) REFERENCES Endereco(idEndereco));";
    public static final String Arquivo = "CREATE TABLE Arquivo(idArquivo INT NOT NULL,AnexoArquivoTipoBlob BLOB NOT NULL,TipoArquivo VARCHAR(30) NOT NULL, DataCriacao DATE NOT NULL, idBanda INT NOT NULL, idEstabelecimento INT NOT NULL, idEspectador INT NOT NULL, PRIMARY KEY (idArquivo), FOREIGN KEY (idBanda) REFERENCES Banda(idBanda),  FOREIGN KEY (idEstabelecimento) REFERENCES Estabelecimento(idEstabelecimento), FOREIGN KEY (idEspectador) REFERENCES Espectador(idEspectador));";
    public static final String Proposta = "CREATE TABLE Proposta(IdProposta INT NOT NULL, DataProposta DATE NOT NULL, ValorProsposta FLOAT NOT NULL, DescricaoProposta VARCHAR(100) NOT NULL, idEvento INT NOT NULL, idEstabelecimento INT NOT NULL, PRIMARY KEY (IdProposta), FOREIGN KEY (idEvento) REFERENCES Evento(idEvento),FOREIGN KEY (idEstabelecimento) REFERENCES Estabelecimento(idEstabelecimento));";
    public static final String Estabelecimento = "CREATE TABLE Estabelecimento( idEstabelecimento INT NOT NULL,CnpjEstabelecimento VARCHAR(45) NOT NULL, RazaoSocialEstabelecimento VARCHAR(45) NOT NULL, NomeFantasiaEstabelecimento VARCHAR(45) NOT NULL,HoraInicioEstabelecimento DATE NOT NULL, HoraTerminoEstabelecimento DATE NOT NULL, DescricaoAmbienteEstabelecimento VARCHAR(100) NOT NULL,TipoUsuario VARCHAR(45) NOT NULL,TelEstabelecimento VARCHAR(45) NOT NULL,idAutenticacao INT NOT NULL,idEndereco INT NOT NULL, PRIMARY KEY (idEstabelecimento),FOREIGN KEY (idAutenticacao) REFERENCES Autenticacao(idAutenticacao), FOREIGN KEY (idEndereco) REFERENCES Endereco(idEndereco));";
    public static final String Espectador = "CREATE TABLE Espectador(idEspectador INT NOT NULL, NomeEspectador VARCHAR(45) NOT NULL,DataCriacao DATE NOT NULL, TipoUsuario VARCHAR(10) NOT NULL, idAutenticacao INT NOT NULL,  PRIMARY KEY (idEspectador), FOREIGN KEY (idAutenticacao) REFERENCES Autenticacao(idAutenticacao));";
    public static final String Endereco = "CREATE TABLE Endereco( idEndereco INT NOT NULL,LogradouroEndereco VARCHAR(60) NOT NULL, BairroEndereco VARCHAR(70) NOT NULL,ComplementoEntedeco VARCHAR(60) NOT NULL,CidadeEndereco VARCHAR(20) NOT NULL,CepEndereco VARCHAR(10) NOT NULL, UfEndereco CHAR(2) NOT NULL,DataCriacao DATE NOT NULL,PRIMARY KEY (idEndereco));";
    public static final String Avaliacao = "CREATE TABLE Avaliacao(idAvaliacao INT NOT NULL,NotaAvaliacao INT NOT NULL,DescricaoAvaliacao VARCHAR(45) NOT NULL,TipoUsuarioAvaliacao VARCHAR(45) NOT NULL,DataAvaliacao DATE NOT NULL,idEvento INT NOT NULL,PRIMARY KEY (idAvaliacao),FOREIGN KEY (idEvento) REFERENCES Evento(idEvento));";
    public static final String Evento = "CREATE TABLE Evento(idEvento INT NOT NULL,DataHoraInicio DATE NOT NULL,DataHoraFim DATE NOT NULL,StatusEvento VARCHAR(15) NOT NULL,NotaEstab VARCHAR(45) NOT NULL,NotaMusico VARCHAR(45) NOT NULL,ComentarioEstab VARCHAR(100) NOT NULL,ComentarioMusico VARCHAR(100) NOT NULL,idBanda INT NOT NULL,PRIMARY KEY (idEvento),FOREIGN KEY (idBanda) REFERENCES Banda(idBanda),UNIQUE (NotaEstab, NotaMusico));";
    public static final String Autenticacao = "CREATE TABLE Autenticacao(idAutenticacao INT NOT NULL,LoginAutenticacao VARCHAR(45) NOT NULL,SenhaAutenticacao VARCHAR(45) NOT NULL,TipoUsuarioAutenticacao VARCHAR(45) NOT NULL,DataCriacao DATE NOT NULL,DataUltimoLogin DATE NOT NULL,PRIMARY KEY (idAutenticacao));";
    public static final String  Banda = "CREATE TABLE Banda(idBanda INT NOT NULL,NomeBanda VARCHAR(45) NOT NULL,GeneroBanda VARCHAR(45) NOT NULL,DataCriacao DATE NOT NULL,PRIMARY KEY (idBanda));";


    public Banco(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // db.execSQL("CREATE TABLE IF NOT EXISTS Espectador(idEspectador INTEGER PRIMARY KEY AUTOINCREMENT, NomeEspectador VARCHAR(200), DataCriacao DATETIME, TipoUsuario VARCHAR(50),idAutenticacao INTEGER)");

        // EXECUÇÃO DO COMANDO SQL PARA PERSISTIR  AS TABELAS
        db.execSQL(Musico_Banda);
        db.execSQL(Convite);
        db.execSQL(Musico);
        db.execSQL(Arquivo);
        db.execSQL(Proposta);
        db.execSQL(Estabelecimento);
        db.execSQL(Espectador);
        db.execSQL(Endereco);
        db.execSQL(Avaliacao);
        db.execSQL(Evento);
        db.execSQL(Autenticacao);
        db.execSQL(Banda);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        //db.execSQL("");//Acho que nao preciso baseado no que esta escrito emcima
        onCreate(db);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
