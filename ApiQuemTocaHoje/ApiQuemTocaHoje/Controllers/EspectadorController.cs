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

    [Route("api/espectador")]
    [ApiController]
    public class EspectadorController : Controller
    {
        private readonly ContextoBanco Contexto;

        private RepositoryBase<Espectador> repositorioespectador;

        public RepositoryBase<Espectador> RespositorioEspectador
        {
            get
            {
                if (repositorioespectador == null)
                {
                    repositorioespectador = new RepositoryBase<Espectador>(Contexto);
                }

                return repositorioespectador;
            }
            set { repositorioespectador = value; }
        }


        public EspectadorController(ContextoBanco contexto)
        {
            Contexto = contexto;
        }

        // GET api/autenticacao
        [HttpGet]
        public ActionResult<IEnumerable<Espectador>> Get()
        {
            var item = RespositorioEspectador.DbSet.ToList();
            if (item == null)
                return NotFound();

            return item;
        }

        // GET api/autenticacao/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Espectador>> GetAsync(int id)
        {
            var item = await RespositorioEspectador.DbSet.FindAsync(id);
            if (item == null)
                return NotFound();

            return item;
            //return "value";
        }

        // GET api/autenticacao/5
        [HttpGet("aut")]
        public async Task<ActionResult<Espectador>> GetPorIdAutenticacaoAsync([FromQuery] int id)
        {
            var item = await RespositorioEspectador.DbSet.Where(x=>x.IdAutenticacao.Equals(id)).FirstOrDefaultAsync();
            if (item == null)
                return NotFound();

            return item;
            //return "value";
        }

        // GET api/autenticacao/5
        [HttpGet("nome")]
        public async Task<ActionResult<List<Espectador>>> GetPorNomeAsync([FromQuery]string nome)
        {
            var item = await RespositorioEspectador.DbSet.Where(x => x.NomeEspectador.StartsWith(nome)).ToListAsync();
            if (item == null || item.Count == 0)
                return NotFound();

            return item;
            //return "value";
        }

        // POST api/autenticacao
        //no post preciso colocar postman que o content-type é do tipo application/json
        [HttpPost]
        public async Task<ActionResult<Espectador>> PostAsync([FromBody] EspectadorViewModel values)
        {
            Autenticacao autenticacao = await Contexto.Autenticacao.Where(x => x.IdAutenticacao == values.IdAutenticacao).FirstOrDefaultAsync();

            if (autenticacao != null)
            {
                Espectador item = new Espectador()
                {
                    Autenticacao = autenticacao,
                    IdAutenticacao = autenticacao.IdAutenticacao,
                    NomeEspectador = values.Nome,
                };

                RespositorioEspectador.DbSet.Add(item);
                await RespositorioEspectador.Contexto.SaveChangesAsync();

                return Ok(item);
            }
            else
            {
                return BadRequest();
            }
        }

        // PUT api/autenticacao/5
        [HttpPut("{id}")]
        public async Task<IActionResult> Put(int id, [FromBody] Espectador item)
        {
            if (id != item.IdEspectador)
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
