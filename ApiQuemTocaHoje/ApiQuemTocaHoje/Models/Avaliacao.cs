using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ApiQuemTocaHoje.Models
{
    public class Avaliacao
    {
        public int IdAvaliacao { get; set; }
        public int NotaBanda { get; set; }
        public int NotaEstabelecimento { get; set; }
        public string ComentarioBanda { get; set; }
        public string ComentarioEstabelecimento { get; set; }
        public DateTime DataBanda { get; set; }
        public DateTime DataEstabelecimento { get; set; }
        //public int NotaAvaliacao { get; set; }
        //public string DescricaoAvaliacao { get; set; }
        //public string TipoUsuarioAvaliacao { get; set; }
        //public DateTime DataAvaliacao { get; set; }
        public int idEvento { get; set; }

        public Evento Evento { get; set; }
    }
}
