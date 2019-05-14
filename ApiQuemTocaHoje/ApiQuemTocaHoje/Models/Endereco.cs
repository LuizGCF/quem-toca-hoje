using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ApiQuemTocaHoje.Models
{
    public class Endereco
    {
        public int IdEndereco { get; set; }
        public string LogradouroEndereco { get; set; }
        public string BairroEndereco { get; set; }
        public string ComplementoEntedeco { get; set; }
        public string CidadeEndereco { get; set; }
        public string CepEndereco { get; set; }
        public string UfEndereco { get; set; }
        public DateTime DataCriacao { get; set; }


        //public Estabelecimento Estabelecimento { get; set; }

        //public Musico Musico { get; set; }
    }
}
