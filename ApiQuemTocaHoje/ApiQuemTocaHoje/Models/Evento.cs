using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ApiQuemTocaHoje.Models
{
    public class Evento
    {
        public int IdEvento { get; set; }
        public DateTime DataHoraInicio { get; set; }
        public DateTime DataHoraFim { get; set; }
        public string StatusEvento { get; set; }
        public string NotaEstab { get; set; }
        public string NotaMusico { get; set; }
        public string ComentarioEstab { get; set; }
        public string ComentarioMusico { get; set; }
        public int IdBanda { get; set; }


        //public Avaliacao Avaliacao { get; set; }
        //public Banda Banda { get; set; }

        //public Proposta Proposta { get; set; }
    }
}
