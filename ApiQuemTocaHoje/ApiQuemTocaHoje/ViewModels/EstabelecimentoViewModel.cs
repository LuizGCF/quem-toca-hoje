using ApiQuemTocaHoje.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ApiQuemTocaHoje.ViewModels
{
    public class EstabelecimentoViewModel
    {
        public string NomeDono { get; set; }
        public string CNPJ { get; set; }
        public string RazaoSocial { get; set; }
        public string NomeFantasia { get; set; }
        public DateTime HoraInicio { get; set; }
        public DateTime HoraTermino { get; set; }
        public string Descricao { get; set; }
        public string TipoUsuario { get; set; }
        public string Telefone { get; set; }
        public int IdAutenticacao { get; set; }
        public Endereco Endereco { get; set; }
    }
}
