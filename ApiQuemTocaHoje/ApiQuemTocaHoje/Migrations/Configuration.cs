using ApiQuemTocaHoje.Banco;
using System;
using System.Collections.Generic;
using System.Data.Entity.Migrations;
using System.Linq;
using System.Threading.Tasks;

namespace ApiQuemTocaHoje.Migrations
{
    public class Configuration : DbMigrationsConfiguration<ContextoBanco>
    {
        public Configuration()
        {
            AutomaticMigrationsEnabled = true;//atualiza automaticamente mudanças nas tabela do banco
            AutomaticMigrationDataLossAllowed = true;//permite que dados sejam perdidos para a atualizacao das mudanças
            ContextKey = "ApiQuemTocaHoje.Banco.ContextoBanco";
        }
    }
}
