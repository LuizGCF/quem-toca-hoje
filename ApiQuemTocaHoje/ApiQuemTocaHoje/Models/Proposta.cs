using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ApiQuemTocaHoje.Models
{
    public class Proposta
    {
        public int IdProposta { get; set; }
        public DateTime DataProposta { get; set; }
        public double ValorProsposta { get; set; }
        public string DescricaoProposta { get; set; }
        public int IdEvento { get; set; }
        public int IdEstabelecimento { get; set; }

        //public virtual Estabelecimento Estabelecimento { get; set; }
        //public virtual Evento Evento { get; set; }
    }
}
