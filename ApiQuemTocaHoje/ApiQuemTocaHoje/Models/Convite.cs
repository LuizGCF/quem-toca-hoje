using System;

namespace ApiQuemTocaHoje.Models
{
    public class Convite
    {
        public int IdConvite { get; set; }
        public string EmailConvidadoConvite { get; set; }
        public string StatusConvidadoConvite { get; set; }
        public string TokenConvite { get; set; }
        public DateTime DataCriacao { get; set; }
        public int idMusico { get; set; }
        //public int idBanda { get; set; }
        //public int idEstabelecimento { get; set; }

        //public Banda Banda { get; set; }
        //public Estabelecimento Estabelecimento { get; set; }
        //public Musico Musico { get; set; }
    }
}