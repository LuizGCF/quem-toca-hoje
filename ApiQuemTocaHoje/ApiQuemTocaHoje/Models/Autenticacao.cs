using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace ApiQuemTocaHoje.Models
{
    public class Autenticacao
    {
        public int IdAutenticacao { get; set; }
        public string Registro { get; set; }
        public string EmailAutenticacao { get; set; }
        public string LoginAutenticacao { get; set; }
        public string SenhaAutenticacao { get; set; }
        public string TipoUsuarioAutenticacao { get; set; }
        public DateTime DataCriacao { get; set; }
        public DateTime DataUltimoLogin { get; set; }
    }
}
