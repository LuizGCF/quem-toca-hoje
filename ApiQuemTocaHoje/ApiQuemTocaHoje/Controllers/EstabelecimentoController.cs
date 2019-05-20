using ApiQuemTocaHoje.Banco;
using ApiQuemTocaHoje.Models;
using ApiQuemTocaHoje.Repositorio;
using ApiQuemTocaHoje.ViewModels;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Threading.Tasks;

namespace ApiQuemTocaHoje.Controllers
{
    [Route("api/estabelecimento")]
    [ApiController]
    public class EstabelecimentoController : Controller
    {
        private readonly ContextoBanco Contexto;

        private RepositoryBase<Estabelecimento> repositorioespectador;

        public RepositoryBase<Estabelecimento> RespositorioEspectador
        {
            get
            {
                if (repositorioespectador == null)
                {
                    repositorioespectador = new RepositoryBase<Estabelecimento>(Contexto);
                }

                return repositorioespectador;
            }
            set { repositorioespectador = value; }
        }


        public EstabelecimentoController(ContextoBanco contexto)
        {
            Contexto = contexto;
        }

        // GET api/autenticacao
        [HttpGet]
        public ActionResult<IEnumerable<Estabelecimento>> Get()
        {
            var item = RespositorioEspectador.DbSet.ToList();
            if (item == null)
                return NotFound();

            return item;
        }

        // GET api/autenticacao/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Estabelecimento>> GetAsync(int id)
        {
            var item = await RespositorioEspectador.DbSet.FindAsync(id);
            if (item == null)
                return NotFound();

            return item;
            //return "value";
        }

        [HttpGet("nome")]
        public async Task<ActionResult<List<Estabelecimento>>> GetPeloNomeAsync([FromQuery] string nome)
        {
            var item = await RespositorioEspectador.DbSet.Where(x => x.NomeFantasiaEstabelecimento.StartsWith(nome)).ToListAsync();
            if (item == null || item.Count == 0)
                return NotFound();

            return item;
            //return "value";
        }

        // POST api/autenticacao
        //no post preciso colocar postman que o content-type é do tipo application/json
        [HttpPost]
        public async Task<ActionResult<Estabelecimento>> PostAsync([FromBody] EstabelecimentoViewModel values)
        {
            Autenticacao autenticacao = await Contexto.Autenticacao.Where(x => x.IdAutenticacao == values.IdAutenticacao).FirstOrDefaultAsync();
            if (autenticacao != null)
            {
                Estabelecimento item = new Estabelecimento()
                {
                    Autenticacao = autenticacao,
                    CnpjEstabelecimento = values.CNPJ,
                    DescricaoAmbienteEstabelecimento = values.Descricao,
                    Endereco = values.Endereco,
                    HoraInicioEstabelecimento = values.HoraInicio,
                    HoraTerminoEstabelecimento = values.HoraTermino,
                    IdAutenticacao = autenticacao.IdAutenticacao,
                    IdEndereco = values.Endereco.IdEndereco,
                    NomeDono = values.NomeDono,
                    NomeFantasiaEstabelecimento = values.NomeFantasia,
                    RazaoSocialEstabelecimento = values.RazaoSocial,
                    TelEstabelecimento = values.Telefone,
                    TipoUsuario = values.TipoUsuario
                };
                RespositorioEspectador.DbSet.Add(item);
                await RespositorioEspectador.Contexto.SaveChangesAsync();

                return Ok(item);//CreatedAtAction(nameof(Endereco), new { id = item.IdEstabelecimento }, item);
            }
            else
            {
                return BadRequest();
            }
        }

        // PUT api/autenticacao/5
        [HttpPut("{id}")]
        public async Task<IActionResult> Put(int id, [FromBody] Estabelecimento item)
        {
            if (id != item.IdEstabelecimento)
            {
                return BadRequest();
            }

            Contexto.Entry(item).State = EntityState.Modified;
            await Contexto.SaveChangesAsync();

            return NoContent();
        }

        // DELETE api/autenticacao/5
        [HttpDelete("{id}")]
        public async Task<ActionResult> DeleteAsync(int id)
        {
            var todoItem = await RespositorioEspectador.DbSet.FindAsync(id);

            if (todoItem == null)
            {
                return NotFound();
            }

            RespositorioEspectador.DbSet.Remove(todoItem);
            await RespositorioEspectador.Contexto.SaveChangesAsync();

            return NoContent();
        }
    }
}
