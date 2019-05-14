using System;
using System.Collections.Generic;

namespace ApiQuemTocaHoje.Models
{
    public class Arquivo
    {
        public int IdArquivo { get; set; }
        public byte[] AnexoArquivo { get; set; }
        public string TipoArquivo { get; set; }
        public DateTime DataCriacao { get; set; }
        public int idBanda { get; set; }
        public int idEstabelecimento { get; set; }
        public int idEspectador { get; set; }

        //public Banda Banda { get; set; }
        //public Espectador Espectador { get; set; }
        //public Estabelecimento Estabelecimento { get; set; }
        //public Musico Musico { get; set; }
    }
}