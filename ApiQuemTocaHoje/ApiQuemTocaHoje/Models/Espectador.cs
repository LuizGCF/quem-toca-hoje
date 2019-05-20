using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace ApiQuemTocaHoje.Models
{
    public class Espectador
    {
        public int IdEspectador { get; set; }
        public string NomeEspectador { get; set; }
        public DateTime DataCriacao { get; set; }
        public string TipoUsuario { get; set; }
        public int IdAutenticacao { get; set; }
        //falta um endereço aqui?
        //public Arquivo Arquivo { get; set; }

        public Autenticacao Autenticacao { get; set; }

        //public int IdEspectador { get; set; }
        //public string NomeEspectador { get; set; }
        //public DateTime DataCriacao { get; set; }
        //public string TipoUsuario { get; set; }
    }
}
