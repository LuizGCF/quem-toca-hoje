using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ApiQuemTocaHoje.Models
{
    public class Musico
    {
        public Musico()
        {
            //this.Convite = new HashSet<Convite>();
            this.Banda = new HashSet<Banda>();
        }

        public int IdMusico { get; set; }
        public string NomeMusico { get; set; }
        public string NomeArtMusico { get; set; }
        public int idConvite { get; set; }
       
        public DateTime DataCriacao { get; set; }
        public string TelMusico { get; set; }
        public int IdAutenticacao { get; set; }
        public int IdArquivo { get; set; }
        public int IdEndereco { get; set; }

        public virtual Arquivo Arquivo { get; set; }
        public virtual Autenticacao Autenticacao { get; set; }

        public virtual Convite Convite { get; set; }
        public virtual Endereco Endereco { get; set; }

        public virtual ICollection<Banda> Banda { get; set; }
    }
}
