using ApiQuemTocaHoje.Migrations;
using ApiQuemTocaHoje.Models;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity;
using System.Data.Entity.ModelConfiguration.Conventions;
using System.Linq;
using System.Threading.Tasks;

namespace ApiQuemTocaHoje.Banco
{
    public class ContextoBanco : DbContext
    {
        public static string ConnectionString;

        public ContextoBanco(string connectionString)
            : base(connectionString)
        {
            ConnectionString = connectionString;
            Database.SetInitializer(new MigrateDatabaseToLatestVersion<ContextoBanco, Configuration>());
        }
        public DbSet<Arquivo> Arquivo { get; set; }
        public DbSet<Autenticacao> Autenticacao { get; set; }
        public DbSet<Avaliacao> Avaliacao { get; set; }
        public DbSet<Banda> Banda { get; set; }
        public DbSet<Convite> Convite { get; set; }
        public DbSet<Endereco> Endereco { get; set; }
        public DbSet<Espectador> Espectador { get; set; }
        public DbSet<Estabelecimento> Estabelecimento { get; set; }
        public virtual DbSet<Evento> Evento { get; set; }
        public DbSet<Musico> Musico { get; set; }
        public virtual DbSet<Proposta> Proposta { get; set; }


        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Autenticacao>()//classe modelo
                .HasKey(t=>t.IdAutenticacao)//tem de chave primaria este id
                .ToTable("Autenticacao")//nome da tabela
                .Property(x => x.LoginAutenticacao).HasMaxLength(30);//propriedade x tem tamanho x

            modelBuilder.Entity<Musico>()
                .HasMany(b => b.Banda)//relaçao n:n
                .WithMany(m => m.Musicos)
                .Map(mb =>
                {
                    mb.MapLeftKey("IdBanda");
                    mb.MapRightKey("IdMusico");
                    mb.ToTable("MusicoBanda");
                });
            modelBuilder.Entity<Musico>()
                .HasKey(t => t.IdMusico)
                .ToTable("Musico")                
                .Property(x => x.TelMusico).HasMaxLength(15);


            modelBuilder.Entity<Banda>()
                .HasMany<Musico>(m => m.Musicos)//relaçao n:n
                .WithMany(b => b.Banda)
                .Map(mb =>
                {
                    mb.MapLeftKey("IdBanda");
                    mb.MapRightKey("IdMusico");
                    mb.ToTable("MusicoBanda");
                });
            modelBuilder.Entity<Banda>()
                .HasKey(t => t.IdBanda)
                .ToTable("Banda")
                .Property(x => x.Nome).HasMaxLength(200);

            modelBuilder.Entity<Espectador>()
                .HasKey(t => t.IdEspectador)
                .ToTable("Espectador")
                .Property(x => x.NomeEspectador).HasMaxLength(200);

            modelBuilder.Entity<Arquivo>()
                .HasKey(t => t.IdArquivo)
                .ToTable("Arquivo")
                .Property(x => x.AnexoArquivo).HasMaxLength(2000);//?? não sei o tamanho de um array de bytes

            modelBuilder.Entity<Avaliacao>()
                .HasKey(t => t.IdAvaliacao)
                .ToTable("Avaliacao")
                .Property(x => x.idEvento).IsRequired();

            modelBuilder.Entity<Convite>()
                .HasKey(t => t.IdConvite)
                .ToTable("Convite")
                .Property(x => x.TokenConvite).HasMaxLength(6);

            modelBuilder.Entity<Endereco>()
                .HasKey(t => t.IdEndereco)
                .ToTable("Endereco")
                .Property(x => x.CepEndereco).HasMaxLength(9);

            modelBuilder.Entity<Estabelecimento>()
                .HasKey(t => t.IdEstabelecimento)
                .ToTable("Estabelecimento")
                .Property(x => x.DescricaoAmbienteEstabelecimento).HasMaxLength(300);

            modelBuilder.Entity<Evento>()
                .HasKey(t => t.IdEvento)
                .ToTable("Evento")
                .Property(x => x.NotaEstab).HasMaxLength(1);

            modelBuilder.Entity<Proposta>()
                .HasKey(t => t.IdProposta)
                .ToTable("Proposta")
                .Property(x => x.ValorProsposta).IsRequired();

            //modelBuilder.Conventions.Remove<ManyToManyCascadeDeleteConve‌​ntion>();

            base.OnModelCreating(modelBuilder);
        }
    }
}
