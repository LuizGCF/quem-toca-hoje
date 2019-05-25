using ApiQuemTocaHoje.Banco;
using System;
using System.Collections.Generic;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Threading.Tasks;

namespace ApiQuemTocaHoje.Factory
{
    public class FactoryBanco : IDbContextFactory<ContextoBanco>
    {
        public ContextoBanco Create()
        {
            return new ContextoBanco(ContextoBanco.ConnectionString);
        }
    }
}
