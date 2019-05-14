using System;
using System.Collections.Generic;

namespace ApiQuemTocaHoje.Models
{
    public class Banda
    {
        public Banda()
        {
            //this.Arquivo = new HashSet<Arquivo>();
            //this.Convite = new HashSet<Convite>();
            //this.Evento = new HashSet<Evento>();
            this.Musicos = new HashSet<Musico>();
        }

        public int IdBanda { get; set; }
        //public int NumIntegranteBanda { get; set; }
        public string Nome { get; set; }
        public string GeneroBanda { get; set; }
        public DateTime DataCriacao { get; set; }


        public Arquivo Arquivo { get; set; }

        public Convite Convite { get; set; }

        public Evento Evento { get; set; }

        public ICollection<Musico> Musicos { get; set; }



        //public int IdBanda { get; set; }
        //public string Nome { get; set; }
        //public string Descricao { get; set; }
        //public List<string> Generos { get; set; }
        //public DateTime DataCriacao { get; set; }
        //public int IdArquivo { get; set; }
        //public Arquivo Arquivo { get; set; }

        //public virtual ICollection<Musico> Musicos { get; set; }

    }
}