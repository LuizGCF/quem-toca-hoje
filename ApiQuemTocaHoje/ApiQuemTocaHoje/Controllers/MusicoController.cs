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
    [Route("api/musico")]
    [ApiController]
    public class MusicoController : Controller
    {
        private readonly ContextoBanco Contexto;

        private RepositoryBase<Musico> repositorioespectador;

        public RepositoryBase<Musico> RespositorioEspectador
        {
            get
            {
                if (repositorioespectador == null)
                {
                    repositorioespectador = new RepositoryBase<Musico>(Contexto);
                }

                return repositorioespectador;
            }
            set { repositorioespectador = value; }
        }


        public MusicoController(ContextoBanco contexto)
        {
            Contexto = contexto;
        }

        // GET api/autenticacao
        [HttpGet]
        public ActionResult<IEnumerable<Musico>> Get()
        {
            var item = RespositorioEspectador.DbSet.ToList();
            if (item == null)
                return NotFound();

            return item;
        }

        // GET api/autenticacao/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Musico>> GetAsync(int id)
        {
            var item = await RespositorioEspectador.DbSet.FindAsync(id);
            if (item == null)
                return NotFound();

            return item;
            //return "value";
        }

        // GET api/autenticacao/5
        [HttpGet("nome")]
        public async Task<ActionResult<List<Musico>>> GetPorNomeAsync([FromQuery] string nome)
        {
            var item = await RespositorioEspectador.DbSet.Where(x => x.NomeMusico.StartsWith(nome)).ToListAsync();
            if (item == null || item.Count == 0)
                return NotFound();

            return item;
            //return "value";
        }

        // GET api/autenticacao/5
        [HttpGet("aut")]
        public async Task<ActionResult<Musico>> GetPorIdAutenticacaoAsync([FromQuery] int id)
        {
            var item = await RespositorioEspectador.DbSet.Where(x => x.IdAutenticacao.Equals(id)).FirstOrDefaultAsync();
            if (item == null )
                return NotFound();

            return item;
            //return "value";
        }

        // POST api/autenticacao
        //no post preciso colocar postman que o content-type é do tipo application/json
        [HttpPost]
        public async Task<ActionResult<Musico>> PostAsync([FromBody] MusicoViewModel values)
        {
            try
            {
                Autenticacao autenticacao = await Contexto.Autenticacao.Where(x => x.IdAutenticacao == values.IdAutenticacao).FirstOrDefaultAsync();

                if (autenticacao != null)
                {
                    Musico item = new Musico()
                    {
                        Autenticacao = autenticacao,
                        DataCriacao = DateTime.Now,
                        Endereco = values.Endereco,
                        IdAutenticacao = autenticacao.IdAutenticacao,
                        NomeArtMusico = values.NomeArtistico,
                        NomeMusico = values.Nome,
                        TelMusico = values.Telefone,
                        IdEndereco = values.Endereco.IdEndereco,                       
                        Convite = null,//values.Convite!=null ? values.Convite : null,
                        Arquivo = null//provavelmente nem existira depois, ou só sera uma string com o caminho do arquivo
                    };
                    RespositorioEspectador.DbSet.Add(item);
                    await RespositorioEspectador.Contexto.SaveChangesAsync();

                    return Ok(item);
                }
                else
                    return BadRequest();
            }
            catch(Exception e)
            {
                throw new Exception(e.Message);
            }
        }

        // PUT api/autenticacao/5
        [HttpPut("{id}")]
        public async Task<IActionResult> Put(int id, [FromBody] Musico item)
        {
            if (id != item.IdMusico)
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
