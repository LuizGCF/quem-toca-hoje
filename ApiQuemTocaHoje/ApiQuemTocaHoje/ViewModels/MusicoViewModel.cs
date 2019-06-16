using ApiQuemTocaHoje.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ApiQuemTocaHoje.ViewModels
{
    public class MusicoViewModel//Verificar relacionado ao convite/arquivo serem "obrigatorios" aparentemente
    {
        public string Nome { get; set; }
        public string NomeArtistico { get; set; }
        //public int IdConvite { get; set; }
        public string Telefone { get; set; }
        public int IdAutenticacao { get; set; }
        public Endereco Endereco { get; set; }
    }
}
