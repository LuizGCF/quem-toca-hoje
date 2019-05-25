using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ApiQuemTocaHoje.Models
{
    public class Estabelecimento
    {
        public int IdEstabelecimento { get; set; }
        public string NomeDono { get; set; }
        public string CnpjEstabelecimento { get; set; }
        public string RazaoSocialEstabelecimento { get; set; }
        public string NomeFantasiaEstabelecimento { get; set; }
        public DateTime HoraInicioEstabelecimento { get; set; }
        public DateTime HoraTerminoEstabelecimento { get; set; }
        public string DescricaoAmbienteEstabelecimento { get; set; }
        public string TipoUsuario { get; set; }
        public string TelEstabelecimento { get; set; }
        public int IdAutenticacao { get; set; }
        public int IdEndereco { get; set; }

        //public ICollection<Arquivo> Arquivo { get; set; }
        public Autenticacao Autenticacao { get; set; }

        //public ICollection<Convite> Convite { get; set; }
        public Endereco Endereco { get; set; }

        //public ICollection<Proposta> Proposta { get; set; }
    }
}
